import java.io.IOException;

 
public class Tabou {
	
	  
	private String [][] liste;// liste de la Tabou
 
	private int t;// 
 
	
	private int MAX_GEN;// nombre d'iteration
	private int N;// nombre de voisin de recherche chaque fois
	private int ll;// longuer de la liste de Tabou
	private int cityNum; // nombre de ville
 
	@SuppressWarnings("unused")
	private int bestT;// nombre d'currence de la meilleur solution
 
	private Route Ghh;// solution de debut
	private Route bestGh;// meilleur solution
	private double bestEvaluation;
	private Route LocalGhh;// meilleur solution pour l'instant
	private double localEvaluation;
	private double tempEvaluation;
	
	
	public Tabou() {
 
	}
 
	/**
	 * constructor of GA
	 * 
	 * @param n
	 *            nombre de ville
	 * @param g
	 *            fois de recherche
	 * @param c
	 *            nombre de voisin de recherche chaque fois
	 * @param m
	 *            longueur de la liste de Tabou
	 * 
	 **/
	public Tabou(int n, int g, int c, int m) {
		cityNum = n;
		MAX_GEN = g;
		N = c;
		ll = m;
	}
 
	  
 
	// s'il est dans la liste de Tabou
	public int panduan(Route tempGh) {
		int i, j;
		int flag = 0;
		for (i = 0; i < ll; i++) {
			flag = 0;
			for (j = 0; j < cityNum; j++) {
				if (tempGh.getCities().get(i).toString() != liste[i][j]) {
					flag = 1;// non
					break;
				}
			}
			if (flag == 0)// oui
			{
				// return 1;
				break;
			}
		}
		if (i == ll)
		{
			return 0;// non
		} else {
			return 1;// si
		}
	}
 
	public void UpdateListe(Route localGhh2) {
		int i, j, k;
		// enlever le dernier element dans la liste de Tabou et deplacer les restes
		for (i = 0; i < ll - 1; i++) {
			for (j = 0; j < cityNum; j++) {
				liste[i][j] = liste[i + 1][j];
			}
		}
 
		// ajouter a la liste de Tabou
		for (k = 0; k < cityNum; k++) {
			liste[ll - 1][k] = localGhh2.getCities().get(k).toString();
		}
 
	}
	
	public Route solve(Route Rinit) {
		//INIT
		bestEvaluation = Integer.MAX_VALUE;
		localEvaluation = Integer.MAX_VALUE;
		tempEvaluation = Integer.MAX_VALUE;
 
		liste = new String[ll][cityNum];
		bestT = 0;
		t = 0;
 
		//PROG	
		int nn;
		Ghh= new Route(Rinit);// Ghh initialisation
		bestGh=new Route(Ghh.getCities());// copier Ghh a la meilleur solution bestGh
		//copy(Ghh.getCities(),bestGh);
		bestEvaluation = Ghh.getTotalDistance();
		//System.out.println(bestGh+" / "+bestGh.getTotalStringDistance());
 
		while (t < MAX_GEN) {
				nn = 0;
				localEvaluation = Integer.MAX_VALUE;
				while (nn < N) {
					Route tempGhh = Ghh.getAdjacentRoute();// obtenir le voisin de Ghh, tempGhh
					if (panduan(tempGhh) == 0)// si Ghh est dans la liste de Tabou
					{
						// non
						tempEvaluation = tempGhh.getTotalDistance();
						if (tempEvaluation < localEvaluation) {
							LocalGhh=new Route(tempGhh.getCities());
							localEvaluation = tempEvaluation;
						}
						nn++;
					}
				}
				if (localEvaluation < bestEvaluation) {
					bestT = t;
					bestGh=new Route (LocalGhh.getCities());
					bestEvaluation = localEvaluation;
				}
				Ghh=new Route(LocalGhh.getCities());
				//System.out.println(Ghh+" // "+Ghh.getTotalStringDistance());
				//System.out.println(LocalGhh+" / "+LocalGhh.getTotalStringDistance());
				UpdateListe(LocalGhh);
				t++;
		}
 
		/*System.out.println("Nombre d'occurrence de la meilleur solution：");
		System.out.println(bestT);
		System.out.println("La distance optimale");
		System.out.println(bestEvaluation);
		System.out.println("La meilleur solution：");
		System.out.print(bestGh+" / "+bestGh.getTotalStringDistance());*/
		return(bestGh);
	}
 
	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		
	}
}