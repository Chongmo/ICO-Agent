package Comportements;
import java.io.IOException;
import java.io.Serializable;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

public class Behaviour_Tabu extends	Behaviour{

	public int MAX_GEN=100;// nombre d'iteration
	public int N=5;// nombre de voisin de recherche chaque fois
	public int ll=1;// longuer de la liste de Tabou
	public int cityNum; // nombre de ville
 
	private Route Ghh;// solution de debut
	private Route bestGh;// meilleur solution
	private double bestEvaluation;
	private Route LocalGhh;// meilleur solution maintenant
	private double localEvaluation;
	private double tempEvaluation;
	private double received_dist;
	private double current_dist;
	private Resultat Result;
	private int compt=0;
	private boolean bool = false;
	
  
	private String [][] liste;// liste de la Tabou
 
	private int t;
	
	public Behaviour_Tabu (Object[] args) {

		Ghh = (Route) args[0] ;//Re√ßoit la route d'√©tudier
		cityNum = Ghh.getCities().size();

	}
	
	
	
	public void onStart() {
		cityNum = 6;
		MAX_GEN = 1000;
		N = 5;
		ll = 1;
		//INIT !
		
			t=0;

			bestEvaluation = Integer.MAX_VALUE;
			localEvaluation = Integer.MAX_VALUE;
			tempEvaluation = Integer.MAX_VALUE;
	 
			liste = new String[ll][cityNum];

		System.out.println("DÈbut du comportement Tabou...");	
		
	   
	}
	
	public int judge(Route tempGh) {
		int i, j;
		int flag = 0;
		for (i = 0; i < ll; i++) {
			flag = 0;
			for (j = 0; j < cityNum; j++) {
				if (tempGh.getCities().get(i).toString() != liste[i][j]) {
					flag = 1;// non
					break;
				}
			}
			if (flag == 0)// oui
			{
				// return 1;
				break;
			}
		}
		if (i == ll)
		{
			return 0;// non
		} else {
			return 1;// si
		}
	}
	
	public void UpdateListe(Route localGhh2) {
		int i, j, k;
		// enlever le dernier element dans la liste de Tabou et deplacer les restes
		for (i = 0; i < ll - 1; i++) {
			for (j = 0; j < cityNum; j++) {
				liste[i][j] = liste[i + 1][j];
			}
		}
 
		// ajouter a la liste de Tabou
		for (k = 0; k < cityNum; k++) {
			liste[ll - 1][k] = localGhh2.getCities().get(k).toString();
		}
 
	}

	// Message
	
	@SuppressWarnings("deprecation")
	public ACLMessage reponse = new ACLMessage();
	public String destinataire = "AGENT_Collector";
	
	public void action() {
		ACLMessage message = myAgent.receive();
		if (message != null) {
			System.out.println("Agent Tabou : J'ai reÁu un message de  "
					+ message.getSender().getName() /* + " Message: " + message.getContent() */);
			received_dist = Double.parseDouble(message.getContent());
			System.out.println(received_dist);
			current_dist = received_dist;
			
			do {
			double start = System.currentTimeMillis();
			
			int nn;
			bestGh=new Route(Ghh.getCities());// copier Ghh a la meilleur solution bestGh
			//copy(Ghh.getCities(),bestGh);
			bestEvaluation = Ghh.getTotalDistance();
	 
			while (t < MAX_GEN) {
					nn = 0;
					localEvaluation = Integer.MAX_VALUE;
					while (nn < N) {
						Route tempGhh = Ghh.getAdjacentRoute();// obtenir le voisin de Ghh, tempGhh
						if (judge(tempGhh) == 0)// si Ghh est dans la liste de Tabou
						{
							// non
							tempEvaluation = tempGhh.getTotalDistance();
							if (tempEvaluation < localEvaluation) {
								LocalGhh=new Route(tempGhh.getCities());
								localEvaluation = tempEvaluation;
							}
							nn++;
						}
					}
					if (localEvaluation < bestEvaluation) {
						bestGh=new Route (LocalGhh.getCities());
						bestEvaluation = localEvaluation;
					}
					Ghh=new Route(LocalGhh.getCities());
					UpdateListe(LocalGhh);
					t++;
			}
			Route R = bestGh;
			double s=R.getTotalDistance();
			double end = System.currentTimeMillis()-start;
			if (s < current_dist) {
				Result = new Resultat(R,s,end);
				current_dist = s;
			}
			MAX_GEN = MAX_GEN +10;
			compt = compt+1;
			}
		while (Result.dist>=received_dist && compt<20);{
		
		// Envoie des rÈsultats ‡ l'agent collecteur
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
				MAX_GEN=10;
				int nn;
				bestGh=new Route(Ghh.getCities());// copier Ghh a la meilleur solution bestGh
				//copy(Ghh.getCities(),bestGh);
				bestEvaluation = Ghh.getTotalDistance();
		 
				while (t < MAX_GEN) {
						nn = 0;
						localEvaluation = Integer.MAX_VALUE;
						while (nn < N) {
							Route tempGhh = Ghh.getAdjacentRoute();// obtenir le voisin de Ghh, tempGhh
							if (judge(tempGhh) == 0)// si Ghh est dans la liste de Tabou
							{
								// non
								tempEvaluation = tempGhh.getTotalDistance();
								if (tempEvaluation < localEvaluation) {
									LocalGhh=new Route(tempGhh.getCities());
									localEvaluation = tempEvaluation;
								}
								nn++;
							}
						}
						if (localEvaluation < bestEvaluation) {
							bestGh=new Route (LocalGhh.getCities());
							bestEvaluation = localEvaluation;
						}
						Ghh=new Route(LocalGhh.getCities());
						UpdateListe(LocalGhh);
						t++;
				}
				compt=compt+1;
				Route R = bestGh;
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
	
}
