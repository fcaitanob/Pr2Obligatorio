package clases_generales;

import java.util.HashMap;

public class Alumnos {

    private HashMap<Integer, Alumno> tablaAlumnos;

    
    
    
    public void setTablaAlumnos(HashMap<Integer, Alumno> tablaAlumnos) {
		this.tablaAlumnos = tablaAlumnos;
	}

	public void inicializarAlumnos() {
        this.tablaAlumnos = new HashMap<>();
	}

	public Alumnos() {
        this.tablaAlumnos = new HashMap<>();
    }

    public void alta(Alumno alumno) {
    	tablaAlumnos.put(alumno.getCi(), alumno);
    }

    public Alumno obtiene(int ci) {
        return tablaAlumnos.get(ci);
    }

    public boolean consulta(int ci) {
        return tablaAlumnos.containsKey(ci);
    }

    public void baja(int ci) {
    	tablaAlumnos.remove(ci);
    }

    public void mostrarTodos() {
        for (Alumno a : tablaAlumnos.values()) {
            System.out.println(a);
        }
    }

	public HashMap<Integer, Alumno> getTablaAlumnos() {
		return tablaAlumnos;
	}


}
