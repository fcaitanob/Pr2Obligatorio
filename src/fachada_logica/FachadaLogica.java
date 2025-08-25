package fachada_logica;


import java.time.LocalDate;
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
        for (int i=0; i<al.getSecAdmControlaAlu().size(); i++) {
        	li.add(al.getSecAdmControlaAlu().get(i).getAdm());
        }
        //System.out.println("estoy en obtenerAdmsDealumno.....");
        //System.out.println(al.toString());
        return li;
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
    
    public void altaAlumnoExterno(int ci, String nombre, String hobby) {
        Alumno a = new Externo(ci, nombre, hobby);
        alumnos.alta(a);
        
    }

    public void altaAlumnoInterno(int ci, String nombre, String regimen) {
        Alumno a = new Interno(ci, nombre, regimen);
        alumnos.alta(a);
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

    public void altaAdministrador(int ci) {
        Administrador admin = new Administrador(ci);
        administradores.alta(admin);
    }
	
	//----------------------------------
    // Otros
	//----------------------------------

    public void asignarAlumnoAAdministrador(int ciAlumno, int ciAdmin, LocalDate fi, LocalDate ff) {
        Administrador admin = administradores.obtiene(ciAdmin);
        Alumno alumno = alumnos.obtiene(ciAlumno);
        AdmControlaAlu c = new AdmControlaAlu(alumnos.obtiene(ciAlumno), administradores.obtiene(ciAdmin), fi, ff);

        if (admin != null && alumno != null && admin.getSecAdmControlaAlu().size() < Administrador.MAX_ALUMNO 
        		&& alumno.getSecAdmControlaAlu().size() < Alumno.MAX_ADMINISTRADOR) {
            admin.agregarAdmControlaAlu(c);
            alumno.agregarAdmControlaAlu(c);
        } else {
            System.out.println("No se pudo asignar: administrador inexistente, alumno inexistente o límite alcanzado.");
        }
    }
    
    
    public void prtDocControl(int ciAlu, int ciAdm) {
    	ArrayList<AdmControlaAlu> lista = new ArrayList<>();
    	lista = this.administradores.obtiene(ciAdm).getSecAdmControlaAlu();
    	boolean encontre = false;
    	
    	for(int i = 0; i<lista.size(); i++) {
    		if(lista.get(i).getAdm().getCi()==ciAdm &&
    		   lista.get(i).getAlu().getCi()==ciAlu) {
    			encontre = true;
    			System.out.println(lista.get(i).toString());
  
    		}
    	}
    	if(!encontre) {
    		System.out.println("No encontre la pareja Administrador controla Alumno");
    		System.out.println("Para: ciAlu - " + ciAlu + " ciAdm - " + ciAdm);
    	}

    }
    
    
	
	//----------------------------------------
	// Inicializar objetos y cargar a mano
	//----------------------------------------
	public void inicializarSinBD() {
		
        // Alta de alumnos internos 
        altaAlumnoInterno(111, "nombre uno", "vegetariano");
		
		// Alta alumnos externos
        altaAlumnoExterno(222, "nombre dos", "futbol");
		
		// Alta de administradores
        altaAdministrador(99999999);
        altaAdministrador(88888888);
        altaAdministrador(77777777);
        altaAdministrador(77777770);
        altaAdministrador(77777771);
        altaAdministrador(77777772);
        altaAdministrador(77777773);
        altaAdministrador(77777774);
        altaAdministrador(77777775);
        altaAdministrador(77777776);
        altaAdministrador(77777778);
        altaAdministrador(777777790);
        altaAdministrador(777777791);
        altaAdministrador(777777792);
        altaAdministrador(777777793);
        altaAdministrador(777777794);
        altaAdministrador(777777795);
        altaAdministrador(777777796);
        altaAdministrador(777777797);


		// Alta de administradores x alumno
        asignarAlumnoAAdministrador(111, 99999999, LocalDate.now(), LocalDate.of(2025, 12, 31));
        asignarAlumnoAAdministrador(222, 99999999, LocalDate.now(), LocalDate.of(2025, 12, 31));
		
		
	}



    
	
}
