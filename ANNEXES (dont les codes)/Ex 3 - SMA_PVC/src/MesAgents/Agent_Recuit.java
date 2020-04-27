package MesAgents;

import Comportements.Behaviour_RS;
import jade.core.Agent;

public class Agent_Recuit extends Agent{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void setup() {
		
		Object[] args = getArguments();
		
		Behaviour_RS C1 = new Behaviour_RS(args); // Création d'un comportment
		addBehaviour(C1); // Ajout du comportement 
		
		// doDelete(); //Detruit l'agent après execution
	
	}
	
}
