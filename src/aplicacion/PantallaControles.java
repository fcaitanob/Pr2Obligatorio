package aplicacion;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;

import fachada_logica.FachadaLogica;
import clases_generales.*;

public class PantallaControles extends JDialog {
    private static final long serialVersionUID = 1L;
    

    public PantallaControles(JFrame parent, FachadaLogica fl, int ciAdm, int ciAlu, LocalDate fi, LocalDate ff) {
    	super(parent, "Visualizar control", true); // true = modal
    	setSize(800, 400);
    	setLocationRelativeTo(parent);
    	setLayout(new BorderLayout(10,10));


    	// ----- Panel de datos -----
    	// GridLayout(3,2,5,5)
    	// n pares de 2 columnas, espacio horizontal entre columas de 5 y espacio vertical entre columnas de 5
    	JPanel panelDatos1 = new JPanel(new GridLayout(3, 2, 5, 5)); 
    	panelDatos1.setBorder(BorderFactory.createTitledBorder(""));
        
    	panelDatos1.add(new JLabel(" " ));
    	panelDatos1.add(new JLabel("Fecha del control: " + LocalDate.now()));
    	add(panelDatos1, BorderLayout.NORTH);

    	// ----- Panel de datos -----
    	// GridLayout(3,2,5,5)
    	// n pares de 2 columnas, espacio horizontal entre columas de 5 y espacio vertical entre columnas de 5
    	JPanel panelDatos = new JPanel(new GridLayout(4, 2, 5, 2)); 
    	panelDatos.setBorder(BorderFactory.createTitledBorder(""));
        
    	panelDatos.add(new JLabel("CI Administrador: " + String.valueOf(ciAdm)));
    	panelDatos.add(new JLabel("CI Alumno: " + String.valueOf(ciAlu)));
    	panelDatos.add(new JLabel("Fecha inicio: " + String.valueOf(fi)));
    	panelDatos.add(new JLabel("Fecha fin: " + String.valueOf(ff)));
        
    	add(panelDatos, BorderLayout.CENTER);



	    // ----- Botones inferiores -----
	    JPanel panelBotones = new JPanel();
	    JButton btnCerrar = new JButton("Cerrar");
	    panelBotones.add(btnCerrar);
	    add(panelBotones, BorderLayout.SOUTH);


        // Acción de cerrar
        btnCerrar.addActionListener(e -> dispose());
    }
}
