import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Simulation {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, EncryptedDocumentException, IOException {
		// INIT
		double start=0;	
		double stop =0;


		int no_ligne = 2; 
				
		double [] taillePB = {6, 10, 20, 50, 80};
		 
		// double [] taillePB = {6};

		 double [] T0 = {1, 5, 10, 20, 30};
		 double [] A = {0.5};//0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9};
		 int [] NBCycle = {50, 100, 150, 200};

		 double [][] temps_exec = new double[3][taillePB.length];		
		 double [][] ecart_fitness = new double[3][taillePB.length];		
		 double [][] D_solution = new double[3][taillePB.length];		

        
        //Ouverture Fichier Excel
        String nom_f ="C:/Users/Utiisateur/Documents/Centrale Lille/#Electifs/Intelligence Collaborative/Resultats.xlsx";
		FileInputStream file = new FileInputStream(new File(nom_f));
		XSSFWorkbook classeur = new XSSFWorkbook(file);
		XSSFSheet feuille = classeur.getSheetAt(0);
		
		
	
		// Tests
		ArrayList<City> initialCities;
		Route sol0;
		
	
		 //RS
	/*	  for (double NB_Villes : taillePB) {
			//Init Boucle
			initialCities = initVilles((int)NB_Villes);
			sol0 = new Route(initialCities) ;	

			System.out.println(sol0);
			
			for (double t0 : T0) {

				for (double a : A) {
						for (int nb_iter_cycle : NBCycle) {
					//	System.out.println("TaillePB : "+NB_Villes + " T0 : "+t0+ " a : "+a+" nb_cycles : "+nb_iter_cycle);
						//Ecriture des parammètres
						feuille.getRow(no_ligne).createCell(0).setCellValue(NB_Villes);
						feuille.getRow(no_ligne).createCell(1).setCellValue("Recuit Simulé");
						feuille.getRow(no_ligne).createCell(2).setCellValue(t0);
						feuille.getRow(no_ligne).createCell(3).setCellValue(a);
						feuille.getRow(no_ligne).createCell(4).setCellValue(nb_iter_cycle);

						//Recuit Simulé
						start = System.currentTimeMillis();
						Route Route_rs = RS.Recuit_Simule(nb_iter_cycle,  t0, a, sol0);
						stop = System.currentTimeMillis();
						
						
						feuille.getRow(no_ligne).createCell(9).setCellValue(Route_rs.getTotalDistance());
						feuille.getRow(no_ligne).createCell(8).setCellValue((stop - start)/1000.);
						no_ligne++;					
					}
				}
				}
		  }
		  
		  try (OutputStream fileOut = new FileOutputStream(nom_f)) {
		    	 classeur.write(fileOut);
		     }
			*/
		
			for (double NB_Ville : taillePB) {
				//Init Boucle
				initialCities = initVilles((int)NB_Ville);
				sol0 = new Route(initialCities) ;
				System.out.println(sol0);
		
	
				//Génétique 1
				 start = System.currentTimeMillis();
				Route Route_AG = Population.Resolution1((int)NB_Ville, sol0);  
				 stop = System.currentTimeMillis();
				 
				feuille.getRow(no_ligne).createCell(0).setCellValue((int)NB_Ville);
				feuille.getRow(no_ligne).createCell(1).setCellValue("Algorithme Génétique 1");
				feuille.getRow(no_ligne).createCell(9).setCellValue(Route_AG.getTotalDistance());
				feuille.getRow(no_ligne).createCell(8).setCellValue((stop - start)/1000.);
				
				no_ligne++;	
				
				//Génétique 2
				 start = System.currentTimeMillis();
				Route_AG = Population.Resolution2((int)NB_Ville, sol0);  
				 stop = System.currentTimeMillis();
				 
				feuille.getRow(no_ligne).createCell(0).setCellValue((int)NB_Ville);
				feuille.getRow(no_ligne).createCell(1).setCellValue("Algorithme Génétique 2");
				feuille.getRow(no_ligne).createCell(9).setCellValue(Route_AG.getTotalDistance());
				feuille.getRow(no_ligne).createCell(8).setCellValue((stop - start)/1000.);
				
				no_ligne++;	
				
				//Génétique 3
				 start = System.currentTimeMillis();
				Route_AG = Population.Resolution3((int)NB_Ville, sol0);  
				 stop = System.currentTimeMillis();
				 
				feuille.getRow(no_ligne).createCell(0).setCellValue((int)NB_Ville);
				feuille.getRow(no_ligne).createCell(1).setCellValue("Algorithme Génétique 3");
				feuille.getRow(no_ligne).createCell(9).setCellValue(Route_AG.getTotalDistance());
				feuille.getRow(no_ligne).createCell(8).setCellValue((stop - start)/1000.);
				
				no_ligne++;	
				
				//Génétique 4
				 start = System.currentTimeMillis();
				Route_AG = Population.Resolution4((int)NB_Ville, sol0);  
				 stop = System.currentTimeMillis();
				 
				feuille.getRow(no_ligne).createCell(0).setCellValue((int)NB_Ville);
				feuille.getRow(no_ligne).createCell(1).setCellValue("Algorithme Génétique 4");
				feuille.getRow(no_ligne).createCell(9).setCellValue(Route_AG.getTotalDistance());
				feuille.getRow(no_ligne).createCell(8).setCellValue((stop - start)/1000.);
				
				no_ligne++;	
				
				//Génétique 5
				 start = System.currentTimeMillis();
				Route_AG = Population.Resolution5((int)NB_Ville, sol0);  
				 stop = System.currentTimeMillis();
				 
				feuille.getRow(no_ligne).createCell(0).setCellValue((int)NB_Ville);
				feuille.getRow(no_ligne).createCell(1).setCellValue("Algorithme Génétique 5");
				feuille.getRow(no_ligne).createCell(9).setCellValue(Route_AG.getTotalDistance());
				feuille.getRow(no_ligne).createCell(8).setCellValue((stop - start)/1000.);
				
				no_ligne++;	
				
				//Génétique 6
				 start = System.currentTimeMillis();
				Route_AG = Population.Resolution6((int)NB_Ville, sol0);  
				 stop = System.currentTimeMillis();
				 
				feuille.getRow(no_ligne).createCell(0).setCellValue((int)NB_Ville);
				feuille.getRow(no_ligne).createCell(1).setCellValue("Algorithme Génétique 6");
				feuille.getRow(no_ligne).createCell(9).setCellValue(Route_AG.getTotalDistance());
				feuille.getRow(no_ligne).createCell(8).setCellValue((stop - start)/1000.);
				
				no_ligne++;	
				
				//Génétique 7
				 start = System.currentTimeMillis();
				Route_AG = Population.Resolution7((int)NB_Ville, sol0);  
				 stop = System.currentTimeMillis();
				 
				feuille.getRow(no_ligne).createCell(0).setCellValue((int)NB_Ville);
				feuille.getRow(no_ligne).createCell(1).setCellValue("Algorithme Génétique 7");
				feuille.getRow(no_ligne).createCell(9).setCellValue(Route_AG.getTotalDistance());
				feuille.getRow(no_ligne).createCell(8).setCellValue((stop - start)/1000.);
				
				no_ligne++;	
				
				//Génétique 8
				 start = System.currentTimeMillis();
				Route_AG = Population.Resolution8((int)NB_Ville, sol0);  
				 stop = System.currentTimeMillis();
				 
				feuille.getRow(no_ligne).createCell(0).setCellValue((int)NB_Ville);
				feuille.getRow(no_ligne).createCell(1).setCellValue("Algorithme Génétique 8");
				feuille.getRow(no_ligne).createCell(9).setCellValue(Route_AG.getTotalDistance());
				feuille.getRow(no_ligne).createCell(8).setCellValue((stop - start)/1000.);
				
				no_ligne++;	
			
			}
			
			  try (OutputStream fileOut = new FileOutputStream(nom_f)) {
			    	 classeur.write(fileOut);
			     }
			
			  
			
			
			int [] LV = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
			int [] LT = {1, 2};



            
      
               
                
for (double NB_Ville : taillePB) {
	initialCities = initVilles((int)NB_Ville);
	sol0 = new Route(initialCities) ;
	System.out.println(sol0);
      
	for (int nb_cycle : NBCycle) {  
		for (int lv : LV) { 
			for (int lt : LT) {
				feuille.getRow(no_ligne).createCell(0).setCellValue(NB_Ville);
				feuille.getRow(no_ligne).createCell(1).setCellValue("Tabou");
				feuille.getRow(no_ligne).createCell(5).setCellValue(lv);
				feuille.getRow(no_ligne).createCell(6).setCellValue(lt);
				feuille.getRow(no_ligne).createCell(4).setCellValue(nb_cycle);
		     
                
                
				//Tabou
				 start = System.currentTimeMillis();
				 Route Route_Tabou = new Tabou((int)NB_Ville, nb_cycle, lv, lt).solve(sol0); /// A completer
				 stop = System.currentTimeMillis();
				// System.out.println(Route_Tabou.getTotalStringDistance()+Route_Tabou);
				
				 feuille.getRow(no_ligne).createCell(9).setCellValue(Route_Tabou.getTotalDistance());
				feuille.getRow(no_ligne).createCell(8).setCellValue((stop - start)/1000.);
				
				no_ligne++;
			}	
		}
	}
}
     

		// Ecriture dans le fichier de sortie
	     try (OutputStream fileOut = new FileOutputStream(nom_f)) {
	    	 classeur.write(fileOut);
	     }

	     classeur.close();
	     file.close();
	     System.out.println("Terminé !");
		
	}




	public static ArrayList<City> initVilles(int NB_Villes) throws InstantiationException, IllegalAccessException, ClassNotFoundException {

		ArrayList<City> initialCities=null;
	
		
			try {

				initialCities =  BDD_Villes.importVille(NB_Villes);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
			return(initialCities);
	}
	
	public void SaveXL() {
		// 7 8 9
		
		
		
	}
	
	



}