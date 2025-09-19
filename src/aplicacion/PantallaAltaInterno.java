package aplicacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import fachada_logica.FachadaLogica;
import clases_generales.*;

public class PantallaAltaInterno extends JDialog {
    private static final long serialVersionUID = 1L;

    	public PantallaAltaInterno(JFrame parent, FachadaLogica fl) {
        super(parent, "Alta Alumno Interno", true); // true = modal
        setSize(500, 400);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10,10));


        // ----- Panel de datos -----
        // GridLayout(3,2,5,5)
        // n pares de 2 columnas, espacio horizontal entre columas de 5 y espacio vertical entre columnas de 5
        JPanel panelDatos = new JPanel(new GridLayout(7, 2, 5, 5)); 
        panelDatos.setBorder(BorderFactory.createTitledBorder("Alta Alumno Interno"));
        panelDatos.add(new JLabel("CI:"));
        JTextField txtCI = new JTextField("");
        panelDatos.add(txtCI);

        panelDatos.add(new JLabel("Nombre:"));
        JTextField txtNombre = new JTextField("");
        panelDatos.add(txtNombre);

        panelDatos.add(new JLabel("Edad:"));
        JTextField txtEdad = new JTextField("");
        panelDatos.add(txtEdad);

        panelDatos.add(new JLabel("Dirección:"));
        JTextField txtDir = new JTextField("");
        panelDatos.add(txtDir);

        panelDatos.add(new JLabel("Cuota mansual:"));
        JTextField txtCuotaMen = new JTextField("");
        panelDatos.add(txtCuotaMen);

        panelDatos.add(new JLabel("Cuota real:"));
        JTextField txtCuotaReal = new JTextField("");
        panelDatos.add(txtCuotaReal);

        
        panelDatos.add(new JLabel("Régimen alimentario:"));
        JTextField txtRegAlim = new JTextField("");
        panelDatos.add(txtRegAlim);

        
        add(panelDatos, BorderLayout.NORTH);



        // ----- Botones inferiores -----
        JPanel panelBotones = new JPanel();
        JButton btnGuardar = new JButton("Guardar");
        JButton btnCancelar = new JButton("Cancelar");
        panelBotones.add(btnGuardar);
        panelBotones.add(btnCancelar);
        add(panelBotones, BorderLayout.SOUTH);

        
     // --- VALIDACIÓN CUANDO SE PIERDE EL FOCO ---
        txtCI.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                try {
                    String texto = txtCI.getText().trim();
                    int numero = Integer.parseInt(texto); // Lanza excepción si no es número

                    if (texto.length() < 7 || texto.length() > 8) {
                        JOptionPane.showMessageDialog(
                            PantallaAltaInterno.this,
                            "La cédula debe tener 7 u 8 dígitos",
                            "Dato inválido",
                            JOptionPane.WARNING_MESSAGE
                        );
                        txtCI.requestFocus();
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(
                        PantallaAltaInterno.this,
                        "Debe ingresar solo números",
                        "Dato inválido",
                        JOptionPane.ERROR_MESSAGE
                    );
                    txtCI.requestFocus();
                }
            }
        });
        
        
        // Acción de guardar
        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Actualizamos datos del alumno interno
            	Interno aluInt = new Interno(0, "", "");
            	aluInt.setCi(Integer.valueOf(txtCI.getText()));
            	aluInt.setNombre(txtNombre.getText());
            	aluInt.setEdad(Integer.valueOf(txtEdad.getText()));
            	aluInt.setDireccion(txtDir.getText());
            	aluInt.setCuotaMensual(Float.valueOf(txtCuotaMen.getText()));
            	aluInt.setCuotaReal(Float.valueOf(txtCuotaReal.getText()));
            	aluInt.setRegAlim(txtRegAlim.getText());
                fl.altaAlumnoInterno(aluInt.getCi(), aluInt.getNombre(), aluInt.getEdad(),aluInt.getDireccion(), aluInt.getCuotaMensual(), aluInt.getCuotaReal(), aluInt.getRegAlim());

                dispose();
            }
        });

        // Acción de cancelar
        btnCancelar.addActionListener(e -> dispose());
        

        
        
        
    }
}
