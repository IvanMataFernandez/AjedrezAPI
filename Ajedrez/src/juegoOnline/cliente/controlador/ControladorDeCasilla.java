package juegoOnline.cliente.controlador;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import juegoBase.modelo.Movimiento;
import juegoBase.modelo.Tupla;
import juegoOnline.Controlador.MovimientosPosibleDePieza;
import juegoOnline.cliente.modelo.Cliente;
import juegoBase.vista.Casilla;
import juegoOnline.cliente.vista.Pantalla;

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



		
		
		// No es nuestro turno
		
		if (!cli.miTurno()) {System.out.println("No"); return;}
		
		int f = this.laCasilla.getFila();
		int c = this.laCasilla.getCol();
		int num = p.procesarClick(f, c);
		
		// Es nuestro turno
		
		if (num == 1) {
			MovimientosPosibleDePieza piezaAMover = cli.inicioLegal(f, c);
			
			if (piezaAMover != null) { 

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
				p.eliminarClick(1);
				p.desmarcarTodo();
			}
			
		} else {
			
			boolean val = false;
			int i = 0;
			int f1 = p.primerClickFila();
			int c1 = p.primerClickCol();
			Tupla t = null;

			ArrayList<Tupla> destinos = cli.inicioLegal(f1, c1).getDestinos();

			while (!val && i < destinos.size()) {
				t = destinos.get(i);
				val = t.getF() == f && t.getC() == c;
				i++;	
			}
			
			
			if (val) {

				
				p.desmarcarTodo();
				p.eliminarClick(2);
				p.eliminarClick(1);
				
				// INFORMAR A SERVER DE MOVE
				
				try {
					cli.informarAServerDeMove(new Movimiento(f1, c1, t));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				

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