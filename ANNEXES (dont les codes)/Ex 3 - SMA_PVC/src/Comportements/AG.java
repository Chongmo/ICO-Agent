package Comportements;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;


public class AG {
	
	public static Route mutation_simple(Route R) {
		ArrayList<City> j= (R.getCities());
		int Taille = j.size();
		Random r = new Random();
		int p= r.nextInt(Taille);
		int q= r.nextInt(Taille);
		City V = j.get(p);
		City W = j.get(q);
		j.set(q,V);
		j.set(p,W);
		Route A = new Route(j);
		return A;
	    
	}
	
	
	@SuppressWarnings("unchecked")
	public static Route mutation(Route R) {
		ArrayList<City> K =R.getCities();
		ArrayList<City> i= (ArrayList<City>) K.clone();
		ArrayList<City> j= (ArrayList<City>) i.clone();
		Random r = new Random();
		int Taille = K.size();
		int p=r.nextInt(Taille);
		int q=r.nextInt(Taille);
		while ((p>=q)) {
			p=r.nextInt(Taille);
			q= r.nextInt(Taille);
		}
		for (int s=q;s>=p;s--) {
			i.set(s, j.get(p+q-s));
		}
		Route A = new Route(i);
		return A;	    
	}

	public static Couple  croisement_simple(Couple c) {
		try {
			Route R1 = c.getR1();
			Route R2 = c.getR2();
			ArrayList<City> C1=R1.getCities();
			ArrayList<City> C2=R2.getCities();
			Random r = new Random();
			int Taille = C2.size();
			int p=r.nextInt(Taille);
			int q=r.nextInt(Taille);
			while ((p>=q)) {
				p=r.nextInt(Taille);
				q=r.nextInt(Taille);
			}
			ArrayList<String> l = new ArrayList<String>();
			ArrayList<String> m = new ArrayList<String>();
			ArrayList<String> n = new ArrayList<String>();
			ArrayList<City> C3= new ArrayList<City>();
			ArrayList<City> C4= new ArrayList<City>();
			ArrayList<City> VR3= new ArrayList<City>();
			ArrayList<City> VR4= new ArrayList<City>();
			City V = new City("O",-1,-1);
			//System.out.println(R2.toString());
			//System.out.println(C1.toString());
			//System.out.println(C2.toString());
			
			
			for(int j=0;j<Taille;j++) {
				l.add(C1.get(j).getName());
				C3.add(V);
				C4.add(V);
				
			}
			
			for(int j=p;j<=q;j++) {
				C3.set(j, C2.get(j));
				C4.set(j, C1.get(j));
				m.add(C2.get(j).getName());
				n.add(C1.get(j).getName());
			}
			
			for(int j=0;j<Taille;j++) {
				if(!m.contains(l.get(j))) {
			      VR3.add(C1.get(j));
				}
				if(!n.contains(l.get(j))) {
			      VR4.add(C1.get(j));
					}
			}
			
			int s1=0;
			int s2=0;

			for(int j=0;j<Taille;j++) {
				if(!l.contains(C3.get(j).getName())) {
					C3.set(j, VR3.get(s1));
					s1=s1+1;
				}
				
				if(!l.contains(C4.get(j).getName())) {
					C4.set(j, VR4.get(s2));
					s2=s2+1;
				}
				
			}
   
			Route R3 = new Route(C3);
			Route R4 = new Route(C4);
			Couple C = new Couple(R3,R4);
			return C;
		} catch (Exception IndexOutOfBoundsException) {
			
			return c;
		}   
		}
	
	public static Couple  croisement_PMX(Couple c) {
		Route R1 = c.getR1();
		Route R2 = c.getR2();
		ArrayList<City> C1=R1.getCities();
		ArrayList<City> C2=R2.getCities();
		Random r = new Random();
		int Taille = C2.size();
		int p=r.nextInt(Taille);
		int q=r.nextInt(Taille);
		while ((p>=q)) {
			p=r.nextInt(Taille);
			q=r.nextInt(Taille);
		}
		ArrayList<String> l1 = new ArrayList<String>();
		ArrayList<String> l2 = new ArrayList<String>();
		ArrayList<City> C3= (ArrayList<City>) C1.clone();
		ArrayList<City> C4= (ArrayList<City>) C2.clone();
		//System.out.println(R2.toString());
		
		//System.out.println(C1.toString());
		//System.out.println(C2.toString());
		
		for(int j=p;j<=q;j++) {
			C3.set(j, C2.get(j));
			C4.set(j, C1.get(j));
			l1.add(C2.get(j).getName());      
			l2.add(C1.get(j).getName());
		}
		//System.out.println(p);
		//System.out.println(q);
		
		
		for (int i=0;i<Taille;i++) {
			for(int j=0;j<p;j++) {
				//System.out.println(C3.toString());
				//System.out.println(C4.toString());
				
				if (l1.contains(C3.get(j).getName())) {
					int k = l1.indexOf(C3.get(j).getName());
					C3.set(j,C4.get(p+k));
				}
				if (l2.contains(C4.get(j).getName())) {
					int l = l2.indexOf(C4.get(j).getName());
					C4.set(j,C3.get(p+l));
				}
			}
			
			for(int j=q+1;j<Taille;j++) {
				if (l1.contains(C3.get(j).getName())) {
					int m = l1.indexOf(C3.get(j).getName());
					C3.set(j,C4.get(p+m));
				}
				if (l2.contains(C4.get(j).getName())) {
					int n = l2.indexOf(C4.get(j).getName());
					C4.set(j,C3.get(p+n));
				}
			}
		
		}
		
		if ((Route.DetectDoublon(C3))||(Route.DetectDoublon(C4))) {
			Route R3 = mutation(R1);
			Route R4 = mutation(R2);
			Couple C = new Couple(R3,R4);
			return C;
		}
		
		else {
			Route R3 = new Route(C3);
			Route R4 = new Route(C4);
			Couple C = new Couple(R3,R4);
			return C;
	     }
	}
	       
}
