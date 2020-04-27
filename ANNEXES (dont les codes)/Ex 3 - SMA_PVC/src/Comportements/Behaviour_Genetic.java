package Comportements;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;


public class Behaviour_Genetic extends Behaviour  {
	
			// INIT
			private Route route;
			final Route r;
			private double received_dist;
			private double current_dist;
			private int nb_cycle;
			private Resultat Result;
			private int compt=0;
			private boolean bool = false;
			
			public Behaviour_Genetic(Object[] args) {
			
			 r = (Route)args[0] ;//Reçoit la route à étudier

			}

			public void onStart() {
				
				System.out.println("Début du comportement Genetique...");	
				System.out.println(r);
			   
			}
		
			// Message
			
			@SuppressWarnings("deprecation")
			public ACLMessage reponse = new ACLMessage();
			public String destinataire = "AGENT_Collector";
			
			public void action() {
				ACLMessage message = myAgent.receive();
				if (message != null) {
					System.out.println("Agent AG : J'ai reçu un message de  "
							+ message.getSender().getName() /* + " Message: " + message.getContent() */);
					received_dist = Double.parseDouble(message.getContent());
					System.out.println(received_dist);
					current_dist = received_dist;
					
					nb_cycle=10;
				
					do {
					double start = System.currentTimeMillis();
					Route R=Population.Resolution3(nb_cycle,r);
					double s=R.getTotalDistance();
					double end = System.currentTimeMillis()-start;
					if (s < current_dist) {
						Result = new Resultat(R,s,end);
						current_dist = s;
					}
					nb_cycle = nb_cycle +10;
					compt = compt+1;
					}
				while (Result.dist>=received_dist && compt<20);{
				
				// Envoie des résultats à l'agent collecteur
				@SuppressWarnings("deprecation")
				ACLMessage resultsMessage = new ACLMessage();
				String destinataire = "Agent_Collector";
				resultsMessage.addReceiver(new AID(destinataire + "@maPlateforme",AID.ISGUID));
				try {
					resultsMessage.setContentObject(Result);
				} catch (IOException e) {
					e.printStackTrace();
				}
				myAgent.send(resultsMessage);
	
				}
				bool = (message == null);
				}
			    else {
						double start = System.currentTimeMillis();
						nb_cycle=10;
						Route R=Population.Resolution1(nb_cycle,r);
						double end = System.currentTimeMillis()-start;
						Result = new Resultat(R,R.getTotalDistance(),end);
						compt = compt+1;
						ACLMessage resultsMessage = new ACLMessage();
						String destinataire = "Agent_Collector";
						resultsMessage.addReceiver(new AID(destinataire + "@maPlateforme",AID.ISGUID));
						try {
							resultsMessage.setContentObject(Result);
						} catch (IOException e) {
							e.printStackTrace();
						}
						myAgent.send(resultsMessage);
						}
						myAgent.doWait(60000);
					}
				
			public int onEnd() {
				return 1;
			}

			public boolean done() {
				return bool;
			}
			
				
		}



