package vista;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.*;


public class Resultado extends JFrame{
	
	
	// TODO: La ventana para mostrar victoria
	//       mostrar cantidad de victorias y dar opcion de revancha
	
	private static Resultado r;
	private JPanel panelPrincipal;
	
	private Resultado(int pResult) {
        // Configurar la ventana
        super.setTitle("Resultado");
        super.setSize(500, 500);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setResizable(false);
        
		this.panelPrincipal = new JPanel();
		this.panelPrincipal.setLayout(new BorderLayout());
		this.panelPrincipal.setBounds(0, 0, 500, 500);
        
        // Crear y agregar componentes
        String texto = "";
        
        if (pResult == 0) {
			texto = "La partida ha acabado en empate";
			
        } else if (pResult == 1) {
        	
        	texto = "Han ganado las fichas blancas";
        } else {
        	
        	texto = "Han ganado las fichas negras";
        }
        
        JLabel etiqueta = new JLabel(texto, SwingConstants.CENTER);
        etiqueta.setFont(new Font("Serif", Font.BOLD, 32));
        etiqueta.setBackground(Color.gray);
        etiqueta.setOpaque(true);

        this.panelPrincipal.add(etiqueta, BorderLayout.NORTH);
        
        super.setContentPane(this.panelPrincipal);
        super.setVisible(true);
    }
	
	public static Resultado getResultado(int pResultado) {
		if (Resultado.r == null) {
			Resultado.r = new Resultado(pResultado);
		}
		return r;
	}


}
