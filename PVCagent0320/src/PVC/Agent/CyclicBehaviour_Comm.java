package PVC.Agent;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class CyclicBehaviour_Comm extends CyclicBehaviour {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public void onStart() {
		
		System.out.println("Debut du comportement...");

	}
	
	
	public void action() { //Action effectu�e � chaque tour de boucle
		
		//L'agent est � l'�coute de son environnement
		ACLMessage msg = myAgent.receive();
		
		if(msg!=null) {
			
			System.out.println("Message de " + msg.getSender().getName().replaceAll("@maPlateforme","")+ " "+ msg.getContent());
			ACLMessage reponse = msg.createReply();
			reponse.setPerformative(ACLMessage.INFORM);
			reponse.setContent("OK");
			myAgent.send(reponse);
		}
		else block();
		
	}	
	
}
