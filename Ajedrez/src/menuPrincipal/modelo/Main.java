package menuPrincipal.modelo;

import menuPrincipal.controlador.ControladorDeMenu;

public class Main {

	
	public static void main (String[] args) {

		ControladorDeMenu.getControlador().crearMenuPrincipal();
		MainLoopMenuPrincipal.getMainLoop().ejecutarMainLoop();
	}
	
}
