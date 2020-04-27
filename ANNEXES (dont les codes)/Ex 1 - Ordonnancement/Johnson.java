import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import static java.util.stream.Collectors.*;
import static java.util.Map.Entry.*;

public class Johnson {
	
	int resultat[];
	
	public void order(Atelier a) {
		
		Map<Integer, Integer> U = new HashMap<>();
		Map<Integer, Integer> V = new HashMap<>();
		
		int[][] ordo = a.getOrdo();
		
		//MV= M1+M2 //a_i
		
		 //M3 //b_i
		
		for(int i = 0; i <a.getNb_taches() ; i++)
		  if (ordo[i][1]+ordo[i][2]<ordo[i][3]) //a_i<b_i
			  U.put(i,ordo[i][1]+ordo[i][2]); //a_i
		  else	
			  V.put(i,ordo[i][3]);	//b_i
	
	//ordonner par a_i croissants // 
	    U = U
	            .entrySet()
	            .stream()
	            .sorted(comparingByValue())
	            .collect(
	                toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
	                    LinkedHashMap::new));
	  //ordonner par b_i croissants   
	    V = V
	            .entrySet()
	            .stream()
	            .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
	            .collect(
	                toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
	                    LinkedHashMap::new));
	    
	    
	    Set<Integer> keys_U=U.keySet();
	    Set<Integer> keys_V=V.keySet();
	   
	    
	    resultat =new int[a.getNb_taches()];
	    
	    int i = 0;
		for (int s: keys_U)
			resultat[i++] = s;
		
		for (int s: keys_V)
			resultat[i++] = s;
		
		this.resultat=resultat;
	    
	
		
	}
	
	

}
