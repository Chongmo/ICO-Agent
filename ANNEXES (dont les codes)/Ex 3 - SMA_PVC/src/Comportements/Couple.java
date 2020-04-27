package Comportements;
import java.util.ArrayList;
import java.util.Arrays;

public class Couple {
	private Route R1;
	private Route R2;
	public Route getR1() {
		return R1;
	}
	public void setR1(Route r1) {
		R1 = r1;
	}
	public Route getR2() {
		return R2;
	}
	public void setR2(Route r2) {
		R2 = r2;
	}
	public Couple(Route r1, Route r2) {
		super();
		R1 = r1;
		R2 = r2;
	}
	
	
}
