package fachada_logica;


import java.util.ArrayList;
import java.util.Map;

import clases_generales.*;

public class FachadaLogica {

	private Alumnos alumnos;
	private Administradores administradores;
	
	public FachadaLogica() {
		alumnos = new Alumnos();
		administradores = new Administradores();
	}

	public Alumnos getAlumnos() {
		return alumnos;
	}

	public Administradores getAdministradores() {
		return administradores;
	}
	
	//----------------------------------
	// Operaciones con alumnos 
	//----------------------------------

    public void altaAlumno(Alumno alu) {
        alumnos.alta(alu);
    }

    public Alumno obtenerAlumno(int ci) {
        return alumnos.obtiene(ci);
    }

    public ArrayList<Administrador> obtenerAdmsDeAlumno(int ci) {
        Alumno al;
        ArrayList<Administrador> li = new ArrayList<Administrador>();
        al = alumnos.obtiene(ci);
        li = al.getSecAdministradores();
        //System.out.println("estoy en obtenerAdmsDealumno.....");
        //System.out.println(al.toString());
        return li;
    }
    
    public void calcularCuotasTodosNOUSAR() {
    	
    	// recorro el hashmap y calculo la cuota para cada alumno.
    	for(Map.Entry<Integer, Alumno> entrada : alumnos.getTablaAlumnos().entrySet()) {
    		entrada.getValue().calculoCuota();
    	}
    }

    public void calcularCuotasTodos() {
    	
    	// recorro el hashmap y calculo la cuota para cada alumno.
    	for(Alumno entrada : alumnos.getTablaAlumnos().values()) {
    		entrada.calculoCuota();
    	}
    }

    
    
    public boolean existeAlumno(int ci) {
        return alumnos.consulta(ci);
    }

    public void bajaAlumno(int ci) {
        alumnos.baja(ci);
    }

    public void mostrarAlumnos() {
        alumnos.mostrarTodos();
    }

	//----------------------------------
    // Operaciones con administradores
	//----------------------------------

    public void altaAdministrador(Administrador admin) {
        administradores.alta(admin);
    }

    public Administrador obtenerAdministrador(int ci) {
        return administradores.obtiene(ci);
    }

    public boolean existeAdministrador(int ci) {
        return administradores.consulta(ci);
    }

    public void bajaAdministrador(int ci) {
        administradores.baja(ci);
    }

    public void mostrarAdministradores() {
        administradores.mostrarTodos();
    }

	//----------------------------------
    // Otros
	//----------------------------------

    public void asignarAlumnoAAdministrador(int ciAlumno, int ciAdmin) {
        Administrador admin = administradores.obtiene(ciAdmin);
        Alumno alumno = alumnos.obtiene(ciAlumno);

        if (admin != null && alumno != null && admin.getSecAlumnos().size() < Administrador.MAX_ALUMNO 
        		&& alumno.getSecAdministradores().size() < Alumno.MAX_ADMINISTRADOR) {
            admin.agregarAlumno(alumno);
            alumno.agregarAdministrador(admin);
        } else {
            System.out.println("No se pudo asignar: administrador inexistente, alumno inexistente o límite alcanzado.");
        }
    }
	
	//---------------------------------------------------
    // Interacción con modelo para que la fachada
    // sea el único punto de control con la aplicación
	//----------------------------------------------------

    public void altaAlumnoExterno(int ci, String nombre, String hobby) {
        Alumno a = new Externo(ci, nombre, hobby);
        alumnos.alta(a);
        
    }

    public void altaAlumnoInterno(int ci, String nombre, String regimen) {
        Alumno a = new Interno(ci, nombre, regimen);
        alumnos.alta(a);
    }

    public void altaAdministrador(int ci) {
        Administrador admin = new Administrador(ci);
        administradores.alta(admin);
    }
	
	
}
