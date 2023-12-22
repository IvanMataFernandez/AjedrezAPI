package juegoBase.modelo.IA;

import java.util.ArrayList;
import java.util.Random;

import juegoBase.modelo.Pieza;

public class JugadorIARandom extends JugadorIA {

	
	private Random rng;
	
	public JugadorIARandom(boolean pBlanco) {
		super(pBlanco);
		this.rng = new Random();
	}

	@Override
	public Posicion pensar(Pieza[][] tablero, ArrayList<Posicion> jugadas) {
		
		
		return jugadas.get(this.rng.nextInt(jugadas.size()));
	}

}
