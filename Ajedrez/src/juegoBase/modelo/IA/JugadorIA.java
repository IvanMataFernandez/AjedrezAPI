package juegoBase.modelo.IA;

import java.util.ArrayList;

import juegoBase.controlador.ComandoAInterfazAscension;
import juegoBase.controlador.ComandoAInterfazAñadirPieza;
import juegoBase.controlador.ComandoAInterfazBorrarPieza;
import juegoBase.controlador.ComandoAInterfazMoverPieza;
import juegoBase.controlador.ControladorDeReproductorDeAudio;
import juegoBase.modelo.Jugador;
import juegoBase.modelo.Matriz;
import juegoBase.modelo.Movimiento;
import juegoBase.modelo.Pieza;
import juegoBase.modelo.Tupla;
import juegoBase.vista.Pantalla;

public abstract class JugadorIA extends Jugador {
	
	
	// TODO: Crear más IAs distintas, debuggear code
	
	/*
	  Debuggear si la clase MovimientoIA funciona correctamente. MovimientoIA debería ser capaz
	  de actualizar el tablero real y deshacer movimientos ya confirmados de manera segura siempre
	  que se deshagan en orden inverso al que se ejecutaron.
	  
	  Usa el método realizarMovimiento() para actualizar inmediatamente los cambios en la matriz
	  y en las listas de piezas de players.

	 */

	public JugadorIA(boolean pBlanco) {
		super(pBlanco);
	}
	
	private void procesarInstruccionesAPantalla(ArrayList<ComandoAInterfazBorrarPieza> comandos) {
		
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
				
			}  else if (com instanceof ComandoAInterfazAscension) {
	
				
				        Matriz j = Matriz.getMatriz();

				        // IA siempre asciende a Reina
				        
						int tipo = 4;
						
						// Actualizar la instrucción para mostrar en interfaz la pieza correcta
						
						ComandoAInterfazAscension com2 = (ComandoAInterfazAscension) com;
						com2.setTipo(tipo);
						
						// Actualizar la pieza
						
						j.eliminarPieza(com2.getF(), com2.getC());
						j.añadirPieza(com2.getF(), com2.getC(), com2.esBlanco(), com2.getTipo());
						
						// Queuear las instrucciones del cambio de pieza
						
						ComandoAInterfazAñadirPieza comando = new ComandoAInterfazAñadirPieza(com2.getF(),com2.getC(), com2.esBlanco());
						comando.setTipo(com2.getTipo());
						comandos.add(comando);						
			 			
				
				
				
			} else {

				p.quitarPieza(com.getF(), com.getC());
				
			}
			
		}

	}
	
	protected void obtenerJugadas(Pieza[][] tablero) {
		
	}
	
	
	public void realizarJugada() {
		
		// Obtener todas las jugadas legales que se pueden realizar
		
		Pieza[][] tablero = Matriz.getMatriz().getTablero();
		ArrayList<Posicion> jugadas = new ArrayList<Posicion>();
		
		for (Pieza p : super.getPiezas()) {
			 ArrayList<Tupla> l = p.obtenerMovimientosLegales();
			 int c = p.getPosX();
			 int f = p.getPosY();
			 
			 for (Tupla t: l) {
				 jugadas.add(new Posicion(f,c,t.getF(),t.getC()));
			 }
			
		}
		
		Posicion pos = this.pensar(tablero, jugadas);
		
		// Mover la pieza
		
		Movimiento mov = Matriz.getMatriz().moverPieza(pos.getF1(), pos.getC1(), pos.getC1(), pos.getC2());

		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {}
		
		this.procesarInstruccionesAPantalla(mov.informarPantalla());
		Pantalla.getPantalla().desmarcarTodo();
		Pantalla.getPantalla().repaint();
		
		ControladorDeReproductorDeAudio reproductor = new ControladorDeReproductorDeAudio();
		reproductor.reproducirAudio("movimiento");
		
		
	}
	

	
	public abstract Posicion pensar(Pieza[][] tablero, ArrayList<Posicion> jugadas);

}
