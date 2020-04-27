import java.util.LinkedList;
import java.util.Random;

public class RS2 {

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
		
	
	public static Route Proba(int N, Route r) {
        LinkedList<Route> res = new LinkedList<Route>();
        LinkedList<Integer> occ = new LinkedList<Integer>();
        double t0 = 10;
		double a = 0.5;
		int nb_iter_cycle= 100;

        for (int i=0;i<N;i++) {
        	
        	Route A = Recuit_Simule(nb_iter_cycle,  t0, a, r);
        	if (!res.contains(A)) {
        		res.add(A);
        		occ.add(1);
        	}
        	else {
        		occ.set(res.indexOf(A), occ.get(res.indexOf(A))+1);
        	}
        }
        int n = res.size();
        
        float[] Proba = new float[n];
        int s=0;
        Proba[0]=(float) occ.get(0)/N;
        for (int i=1;i<n;i++) {
        	Proba[i] = (float) occ.get(i)/N;
        	if (Proba[i]>Proba[s]) {
        		s=i;
        	}
        }
        Route R = res.get(s);
        //float r = (float) s/N;
        return R;
    }
	
	
}
