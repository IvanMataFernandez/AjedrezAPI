package menuPrincipal.controlador;

import menuPrincipal.vista.MenuPrincipal;

public class ControladorDeMenu {

	
	private static ControladorDeMenu c;
	
	
	private ControladorDeMenu () {}
	
	public static ControladorDeMenu getControlador() {
		if (ControladorDeMenu.c == null) {
			ControladorDeMenu.c = new ControladorDeMenu();
		}
		return ControladorDeMenu.c;
	}
	
	
	public void crearMenuPrincipal ( ) {MenuPrincipal.getMenu().crearPantalla();}
	
	public void cerrarMenuPrincipal () {MenuPrincipal.getMenu().cerrarVentana();}
	
}
