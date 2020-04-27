package Comportements;


import java.io.IOException;
import java.io.Serializable;
import java.util.Random;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

public class Behaviour_RS extends Behaviour  {
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		// INIT
		public Route sol; 
		public Route sol_et;
		public boolean nouveau_cycle= true;
		public double t;
		public double t0 = 5;
		public int nb_iter_cycle=100;
		public double a=0.4; 		
		private double received_dist;
		private double current_dist;
		private Resultat Result;
		private int compt=0;
		private boolean bool = false;
					
		public Behaviour_RS(Object[] args) {
					
					 sol = (Route)args[0] ;//Reçoit la route à étudier

					}

		public void onStart() {
						
						System.out.println("Début du comportement RS...");	
						
					   
					}
				
					// Message
					
					@SuppressWarnings("deprecation")
					public ACLMessage reponse = new ACLMessage();
					public String destinataire = "AGENT_Collector";
					
					public void action() {
						ACLMessage message = myAgent.receive();
						if (message != null) {
							System.out.println("Agent RS : J'ai reçu un message de  "
									+ message.getSender().getName() /* + " Message: " + message.getContent() */);
							received_dist = Double.parseDouble(message.getContent());
							System.out.println(received_dist);
							current_dist = received_dist;
							
						
							do {
							double start = System.currentTimeMillis();
							Route R=RS.Recuit_Simule(nb_iter_cycle, t0, a, sol);
							double s=R.getTotalDistance();
							double end = System.currentTimeMillis()-start;
							if (s < current_dist) {
								Result = new Resultat(R,s,end);
								current_dist = s;
							}
							t0=t0+0.5;
							compt = compt+1;
							}
						while (Result.dist>=received_dist && compt<20);{
						
						
						// Envoi les résultats à l'agent collecteur
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
								Route R=RS.Recuit_Simule(nb_iter_cycle, t0, a, sol);
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
							}
						
				public int onEnd() {
						return 1;
					}

				public boolean done() {
					return bool;
				}
					


			
	}

