package juegoBase.controlador;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import juegoBase.modelo.Matriz;
import juegoBase.modelo.ListaJugadores;
import juegoBase.modelo.MainLoop;
import juegoBase.modelo.Movimiento;
import juegoBase.modelo.Tupla;
import juegoBase.vista.*;

public class ControladorDeCasilla implements MouseListener {

	private Casilla laCasilla;
	
	
	public ControladorDeCasilla(Casilla cas) {this.laCasilla = cas;}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		/* Preguntar a la casilla si es el primer o segundo click
		   Pasar consulta al motor de Matriz, lògica:
		
		If 1st click:
			If it's your own piece -> show stored ranges, color respective tiles
			Else -> Tell UI to remove 1st click, reset ALL colored tiles
		Else:	
			If it's within the ranges -> Move, reset ALL colored tiles
			Else -> Tell UI to remove 2nd click, reset ALL colored tiles
		
		
		
		*/
		
		Pantalla p = Pantalla.getPantalla();
		MainLoop m = MainLoop.getMainLoop();
		ListaJugadores l = ListaJugadores.getListaJugadores();
		Matriz j = Matriz.getMatriz();
		int f = this.laCasilla.getFila();
		int c = this.laCasilla.getCol();
		int num = p.procesarClick(f, c);

		
		if (num == 1) {
			
			if (j.hayPieza(f, c) && !(j.esBlanco(f, c) ^ l.esTurnoDeBlanco())) { 

				ArrayList<Tupla> casillasPosibles = j.obtenerMovimientosLegalesDe(f, c);
				p.marcarComoCasillaActual(f, c);
				
				for (Tupla t: casillasPosibles) {
					if (t.come()) {
						p.marcarComoEspacioADondeComer(t.getF(), t.getC());		
				
					} else {
						p.marcarComoEspacioAMover(t.getF(), t.getC());
					}
				}
				
				
			} else {
				p.eliminarClick(1);
				p.desmarcarTodo();
			}
			
		} else {
			
			boolean val = false;
			int i = 0;
			int f1 = p.primerClickFila();
			int c1 = p.primerClickCol();
			
			ArrayList<Tupla> casillasPosibles = j.obtenerMovimientosLegalesDe(f1, c1);

			while (!val && i < casillasPosibles.size()) {
				Tupla t = casillasPosibles.get(i);
				val = t.getF() == f && t.getC() == c;
				i++;	
			}
			
			
			if (val) {
				// Mover internamente en el Matriz
				
				Movimiento mov = j.moverPieza(f1, c1, f, c);
				
				// Mover las piezas necesarias en la interfaz
				this.procesarInstruccionesAPantalla(mov.informarPantalla());
				
				ControladorDeReproductorDeAudio reproductor = new ControladorDeReproductorDeAudio();
				reproductor.reproducirAudio("movimiento");
				
				p.desmarcarTodo();
				p.eliminarClick(2);
				p.eliminarClick(1);

				m.setSeMovio();
				
			} else {

				p.eliminarClick(2);
				p.eliminarClick(1);

				p.desmarcarTodo();
			}
			
		}
		
		
		
		
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

						// Crear nueva UI y esperar al input del usuario
						
						ControladorDePromociones con = new ControladorDePromociones();
						int tipo = con.elegirPromocion();
						
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
	


   
	

	@Override
	public void mousePressed(MouseEvent e) {
		// NO USADO
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// NO USADO
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// NO USADO
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// NO USADO
	}

}
