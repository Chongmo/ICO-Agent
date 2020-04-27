package PVC.Agent;


import PVC.Agent.CyclicBehaviour_Comm;
import PVC.Data.CityData;
import PVC.Definitions.Route;
import jade.core.Agent;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

public class Agent_Collector extends Agent{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void setup() {
		
		CyclicBehaviour_Comm C1 = new CyclicBehaviour_Comm(); // Cr�ation d'un comportment
		addBehaviour(C1); 
	}
	public static void main(String[] args) throws StaleProxyException {
		CityData Data = new CityData(500);
        Route initRoute = new Route(Data.getCities());

		// Création de la plateforme SMA
		Runtime rt = Runtime.instance();  //Création d'une instance de JADE unique par ORDI
		ProfileImpl pMain = new ProfileImpl(null, 2222,"maPlateforme"); //Création du Profil (Port + Nom) du conteneur Principal
		AgentContainer mc =rt.createMainContainer(pMain); //Création du conteneur Principale
		
		// Lancement de l'interface Graphique de JADE  (RMA) //
		AgentController rma,rs, coordinateur;
		rma = mc.createNewAgent("rma","jade.tools.rma.rma", null); // Créer l'agent RMA
		rma.start(); //Lancer l'agent RMA	
		
		Object[] arg = {(Object)initRoute};	

		rs = mc.createNewAgent("AGENT_RS","PVC.Agent.Agent_RS",arg ); // Créer l'agent 
		rs.start(); //Lancer l'agent RS
		
		coordinateur = mc.createNewAgent("AGENT_Collector","PVC.Agent.Agent_Collector",null ); // Créer l'agent 
		coordinateur.start(); //Lancer l'agent Collector
		

	}
		
		
	
}
