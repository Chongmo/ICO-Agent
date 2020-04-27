import java.util.Arrays;
import java.util.Random;

public class Opti_Atelier_Vois_v2 {

	
	
	public static void main(String[] args) {


		int T_Ordo [][]  = new int[][]   {{6,	1,	5},
                    {3,	5,	8},
                    {10,	4,	1},
                    {14,	6,	3},
                    {5,	10,	6},
                    {9,	6,	10},
                    {7,	9,	12},
                    {11,	8,	9},
                    {2,	6,	6},
                    {3,	1,	7}	};
                    
                    
              Atelier Ordo = new Atelier(T_Ordo, 3, 10); // Redéfinir le nb de tours de boucle si chgt du nb du tâches
        	  int [] Meilleur_Ordo = Opti_Voisinage( Ordo ) ;           
        	  System.out.println("Le meilleur ordonnancement est : " + Arrays.toString(Meilleur_Ordo));
        	  System.out.println("Son C_max  est : " +f(Meilleur_Ordo,Ordo.getNb_machine(),Ordo));
           
          
          
         /*// Pour faire des tests (plusieurs executions)
          * for (int h=0; h<20 ;h++) {
        	  Atelier Ordo = new Atelier(T_Ordo, 3, 10); // Redéfinir le nb de tours de boucle si chgt du nb du tâches
        	  int [] Meilleur_Ordo = Opti_Voisinage( Ordo ) ;           
        	  System.out.println("Le meilleur ordonnancement est : " + Arrays.toString(Meilleur_Ordo));
        	  System.out.println("Son C_max  est : " +f(Meilleur_Ordo,Ordo.getNb_machine(),Ordo));
           
          }*/

		}
	
	
		public static int[] Opti_Voisinage(Atelier At ) {
			
			//Init
			int nb_Voisin_max = 400000;//(int) Math.pow(10, 8);	/////////////// A redéfinir si chgmt
			
			int nb_vois=0;
			
			int C_max =0;
			for (int j =0 ;j<At.getNb_taches();j++) {
				for (int duree : At.getTache(j)) {
					C_max = C_max + duree;
				}
				
			}
			
			
			// Solution Complète Initiale
			int [] Meilleur = new int[At.getNb_taches()];
			for ( int i = 0 ; i<At.getNb_taches();i++) {
				Meilleur[i] = i;
			}

			
			
			while (nb_vois<nb_Voisin_max) {
					int [] Voisin = V(Meilleur);
				
							
				//if(Contient(Voisin, Voisin_et)==false) {
				//	Voisin_et[nb_vois]=Voisin;
					
					
					
					
					int C = f(Voisin, At.getNb_machine(), At);
					if (C<C_max) {
						Meilleur = Voisin;
						C_max = C;
					//}
				}
				nb_vois ++;
			}
				
			
			
			
			return Meilleur;
		}
		public static boolean Contient(int[] elt , int [][]L) {
			boolean test = false;
			
			for (int [] Liste : L ) {
				
				if (Comparaison_Tab(Liste,elt)) {
					test=true;
				}
					
			}
			
			return test;
			
		}
		
		public static boolean Comparaison_Tab(int[] t1,int[] t2) {
			boolean test = true;
			int i=0;
			while(test && i<t1.length) {
				test=t1[i]==t2[i]; 
				i++;
				
			}

				return(test);
		}
		
		
		
		
		public static int f(int [] L_Tache, int nbmachine, Atelier At1) {
			//Fonction d'évaluation qui permet d'évaluer la solution donnée en argument
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
		
		
		
	public static int[] V(int [] L_Tache) {
			
			Random rand =new Random();
			
			//Choix aléatoire
			int i = rand.nextInt(L_Tache.length-1); // Pour ne pas toucher à la première ville de départ
			int j = rand.nextInt(L_Tache.length-1);
			
			//Permutation
			int aux=L_Tache[i];
			L_Tache[i]=L_Tache[j];
			L_Tache[j]=aux;
				
				
			return L_Tache;
			
		}
			
		}
	



/*///Affichage du contenu d'une liste
					System.out.println("  \nLa Liste contient : " );
					for (int [] L :Voisin_et) {
						System.out.print(Arrays.toString(L));
					}
					System.out.println("  \nFIN  " );
					
					
					*/ 

