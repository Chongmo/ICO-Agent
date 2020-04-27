package Comportements;
import Comportements.Resultat;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

public class Behaviour_Collector extends CyclicBehaviour { /**
	 * 
	 */
	Route current_Route;
	public Behaviour_Collector(Object[] args) {

		current_Route = (Route) args[0];//Reçoit la route à étudier

	}
	
	private static final long serialVersionUID = 1L;
	
	    private int cpt = 0;
	    private int nbAgents = 4;
		private Map<String, Resultat> Resultat;
		private double minDist = 100000000;
		double time = 0;
		private Resultat previous_min = new Resultat(current_Route,minDist,time);
		boolean better = false;
		private HashMap<String,Resultat> resultat;
		@Override
		public void onStart() {
			super.onStart();
			resultat = new HashMap<>();
		}

		@Override
		public void action() {

			// L'agent collecteur, recoit les messages de tous nos agents
			ACLMessage message = myAgent.receive();
			if (message != null) {
				System.out.println(
						"Agent collecteur : J'ai reçu un message de  " + message.getSender().getName() /* + " Message: " + message.getContent() */);
				try {
					resultat.put(message.getSender().getName(), (Resultat) message.getContentObject());
				} catch (UnreadableException e) {
					e.printStackTrace();
				}
			}

			// On attend que tout nos agents ont envoyé leur message

			if (resultat.size()==nbAgents) {
				for (String s : resultat.keySet()) {
					if (resultat.get(s).dist < previous_min.dist) {
						previous_min = new Resultat(resultat.get(s).R, resultat.get(s).dist, resultat.get(s).time);
						better = true;
					}
					
				}

			if (better) {
					String m = previous_min.toStringR();
					System.out.println("On a trouvé mieux !");
					System.out.println("Le nouveau résultat trouvé est ");
					System.out.println(m);
					System.out.println("On essaye de trouver encore mieux...");

					@SuppressWarnings("deprecation")
					ACLMessage new_min = new ACLMessage();
					//String destinataire1 = "Agent_Genetique";
					//String destinataire2 = "Agent_Recuit";
					for (String s : resultat.keySet()) {
						new_min.addReceiver(new AID(s,AID.ISGUID));
					}
					new_min.setContent(String.valueOf(previous_min.dist));
					
					// On réinitialise notre dictionnaire
					resultat.clear();
					
					better = false;
					myAgent.send(new_min);
					
					
				}
			 else {
					String m = previous_min.toStringR();
					System.out.println("Désolé, nous n'arrivons pas à trouver mieux..");
					System.out.println("Voici notre résultat optimale : " + m);
					myAgent.doSuspend();;
				}
				
				}


		}

		@Override
		public int onEnd() {
			// TODO Auto-generated method stub
			return super.onEnd();
		}
}
