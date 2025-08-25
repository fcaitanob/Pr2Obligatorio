package aplicacion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import clases_generales.*;
import fachada_logica.FachadaLogica;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

public class GrillaAdministradores extends JFrame {

    public GrillaAdministradores(FachadaLogica fl) {
        setTitle("Lista de Administradores");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Solo cierra esta ventana

        
        // Botón de alta
        JButton btnNuevoAdmin = new JButton("Nuevo Administrador");
               
        
        // ---- Panel superior con filtro y botón ----
        JPanel panelFiltroSuperior = new JPanel(new BorderLayout());

        // Panel de filtro interior (a la izquierda)
        JPanel panelFiltroInterior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelFiltroSuperior.add(new JLabel("Filtro por CI: "));
        JTextField txtFiltro = new JTextField(10);
        panelFiltroInterior.add(txtFiltro);

        panelFiltroSuperior.add(panelFiltroInterior, BorderLayout.WEST);
        
        panelFiltroSuperior.add(panelFiltroInterior, BorderLayout.WEST);
        panelFiltroSuperior.add(btnNuevoAdmin, BorderLayout.EAST);
        this.add(panelFiltroSuperior, BorderLayout.NORTH);

        // Encabezados de la tabla
        String[] columnas = {"CI", "Modificar", "Borrar"};

        // Traemos los administradores de la fachada
        Administradores admins = fl.getAdministradores();

        // Cargamos los datos en una matriz para la JTable
        Object[][] datos = new Object[admins.getTablaAdministradores().size()][3];
        int fila = 0;
        for (Integer ci : admins.getTablaAdministradores().keySet()) {
            datos[fila][0] = ci;
            datos[fila][1] = "MOD";
            datos[fila][2] = "BORRAR";
            fila++;
        }

        // Modelo de la tabla
        DefaultTableModel modelo = new DefaultTableModel(datos, columnas);

        // Creamos la JTable y el scroll
        JTable tabla = new JTable(modelo);
        JScrollPane scrollPane = new JScrollPane(tabla);

        add(scrollPane, BorderLayout.CENTER);
        
        
        // Detectar clic en columnas Modificar o Borrar
        tabla.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int fila = tabla.rowAtPoint(e.getPoint());
                int columna = tabla.columnAtPoint(e.getPoint());

                if (columna == 1) { 
                    int ci = (int) tabla.getValueAt(fila, 0);
                    JOptionPane.showMessageDialog(null, "Modificar administrador CI: " + ci);
                    // Aquí llamas a tu lógica para modificar
                } else if (columna == 2) {
                    int ci = (int) tabla.getValueAt(fila, 0);
                    JOptionPane.showMessageDialog(null, "Borrar administrador CI: " + ci);
                    // Aquí llamas a tu lógica para borrar
                }
            }
        });
        
        // Detectar clic en boton de alta
        btnNuevoAdmin.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		JOptionPane.showMessageDialog(null, "Alta de nuevo administrador ");
        		
            }
        });

        // Agrego el sorter para permitir filtrado
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(modelo);
        tabla.setRowSorter(sorter);

        // Listener para el filtro
        txtFiltro.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String texto = txtFiltro.getText().trim();
                if (texto.length() == 0) {
                    sorter.setRowFilter(null); // sin filtro
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter(".*" + texto + ".*", 0)); // columna 0 = CI
                }
            }
        });

        
    }

}

