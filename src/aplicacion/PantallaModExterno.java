package aplicacion;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fachada_logica.FachadaLogica;
import clases_generales.*;

public class PantallaModExterno extends JDialog {
    private static final long serialVersionUID = 1L;

    	public PantallaModExterno(JFrame parent, FachadaLogica fl, int ci) {
        super(parent, "Modificar Alumno Externo", true); // true = modal
        setSize(500, 400);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10,10));

        // Traemos el alumno
        Externo aluExt = (Externo) fl.getAlumnos().getTablaAlumnos().get(ci);

        // ----- Panel de datos -----
        // GridLayout(3,2,5,5)
        // n pares de 2 columnas, espacio horizontal entre columas de 5 y espacio vertical entre columnas de 5
        JPanel panelDatos = new JPanel(new GridLayout(7, 2, 5, 5)); 
        panelDatos.setBorder(BorderFactory.createTitledBorder("Modificar Alumno Externo"));
        panelDatos.add(new JLabel("CI:"));
        panelDatos.add(new JLabel(String.valueOf(ci)));

        panelDatos.add(new JLabel("Nombre:"));
        JTextField txtNombre = new JTextField(aluExt.getNombre());
        panelDatos.add(txtNombre);

        panelDatos.add(new JLabel("Edad:"));
        JTextField txtEdad = new JTextField(String.valueOf(aluExt.getEdad()));
        panelDatos.add(txtEdad);

        panelDatos.add(new JLabel("Dirección:"));
        JTextField txtDir = new JTextField(aluExt.getDireccion());
        panelDatos.add(txtDir);

        panelDatos.add(new JLabel("Cuota mensual:"));
        JTextField txtCuotaMen = new JTextField(String.valueOf(aluExt.getCuotaMensual()));
        panelDatos.add(txtCuotaMen);

        panelDatos.add(new JLabel("Cuota real:"));
        JTextField txtCuotaReal = new JTextField(String.valueOf(aluExt.getCuotaReal()));
        panelDatos.add(txtCuotaReal);

        
        panelDatos.add(new JLabel("Hobby:"));
        JTextField txtHobby = new JTextField(aluExt.getHobby());
        panelDatos.add(txtHobby);

        
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
                // Actualizamos datos del alumno externo
            	aluExt.setNombre(txtNombre.getText());
            	aluExt.setEdad(Integer.valueOf(txtEdad.getText()));
            	aluExt.setDireccion(txtDir.getText());
            	aluExt.setCuotaMensual(Float.valueOf(txtCuotaMen.getText()));
            	aluExt.setCuotaReal(Float.valueOf(txtCuotaReal.getText()));
            	aluExt.setHobby(txtHobby.getText());
                // para modificar hago baja y alta
                fl.bajaAlumno(ci);
                fl.altaAlumnoExterno(ci, aluExt.getNombre(), aluExt.getEdad(),aluExt.getDireccion(), aluExt.getCuotaMensual(), aluExt.getCuotaReal(), aluExt.getHobby());
                dispose();
            }
        });

        // Acción de cancelar
        btnCancelar.addActionListener(e -> dispose());
    }
}
