package juegoOnline.cliente.controlador;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import juegoBase.modelo.Movimiento;
import juegoBase.modelo.Tupla;
import juegoOnline.cliente.modelo.Cliente;
import juegoBase.vista.Casilla;
import juegoOnline.cliente.vista.Pantalla;
import juegoOnline.server.controlador.MovimientosPosibleDePieza;

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
		Cliente cli = Cliente.getCliente();



		
		
		// No es nuestro turno, skippear input
		
		if (!cli.miTurno()) {return;}
		
		// Es nuestro turno, procesar input
		
		int f = this.laCasilla.getFila();
		int c = this.laCasilla.getCol();
		int num = p.procesarClick(f, c);
		
		
		if (num == 1) {
			
			// Ver si el server nos ha dicho que la pieza elegida se puede mover
			
			MovimientosPosibleDePieza piezaAMover = cli.inicioLegal(f, c);
			
			if (piezaAMover != null) { 
				
				// Se puede mover, marcar las casillas que el server nos deja mover a

				ArrayList<Tupla> casillasPosibles = piezaAMover.getDestinos();
				p.marcarComoCasillaActual(f, c);
				
				for (Tupla t: casillasPosibles) {
					
					if (t.come()) {
						p.marcarComoEspacioADondeComer(t.getF(), t.getC());		
				
					} else {
						p.marcarComoEspacioAMover(t.getF(), t.getC());
					}
				}
				
				
			} else {
				
				// No se puede mover una pieza en esa casilla, hacer rollback
				
				p.eliminarClick(1);
				p.desmarcarTodo();
			}
			
		} else {
			
			// Ya se ha elegido una pieza valida, ver si podemos moverla al click donde hemos hecho
			
			boolean val = false;
			int i = 0;
			int f1 = p.primerClickFila();
			int c1 = p.primerClickCol();
			Tupla t = null;

			
			// Comparar nuestro click con los movimientos legales que nos ha dado el server de esa pieza
			
			ArrayList<Tupla> destinos = cli.inicioLegal(f1, c1).getDestinos();

			while (!val && i < destinos.size()) {
				t = destinos.get(i);
				val = t.getF() == f && t.getC() == c;
				i++;	
			}
			
			
			if (val) {
				

				
				// Informar al server del movimiento a realizar por la red
				
				try {
					cli.informarAServerDeMove(new Movimiento(f1, c1, t));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				p.eliminarClick(2);
				p.eliminarClick(1);
				
		//		p.desmarcarTodo(); -> No se necesita, se ejecuta después al actualizar los movimientos de las piezas
				

			} else {
				
				// No se posible el move, hacer rollback

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