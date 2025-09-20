package aplicacion;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

import fachada_logica.FachadaLogica;

public class MenuPpal {
	
	FachadaLogica fl = null;
	
	public MenuPpal(FachadaLogica fl) {
		this.fl = fl;
	}
	
	public MenuPpal() {
		
	}

	public void muestroMenu() {
        Frame frame = new Frame("Menú Principal");
        frame.setSize(400, 300);
        frame.setLayout(new FlowLayout());
        
        // MenuBar
        MenuBar menuBar = new MenuBar();
        
        // Supervisor Menu
        Menu supervisorMenu = new Menu("Supervisor");
        MenuItem administradoresItem = new MenuItem("Lista Administradores");
        MenuItem salirItem = new MenuItem("Salir");
        
        supervisorMenu.add(administradoresItem);
        supervisorMenu.addSeparator();
        supervisorMenu.add(salirItem);
        
        // Administrador Menu
        Menu administradorMenu = new Menu("Administrador");
        MenuItem alumnosItem = new MenuItem("Lista Alumnos");
        
        administradorMenu.add(alumnosItem);

        // Utilidades Menu
        Menu utilidadesMenu = new Menu("Utilidades");
        MenuItem inicializarConBDItem = new MenuItem("Inicializar con BD");
        utilidadesMenu.add(inicializarConBDItem);

        MenuItem calcularCuotasTodosItem = new MenuItem("Calcular cuotas");
        utilidadesMenu.add(calcularCuotasTodosItem);

        MenuItem documentosDeControlItem = new MenuItem("Docs de control");
        utilidadesMenu.add(documentosDeControlItem);
        
        
        // agrego menúes en el menuBar
        menuBar.add(supervisorMenu);
        menuBar.add(administradorMenu);
        menuBar.add(utilidadesMenu);
        
        // Pongo el menu bar en el frame
        frame.setMenuBar(menuBar);
        
        // Agrego acción para Salir
        salirItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Cierro window
            }
        });

        

        // Agrego acción para Inicializar CON BD
        inicializarConBDItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fl.inicializarConBD();
            }
        });
        
        
        // Acción para abrir la grilla de administradores
        administradoresItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GrillaAdministradores ga = new GrillaAdministradores(fl);
                ga.setVisible(true);
            }
        });
        
        // Acción para abrir la grilla de alumnos
        alumnosItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GrillaAlumnos ga = new GrillaAlumnos(fl);
                ga.setVisible(true);
            }
        });

        // Acción para calcular cuota de alumnos
        calcularCuotasTodosItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Panel de confirmación
                int opcion = JOptionPane.showConfirmDialog(
                    null,
                    "¿Está seguro de que desea calcular las cuotas?" ,
                    "Confirmar",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
                );
                if (opcion == JOptionPane.YES_OPTION) {
                	fl.calcularCuotasTodos();
                    System.out.println("Cálculo realizado");
                } else {
                    System.out.println("Cálculo NO realizado");
                }
;
            }
        });

        
        
        // window listener para finalizar si le doy en la cruz
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                frame.dispose();
            }
        });
        
        frame.setVisible(true);
    }

	
}
