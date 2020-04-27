package MesAgents;

import Comportements.Behaviour_Genetic;
import Comportements.Behaviour_HillClimbing;
import Comportements.Behaviour_RS;
import Comportements.Behaviour_Tabu;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.SequentialBehaviour;
import jade.core.behaviours.WakerBehaviour;


public class Seq extends Agent {	
		protected void setup(){
			
			Object[] args = getArguments();
			
			Behaviour_HillClimbing Behaviour_HC = new Behaviour_HillClimbing(args);
			Behaviour_RS Behaviour_RS = new Behaviour_RS(args);
			Behaviour_Genetic Behaviour_G = new Behaviour_Genetic(args);
			Behaviour_Tabu Behaviour_T = new Behaviour_Tabu(args);
				
			SequentialBehaviour comportementSequentiel = new SequentialBehaviour();
			
			for (int i=0;i<1;i++) {
				comportementSequentiel.addSubBehaviour(Behaviour_HC);
				comportementSequentiel.addSubBehaviour(Behaviour_G);
				comportementSequentiel.addSubBehaviour(Behaviour_RS);
				comportementSequentiel.addSubBehaviour(Behaviour_T);
			}	
			
			addBehaviour(comportementSequentiel);
			
	
}
}
