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

public class GrillaAlumnos extends JFrame {

	
	
	//Esto  que viene a continuación lo hicimos ya que lo sugiere Eclipse para eliminar un Warning
	private static final long serialVersionUID = 1L;




	private void cargarDatosDesdeCeroEnGrilla(FachadaLogica fl, DefaultTableModel modelo) {
	    Alumnos als = fl.getAlumnos();
	    modelo.setRowCount(0); // limpiar la tabla
	    String tipoAlumno = null;
	    
	    // cargo variable para diferenciar al momento de modificar si es interno o externo
	    for (Integer ci : als.getTablaAlumnos().keySet()) {
            if (fl.getAlumnos().obtiene(ci) instanceof Interno) {
            	tipoAlumno = "Interno";
            } 
            if (fl.getAlumnos().obtiene(ci) instanceof Externo) {
            	tipoAlumno = "Externo";
            } 

	        modelo.addRow(new Object[]{ci, als.getTablaAlumnos().get(ci).getNombre(),tipoAlumno, "MOD", "BORRAR"});
	    }
	}

	
	
	
    public GrillaAlumnos(FachadaLogica fl) {
        setTitle("Lista de Alumnos");
        setSize(600, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Solo cierra esta ventana

        
        // Botón de alta y de refrescar
        JButton btnNuevoAluInt = new JButton("Nuevo Interno");
        JButton btnNuevoAluExt = new JButton("Nuevo Externo");
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
        panelActualizar.add(btnNuevoAluInt);
        panelActualizar.add(btnNuevoAluExt);
        panelFiltroSuperior.add(panelActualizar, BorderLayout.EAST);
        
        this.add(panelFiltroSuperior, BorderLayout.NORTH); // agrega en el objeto grilla

        // Encabezados de la tabla
        String[] columnas = {"CI", "Comentario", "Tipo", "Modificar", "Borrar"};

        // Modelo de la tabla
        DefaultTableModel modelo = new DefaultTableModel(new Object[][]{}, columnas);
        cargarDatosDesdeCeroEnGrilla(fl, modelo); // Carga inicial de renglones con datos
        
        
        // Creamos la JTable y el scroll
        JTable tabla = new JTable(modelo);
        // Ajustar el ancho de las columnas de acción
        tabla.getColumnModel().getColumn(3).setMaxWidth(80);  // Modificar
        tabla.getColumnModel().getColumn(4).setMaxWidth(80);  // Borrar

        JScrollPane scrollPane = new JScrollPane(tabla);

        this.add(scrollPane, BorderLayout.CENTER); // agrego scroll a la grilla
        
        
        // Detectar clic en columnas Modificar o Borrar
        tabla.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int fila = tabla.rowAtPoint(e.getPoint());
                int columna = tabla.columnAtPoint(e.getPoint());

                if (columna == 3) { 
                    int ci = (int) tabla.getValueAt(fila, 0);
                    if (tabla.getValueAt(fila, 2)=="Interno") {
	                    new PantallaModInterno(
	                            GrillaAlumnos.this, // lo paso para armar pantalla modal
	                            fl, 
	                            ci
	                        ).setVisible(true);                
                    }
                    if (tabla.getValueAt(fila, 2)=="Externo") {
	                    new PantallaModExterno(
	                            GrillaAlumnos.this, // lo paso para armar pantalla modal
	                            fl, 
	                            ci
	                        ).setVisible(true);                
                    }
                    cargarDatosDesdeCeroEnGrilla(fl, modelo); 
                } 
                if (columna == 4) {
                    int ci = (int) tabla.getValueAt(fila, 0);
                    // Panel de confirmación
                    int opcion = JOptionPane.showConfirmDialog(
                        null,
                        "¿Está seguro de que desea eliminar al alumno con CI: " + ci + "?",
                        "Confirmar baja",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                    );
                    if (opcion == JOptionPane.YES_OPTION) {
                    	fl.bajaAlumno(ci);
                    	System.out.println("Eliminando alumno CI: " + ci);
                    	cargarDatosDesdeCeroEnGrilla(fl, modelo); // recarga grilla desde cero
                    } else {
                        System.out.println("Baja cancelada.");
                    }
                }
                    
                
                
            }
        });
        
        // Detectar clic en boton de alta
        btnNuevoAluInt.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		new PantallaAltaInterno(
                        GrillaAlumnos.this, // lo paso para armar pantalla modal
                        fl
                    ).setVisible(true);                
                cargarDatosDesdeCeroEnGrilla(fl, modelo); 
        		
            }
        });

        // Detectar clic en boton de actualizar
        btnActualizar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
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

