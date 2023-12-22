package juegoBase.modelo;

import juegoBase.controlador.ControladorDeReproductorDeAudio;
import juegoBase.controlador.Dibujador;
import juegoBase.modelo.IA.JugadorIA;
import juegoBase.vista.Pantalla;
import menuPrincipal.controlador.ControladorDeMenu;


public class MainLoop {
	
	private boolean esperandoMovimiento;
	
	private int respuestaTrasFinDeJuego; // -1 -> En espera | 0 -> Salir | 1 -> Jugar de nuevo
	private static MainLoop j;

	
	private boolean turnoDePlayer;
	
	public static final int VICTORIA_BLANCO = 1;
	public static final int VICTORIA_NEGRO = 2;
	public static final int EMPATE = 0;
	
	private MainLoop() {
		// Constructora privada MAE
		
	}
	
	
	public static MainLoop getMainLoop() {
		if (MainLoop.j == null) {
			MainLoop.j = new MainLoop();
		}
		return MainLoop.j;
	
	}	
	public void setSeMovio() {this.esperandoMovimiento = false;}
	
	public void setRespuestaTrasJuego(int valor) {
		
		// Pre:  0 -> Salir | 1 -> Jugar de nuevo
		
		this.respuestaTrasFinDeJuego = valor;
	}
	
	public void iniciarPrograma(boolean pJugadorVsJugador) {

		Dibujador dib = Dibujador.getDibujador();


		ListaJugadores l = ListaJugadores.getListaJugadores();
		ControladorDeReproductorDeAudio r = new ControladorDeReproductorDeAudio();
		

		
		l.inicializarPlayers(pJugadorVsJugador); 

		
		l.resetearVictorias();


		boolean jugar = true;

		
		while (jugar) {
			
			// Inicializar la interfaz del tablero
			
			dib.inicializarPantalla();

			// Jugar
			
			int resultado = this.jugar();
			
			
			// Quitar la interfaz del tablero
			
			dib.matarPantalla();
			
			// Sumar victoria a quien ganó (o nada si fue empate) y reiniciar las piezas de cada jugador
			
			l.procesarResultado(resultado);
			r.reproducirAudio("fin_partida");
			
			// Crear la interfaz del menú de resultados de partida y esperar a input de botón
			
			
			
			this.respuestaTrasFinDeJuego = -1;
			dib.inicializarResultado(resultado, l.obtenerVictoriasDe(true), l.obtenerVictoriasDe(false));
			
			
			while (this.respuestaTrasFinDeJuego == -1) {
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {}
			}
			
			jugar = this.respuestaTrasFinDeJuego == 1;
			
			
		} 
		
		// Fin de las partidas
		
	
		
	}
	
	private int jugar() {
		
		// Post: 0 -> Empate | 1 -> Blanco | 2 -> Negro
		
		
		ListaJugadores l = ListaJugadores.getListaJugadores();
		l.reiniciarPunteroDeJugadores();
		Jugador j = l.obtenerJugadorActual();
		Matriz tab = Matriz.getMatriz();

		
		tab.inicializarTablero();
		tab.imprimirTablero();


		while (j.recalcularMovimientosLegales(tab.getTablero())) {
			
			if (j.esIA()) {
				this.turnoDePlayer = false;
				// Dejar que la IA haga el move
				((JugadorIA)j).realizarJugada();
				
			} else {
				this.turnoDePlayer = true;
				
				// Dejar que el humano haga el move
				
				this.esperandoMovimiento = true;
				
				// Esperar a que se mueva una ficha
				
				
				while (this.esperandoMovimiento) {
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {}
				}	
			}
		

			
		
			
			
			l.cambiarJugador();
			j = l.obtenerJugadorActual();
			tab.imprimirTablero();
	
		}
		
		
		
		if (j.reyEnJaque()) {
			
			
			if (j.esBlanco()) {
				return MainLoop.VICTORIA_NEGRO ;
			
			} else {
				return MainLoop.VICTORIA_BLANCO;
			}
			
		} else {
			
			return MainLoop.EMPATE;
			
		}
		
		
		
		
		
		
		
		
		
		
		
	}
	
	public boolean turnoDePlayer() {
		return this.turnoDePlayer;
	}
	
}
