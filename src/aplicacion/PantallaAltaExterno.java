package aplicacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import fachada_logica.FachadaLogica;
import clases_generales.*;

public class PantallaAltaExterno extends JDialog {
    private static final long serialVersionUID = 1L;

    	public PantallaAltaExterno(JFrame parent, FachadaLogica fl) {
        super(parent, "Alta Alumno Externo", true); // true = modal
        setSize(500, 400);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10,10));


        // ----- Panel de datos -----
        // GridLayout(3,2,5,5)
        // n pares de 2 columnas, espacio horizontal entre columas de 5 y espacio vertical entre columnas de 5
        JPanel panelDatos = new JPanel(new GridLayout(7, 2, 5, 5)); 
        panelDatos.setBorder(BorderFactory.createTitledBorder("Alta Alumno Externo"));
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

        
        panelDatos.add(new JLabel("Hobby:"));
        JTextField txtHobby = new JTextField("");
        panelDatos.add(txtHobby);

        
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
                            PantallaAltaExterno.this,
                            "La cédula debe tener 7 u 8 dígitos",
                            "Dato inválido",
                            JOptionPane.WARNING_MESSAGE
                        );
                        txtCI.requestFocus();
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(
                        PantallaAltaExterno.this,
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
            	Externo aluExt = new Externo(0, "", "");
            	aluExt.setCi(Integer.valueOf(txtCI.getText()));
            	aluExt.setNombre(txtNombre.getText());
            	aluExt.setEdad(Integer.valueOf(txtEdad.getText()));
            	aluExt.setDireccion(txtDir.getText());
            	aluExt.setCuotaMensual(Float.valueOf(txtCuotaMen.getText()));
            	aluExt.setCuotaReal(Float.valueOf(txtCuotaReal.getText()));
            	aluExt.setHobby(txtHobby.getText());
                fl.altaAlumnoExterno(aluExt.getCi(), aluExt.getNombre(), aluExt.getEdad(),aluExt.getDireccion(), aluExt.getCuotaMensual(), aluExt.getCuotaReal(), aluExt.getHobby());

                dispose();
            }
        });

        // Acción de cancelar
        btnCancelar.addActionListener(e -> dispose());
        

        
        
        
    }
}
