package MesAgents;

import Comportements.Behaviour_RS;
import Comportements.Behaviour_Tabu;
import jade.core.Agent;

public class Agent_Tabou extends Agent{

	
	protected void setup() {
		Object[] args = getArguments();
		
		Behaviour_Tabu C1 = new Behaviour_Tabu(args); // Cr�ation d'un comportment
		addBehaviour(C1); // Ajout du comportement 
		
		// doDelete(); //Detruit l'agent apr�s execution
	
	}
	
}
