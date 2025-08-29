package aplicacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import fachada_logica.FachadaLogica;
import clases_generales.Administrador;

public class PantallaAltaAdministrador extends JDialog {
    private static final long serialVersionUID = 1L;

    	public PantallaAltaAdministrador(JFrame parent, FachadaLogica fl) {
        super(parent, "Alta Administrador", true); // true = modal
        setSize(500, 400);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10,10));


        // ----- Panel de datos -----
        JPanel panelDatos = new JPanel(new GridLayout(2, 2, 5, 5));
        panelDatos.setBorder(BorderFactory.createTitledBorder("Alta Administrador"));
        panelDatos.add(new JLabel("CI:"));

        JTextField txtCI = new JTextField("");
        panelDatos.add(txtCI);

        panelDatos.add(new JLabel("Comentario:"));
        JTextField txtComentario = new JTextField();
        
        
        panelDatos.add(txtComentario);

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
                            PantallaAltaAdministrador.this,
                            "La cédula debe tener 7 u 8 dígitos",
                            "Dato inválido",
                            JOptionPane.WARNING_MESSAGE
                        );
                        txtCI.requestFocus();
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(
                        PantallaAltaAdministrador.this,
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
                // Actualizamos datos del administrador
            	Administrador admin = new Administrador(Integer.parseInt(txtCI.getText()), txtComentario.getText());
                // doy el alta
                fl.altaAdministrador(admin);

                dispose();
            }
        });

        // Acción de cancelar
        btnCancelar.addActionListener(e -> dispose());
        

        
        
        
    }
}
