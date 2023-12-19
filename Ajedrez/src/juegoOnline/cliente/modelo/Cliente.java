package juegoOnline.cliente.modelo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import juegoBase.modelo.Movimiento;
import juegoBase.modelo.Tupla;

public class Cliente {

	private ObjectOutputStream salida;
	private ObjectInputStream entrada;
	private Socket cliente;
	
	private boolean esBlanco;
	
	public Cliente() {}
		
	
	
	public void ejecutarCliente() {
		System.out.println("Intentando Realizar Conexion Con Servidor");
		
		// Crear Socket para realizar la conexión con el servidor
		
		try {
			// Conectar con servidor...
			
			this.cliente = new Socket("127.0.0.1", 12345);
			
			// Recoger streams de entrada y salida
			
			this.salida = new ObjectOutputStream(cliente.getOutputStream());
			this.entrada = new ObjectInputStream(cliente.getInputStream());
			
			// limpiar stream de salida para inicializarlo
			
			this.salida.flush();
			
			// Recibir del servidor el número de jugador asignado
			
			this.esBlanco = this.entrada.readInt() == 1;


			System.out.println("SOY BLANCO -> "+this.esBlanco);


			
			// TODO: Game loop del cliente (server está + UI)


		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		
		
		
		
		
		
		
		

	}
	
}


