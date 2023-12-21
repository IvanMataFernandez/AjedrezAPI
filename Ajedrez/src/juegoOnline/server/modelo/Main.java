package juegoOnline.server.modelo;

public class Main {
	
	public static void main(String args[]) throws InterruptedException {
		
		// Arranca la aplicación del servidor del servicio wireless desde aquí
		
		Servidor aplicacion = new Servidor();
		aplicacion.ejecutarServidor();
	}
}
