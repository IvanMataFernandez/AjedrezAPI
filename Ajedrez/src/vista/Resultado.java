package vista;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;

import controlador.ControladorDeBotonDeFinDeJuego;


@SuppressWarnings("serial")
public class Resultado extends JFrame{
	
	
	// TODO: La ventana para mostrar victoria
	//       mostrar cantidad de victorias y dar opcion de revancha
	
	private static Resultado r;
	private JPanel panelPrincipal;
	
	private Resultado(int pResult, int numVictoriasBlanco, int numVictoriasNegro) {
		
		
		// Dieseño del panel basado en este -> https://kickly.net/wp-content/uploads/2022/02/Soccer-Match-Result-Editable-Template-S.png
		
        // Configurar la ventana
        super.setTitle("Resultado");
        super.setSize(500, 500);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  //      super.setResizable(false);
        super.setLocationRelativeTo(null);

		this.panelPrincipal = new JPanel();
		this.panelPrincipal.setLayout(new BorderLayout());
		this.panelPrincipal.setBounds(0, 0, 500, 100);
        
        // Crear y agregar componentes
        String texto = "";
        
        if (pResult == 0) {
			texto = "La partida ha acabado en empate...";
			
        } else if (pResult == 1) {
        	
        	texto = "¡Han ganado las fichas blancas!";
        } else {
        	
        	texto = "¡Han ganado las fichas negras!";
        }
        
        JLabel etiqueta = new JLabel(texto, SwingConstants.CENTER);
        etiqueta.setFont(new Font("Serif", Font.BOLD, 32));
        etiqueta.setBackground(Color.gray);
        etiqueta.setOpaque(true);

        this.panelPrincipal.add(etiqueta, BorderLayout.NORTH);
        
        JPanel panelPuntuacion = new JPanel();
        panelPuntuacion.setLayout(new GridLayout(2,3,0,0));
        
        
        // Añadir la imagen de la ficha blanca
        
        
        etiqueta = new JLabel("",SwingConstants.CENTER);
        etiqueta.setIcon(new ImageIcon(".\\piezaspng\\reyb.png"));
        panelPuntuacion.add(etiqueta);
        
        // Poner el "VS"
        
        etiqueta = new JLabel("VS",SwingConstants.CENTER);
        etiqueta.setFont(new Font("Serif", Font.BOLD, 40));
        panelPuntuacion.add(etiqueta);
        
        // Añadir la imagen de la ficha negra
        
        etiqueta = new JLabel("",SwingConstants.CENTER);
        etiqueta.setIcon(new ImageIcon(".\\piezaspng\\reyd.png"));
        panelPuntuacion.add(etiqueta);
        
        // Añadir contador wins blanco
        
        
        etiqueta = new JLabel(Integer.toString(numVictoriasBlanco),SwingConstants.CENTER);
        etiqueta.setFont(new Font("Serif", Font.BOLD, 40));
        panelPuntuacion.add(etiqueta);
        
        
        
        // Añadir el "-"
        
        
        etiqueta = new JLabel("-",SwingConstants.CENTER);
        etiqueta.setFont(new Font("Serif", Font.BOLD, 40));
        panelPuntuacion.add(etiqueta);
        
        // Añadir contador wins negro
        
        
        etiqueta = new JLabel(Integer.toString(numVictoriasNegro),SwingConstants.CENTER);
        etiqueta.setFont(new Font("Serif", Font.BOLD, 40));
        panelPuntuacion.add(etiqueta);
        
        this.panelPrincipal.add(panelPuntuacion, BorderLayout.CENTER);
        
        
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(1,2,0,0));
        JButton boton = new JButton("Volver a jugar");
        boton.addActionListener(new ControladorDeBotonDeFinDeJuego(1, this));
        panelBotones.add(boton);
        boton = new JButton("Salir");
        boton.addActionListener(new ControladorDeBotonDeFinDeJuego(0, this));
        panelBotones.add(boton);
        
        this.panelPrincipal.add(panelBotones, BorderLayout.SOUTH);
        
        
        super.setContentPane(this.panelPrincipal);
        super.setVisible(true);
    }
	
	public static Resultado crearVentanaResultados(int pResultado, int vicB, int vicN) {
		Resultado.r = new Resultado(pResultado, vicB, vicN);
		
		return r;
	}


}
