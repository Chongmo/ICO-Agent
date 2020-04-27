
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.TimeZone;

public class BDD_Villes {
	
	
	public static ArrayList<City>  Liste_Villes = new ArrayList<City>();
	public static Connection con;
	
	public static void main(String[] args) throws Exception {
		/*Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		String userName="root";
		String password="";
		String url = "jdbc:mysql://localhost/bdd_ville_pop?serverTimezone="+TimeZone.getDefault().getID();
		
		con=DriverManager.getConnection(url,userName,password);
		
		
		AffichageVille(9);
		
*/
	}

	public BDD_Villes() throws SQLException {
		
		Liste_Villes = new ArrayList<City>();
	}
	
	public static void AffichageVille(int nb) throws SQLException {
		
		//ArrayList<City>  Liste = new ArrayList<City>();
		
		String query = "SELECT * FROM `villes_france_free`\r\n" + 
				"ORDER BY ville_population_2012 DESC\r\n" + 
				"LIMIT "+ nb;
		ResultSet results = null;
		try { Statement stmt = con.createStatement();
		results = stmt.executeQuery(query); }
		catch(Exception e){
			System.out.println("exception due a la requete");
		}
		
		//Affichage
		while(results.next()) {
			System.out.println(results.getString(4)+" " + results.getDouble(21) +" "+ results.getDouble(20));
			//City ville = new City(results.getString(4),results.getDouble(21),results.getDouble(20));
	//		Liste.add(ville);
}
	
	//return Liste;
	}

	
	public static ArrayList<City> importVille(int nb) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		
		Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		String userName="root";
		String password="";
		String url = "jdbc:mysql://localhost/bdd_ville_pop?serverTimezone="+TimeZone.getDefault().getID();
		
		con=DriverManager.getConnection(url,userName,password);
		
		
		String query = "SELECT * FROM `villes_france_free`\r\n" + 
				"ORDER BY ville_population_2012 DESC\r\n" + 
				"LIMIT "+ nb;
		ResultSet results = null;
		
		try { 
			Statement stmt = con.createStatement();
			results = stmt.executeQuery(query); 
			}
		catch(Exception e){
			System.out.println("exception due a la requete");
		}
		
		Liste_Villes = new ArrayList<City>();
		//Affichage
		while(results.next()) {
			//System.out.println(results.getString(4)+" lat : " + results.getDouble(21) +" long  : "+ results.getDouble(20));
			City ville = new City(results.getString(4),results.getDouble(21),results.getDouble(20));
			Liste_Villes.add(ville);
			}
	
	return Liste_Villes;
	}
	
	
}