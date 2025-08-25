package clases_generales;

import java.util.HashMap;

public class Administradores {

    private HashMap<Integer, Administrador> tablaAdministradores;

    public Administradores() {
        this.tablaAdministradores = new HashMap<>();
    }

    public void alta(Administrador admin) {
        tablaAdministradores.put(admin.getCi(), admin);
    }

    public boolean consulta(int ci) {
        return tablaAdministradores.containsKey(ci);
    }

    public Administrador obtiene(int ci) {
        return tablaAdministradores.get(ci);
    }

    public void baja(int ci) {
        tablaAdministradores.remove(ci);
    }

    
    
    
    public HashMap<Integer, Administrador> getTablaAdministradores() {
		return tablaAdministradores;
	}

	public void mostrarTodos() {
        for (Administrador admin : tablaAdministradores.values()) {
            System.out.println(admin);
            //admin.mostrarAlumnos(); // Opcional: muestra alumnos del admin
            System.out.println("-----------------------");
        }
    }	
	
}
