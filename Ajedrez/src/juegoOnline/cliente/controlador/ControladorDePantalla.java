package juegoOnline.cliente.controlador;

import java.util.ArrayList;

import juegoBase.controlador.ComandoAInterfazAñadirPieza;
import juegoBase.controlador.ComandoAInterfazBorrarPieza;
import juegoBase.controlador.ComandoAInterfazMoverPieza;
import juegoBase.controlador.ControladorDePromociones;
import juegoOnline.cliente.vista.Pantalla;
import juegoBase.vista.ReproductorDeAudio;

public class ControladorDePantalla {
	
	
	
	private static ControladorDePantalla c;
	
	
	private ControladorDePantalla() {}
	
	public static ControladorDePantalla getControladorDePantalla() {
		if (ControladorDePantalla.c == null) {
			ControladorDePantalla.c = new ControladorDePantalla();
		}
		
		return ControladorDePantalla.c;
	}
	
	
	public void inicilizarPantalla(boolean pBlanco) {
		Pantalla.getPantalla().inicializarPantalla(pBlanco);
	}
	
	public void procesarInstruccionesAPantalla(ArrayList<ComandoAInterfazBorrarPieza> comandos) {
		
		/* Post: Se procesan las instrucciones sobre que casillas se deben editar y se
		       ven reflejadas en la pantalla. Estos cambios son puramente visuales.
		
	       Nota: Funcionalidades aceptadas:
	       - Borrar (f, c) -> Si hay una pieza en (f, c), se elimina
	       - Mover (f1, c1, f, c) -> Mover la pieza en (f1, c1) a (f, c). Si 
	         había una pieza en (f, c), es eliminada automáticamente
	      
	       - Añadir (f, c, tipo, equipo) -> Poner una pieza de un tipo y equipo concreto
	         en (f, c). Se elimina la pieza que estaba previamente en (f, c)
	         
	         
	         Nota2: Añadir no está implementado todavía
		
		
		*/
		
		Pantalla p = Pantalla.getPantalla();


		for (int i = 0; i != comandos.size(); i++) {
			ComandoAInterfazBorrarPieza com = comandos.get(i);
			
			// Nota, Borrar hereda a las demas, por lo que NO se debe
			// mirar si com instanceof la clase de borrar pq siempre es true,
			// en su lugar, mirar si es además hija de ella mirando para ello la
			// clase concreta
			
			
			if (com instanceof ComandoAInterfazMoverPieza) {
				ComandoAInterfazMoverPieza com2 = (ComandoAInterfazMoverPieza) com;
				p.moverPieza(com2.getF1(), com2.getC1(), com2.getF(), com2.getC());


				
				
			} else if (com instanceof ComandoAInterfazAñadirPieza) {	
				ComandoAInterfazAñadirPieza com2 = (ComandoAInterfazAñadirPieza) com;
				p.ponerPieza(com2.getF(), com2.getC(), com2.getTipo(), com2.esBlanco());
		    	
				// Manejar promociones de peon
				
			}  else {

				p.quitarPieza(com.getF(), com.getC());
				
			}
			
		}
		ReproductorDeAudio.getRep().reproducirSFX("movimiento");
		p.repaint();

	}
	
	
	public int procesarVentanaPromociones() {
		// Crear nueva UI y esperar al input del usuario
		
		ControladorDePromociones con = new ControladorDePromociones();
		return con.elegirPromocion();
		
		
		
	}
	
	public void cerrarPantalla() {
		Pantalla.getPantalla().dispose();
	}
	
}