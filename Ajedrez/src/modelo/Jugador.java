
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
		this.susPiezas.add(pPieza);
		
	}
	
	public void eliminarPieza(Pieza pPieza) {
		this.susPiezas.remove(pPieza);
	}
	
	
	public boolean  recalcularMovimientosLegales() {
		// Post: Movimientos almacenados en cada pieza, true si al menos una puede mover
		// Al principio del turno ejecutar esto para que cada pieza actualice en su AL sus movimientos posibles del turno
		boolean puedeMover = false;
		for (Pieza pieza : susPiezas) {
			puedeMover = pieza.recalcularMovimientosLegales() || puedeMover;
		}
		
		return puedeMover;
		
		
	}
	
}
