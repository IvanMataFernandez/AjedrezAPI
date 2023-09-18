
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
	
	
	public void recalcularMovimientosLegales() {
		// Al principio del turno ejecutar esto para que cada pieza actualice en su AL sus movimientos posibles del turno
		
		for (Pieza pieza : susPiezas) {
			pieza.recalcularMovimientosLegales();
		}
		
		
	}
	
}
