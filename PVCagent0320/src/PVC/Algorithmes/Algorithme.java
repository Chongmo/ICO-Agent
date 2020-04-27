package PVC.Algorithmes;

import PVC.Data.CityData;
import PVC.Definitions.Route;
import PVC.Definitions.Tuple;
import PVC.Definitions.Voisin;
import PVC.Utils.Line;
//import PVC.Utils.Plot;
import PVC.Utils.drawing;
import java.util.Random;
//import com.github.sh0nk.matplotlib4j.PythonExecutionException;

import java.io.IOException;
import java.util.ArrayList;
public abstract class Algorithme {
    private int iter;
    private double temperature;
    private double refroid;
    private Route bestRoute;
    private Route actualRoute;
    private ArrayList<Tuple<Integer, Integer>> T = new ArrayList<>();
    private ArrayList<Line> lines = new ArrayList<>();
    private drawing draw;
    private long currenttime;
    private long executiontime;

    public Algorithme(Route actualRoute) {
        this.actualRoute = actualRoute;
        this.bestRoute = new Route(actualRoute);
        this.iter = 50;
    }

    public Algorithme(Route actualRoute, int it) {
        this(actualRoute);
        this.iter = it;
    }
    public Algorithme(Route actualRoute, int it, double t0, double a) {
        this(actualRoute,it);
        this.temperature = t0;
        this.refroid = a;
    }

    public static void main(String[] args) {
        CityData Data = new CityData(50);
        Route initRoute = new Route(Data.getCities());
    }

    public void tabourun() {
        ArrayList<Double> logger = new ArrayList<>();
        draw = new drawing("Tabou algorithme");
    	draw.setVisible(true);
    	long initialtime = System.currentTimeMillis();
    	
        while (this.iter-- > 0) {
        	logger.add(this.bestRoute.getTotalDistance());
            this.actualRoute = this.getMinRoute();
            this.upgradeT(this.actualRoute.getTransfert());
            if (this.actualRoute.getTotalDistance() < this.bestRoute.getTotalDistance()) {
                this.bestRoute = this.actualRoute;
                
            }
            currenttime = System.currentTimeMillis();
            draw.addData(currenttime,bestRoute.getTotalDistance());
        }
        this.lines.add(new Line(logger, "test"));
        this.executiontime = currenttime-initialtime;
    }
    public void Recuitrun() {
    	ArrayList<Double> logger = new ArrayList<>();
        //draw = new drawing("Recruit algorithme");
    	//draw.setVisible(true);
    	long initialtime = System.currentTimeMillis();
    	boolean nouveau_cycle = true;
    	double t = this.temperature;
    	
    	while(nouveau_cycle) {
    		int nb_iter = 0;
    		nouveau_cycle = false;
    		@SuppressWarnings("unused")
    		int k =0;
    		
    		while(nb_iter<this.iter) {
    			logger.add(this.bestRoute.getTotalDistance());
    			k++;
    			nb_iter++;
    			Route s_prime = this.getRandomRoute();
    			//System.out.println(actualRoute);
    			//System.out.println(s_prime);
    			
    			double delta_f = s_prime.getTotalDistance()-this.actualRoute.getTotalDistance();
    			//System.out.println(delta_f);
    			
    			if(delta_f<0) {
    				this.actualRoute = s_prime;
    				nouveau_cycle = true;
    			}
    			else {
    				double prob = Math.exp(-delta_f/t);
    				double q = new Random().nextDouble();
    				
    				if(q<prob) {
    					this.actualRoute = s_prime;
    					nouveau_cycle = true;
    				}
    			}
    			if(this.actualRoute.getTotalDistance()<this.bestRoute.getTotalDistance()) {
    				this.bestRoute = this.actualRoute;
    				//System.out.println(this.bestRoute+"distance="+this.bestRoute.getTotalDistance());
    			}
    			//System.out.print("nb_iter="+nb_iter);
    			currenttime = System.currentTimeMillis();
                //draw.addData(currenttime,bestRoute.getTotalDistance());
    		}
    		t = this.refroid * t;
    		//System.out.println("t = "+t);
    	}
    	this.lines.add(new Line(logger, "test"));
        this.executiontime = currenttime-initialtime;
        System.out.println("The Best Route: "+this.getBestRoute());
        System.out.println("Min Distance : "+this.getBestRoute().getTotalDistance());
        System.out.println("Simulation time : "+ this.getTotaltime()+ "ms");
    }
    public void plot() throws IOException{
    	drawing draw = new drawing("Tabou algorithme");
    	draw.setVisible(true);
    	//Plot display = new Plot(this.lines, "iterations", "distance", "Tabou");
        //display.show();
    }

    private Route getMinRoute() {
        Voisin actualVoisin = new Voisin(this.actualRoute);
        return actualVoisin.getMinRoute(this.T, this.actualRoute);
    }
    private Route getRandomRoute() {
    	Voisin actualVoisin = new Voisin(this.actualRoute);
    	return actualVoisin.getRandomRoute();
    }

    public Route getActualRoute() {
        return actualRoute;
    }

    public ArrayList<Tuple<Integer, Integer>> getT() {
        return T;
    }

    public Route getBestRoute() {
        return bestRoute;
    }

    public void setIter(int iter) {
        this.iter = iter;
    }
    private void upgradeT(Tuple<Integer, Integer> trans) {
        if (T.size() > 5) {
            T.remove(0);
        }
        T.add(new Tuple<>(trans));
    }
    public long getTotaltime(){
    	return executiontime;
    }
    
    
}
