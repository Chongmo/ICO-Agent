package Comportements;
import java.io.Serializable;

public class Resultat implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Route R;
	public double dist;
	public double time ;
	
	public Resultat(Route r, double dist, double time) {
		super();
		R = r;
		this.dist = dist;
		this.time = time;
	}

	public Route getR() {
		return R;
	}

	public void setR(Route r) {
		R = r;
	}

	public double getDist() {
		return dist;
	}

	public void setDist(double dist) {
		this.dist = dist;
	}

	public double getTime() {
		return time;
	}

	public void setTime(double time) {
		this.time = time;
	}

	public String toStringR() {
		try {
			String s1 = (this.R).toString();
			String s2 = String.valueOf(this.dist);
			String s3 =  String.valueOf(this.time);
			return ( s1 + " : " + s2 + " " + s3);
		} catch (Exception NullPointerException) {
			// TODO Auto-generated catch block
			return (String.valueOf(this.dist));
		}
	}
	
}
