package MesAgents;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import Comportements.Behaviour_Genetic;
import Comportements.City;
import Comportements.Route;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.CyclicBehaviour;

public class Agent_Genetique extends Agent{
	
	

	protected void setup() {
		
		Object[] args = getArguments();
		
		Behaviour_Genetic MyBehaviour = new Behaviour_Genetic(args);
				addBehaviour(MyBehaviour);
			}
		
		// doDelete(); //Detruit l'agent après execution
		}
	

