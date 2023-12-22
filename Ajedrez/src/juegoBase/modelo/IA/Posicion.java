package juegoBase.modelo.IA;

public class Posicion {
	
	private int f1;
	private int c1;
	private int f2;
	private int c2;
	
	public Posicion(int f1, int c1, int f2, int c2) {
		this.f1 = f1;
		this.c1 = c1;
		this.f2 = f2;
		this.c2 = c2;
	}
	
	public int getF1() {return this.f1;}
	public int getC1() {return this.c1;}
	public int getF2() {return this.f2;}
	public int getC2() {return this.c2;}
	
	public String toString() {
		return "("+this.f1+","+this.c1+") --> ("+this.f2+","+this.c2+")";
	}

}
