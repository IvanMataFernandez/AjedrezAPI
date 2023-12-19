package juegoBase.modelo;

public class ListaJugadores {

	private static ListaJugadores l;

	private Jugador[] jugadores; // 0 blanco, 1 negro
	private int jugadorActual;
	
	private ListaJugadores() {
		this.jugadores = new Jugador[2];
		this.jugadores[0] = new Jugador(true);
		this.jugadores[1] = new Jugador(false);
		this.jugadorActual = 0;
	}
	
	
	private void eliminarPiezasDeLosJugadores() {
		
		for (Jugador j: this.jugadores) {
			j.eliminarTodasSusPiezas();
		}
	}
	
	public void procesarResultado(int resultado) {
		/*
			Pre: 0 -> Empate
			     1 -> Blanco gana
			     2 -> Negro gana
		*/
		
		this.eliminarPiezasDeLosJugadores(); // Reiniciar la lista de piezas del juegador
		
		if (resultado != 0) {
			this.jugadores[resultado-1].sumarUnaVictoria();
		}
	}
	
	public int obtenerVictoriasDe(boolean pBlanco) {
		if (pBlanco) {
			return this.jugadores[0].getVictorias();
		} else {
			return this.jugadores[1].getVictorias();
		}
	}
	
	public void reiniciarPunteroDeJugadores() {
		this.jugadorActual = 0;
	}
	
	
	public void cambiarJugador() {
		this.jugadorActual = this.jugadorActual + 1;

	}
	
	public Jugador obtenerJugadorActual () {
		return this.jugadores[this.jugadorActual%2];
	}
	
	public boolean esTurnoDeBlanco() {
		return this.jugadorActual%2==0;
	}
	
	public Jugador obtenerJugador(boolean pBando) {
		if (pBando) {
			return this.jugadores[0];
		} else {
			return this.jugadores[1];		}
		
	}
	
	public static ListaJugadores getListaJugadores() {
		if (ListaJugadores.l == null) {
			ListaJugadores.l = new ListaJugadores();
		}
		return ListaJugadores.l;
	}
	
	
}
