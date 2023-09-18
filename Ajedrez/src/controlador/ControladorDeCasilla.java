package controlador;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import modelo.Juego;
import modelo.Tupla;
import vista.*;

public class ControladorDeCasilla implements MouseListener {

	private Casilla laCasilla;
	
	
	public ControladorDeCasilla(Casilla cas) {this.laCasilla = cas;}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		/* Preguntar a la casilla si es el primer o segundo click
		   Pasar consulta al motor de juego, lògica:
		
		If 1st click:
			If it's your own piece -> show stored ranges, color respective tiles
			Else -> Tell UI to remove 1st click, reset ALL colored tiles
		Else:	
			If it's within the ranges -> Move, reset ALL colored tiles
			Else -> Tell UI to remove 2nd click, reset ALL colored tiles
		
		
		
		*/
		
		Pantalla p = Pantalla.getPantalla();
		Juego j = Juego.getJuego();
		int f = this.laCasilla.getFila();
		int c = this.laCasilla.getCol();
		
		int num = p.procesarClick(f, c);
		
		if (num == 1) {
			
			if (true) { // LLAMADA AL MOTOR DEL JUEGO PARA PREGUNTAR POR PLAYER AQUI
					    // POR AHORA SIMULAMOS QUE ES EL TURNO DE LOS DOS SIEMPRE
				
				ArrayList<Tupla> casillasPosibles = j.obtenerMovimientosLegalesDe(f, c);
				// MARCAR LA CASILLA CLICKADA EN SI
				for (Tupla t: casillasPosibles) {
					// COMPLETAR, MARCAR LAS CASILLAS EN LAS QUE SE PUEDE HACER EL MOV
				}
				
				
			} else {
				p.eliminarClick(num);
				p.desmarcarTodo();
			}
			
		} else {
			
			if (true) {
				// Programar Movimiento AQUI
				p.desmarcarTodo();
				
			} else {
				p.eliminarClick(num);
				p.desmarcarTodo();
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
