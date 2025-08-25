package aplicacion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import clases_generales.*;
import fachada_logica.FachadaLogica;

import java.awt.*;
import java.util.HashMap;

public class GrillaAdministradores extends JFrame {

    public GrillaAdministradores(FachadaLogica fachada) {
        setTitle("Lista de Administradores");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Solo cierra esta ventana

        // Encabezados de la tabla
        String[] columnas = {"CI"};

        // Traemos los administradores de la fachada
        Administradores admins = fachada.getAdministradores();

        // Cargamos los datos en una matriz para la JTable
        Object[][] datos = new Object[admins.getTablaAdministradores().size()][1];
        int fila = 0;
        for (Integer ci : admins.getTablaAdministradores().keySet()) {
            datos[fila][0] = ci;
            fila++;
        }

        // Modelo de la tabla
        DefaultTableModel model = new DefaultTableModel(datos, columnas);

        // Creamos la JTable y el scroll
        JTable tabla = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tabla);

        add(scrollPane, BorderLayout.CENTER);
    }

    // Main para probar la grilla sola con datos de prueba
    public static void main(String[] args) {
        // Fachada de prueba (simulada)
        FachadaLogica fachada = new FachadaLogica();
        fachada.getAdministradores().alta(new Administrador(12345678));
        fachada.getAdministradores().alta(new Administrador(87654321));
        fachada.getAdministradores().alta(new Administrador(45678912));

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                GrillaAdministradores ventana = new GrillaAdministradores(fachada);
                ventana.setVisible(true);
            }
        });
    }
}

