package Comportements;

import java.io.IOException;
import java.io.Serializable;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

public class Behaviour_HillClimbing  extends Behaviour  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int ITERATIONS_BEFORE_MAXIMA = 1000;
	public Route currentRoute; 	
	Resultat Result;
	double received_dist;
	double current_dist;
	int compt=0;
	boolean bool=false;
	
	@SuppressWarnings("deprecation")
	public ACLMessage reponse = new ACLMessage();
	public int iterToMaximaCounter=0;
	Route adjacentRoute;
	String compareRoutes =null;
	public String destinataire = "AGENT_Collector";
	
	
	public Behaviour_HillClimbing(Object[] args) {

		currentRoute = (Route)args[0] ;//Reçoit la route à étudier

	}

			public void onStart() {
							
							System.out.println("Début du comportement HC...");	
							
						   
						}
					
						// Message
						
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
								

								adjacentRoute=obtainAdjacentRoute(new Route(currentRoute));
								/*tant qu'on descend on recommence le comptage
								* puisqu'on est sur le bon chemin*/

								if(adjacentRoute.getTotalDistance()<=currentRoute.getTotalDistance()){
									compareRoutes="<= (proceed)";
									iterToMaximaCounter=0;
									currentRoute=new Route(adjacentRoute);
								} else compareRoutes="> (stay) - iteration n°"+ iterToMaximaCounter++;
				
								Route R=currentRoute;
								double s=R.getTotalDistance();
								double end = System.currentTimeMillis()-start;
								if (s < current_dist) {
									Result = new Resultat(R,s,end);
									current_dist = s;
								}
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
									adjacentRoute=obtainAdjacentRoute(new Route(currentRoute));
									/*tant qu'on descend on recommence le comptage
									* puisqu'on est sur le bon chemin*/

									if(adjacentRoute.getTotalDistance()<=currentRoute.getTotalDistance()){
										compareRoutes="<= (proceed)";
										iterToMaximaCounter=0;
										currentRoute=new Route(adjacentRoute);
									} else compareRoutes="> (stay) - iteration n°"+ iterToMaximaCounter++;
					
									Route R=currentRoute;
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
									myAgent.doWait(120000);
									}
								}
							
					public int onEnd() {
							return 1;
						}

					public boolean done() {
						return bool;
					}
	
	
	
	
	
	
	
	

private Route obtainAdjacentRoute(Route route){
	int x1=0, x2=0;
	//selection random de 2 villes distinctes
	while (x1==x2){
		x1=(int) (route.getCities().size()*Math.random());
		x2=(int) (route.getCities().size()*Math.random());
	}
	//permutaion pour obtenir la route adjacente
	City city1=route.getCities().get(x1);
	City city2=route.getCities().get(x2);	
	route.getCities().set(x2, city1);
	route.getCities().set(x1, city2);
	return route;
}




	
}
