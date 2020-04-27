package Comportements;

import java.util.ArrayList;
import java.util.Collections;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class CyclicBehaviour_Comm extends CyclicBehaviour {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Double> d;


	public void onStart() {
		
		System.out.println("Début du comportement...");

	}
	
	
	public void action() {
		
		//L'agent est à l'écoute de son environnement
		ACLMessage msg = myAgent.receive();
		d.add(Double.parseDouble(msg.getContent()));
		
		if(msg!=null) {
			
			System.out.println("Message de " + msg.getSender().getName().replaceAll("@maPlateforme","")+ " "+ msg.getContent());
		}
		else block();
		
		int minIndex = d.indexOf(Collections.min(d));
				
		ACLMessage reponse = msg.createReply();
		reponse.setPerformative(ACLMessage.INFORM);
		reponse.setContent(String.valueOf(d.get(minIndex)));
		myAgent.send(reponse);
		
		
	}	
	
}
