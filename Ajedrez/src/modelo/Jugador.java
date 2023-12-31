
package modelo;
import java.util.ArrayList;

public class Jugador {

	private boolean blanco;
	private int victorias;
	private ArrayList<Pieza> susPiezas;
	
	public Jugador (boolean pBlanco) {
		this.blanco = pBlanco;
		this.victorias = 0;
		this.susPiezas = new ArrayList<Pieza>();
	}
	
	public void añadirPieza(Pieza pPieza) {
		// Pre: una pieza existente, es del mismo equipo que el jugador
		// Post: Darle una pieza a su equipo
		// Cada vez que una pieza se genera por constructora se añade a su jugador concreto aquí
		
		this.susPiezas.add(pPieza);
		
	}
	
	public void eliminarPieza(Pieza pPieza) {
		// Pre: Una pieza existente de este jugador
		// Post: Quitarle la pieza
		this.susPiezas.remove(pPieza);
	}
	
	
	public boolean  recalcularMovimientosLegales() {
		// Post: Cada pieza actualiza en su AL sus movimientos legales, return true si al menos una puede mover
		
		
		// Al principio del turno se ejecuta esto para que cada pieza 
		// actualice en su AL sus movimientos posibles del turno
		
		boolean puedeMover = false;
		for (Pieza pieza : susPiezas) {
			puedeMover = pieza.recalcularMovimientosLegales() || puedeMover;
		}
		
		return puedeMover;
		
		
	}
	
}
