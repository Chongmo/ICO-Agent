package MesAgents;


import Comportements.Behaviour_HillClimbing;
import jade.core.Agent;

public class Agent_HillClimbing extends Agent{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void setup() {
		Object[] args = getArguments();
		
		Behaviour_HillClimbing C1 = new Behaviour_HillClimbing(args); // Création d'un comportment
		addBehaviour(C1); // Ajout du comportement 
		
		// doDelete(); //Detruit l'agent après execution
	
	}
	
}
