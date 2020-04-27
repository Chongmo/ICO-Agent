import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.text.DocumentFilter;
import Comportements.City;
import Comportements.Route;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;


	public class MainOpti {
	
	private static ArrayList<City> initialCities;

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		// TODO Auto-generated method stub
		MainOpti driver = new MainOpti();
		initVilles();
		Route route = new Route(driver.initialCities);
		printHeading(route);
		System.out.print(route+" / "+route.getTotalStringDistance());
		
		
		
		
		// Création de la plateforme SMA
		Runtime rt = Runtime.instance();  //Création d'une instance de JADE unique par ORDI
		ProfileImpl pMain = new ProfileImpl(null, 2222,"maPlateforme"); //Création du Profil (Port + Nom) du conteneur Principal
		AgentContainer mc =rt.createMainContainer(pMain); //Création du conteneur Principale
		
		// OPTIONNEL //
		
		// Lancement de l'interface Graphique de JADE  (RMA) //
		AgentController rma, hill, rs,ag,tabou,coordinateur,seq;
		try {
			rma = mc.createNewAgent("rma","jade.tools.rma.rma", null); // Créer l'agent RMA
			rma.start(); //Lancer l'agent RMA	
			
			Object[] arg = {(Object)route};
			
			
			hill = mc.createNewAgent("AGENT_HILL","MesAgents.Agent_HillClimbing",arg ); // Créer l'agent hello
		 
			tabou = mc.createNewAgent("AGENT_TABOU","MesAgents.Agent_Tabou",arg ); 
			 
			ag = mc.createNewAgent("AGENT_GENETIQUE","MesAgents.Agent_Genetique",arg ); 
			
			
			rs = mc.createNewAgent("AGENT_RS","MesAgents.Agent_Recuit",arg ); 
			

			ag.start();
			rs.start();
			tabou.start();
			hill.start();
			
			
			coordinateur = mc.createNewAgent("AGENT_Collector","MesAgents.Agent_Collector",arg); // Créer l'agent hello
			coordinateur.start(); 
			
			
			
		} catch (StaleProxyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		
	}
	
	public static void initVilles() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
	//Coordonnées à compléter et ajout d'autres villes...
	int NB_Villes = 80;
	
		try {
			initialCities =  BDD_Villes.importVille(NB_Villes);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}	
	
	
	public static void printHeading(Route route){
		String headingColumn1="Route";//
		String remainingHeadingColumns = "Distance (in km) / Compare adjacent to current route";
		int cityNamesLength = 0;//
		for(int x=0;x< route.getCities().size();x++)
			cityNamesLength+=route.getCities().get(x).getName().length();//
		int arrayLength = cityNamesLength+route.getCities().size()*2;//
		int partialLength = (arrayLength-headingColumn1.length())/2;
		for(int x=0;x< partialLength;x++) System.out.print(" ");
		System.out.println(headingColumn1);
		for(int x=0;x< partialLength;x++) System.out.print(" ");
		if((arrayLength % 2)==0) System.out.print(" ");
		System.out.println(" / "+remainingHeadingColumns);
		cityNamesLength += remainingHeadingColumns.length()+3;
		for(int x=0;x< cityNamesLength+route.getCities().size()*2;x++)
		System.out.print("-");
		System.out.println(""); 
	}
	
	}
	
