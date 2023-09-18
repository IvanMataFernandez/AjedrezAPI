package modelo;

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
	
	
	public void cambiarJugador() {
		this.jugadorActual =+ 1;
	}
	
	public Jugador obtenerJugadorActual () {
		return this.jugadores[this.jugadorActual%2];
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
