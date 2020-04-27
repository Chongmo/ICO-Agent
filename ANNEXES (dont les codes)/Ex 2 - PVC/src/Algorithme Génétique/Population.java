

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class Population {
	private Route[] route;
    
	
	public Population(Route[] route) {
		super();
		this.route = route;
	}

	public Route[] getRoute() {
		return route;
	}

	public void setRoute(Route[] route) {
		this.route = route;
	}
	
	public static Population generate_Pop(int N,Route r) {
    	Route[] Pop = new Route[N];
        Pop[0] = AG.mutation_simple(r);
    	for (int j=1;j<N;j++) {
    		Route K = Pop[j-1];
    		Pop[j]=AG.mutation_simple(AG.mutation(K));
    		
    	}
    	Population P = new Population(Pop);
    	return P;
    }
	
	public static Population Selection_det(Population p) {
		Route[] R=p.getRoute();
		int n=R.length;
		double[] prior = new double[n];
		int i=0;
		for (Route r:R) {
			double s=r.getTotalDistance();
			prior[i]=s;
			i=i+1;
		}
		Tri.triFusion(R,prior);
		Population P = new Population(R);
		return P;
     }
	
	
	public static Population Selection_roulette(Population p) {
		Route[] R=p.getRoute();
		Route[] S=R.clone();
		ArrayList<Integer> Select = new ArrayList<Integer>();
		int n=R.length;
	
		double sum=0;
		double s=0;
		for (Route r:R) {
			 sum=sum+r.getTotalDistance();
			
		}
		for (int k=0;k<(int) n/2;k++) {
			int i=0;
			Random r = new Random();
			double y = r.nextDouble();
			while ((s<y)&&(i<n)) {
				s=s+(R[i].getTotalDistance())/sum;
				i=i+1;
			}
			if (!Select.contains(i)){
				Select.add(i);
			}
		}
		int m = Select.size();
		try {
			for (int k=0;k<m;k++) {
				S[k]=R[Select.get(k)];
			}
		} catch (Exception ArrayIndexOutOfBoundsException) {
			// TODO Auto-generated catch block
			
		}
			
		Population P = new Population(S);
		return P;
     }
	
	
	public static Population evolution1(Population P) {
		Population F = Selection_det(P);
		Route[] R = F.getRoute();
		int n = R.length;
		int it = n/2;
		for(int i=0;i<it;i++) {
			Couple C1 = new Couple(R[i],R[i+1]);
			Couple C2 = AG.croisement_simple(C1);
			R[it+i]=C2.getR1();
			R[it+i]=C2.getR2();
			Random r = new Random();
			int p=r.nextInt(n-1);
			R[p]=AG.mutation_simple(R[p]);
		}
		
		return F;
	}
	
	public static Population evolution2(Population P) {
		Population F = Selection_det(P);
		Route[] R = F.getRoute();
		int n = R.length;
		int it = n/2;
		for(int i=0;i<it;i++) {
			Couple C1 = new Couple(R[i],R[i+1]);
			Couple C2 = AG.croisement_PMX(C1);
			R[it+i]=C2.getR1();
			R[it+i]=C2.getR2();
			Random r = new Random();
			int p=r.nextInt(n-1);
			R[p]=AG.mutation_simple(R[p]);
		}
		
		return F;
	}
	
	public static Population evolution3(Population P) {
		Population F = Selection_det(P);
		Route[] R = F.getRoute();
		int n = R.length;
		int it = n/2;
		for(int i=0;i<it;i++) {
			Couple C1 = new Couple(R[i],R[i+1]);
			Couple C2 = AG.croisement_PMX(C1);
			R[it+i]=C2.getR1();
			R[it+i]=C2.getR2();
			Random r = new Random();
			int p=r.nextInt(n-1);
			R[p]=AG.mutation(R[p]);
		}
		
		return F;
	}
	
	public static Population evolution4(Population P) {
		Population F = Selection_det(P);
		Route[] R = F.getRoute();
		int n = R.length;
		int it = n/2;
		for(int i=0;i<it;i++) {
			Couple C1 = new Couple(R[i],R[i+1]);
			Couple C2 = AG.croisement_simple(C1);
			R[it+i]=C2.getR1();
			R[it+i]=C2.getR2();
			Random r = new Random();
			int p=r.nextInt(n-1);
			R[p]=AG.mutation(R[p]);
		}
		
		return F;
	}
	
	public static Population evolution5(Population P) {
		Population F = Selection_roulette(P);
		Route[] R = F.getRoute();
		int n = R.length;
		int it = n/2;
		for(int i=0;i<it;i++) {
			Couple C1 = new Couple(R[i],R[i+1]);
			Couple C2 = AG.croisement_simple(C1);
			R[it+i]=C2.getR1();
			R[it+i]=C2.getR2();
			Random r = new Random();
			int p=r.nextInt(n-1);
			R[p]=AG.mutation_simple(R[p]);
		}
		
		return F;
	}
	
	public static Population evolution6(Population P) {
		Population F = Selection_roulette(P);
		Route[] R = F.getRoute();
		int n = R.length;
		int it = n/2;
		for(int i=0;i<it;i++) {
			Couple C1 = new Couple(R[i],R[i+1]);
			Couple C2 = AG.croisement_PMX(C1);
			R[it+i]=C2.getR1();
			R[it+i]=C2.getR2();
			Random r = new Random();
			int p=r.nextInt(n-1);
			R[p]=AG.mutation_simple(R[p]);
		}
		
		return F;
	}
	
	public static Population evolution7(Population P) {
		Population F = Selection_roulette(P);
		Route[] R = F.getRoute();
		int n = R.length;
		int it = n/2;
		for(int i=0;i<it;i++) {
			Couple C1 = new Couple(R[i],R[i+1]);
			Couple C2 = AG.croisement_PMX(C1);
			R[it+i]=C2.getR1();
			R[it+i]=C2.getR2();
			Random r = new Random();
			int p=r.nextInt(n-1);
			R[p]=AG.mutation(R[p]);
		}
		
		return F;
	}
	
	public static Population evolution8(Population P) {
		Population F = Selection_roulette(P);
		Route[] R = F.getRoute();
		int n = R.length;
		int it = n/2;
		for(int i=0;i<it;i++) {
			Couple C1 = new Couple(R[i],R[i+1]);
			Couple C2 = AG.croisement_simple(C1);
			R[it+i]=C2.getR1();
			R[it+i]=C2.getR2();
			Random r = new Random();
			int p=r.nextInt(n-1);
			R[p]=AG.mutation(R[p]);
		}
		
		return F;
	}
	
	public static Route Resolution1(int N,Route r) {
		ArrayList<City> C = r.getCities();
    	int n=C.size();
    	int p = 2*n;
    	Population P = generate_Pop(p,r);
    	for(int i=0;i<N;i++) {
    		P = evolution1(P);
    	}
    	Route[] R = P.getRoute();
    	Route A = R[0];
        double s = A.getTotalDistance();
    	System.out.println(A.toString());
    	System.out.println(s);
    	return (A);
    }
	
	public static Route Resolution2(int N,Route r) {
		ArrayList<City> C = r.getCities();
    	int n=C.size();
    	int p = 2*n;
    	Population P = generate_Pop(p,r);
    	for(int i=0;i<N;i++) {
    		P = evolution2(P);
    	}
    	Route[] R = P.getRoute();
    	Route A = R[0];
        double s = A.getTotalDistance();
    	System.out.println(A.toString());
    	System.out.println(s);
    	return (A);
    }
	
	public static Route Resolution3(int N,Route r) {
		ArrayList<City> C = r.getCities();
    	int n=C.size();
    	int p = 3*n;
    	Population P = generate_Pop(p,r);
    	for(int i=0;i<N;i++) {
    		P = evolution3(P);
    	}
    	Route[] R = P.getRoute();
    	Route A = R[0];
        double s = A.getTotalDistance();
    	System.out.println(A.toString());
    	System.out.println(s);
    	return (A);
    }
	
	public static Route Resolution4(int N,Route r) {
		ArrayList<City> C = r.getCities();
    	int n=C.size();
    	int p = 3*n;
    	Population P = generate_Pop(p,r);
    	for(int i=0;i<N;i++) {
    		P = evolution4(P);
    	}
    	Route[] R = P.getRoute();
    	Route A = R[0];
        double s = A.getTotalDistance();
    	System.out.println(A.toString());
    	System.out.println(s);
    	return (A);
    }
	
	public static Route Resolution5(int N,Route r) {
		ArrayList<City> C = r.getCities();
    	int n=C.size();
    	int p = 3*n;
    	Population P = generate_Pop(p,r);
    	for(int i=0;i<N;i++) {
    		P = evolution1(P);
    	}
    	Route[] R = P.getRoute();
    	Route A = R[0];
        double s = A.getTotalDistance();
    	System.out.println(A.toString());
    	System.out.println(s);
    	return (A);
    }
	
	public static Route Resolution6(int N,Route r) {
		ArrayList<City> C = r.getCities();
    	int n=C.size();
    	int p = 3*n;
    	Population P = generate_Pop(p,r);
    	for(int i=0;i<N;i++) {
    		P = evolution6(P);
    	}
    	Route[] R = P.getRoute();
    	Route A = R[0];
        double s = A.getTotalDistance();
    	System.out.println(A.toString());
    	System.out.println(s);
    	return (A);
    }
	
	public static Route Resolution7(int N,Route r) {
		ArrayList<City> C = r.getCities();
    	int n=C.size();
    	int p = 3*n;
    	Population P = generate_Pop(p,r);
    	for(int i=0;i<N;i++) {
    		P = evolution7(P);
    	}
    	Route[] R = P.getRoute();
    	Route A = R[0];
        double s = A.getTotalDistance();
    	System.out.println(A.toString());
    	System.out.println(s);
    	return (A);
    }
	
	public static Route Resolution8(int N,Route r) {
		ArrayList<City> C = r.getCities();
    	int n=C.size();
    	int p = 3*n;
    	Population P = generate_Pop(p,r);
    	for(int i=0;i<N;i++) {
    		P = evolution8(P);
    	}
    	Route[] R = P.getRoute();
    	Route A = R[0];
        double s = A.getTotalDistance();
    	System.out.println(A.toString());
    	System.out.println(s);
    	return (A);
    }
	
	public static Route Proba1(int N, Route r) {
        LinkedList<Route> res = new LinkedList<Route>();
        LinkedList<Integer> occ = new LinkedList<Integer>();
        for (int i=0;i<N;i++) {
        	Route A = Resolution1(N,r);
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
	
	public static Route Proba2(int N, Route r) {
        LinkedList<Route> res = new LinkedList<Route>();
        LinkedList<Integer> occ = new LinkedList<Integer>();
        for (int i=0;i<N;i++) {
        	Route A = Resolution2(N,r);
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
	
	public static Route Proba3(int N, Route r) {
        LinkedList<Route> res = new LinkedList<Route>();
        LinkedList<Integer> occ = new LinkedList<Integer>();
        for (int i=0;i<N;i++) {
        	Route A = Resolution3(N,r);
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
	
	public static Route Proba4(int N, Route r) {
        LinkedList<Route> res = new LinkedList<Route>();
        LinkedList<Integer> occ = new LinkedList<Integer>();
        for (int i=0;i<N;i++) {
        	Route A = Resolution4(N,r);
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
	
	public static Route Proba5(int N, Route r) {
        LinkedList<Route> res = new LinkedList<Route>();
        LinkedList<Integer> occ = new LinkedList<Integer>();
        for (int i=0;i<N;i++) {
        	Route A = Resolution5(N,r);
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
	
	public static Route Proba6(int N, Route r) {
        LinkedList<Route> res = new LinkedList<Route>();
        LinkedList<Integer> occ = new LinkedList<Integer>();
        for (int i=0;i<N;i++) {
        	Route A = Resolution6(N,r);
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
	
	public static Route Proba7(int N, Route r) {
        LinkedList<Route> res = new LinkedList<Route>();
        LinkedList<Integer> occ = new LinkedList<Integer>();
        for (int i=0;i<N;i++) {
        	Route A = Resolution1(N,r);
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
	
	public static Route Proba8(int N, Route r) {
        LinkedList<Route> res = new LinkedList<Route>();
        LinkedList<Integer> occ = new LinkedList<Integer>();
        for (int i=0;i<N;i++) {
        	Route A = Resolution8(N,r);
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
/*	public static void main(String[] args) {
		       ArrayList<City> Cities= new ArrayList<City>(Arrays.asList(

				new City("Lille",50.63,3.07),//
				new City("Paris",48.87,2.33),//
                new City("Lyon",45.75,4.85),
				new City("Marseille",43.30,5.40),
				new City("K",36.30,5.40),
				new City("L",76.30,5.40),
				new City("M",2.30,5.40)//
				));
		       
		       Route r = new Route(Cities);
		       Resolution(10000,r);
		       }
*/	
	
}

