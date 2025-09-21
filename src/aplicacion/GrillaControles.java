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
import java.time.LocalDate;
import java.util.ArrayList;

public class GrillaControles extends JFrame {

	
	
	//Esto  que viene a continuación lo hicimos ya que lo sugiere Eclipse para eliminar un Warning
	private static final long serialVersionUID = 1L;




	private void cargarDatosDesdeCeroEnGrilla(FachadaLogica fl, DefaultTableModel modelo) {
	    Administradores admins = fl.getAdministradores();
	    modelo.setRowCount(0); // limpiar la tabla
	    int ciAlu = 0;
	    LocalDate fchIni = null;
	    LocalDate fchFin = null;
	    
	    ArrayList<AdmControlaAlu> alACA= new ArrayList<>();
	    for (Integer ciAdm : admins.getTablaAdministradores().keySet()) { //recorrdo los adm
	    	alACA = admins.getTablaAdministradores().get(ciAdm).getSecAdmControlaAlu();
	    	for(int i=0; i<alACA.size(); i++) { //recorro los alumnos de ese adm
	    		ciAlu = alACA.get(i).getAlu().getCi();
	    		fchIni = alACA.get(i).getFchInicio();
	    		fchFin = alACA.get(i).getFchFin();    				
	    		modelo.addRow(new Object[]{ciAdm, ciAlu, fchIni, fchFin, "VER"});
	    	}
	    }
	}

	
	
	
    public GrillaControles(FachadaLogica fl) {
        setTitle("Lista de Controles");
        setSize(600, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Solo cierra esta ventana

        
               
        
        // ---- Panel superior con filtro y botón ----
        JPanel panelFiltroSuperior = new JPanel(new BorderLayout());

        // Panel de filtro interior (a la izquierda)
        JPanel panelFiltroInterior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblFiltro = new JLabel("Filtro por ADM: ");
        JTextField txtFiltro = new JTextField(10);
        panelFiltroInterior.add(lblFiltro);
        panelFiltroInterior.add(txtFiltro);

        panelFiltroSuperior.add(panelFiltroInterior);

        
        this.add(panelFiltroSuperior, BorderLayout.NORTH); // agrega en el objeto grilla

        // Encabezados de la tabla
        String[] columnas = {"CI Adm", "CI Alu", "Fch inicio", "Fch fin", "Control"};

        // Modelo de la tabla
        DefaultTableModel modelo = new DefaultTableModel(new Object[][]{}, columnas);
        cargarDatosDesdeCeroEnGrilla(fl, modelo); // Carga inicial de renglones con datos
        
        
        // Creamos la JTable y el scroll
        JTable tabla = new JTable(modelo);
        // Ajustar el ancho de las columnas de acción
        tabla.getColumnModel().getColumn(4).setMaxWidth(80);  // PRT

        JScrollPane scrollPane = new JScrollPane(tabla);

        this.add(scrollPane, BorderLayout.CENTER); // agrego scroll a la grilla
        
        
        // Detectar clic en columna PRT
        tabla.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int fila = tabla.rowAtPoint(e.getPoint());
                int columna = tabla.columnAtPoint(e.getPoint());

                if (columna == 4) { 
                    int ciAdm = (int) tabla.getValueAt(fila, 0);
                    int ciAlu = (int) tabla.getValueAt(fila, 1);
                    LocalDate fchIni = (LocalDate) tabla.getValueAt(fila, 2);
                    LocalDate fchFin = (LocalDate) tabla.getValueAt(fila, 3);
                    new PantallaControles(
                            GrillaControles.this, // lo paso para armar pantalla modal
                            fl, 
                            ciAdm,
                            ciAlu,
                            fchIni,
                            fchFin
                        ).setVisible(true);                
                } 
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

