package menuPrincipal.modelo;

import juegoBase.modelo.MainLoop;
import juegoOnline.cliente.modelo.Cliente;
import menuPrincipal.controlador.ControladorDeMenu;
import menuPrincipal.vista.MenuPrincipal;

public class MainLoopMenuPrincipal {
	
	private static MainLoopMenuPrincipal m;
	private int opcionElegida;

	
	private MainLoopMenuPrincipal () {
		this.opcionElegida = 0;
	}
	
	public static MainLoopMenuPrincipal getMainLoop() {
		if (MainLoopMenuPrincipal.m == null) {
			MainLoopMenuPrincipal.m = new MainLoopMenuPrincipal();
		}
		
		return MainLoopMenuPrincipal.m;
	}
	
	
	public void ejecutarMainLoop()  {
		this.opcionElegida = 0;
		
		
		while (this.opcionElegida != 4) {
			this.esperarMs(500);
			
			switch (this.opcionElegida) {
			
			case 1:
				// Jugar de forma local con otro player
				
				MenuPrincipal.getMenu().cerrarVentana();
				MainLoop.getMainLoop().iniciarPrograma();
				this.opcionElegida = 0;
				ControladorDeMenu.getControlador().crearMenuPrincipal();

				break;
			case 2:
				// Menu: Jugar de forma online con otro player
				
				MenuPrincipal.getMenu().cerrarVentana();
				Cliente.getCliente().ejecutarCliente();
				this.opcionElegida = 0;
				ControladorDeMenu.getControlador().crearMenuPrincipal();
				
				break;
			case 3:
				// TODO: Menu: Jugar contra bot
				this.opcionElegida = 0;
				break;
				
			case 4:
				// Salir

				MenuPrincipal.getMenu().cerrarVentana();
				this.opcionElegida = 0;

				
			
			}		
			

		}
	}
	
	public void setOpcion (int opt) {
		this.opcionElegida = opt;
	}
	
	
	private void esperarMs(int ms) {
		
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {}


	}

}
