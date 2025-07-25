package clases_generales;

import java.util.ArrayList;

public class Administrador extends Persona {
    private ArrayList<Alumno> secAlumnos;
    public static final int MAX_ALUMNO = 14;

    public Administrador(int ci) {
        super(ci);
        this.secAlumnos = new ArrayList<>();
    }

    
    
    
    public ArrayList<Alumno> getSecAlumnos() {
		return secAlumnos;
	}


    //---------------------------------------------------------------------
    // Este método es para el caso de una asignación de administrador a alumno.
    // Ahí, además de agregar el administrador en alumno, se debe agregar el
    // alumno en el Administrador.
    // Pero no se debe agregar en alumno ya que sino queda un loop.
    // Por eso se usa este método y no el agregarAlumno 
    // (que vuelve a llamar a agregarAdministrador
    //---------------------------------------------------------------------
	public boolean agregarAlumnoDesdeAlumno(Alumno a) {
        if (secAlumnos.size() < MAX_ALUMNO) {
            secAlumnos.add(a);
            return true;
        } else {
            System.out.println("No se puede agregar más de " + MAX_ALUMNO + " alumnos.");
            return false;
        }
    }

	public boolean agregarAlumno(Alumno a) {
        if (secAlumnos.size() < MAX_ALUMNO) {
            secAlumnos.add(a);
            a.agregarAdministrador(this);
            return true;
        } else {
            System.out.println("No se puede agregar más de " + MAX_ALUMNO + " alumnos.");
            return false;
        }
    }

    public void mostrarAlumnos() {
        System.out.println("Administrador CI: " + ci + " tiene los siguientes alumnos:");
        for (Alumno a : secAlumnos) {
            System.out.println(a);
        }
    }

    @Override
    public String toString() {
    	String texto = "";
    	texto += "Administrador [CI=" + ci + ", Cantidad de alumnos=" + secAlumnos.size() + "]\n";
    	for (Alumno a : secAlumnos) {
    		texto += "*";
            texto += a.toString();
        }
        return texto;
    }	

	
	
}
