package juegoOnline.Controlador;

import java.io.Serializable;
import java.util.ArrayList;

import juegoBase.modelo.Tupla;

public class MovimientosPosibleDePieza implements Serializable {
	
	
	// Representa un movimiento posible
	
	private int f1; // (f1, c1) -> Posición actual
	private int c1;
    private ArrayList<Tupla> destinos; // -> Destinos donde poner la ficha
	
	
	public MovimientosPosibleDePieza(int f1, int c1) {
		this.c1 = c1;
		this.f1 = f1;
		this.destinos = new ArrayList<Tupla>();

		
	}
	
	public boolean empiezaAqui(int f, int c) {
		return this.f1 == f && this.c1 == c;
	}
	
	public void addDestino(int f2, int c2, boolean come) {
		this.destinos.add(new Tupla(f2, c2, come));
	}
	
	public boolean destinoValido(int f, int c) {
		
		// Pre: Se ha elegido esta casilla donde hay una pieza como el inicio del movimiento. Se quiere mover a (f,c)
		// Post: Verificar si realmente se puede mover a (f,c)
		
		for (Tupla t: this.destinos) {
			
			if (t.getF() == f && t.getC() == c) {
				return true;
			}
		}
		
		return false;
		
	}
	
	public ArrayList<Tupla> getDestinos() {
		return this.destinos;
		
	}
	
	
	public boolean come (int f, int c) {
		
		// Pre: El movimiento de (f1, c1) a (f, c) es válido
		// Post: Comporbar si se come una ficha aqui o no
		
		for (Tupla t: this.destinos) {
			
			if (t.getF() == f && t.getC() == c) {
				return t.come();
			}
		}
		
		// LA EJECUCIÓN NO DEBERÍA LLEGAR AQUÍ
		
		return false;
		
	}
	
	

}
