package aplicacion;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fachada_logica.FachadaLogica;
import clases_generales.*;

public class PantallaModInterno extends JDialog {
    private static final long serialVersionUID = 1L;

    	public PantallaModInterno(JFrame parent, FachadaLogica fl, int ci) {
        super(parent, "Modificar Alumno Interno", true); // true = modal
        setSize(500, 400);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10,10));

        // Traemos el alumno
        Interno aluInt = (Interno) fl.getAlumnos().getTablaAlumnos().get(ci);

        // ----- Panel de datos -----
        // GridLayout(3,2,5,5)
        // n pares de 2 columnas, espacio horizontal entre columas de 5 y espacio vertical entre columnas de 5
        JPanel panelDatos = new JPanel(new GridLayout(7, 2, 5, 5)); 
        panelDatos.setBorder(BorderFactory.createTitledBorder("Modificar Alumno Interno"));
        panelDatos.add(new JLabel("CI:"));
        panelDatos.add(new JLabel(String.valueOf(ci)));

        panelDatos.add(new JLabel("Nombre:"));
        JTextField txtNombre = new JTextField(aluInt.getNombre());
        panelDatos.add(txtNombre);

        panelDatos.add(new JLabel("Edad:"));
        JTextField txtEdad = new JTextField(String.valueOf(aluInt.getEdad()));
        panelDatos.add(txtEdad);

        panelDatos.add(new JLabel("Dirección:"));
        JTextField txtDir = new JTextField(aluInt.getDireccion());
        panelDatos.add(txtDir);

        panelDatos.add(new JLabel("Cuota mansual:"));
        JTextField txtCuotaMen = new JTextField(String.valueOf(aluInt.getCuotaMensual()));
        panelDatos.add(txtCuotaMen);

        panelDatos.add(new JLabel("Cuota real:"));
        JTextField txtCuotaReal = new JTextField(String.valueOf(aluInt.getCuotaReal()));
        panelDatos.add(txtCuotaReal);

        
        panelDatos.add(new JLabel("Régimen alimentario:"));
        JTextField txtRegAlim = new JTextField(aluInt.getRegAlim());
        panelDatos.add(txtRegAlim);

        
        add(panelDatos, BorderLayout.NORTH);



        // ----- Botones inferiores -----
        JPanel panelBotones = new JPanel();
        JButton btnGuardar = new JButton("Guardar");
        JButton btnCancelar = new JButton("Cancelar");
        panelBotones.add(btnGuardar);
        panelBotones.add(btnCancelar);
        add(panelBotones, BorderLayout.SOUTH);

        // Acción de guardar
        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Actualizamos datos del alumno interno
            	aluInt.setNombre(txtNombre.getText());
            	aluInt.setEdad(Integer.valueOf(txtEdad.getText()));
            	aluInt.setDireccion(txtDir.getText());
            	aluInt.setCuotaMensual(Float.valueOf(txtCuotaMen.getText()));
            	aluInt.setCuotaReal(Float.valueOf(txtCuotaReal.getText()));
            	aluInt.setRegAlim(txtRegAlim.getText());
                // para modificar hago baja y alta
                fl.bajaAlumno(ci);
                fl.altaAlumnoInterno(ci, aluInt.getNombre(), aluInt.getEdad(),aluInt.getDireccion(), aluInt.getCuotaMensual(), aluInt.getCuotaReal(), aluInt.getRegAlim());
                dispose();
            }
        });

        // Acción de cancelar
        btnCancelar.addActionListener(e -> dispose());
    }
}
