package PVC.Algorithmes;

import PVC.Data.CityData;
import PVC.Definitions.Route;
import PVC.Definitions.Voisin;


//import java.io.IOException;
public class Recuit extends Algorithme {

    public Recuit(Route actualRoute) {
        super(actualRoute);
    }

    public Recuit(Route actualRoute, int it) {
        super(actualRoute, it);
    }
    public Recuit(Route actualRoute, int it, double t0, double a) {
    	super(actualRoute,it,t0,a);
    }

    public static void main(String[] args) {
        CityData Data = new CityData(100);
        Route initRoute = new Route(Data.getCities());

        Recuit r = new Recuit(initRoute, 100,10.0,0.5);
        System.out.println(r.getBestRoute());

        r.Recuitrun();
        System.out.println("The Best Route: "+r.getBestRoute());
        System.out.println("Min Distance : "+r.getBestRoute().getTotalDistance());
        System.out.println("Simulation time : "+ r.getTotaltime() + "ms");
    }

    private Route getMinRoute() {
        Voisin actualVoisin = new Voisin(this.getActualRoute());
        return actualVoisin.getRandomRoute();
    }
    
}
