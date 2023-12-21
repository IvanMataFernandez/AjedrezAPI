
package juegoBase.modelo;
import java.util.ArrayList;
import java.util.HashMap;

public class Jugador {

	private boolean blanco;
	private int victorias;
	private ArrayList<Pieza> susPiezas;
	private Rey rey;
	
	public Jugador (boolean pBlanco) {
		this.blanco = pBlanco;
		this.victorias = 0;
		this.susPiezas = new ArrayList<Pieza>();
	}
	
	public boolean esBlanco() {
		return this.blanco;
	}
	
	public void añadirPieza(Pieza pPieza) {
		// Pre: una pieza existente, es del mismo equipo que el jugador
		// Post: Darle una pieza a su equipo
		// Cada vez que una pieza se genera por constructora se añade a su jugador concreto aquí
		
		if (pPieza.tipo() == 5) {
			this.rey = (Rey) pPieza;
		}
		
		this.susPiezas.add(pPieza);
		
	}
	
	public void eliminarTodasSusPiezas() {
		this.susPiezas.clear();
	}
	
	
	public void eliminarPieza(Pieza pPieza) {
		// Pre: Una pieza existente de este jugador
		// Post: Quitarle la pieza
		this.susPiezas.remove(pPieza);
	}
	
	
	public boolean  recalcularMovimientosLegales(Pieza[][] pMatriz) {
		// Post: Cada pieza actualiza en su AL sus movimientos legales, return true si al menos una puede mover
		
		
		// Al principio del turno se ejecuta esto para que cada pieza 
		// actualice en su AL sus movimientos posibles del turno
		
		boolean puedeMover = false;
		for (Pieza pieza : susPiezas) {
			puedeMover = pieza.recalcularMovimientosLegales(pMatriz) || puedeMover;
		}
		
		
		return puedeMover;
		
		
	}
	
	public boolean reyEnJaque() {
		return this.rey.enRangoDelRival();
	}
	
	public void sumarUnaVictoria() {
		this.victorias = this.victorias + 1;
	}
	
	public int getVictorias() {
		return this.victorias;
	}
	
	public void reiniciarVictorias() {this.victorias = 0;}
	
	
	
	
	public HashMap<Pieza, ArrayList<Tupla>> getPosiblesMovientos() {
		
		// Post: {Pieza: {mov1, mov2, ..., movN}, Pieza2: {...}, ...}
		
		HashMap<Pieza, ArrayList<Tupla>> h = new HashMap<Pieza, ArrayList<Tupla>> ();
		
		for (Pieza p: this.susPiezas) {
			h.put(p, p.obtenerMovimientosLegales());
			
		}
		
		return h;
		
	}
	

	
}
