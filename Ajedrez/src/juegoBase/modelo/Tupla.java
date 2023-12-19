package juegoBase.modelo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Tupla implements Serializable {
	public Tupla (int f1, int c1, boolean come1) {f = f1; c = c1; come = come1;}
	
	private int f;
	private int c;
	private boolean come;
	
	public int getF() {return this.f;}
	public int getC() {return this.c;}
	public boolean come() {return this.come;}
	
	public boolean esEsteMovimiento(int f1, int c1) {return f1 == this.f && c1 == this.c;}
	
}