package controlador;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import modelo.Juego;
import modelo.ListaJugadores;
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
		ListaJugadores l = ListaJugadores.getListaJugadores();
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
				// Programar Movimiento AQUI
				p.desmarcarTodo();
				p.eliminarClick(2);
				p.eliminarClick(1);

				
			} else {

				p.eliminarClick(2);
				p.eliminarClick(1);

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
