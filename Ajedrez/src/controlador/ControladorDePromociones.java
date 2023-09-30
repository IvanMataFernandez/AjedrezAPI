package controlador;

import vista.PromocionPeonDialog;

public class ControladorDePromociones {

	private int promocionElegida; 
	
	public ControladorDePromociones() {}
	
	
	public int elegirPromocion() {
		
		this.promocionElegida = -1;
    	PromocionPeonDialog dialog = new PromocionPeonDialog(null, true, this);
    	
    	while (this.promocionElegida == -1) {
    		
    		// Esperar a que se clicke un boton
    		
    		try {
    			Thread.sleep(20);
    		} catch (InterruptedException e) {}	
    		
    	}
    	
    	return this.promocionElegida;
	}
	
	
    	public void promocionElegida(int pieza) {this.promocionElegida = pieza;}

}
