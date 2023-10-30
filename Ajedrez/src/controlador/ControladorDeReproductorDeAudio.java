package controlador;

import vista.ReproductorDeAudio;

public class ControladorDeReproductorDeAudio {
	
	ReproductorDeAudio r;
	
	
	public ControladorDeReproductorDeAudio() {
		this.r = ReproductorDeAudio.getRep();
		
	}
	
	
	public void reproducirAudio(String nombreAudio) {
		this.r.reproducirSFX(nombreAudio);
	}

}
