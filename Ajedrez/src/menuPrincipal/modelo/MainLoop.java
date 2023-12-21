package menuPrincipal.modelo;

import menuPrincipal.vista.MenuPrincipal;

public class MainLoop {
	
	private static MainLoop m;
	private int opcionElegida;

	
	private MainLoop () {
		this.opcionElegida = 0;
	}
	
	public static MainLoop getMainLoop() {
		if (MainLoop.m == null) {
			MainLoop.m = new MainLoop();
		}
		
		return MainLoop.m;
	}
	
	
	public void ejecutarMainLoop()  {
		this.opcionElegida = 0;
		
		
		while (this.opcionElegida != 4) {
			this.esperarMs(500);
			
			switch (this.opcionElegida) {
			
			case 1:
				// Jugar de forma local con otro player
				
				MenuPrincipal.getMenu().cerrarVentana();
				MenuPrincipal.getMenu().jugarVsJugador();
				this.opcionElegida = 0;


				break;
			case 2:
				// TODO: Menu: Jugar de forma online con otro player
				
				
				
				this.opcionElegida = 0;
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
