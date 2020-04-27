import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

public class C_max {

	
		public static int f(int [] L_Tache, int nbmachine, Atelier At1) {
			//Fonction d'évaluation qui permet d'évaluer la solution donnée en argument
			int [][] OrdoRef = At1.getOrdo();
			int [][] Ordo = new int [At1.getNb_taches()][nbmachine];

			// Prise en compte de la liste donnée en entrée
			
			for (int l = 0 ; l<At1.getNb_taches() ;l++) {
				
				int [] T =At1.getTache( L_Tache[l]);
				Ordo[l][0] = T[0];
				Ordo[l][1]= T[1];
				Ordo[l][2] = T[2];
			}
			
			
			
			
			int Date_de_fin[][] = new int[At1.getNb_taches()][At1.getNb_machine()];
									
			for (int no_machine = 0 ; no_machine<At1.getNb_machine(); no_machine++) {
				 //Cas particuliers
			
								
				switch (no_machine) {
					case 0 :  Date_de_fin[0][no_machine] = Ordo[0][no_machine];
							break;
				
					case 1:	 Date_de_fin[0][no_machine] = Ordo[0][no_machine-1] + Ordo[0][no_machine];
		                     break;
		            case 2:  Date_de_fin[0][no_machine] = Ordo[0][no_machine-1] + Ordo[0][no_machine];
		                     break;
				 }
				 		           
				//Cas généraux
				for(int i = 1 ; i<At1.getNb_taches()  ;i++ ) {
					if(no_machine >= 1) { 
					Date_de_fin[i][no_machine] = Ordo[i][no_machine] + Math.max(Date_de_fin[i-1][no_machine], Date_de_fin[i][no_machine-1])	;
					}
					else {
						Date_de_fin[i][no_machine] = Ordo[i][no_machine] + Date_de_fin[i-1][no_machine];
					}
						
				}
			}
		int C_max = Date_de_fin[At1.getNb_taches()-1][At1.getNb_machine()-1];
			
			return( C_max);
		}
}
		
		
