package aplicacion;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fachada_logica.FachadaLogica;
import clases_generales.Administrador;

public class PantallaModAdministrador extends JDialog {
    private static final long serialVersionUID = 1L;

    	public PantallaModAdministrador(JFrame parent, FachadaLogica fl, int ci) {
        super(parent, "Modificar Administrador", true); // true = modal
        setSize(500, 400);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10,10));

        // Traemos el administrador
        Administrador admin = fl.getAdministradores().getTablaAdministradores().get(ci);

        // ----- Panel de datos -----
        JPanel panelDatos = new JPanel(new GridLayout(2, 2, 5, 5));
        panelDatos.setBorder(BorderFactory.createTitledBorder("Modificar Administrador"));
        panelDatos.add(new JLabel("CI:"));
        panelDatos.add(new JLabel(String.valueOf(ci)));

        panelDatos.add(new JLabel("Comentario:"));
        JTextField txtComentario = new JTextField(admin.getComentarioAdm());
        panelDatos.add(txtComentario);

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
                // Actualizamos datos del administrador
                admin.setComentarioAdm(txtComentario.getText());
                // para modificar hago baja y alta
                fl.bajaAdministrador(admin.getCi());
                fl.altaAdministrador(admin);

                dispose();
            }
        });

        // Acción de cancelar
        btnCancelar.addActionListener(e -> dispose());
    }
}
