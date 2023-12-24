package juegoOnline.cliente.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import juegoBase.controlador.ControladorDeBotonDeFinDeJuego;
import juegoOnline.cliente.controlador.ControladorDeBoton;

@SuppressWarnings("serial")
public class Resultado extends JFrame{
	

	private static Resultado r;
	private JPanel panelPrincipal;
	
	private Resultado(int pResult) {
		
		
		// Dieseño del panel basado en este -> https://kickly.net/wp-content/uploads/2022/02/Soccer-Match-Result-Editable-Template-S.png
		
        // Configurar la ventana
        super.setTitle("Resultado");
        super.setSize(500, 500);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setLocationRelativeTo(null);

		this.panelPrincipal = new JPanel();
		this.panelPrincipal.setLayout(new BorderLayout());
		this.panelPrincipal.setBounds(0, 0, 500, 100);
        
        // Crear y agregar componentes
        String texto = "";
        String lab1 = "";
        String lab2= "";
        
        if (pResult == 0) {
			texto = "La partida ha acabado en empate...";
        	lab1 = "Tie";
        	lab2 = "Tie";		
        } else if (pResult == 1) {
        	
        	texto = "¡Han ganado las fichas blancas!";
        	lab1 = "Win";
        	lab2 = "Loss";
        } else {
        	
        	texto = "¡Han ganado las fichas negras!";
        	lab1 = "Loss";
        	lab2 = "Win";
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
        etiqueta.setIcon(new ImageIcon( "."+File.separator+"assets"+File.separator+"piezaspng"+File.separator+"wk.png"));
        panelPuntuacion.add(etiqueta);
        
        // Poner el "VS"
        
        etiqueta = new JLabel("VS",SwingConstants.CENTER);
        etiqueta.setFont(new Font("Serif", Font.BOLD, 40));
        panelPuntuacion.add(etiqueta);
        
        // Añadir la imagen de la ficha negra
        
        etiqueta = new JLabel("",SwingConstants.CENTER);
        etiqueta.setIcon(new ImageIcon( "."+File.separator+"assets"+File.separator+"piezaspng"+File.separator+"bk.png"));
        panelPuntuacion.add(etiqueta);
        
        // Añadir contador wins blanco
        
        
        etiqueta = new JLabel(lab1,SwingConstants.CENTER);
        etiqueta.setFont(new Font("Serif", Font.BOLD, 40));
        panelPuntuacion.add(etiqueta);
        
        
        
        // Añadir el "-"
        
        
        etiqueta = new JLabel("-",SwingConstants.CENTER);
        etiqueta.setFont(new Font("Serif", Font.BOLD, 40));
        panelPuntuacion.add(etiqueta);
        
        // Añadir contador wins negro
        
        
        etiqueta = new JLabel(lab2,SwingConstants.CENTER);
        etiqueta.setFont(new Font("Serif", Font.BOLD, 40));
        panelPuntuacion.add(etiqueta);
        
        this.panelPrincipal.add(panelPuntuacion, BorderLayout.CENTER);
        
        
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(1,1,0,0));

        JButton boton = new JButton("Salir");
        boton.addActionListener(new ControladorDeBoton(2));
        panelBotones.add(boton);
        
        this.panelPrincipal.add(panelBotones, BorderLayout.SOUTH);
        
        
        super.setContentPane(this.panelPrincipal);
        super.setVisible(true);
    }
	

	
	public static Resultado crearVentanaResultados(int pResultado) {
		Resultado.r = new Resultado(pResultado);
		
		return r;
	}

	public static void borrarVentanaResultados() {r.dispose();}

}