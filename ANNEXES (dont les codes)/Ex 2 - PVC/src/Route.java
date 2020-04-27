

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Route implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<City> cities=new ArrayList<City>();
	
	public String toString(){ return Arrays.toString(cities.toArray());}
	
	//constructeurs
	//-->à partir d'une autre route
	public Route(Route route){
		// ajouter les villes de la route en param comme villes de cette route ajouter
		route.cities.stream().forEach(x->cities.add(x));
	}
	//-->à partir d'une liste de villes qu'on mélange pour ne pas garder le même ordre
	
	public Route(ArrayList<City> cities){
		this.cities.addAll(cities);
		//Collections.shuffle(this.cities);
	}
	
	//get methods
	public ArrayList<City> getCities(){ return cities;}
	
	public double getTotalDistance(){
		int citiesSize = this.cities.size();
		return this.cities.stream().mapToDouble(x->{
		int cityIndex=this.cities.indexOf(x);
		double returnValue=0;
		if(cityIndex<citiesSize-1) returnValue=x.measureDistance(this.cities.get(cityIndex+1));
		return returnValue;
		}).sum()+this.cities.get(citiesSize-1).measureDistance(this.cities.get(0));
	}
	
	public String getTotalStringDistance(){
		String returnValue = String.format("%.2f",this.getTotalDistance());
		if(returnValue.length()==7) returnValue=" "+returnValue;
		return returnValue;
	}
	
	
	public Route getAdjacentRoute(){

		int x1=0, x2=0;
		//selection random de 2 villes distinctes
		while (x1==x2){
			x1=(int) (this.getCities().size()*Math.random());
			x2=(int) (this.getCities().size()*Math.random());
		}

		//permutaion pour obtenir la route adjacente
		City city1=this.getCities().get(x1);
		City city2=this.getCities().get(x2);	
		this.getCities().set(x2, city1);
		this.getCities().set(x1, city2);
		
		return this;
	}
	
	public static boolean DetectDoublon(ArrayList<City> C)
  	{
      for (int i = 0; i < C.size(); i++)
      {
          for (int j = 0; j < i; j++) {
              final Object o1 = C.get(i).getName();
              final Object o2 = C.get(j).getName();
              if (o1.equals(o2)) {
                      return true;
                  }
              }
          }
      return false;
  }

}