import java.lang.reflect.Array;

public class Atelier {
	private int[][] Ordo;
	private int nb_machine;
	private int nb_taches;
	
	public Atelier(int[][] ordo,int nbm,int nbt) {
		Ordo = ordo;
		nb_machine=nbm;
		nb_taches=nbt;
	}
	
	

	public int[][] getOrdo() {
		return Ordo;
	}



	public void setOrdo(int[][] ordo) {
		Ordo = ordo;
	}
	
	public int getNb_machine() {
		return nb_machine;
	}



	public void setNb_machine(int nb_machine) {
		this.nb_machine = nb_machine;
	}



	public int getNb_taches() {
		return nb_taches;
	}



	public void setNb_taches(int nb_taches) {
		this.nb_taches = nb_taches;
	}



	public int[] getTache(int i) {
		int[] column = new int[nb_machine];
		
		for(int j=0;j<nb_machine;j++) {
			column[j]=Ordo[i][j];
		}
		return column;
	}
	


	public static void main(String[] args) {
		
		int[][] Ordo = {{}};
		// TODO Auto-generated method stub

	}

}
