package juegoOnline.Controlador;

import java.io.Serializable;
import java.util.ArrayList;

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
	
	public void addDestino(int f2, int c2, boolean come) {
		this.destinos.add(new Tupla(f2, c2, come));
	}
	
	public boolean destinoValido(int f, int c) {
		
		// Pre: Se ha elegido esta casilla donde hay una pieza como el inicio del movimiento. Se quiere mover a (f,c)
		// Post: Verificar si realmente se puede mover a (f,c)
		
		for (Tupla t: this.destinos) {
			
			if (t.f2 == f && t.c2 == c) {
				return true;
			}
		}
		
		return false;
		
	}
	
	
	public boolean come (int f, int c) {
		
		// Pre: El movimiento de (f1, c1) a (f, c) es válido
		// Post: Comporbar si se come una ficha aqui o no
		
		for (Tupla t: this.destinos) {
			
			if (t.f2 == f && t.c2 == c) {
				return t.come;
			}
		}
		
		// LA EJECUCIÓN NO DEBERÍA LLEGAR AQUÍ
		
		return false;
		
	}
	
	
	private class Tupla implements Serializable {
		 int f2; // (f2, c2) -> Posición final
		 int c2;
		 boolean come; // -> Si resulta en una eliminación o no (solo para efecto visual)
		 
		public Tupla (int f, int c, boolean co) {
			f2 = f;
			c2 = c;
			come = co;
			
		}
		

		 
		 
		
	}

}
