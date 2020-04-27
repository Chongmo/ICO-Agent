import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

public class MainOptimisation {

	public static void main(String[] args) {
		Runtime rt = Runtime.instance();  //Création d'une instance de JADE unique par ORDI
		ProfileImpl pMain = new ProfileImpl(null, 2222,"maPlateforme"); //Création du Profil (Port + Nom) du conteneur Principal
		AgentContainer mc =rt.createMainContainer(pMain); //Création du conteneur Principale
		
		// OPTIONNEL //
		
		// Lancement de l'interface Graphique de JADE  (RMA) //
		AgentController rma, hello, timer;
		try {
			rma = mc.createNewAgent("rma","jade.tools.rma.rma", null); // Créer l'agent RMA
			rma.start(); //Lancer l'agent RMA	
						
		//	hello = mc.createNewAgent("toto","MesAgents.Agent_HelloWord", null); // Créer l'agent hello
			//hello.start(); //Lancer l'agent hello
			
			timer = mc.createNewAgent("titi","MesAgents.Agent_TIME", null); // Créer l'agent hello
			timer.start(); //Lancer l'agent hello
			
			
		} catch (StaleProxyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}

}
