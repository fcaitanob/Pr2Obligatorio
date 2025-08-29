package aplicacion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import fachada_logica.FachadaLogica;
import clases_generales.Alumno;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.List;

public class PantallaRelacionAlumnosAdministrador extends JDialog {
    
    private static final long serialVersionUID = 1L;
    private DefaultTableModel modeloAlumnos;

    public PantallaRelacionAlumnosAdministrador(JFrame parent, FachadaLogica fl, int ciAdmin) {
        super(parent, "Alumnos del Administrador " + ciAdmin, true);
        setSize(500, 400);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        // Modelo de tabla
        modeloAlumnos = new DefaultTableModel(new Object[] {"CI Alumno", "Nombre"}, 0);
        JTable tablaAlumnos = new JTable(modeloAlumnos);

        // Cargar datos desde fachada
        List<Alumno> alumnos = fl.obtenerAlumnosDeAdm(ciAdmin);
        for (Alumno alu : alumnos) {
            modeloAlumnos.addRow(new Object[] {alu.getCi(), alu.getNombre()});
        }
        add(new JScrollPane(tablaAlumnos), BorderLayout.CENTER);

        // Panel inferior con botones
        JPanel panelBotones = new JPanel();
        JButton btnAgregar = new JButton("Agregar alumno");
        JButton btnEliminar = new JButton("Eliminar alumno");
        JButton btnCerrar = new JButton("Cerrar");
        panelBotones.add(btnAgregar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnCerrar);
        add(panelBotones, BorderLayout.SOUTH);

        // Acción agregar
        btnAgregar.addActionListener(e -> {
            String ciStr = JOptionPane.showInputDialog(this, "Ingrese CI del alumno:");
            if (ciStr != null && !ciStr.trim().isEmpty()) {
                try {
                    int ciAlumno = Integer.parseInt(ciStr);
            		LocalDate fechaIni = LocalDate.of(2020, 2, 15);
            		LocalDate fechaFin = LocalDate.of(2021, 2, 15);
                    if (fl.asignarAlumnoAAdministrador(ciAlumno, ciAdmin, fechaIni, fechaFin)) {
                    	modeloAlumnos.addRow(new Object[]{ciAlumno, fl.getAlumnos().obtiene(ciAlumno).getNombre()});
                    } else {
                        JOptionPane.showMessageDialog(this, "No se pudo hacer la asignación");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Debe ingresar un número válido");
                }
            }
        });

        // Acción eliminar
        btnEliminar.addActionListener(e -> {
            int fila = tablaAlumnos.getSelectedRow();
            if (fila >= 0) {
                int ciAlumno = (int) modeloAlumnos.getValueAt(fila, 0);
                fl.desasignarAlumnoAAdministrador(ciAlumno, ciAdmin, null, null);
                modeloAlumnos.removeRow(fila);
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un alumno para eliminar.");
            }
        });

        // Acción cerrar
        // En esta clase uso siempre funciones lambda (a partir de Java 8)
        // En GrillaAdministradores utilizo el addActionListener con un new de un ActionListener adentro
        btnCerrar.addActionListener(e -> dispose());
    }
}
