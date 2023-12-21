package juegoBase.vista;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

// Clase encargada de hacer sonar audio en el programa

public class ReproductorDeAudio {
	private static ReproductorDeAudio r;
	private Clip clip;
	
	
	private ReproductorDeAudio() {
		// MAE con cons privada
	}
	
	public static ReproductorDeAudio getRep() {
		if (ReproductorDeAudio.r == null) {
			ReproductorDeAudio.r = new ReproductorDeAudio();
		}
		return ReproductorDeAudio.r;
	}
	
	public void reproducirSFX(String pFile) {
		// Pre: Nombre de archivo .wav existente (sin la extension puesta) en el path assets/audio/(archivo).wav
		//      para ser ejecutado
		try {
			AudioInputStream audio = AudioSystem.getAudioInputStream(new File("."+File.separator+"assets"+File.separator+"audio"+File.separator+""+pFile+".wav"));
			this.clip = AudioSystem.getClip();
			this.clip.open(audio);
			this.clip.start();
		} catch (Exception e) {
			System.out.println("Debug: Error inesperado, arreglar. Causa:");
			e.printStackTrace();
		}
		
		
		
	} 

}
