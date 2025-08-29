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

public class GrillaAdministradores extends JFrame {

	
	/**
	 * Esto lo hicimos ya que lo sugiere Eclipse para eliminar un Warning 
	 */
	private static final long serialVersionUID = 1L;




	private void cargarDatosDesdeCeroEnGrilla(FachadaLogica fl, DefaultTableModel modelo) {
	    Administradores admins = fl.getAdministradores();
	    modelo.setRowCount(0); // limpiar la tabla
	    
	    for (Integer ci : admins.getTablaAdministradores().keySet()) {
	        modelo.addRow(new Object[]{ci, admins.getTablaAdministradores().get(ci).getComentarioAdm(),"MOD", "BORRAR", "ALUs"});
	    }
	}

	
	
	
    public GrillaAdministradores(FachadaLogica fl) {
        setTitle("Lista de Administradores");
        setSize(600, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Solo cierra esta ventana

        
        // Botón de alta y de refrescar
        JButton btnNuevoAdmin = new JButton("Nuevo Administrador");
        JButton btnActualizar = new JButton("Actualizar");
               
        
        // ---- Panel superior con filtro y botón ----
        JPanel panelFiltroSuperior = new JPanel(new BorderLayout());

        // Panel de filtro interior (a la izquierda)
        JPanel panelFiltroInterior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblFiltro = new JLabel("Filtro por CI: ");
        JTextField txtFiltro = new JTextField(10);
        panelFiltroInterior.add(lblFiltro);
        panelFiltroInterior.add(txtFiltro);

        //panelFiltroSuperior.add(panelFiltroInterior, BorderLayout.WEST);
        panelFiltroSuperior.add(panelFiltroInterior);
        //panelFiltroSuperior.add(btnNuevoAdmin, BorderLayout.EAST);

        //boton actualizar y nuevo ajustado en otro panel
        JPanel panelActualizar = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelActualizar.add(btnActualizar);
        panelActualizar.add(btnNuevoAdmin);
        panelFiltroSuperior.add(panelActualizar, BorderLayout.EAST);
        
        this.add(panelFiltroSuperior, BorderLayout.NORTH); // agrega en el objeto grilla

        // Encabezados de la tabla
        String[] columnas = {"CI", "Comentario", "Modificar", "Borrar", "Alumnos"};

        // Modelo de la tabla
        DefaultTableModel modelo = new DefaultTableModel(new Object[][]{}, columnas);
        cargarDatosDesdeCeroEnGrilla(fl, modelo); // Carga inicial de renglones con datos
        
        
        // Creamos la JTable y el scroll
        JTable tabla = new JTable(modelo);
        // Ajustar el ancho de las columnas de acción
        tabla.getColumnModel().getColumn(2).setMaxWidth(80);  // Modificar
        tabla.getColumnModel().getColumn(3).setMaxWidth(80);  // Borrar
        tabla.getColumnModel().getColumn(4).setMaxWidth(80);  // Alumnos

        JScrollPane scrollPane = new JScrollPane(tabla);

        add(scrollPane, BorderLayout.CENTER);
        
        
        // Detectar clic en columnas Modificar o Borrar
        tabla.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int fila = tabla.rowAtPoint(e.getPoint());
                int columna = tabla.columnAtPoint(e.getPoint());

                if (columna == 2) { 
                    int ci = (int) tabla.getValueAt(fila, 0);
                    //JOptionPane.showMessageDialog(null, "Modificar administrador CI: " + ci);
                    new PantallaModAdministrador(
                            GrillaAdministradores.this, // lo paso para armar pantalla modal
                            fl, 
                            ci
                        ).setVisible(true);                
                    //actualiza luego del cierre de pantalla modal
                    // de modificación del administrador
                    cargarDatosDesdeCeroEnGrilla(fl, modelo); 
                } 
                if (columna == 3) {
                    int ci = (int) tabla.getValueAt(fila, 0);
                    // Panel de confirmación
                    int opcion = JOptionPane.showConfirmDialog(
                        null,
                        "¿Está seguro de que desea eliminar al administrador con CI: " + ci + "?",
                        "Confirmar baja",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                    );
                    if (opcion == JOptionPane.YES_OPTION) {
                    	fl.bajaAdministrador(ci);
                    	System.out.println("Eliminando administrador CI: " + ci);
                    	cargarDatosDesdeCeroEnGrilla(fl, modelo); // recarga grilla desde cero
                    } else {
                        System.out.println("Baja cancelada.");
                    }
                }
                if (columna == 4) {
                    int ci = (int) tabla.getValueAt(fila, 0);
                    // Alumnos del administrador
                    //JOptionPane.showMessageDialog(null, "Alumnos del administrador CI: " + ci);
                    new PantallaRelacionAlumnosAdministrador(
                            GrillaAdministradores.this, // lo paso para armar pantalla modal
                            fl, 
                            ci
                        ).setVisible(true);                
                    
                }
                
                
            }
        });
        
        // Detectar clic en boton de alta
        btnNuevoAdmin.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//JOptionPane.showMessageDialog(null, "Alta de nuevo administrador ");
                new PantallaAltaAdministrador(
                        GrillaAdministradores.this, // lo paso para armar pantalla modal
                        fl
                    ).setVisible(true);                
                //actualiza luego del cierre de pantalla modal
                // de modificación del administrador
                cargarDatosDesdeCeroEnGrilla(fl, modelo); 
        		
            }
        });

        // Detectar clic en boton de actualizar
        btnActualizar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//JOptionPane.showMessageDialog(null, "Actualizar datos de la grilla ");
            	cargarDatosDesdeCeroEnGrilla(fl, modelo); // recarga grilla desde cero
        		
            }
        });

        // Agrego el sorter para permitir filtrado
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(modelo);
        tabla.setRowSorter(sorter);
        
        // Ordenar inicialmente por CI (columna 0)
        java.util.List<RowSorter.SortKey> sortParaCI = new java.util.ArrayList<>();
        sortParaCI.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
        sorter.setSortKeys(sortParaCI);
        sorter.sort();

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

