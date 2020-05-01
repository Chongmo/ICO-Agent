package PVC.Algorithmes;

import PVC.Data.CityData;
import PVC.Definitions.Route;
import PVC.Utils.Line;
//import com.github.sh0nk.matplotlib4j.PythonExecutionException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Recuit extends Algorithme {
    private float temperature = 10;
    private float refroid = 0.5f;
    public Recuit() {
        super();
    }
    public Recuit(Route actualRoute) {
        super(actualRoute);
    }

    public Recuit(Route actualRoute, int it) {
        super(actualRoute, it);
    }

    public Recuit(Route actualRoute, int it, float t0, float a) {
        super(actualRoute, it);
        this.temperature = t0;
        this.refroid = a;
    }


    public static void main(String[] args) throws IOException {
        CityData Data = new CityData(50);
        Route initRoute = new Route(Data.getCities());

        Recuit r = new Recuit(initRoute, 100, 10.0f, 0.5f);
        System.out.println(r.getBestRoute());

        r.runtest();
        System.out.println("The Best Route: " + r.getBestRoute());
        System.out.println("Min Distance : " + r.getBestRoute().getTotalDistance());
    }

    @Override
    public void runtest() {
        ArrayList<Float> logger = new ArrayList<>();
        boolean nouveau_cycle = true;
        float t = this.temperature;

        while (nouveau_cycle) {
            int nb_iter = 0;
            nouveau_cycle = false;
            @SuppressWarnings("unused")
            int k = 0;

            while (nb_iter < this.iter) {
                logger.add(this.bestRoute.getTotalDistance());
                k++;
                nb_iter++;
                Route s_prime = this.getRandomRoute();
                //System.out.println(actualRoute);
                //System.out.println(s_prime);

                float delta_f = s_prime.getTotalDistance() - this.actualRoute.getTotalDistance();
                //System.out.println(delta_f);

                if (delta_f < 0) {
                    this.actualRoute = s_prime;
                    nouveau_cycle = true;
                } else {
                    float prob = (float) Math.exp(-delta_f / t);
                    float q = (float) new Random().nextDouble();

                    if (q < prob) {
                        this.actualRoute = s_prime;
                        nouveau_cycle = true;
                    }
                }
                if (this.actualRoute.getTotalDistance() < this.bestRoute.getTotalDistance()) {
                    this.bestRoute = this.actualRoute;
                    //System.out.println(this.bestRoute+"distance="+this.bestRoute.getTotalDistance());
                }
                //System.out.print("nb_iter="+nb_iter);
            }
            t = this.refroid * t;
            //System.out.println("t = "+t);
        }
        this.lines.add(new Line(logger, "test"));
    }
}