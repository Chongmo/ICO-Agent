package MesAgents;


import Comportements.Behaviour_Collector;
import jade.core.Agent;

public class Agent_Collector extends Agent{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void setup() {
		Object[] args = getArguments();
		
		Behaviour_Collector C1 = new Behaviour_Collector(args); // Création d'un comportment
		addBehaviour(C1); // Ajout du comportement 
		
		// doDelete(); //Detruit l'agent après execution
	
	}
	
}
