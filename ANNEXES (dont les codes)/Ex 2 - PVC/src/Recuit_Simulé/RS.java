import java.util.Random;

public class RS {

	public static Route Recuit_Simule(int nb_iter_cycle, double t0, double a, Route sol0) {
		
		// INITIALISATION
		Route sol = sol0; 
		Route sol_et=sol;
		boolean nouveau_cycle = true;
		double t = t0; // Température Initial du système atention t!=0
		
		
		// PROCESSUS ITERATIF
		
		while (nouveau_cycle) {
			
			
			int nb_iter = 0;
			nouveau_cycle = false;
			@SuppressWarnings("unused")
			int k=0;
			while(nb_iter<nb_iter_cycle) {

				
				
				k++;
				nb_iter++;

				Route s_prime = sol.getAdjacentRoute();
				
				double delta_f = s_prime.getTotalDistance()-sol.getTotalDistance();

				if ((delta_f)<0) {
					sol=s_prime;
					nouveau_cycle = true;
				}
				else {
					double prob = Math.exp(-delta_f/t);
					double q = new Random().nextDouble();
					
					if(q<prob) {
						sol=s_prime;
						nouveau_cycle = true;
					}
				}
				
				if(sol.getTotalDistance()<sol_et.getTotalDistance()) {
					sol_et = sol;

				}
								
			} //Fin while imbriqué
			
			t = a*t;
		
			

		}	
		
		return(sol_et);			
			}
		
	
		
	
	
}
