package vista;
import javax.swing.*;


public class Resultado extends JFrame{
	
	
	private static Resultado r;
	private Resultado() {
        // Configurar la ventana
        setTitle("Resultado");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Crear y agregar componentes
        
        JLabel etiqueta = new JLabel("¡Hola, mundo!");
        if (pResult == Juego.EMPATE) {
			etiqueta = "La partida ha acabado en empate";
        }else if (pResult == Juego.VICTORIA_BLANCO) {
        	etiqueta = "Han ganado las fichas blancas";
        }else {
        	etiqueta = "Han ganado las fichas negras";
        }
        add(etiqueta);
    }
	
	public static Resultado getResultado() {
		if (Resultado.r == null) {
			Resultado.r = new Resultado();
		}
		return r;
	}
	
	

    public static void main(String[] args) {
        // Crear la interfaz y hacerla visible
        MiInterfaz interfaz = new MiInterfaz();
        interfaz.setVisible(true);
    }

}
