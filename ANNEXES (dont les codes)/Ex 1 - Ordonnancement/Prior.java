
public class Prior {
	
	public static int duree_tot(int[] t) { // fonction qui renvoit la durée totale sur les 3 machines d'une tâche
		int val = 0;
		int n = t.length;
		for (int i = 0; i< n; i++) {
			val = val + t[i];
		}
		return val;			
	}

	public static int duree_min(int[] t) { // fonction qui renvoit la durée la plus courte d'une tâche sur les 3 machines possibles
		int val = t[0];
		int n = t.length;
		for (int i = 1; i< n; i++) {
			if ( val > t[i] ) {
				val = t[i];
			}
		}
		return val;			
	}
	
	public static int duree_max(int[] t) { // fonction qui renvoit la durée la plus longue d'une tâche sur les 3 machines possibles
		int val = 0;
		int n = t.length;
		for (int i = 1; i< n; i++) {
			if ( val < t[i] ) {
				val = t[i];
			}
		}
		return val;			
	}
	
	public static int variance(int[] t) { // fonction qui renvoit une valeur correspondant à l'écart type des durées d'une tâche sur les 3 machines
		int val = t[0];
		int n = t.length;
		int moyenne = duree_tot(t);
		for (int i = 1; i< n; i++) {
			val = val + (n * t[i] - moyenne)*(n * t[i] - moyenne);
		}
		return val;
	}
	
	public static int[] min_tot(Atelier A) { // A chaque tâche, on attribue une valeur correspondant à duree_tot puis on classe les tâches avec ces valeurs
		int n = A.getNb_taches();
		int[] T = new int[n];
		int[] P = new int[n];
		for (int i=0;i<n;i++) {
			T[i]= i;
			int[] t = A.getTache(i);
			P[i] = duree_tot(t);
		}
	
		Tri.triFusion(T,P);
		return T;
	}

	public static int[] min_m(Atelier A) { // A chaque tâche, on attribue une valeur correspondant à duree_min puis on classe les tâches avec ces valeurs
		int n = A.getNb_taches();
		int[] T = new int[n];
		int[] P = new int[n];
		for (int i=0;i<n;i++) {
			T[i]= i;
			int[] t = A.getTache(i);
			P[i] = duree_min(t);
		}
	
		Tri.triFusion(T,P);
		return T;
	}
	
	public static int[] max_m(Atelier A) { // A chaque tâche, on attribue une valeur correspondant à duree_max puis on classe les tâches avec ces valeurs
		int n = A.getNb_taches();
		int[] T = new int[n];
		int[] P = new int[n];
		for (int i=0;i<n;i++) {
			T[i]= i;
			int[] t = A.getTache(i);
			P[i] = duree_max(t);
		}
	
		Tri.triFusion(T,P);
		return T;
	}
	
	public static int[] var_min(Atelier A) { // A chaque tâche, on attribue une valeur correspondant à variance puis on classe les tâches avec ces valeurs
		int n = A.getNb_taches();
		int[] T = new int[n];
		int[] P = new int[n];
		for (int i=0;i<n;i++) {
			T[i]= i;
			int[] t = A.getTache(i);
			P[i] = variance(t);
		}
	
		Tri.triFusion(T,P);
		return T;
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

