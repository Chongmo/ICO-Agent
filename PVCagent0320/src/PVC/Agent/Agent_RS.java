package PVC.Agent;

import PVC.Agent.behaviour_RS;
import jade.core.Agent;

public class Agent_RS extends Agent{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void setup() {
		Object[] args = getArguments();
		
		behaviour_RS C1 = new behaviour_RS(args); // Création d'un comportment
		addBehaviour(C1);
	}

}
