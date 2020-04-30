package PVC.Agent;

import java.io.IOException;
import java.io.Serializable;

import PVC.Algorithmes.Recuit;
import PVC.Definitions.Route;
import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

public class behaviour_RS extends Behaviour {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Route sol;
	public Route sol_et;
	public boolean nouveau_cycle= true;
	public float t;
	public float t0 = 190;
	public int nb_iter_cycle;
	public float a;

	// Message
		@SuppressWarnings("deprecation")
		public ACLMessage reponse = new ACLMessage();
		public String destinataire = "Agent_Collector";
		

	public behaviour_RS(Object[] args) {
		// TODO Auto-generated constructor stub
		sol = (Route) args[0];
	}
	public void onStart() {
		System.out.println("Debut du comportement..");
		t = t0;
		nb_iter_cycle = 100;
		a = 0.4f;
		sol_et = sol;
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub
		Recuit recuit = new Recuit(sol,nb_iter_cycle,t,a);
		recuit.runtest();
		sol_et = recuit.getBestRoute();
		
	}

	@Override
	public boolean done() {
		// TODO Auto-generated method stub
		return (!nouveau_cycle);
	}
	public int onEnd() {
		
		//System.out.println(" Agent_RS :" + sol_et.toString());	
		
		emettre(sol_et);
		
		System.out.println("Fin du comportement... Agent_RS");
		return 1;
	}
	public Route recevoir() {
		ACLMessage msg = myAgent.receive();
		Serializable r = null;
		if(msg!=null) {
			 try {
				r = msg.getContentObject();
			//	reponse = msg.createReply();
				reponse.setPerformative(ACLMessage.INFORM);			
				
			} catch (UnreadableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		else block();
		
		return (Route) r;
	}

	public void emettre(Route re) {
		try {
			reponse.addReceiver(new AID(destinataire + "@maPlateforme",AID.ISGUID));
			reponse.setContentObject(re.toString() + re.getTotalDistance());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		myAgent.send(reponse);

		}


}


