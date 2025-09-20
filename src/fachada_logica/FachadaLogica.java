package fachada_logica;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import clases_generales.*;
import fachada_persistencia.*;

public class FachadaLogica {

	private Alumnos alumnos;
	private Administradores administradores;
	private FachadaPersistencia fp;
	
	public FachadaLogica() {
		alumnos = new Alumnos();
		administradores = new Administradores();
		fp = new FachadaPersistencia(this);
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

    
    public ArrayList<Alumno> obtenerAlumnosDeAdm(int ci) {
        Administrador ad;
        ArrayList<Alumno> li = new ArrayList<Alumno>();
        ad = administradores.obtiene(ci);
        for (int i=0; i<ad.getSecAdmControlaAlu().size(); i++) {
        	li.add(ad.getSecAdmControlaAlu().get(i).getAlu());
        }
        //System.out.println("estoy en obtenerAdmsDealumno.....");
        //System.out.println(al.toString());
        return li;
    }

    
    
    public void calcularCuotasTodos() {
    	
    	// recorro el hashmap y calculo la cuota para cada alumno.
    	for(Alumno entrada : alumnos.getTablaAlumnos().values()) {
    		entrada.calculoCuota();
    		fp.bajaAlumnoSoloBD(entrada.getCi());
            if (entrada instanceof Interno) {
            	//tipoAlumno = "Interno";
            	fp.altaAluIntBD((Interno) entrada);
            } 
            if (entrada instanceof Externo) {
            	//tipoAlumno = "Externo";
            	fp.altaAluExtBD((Externo) entrada);
            } 

    		
    	}
    }

    
    
    public boolean existeAlumno(int ci) {
        return alumnos.consulta(ci);
    }

    public void bajaAlumno(int ci) {
        alumnos.baja(ci);
        fp.bajaAlumnoSoloBD(ci);
    }

    public void mostrarAlumnos() {
        alumnos.mostrarTodos();
    }
    
    public void altaAlumnoExterno(int ci, String nombre, String hobby) {
        Alumno a = new Externo(ci, nombre, hobby);
        alumnos.alta(a);
        
    }

    public void altaAlumnoInterno(int ci, String nombre, int edad, String dir, float cuotaMensual, float cuotaReal, String regimen) {
        Alumno a = new Interno(ci, nombre, edad, dir, cuotaMensual, cuotaReal, regimen);
        alumnos.alta(a);
        fp.altaAluIntBD((Interno) a);
    }

    public void altaAlumnoExterno(int ci, String nombre, int edad, String dir, float cuotaMensual, float cuotaReal, String hobby) {
        Alumno a = new Externo(ci, nombre, edad, dir, cuotaMensual, cuotaReal, hobby);
        alumnos.alta(a);
        fp.altaAluExtBD((Externo) a);
    }

    
    
	//----------------------------------
    // Operaciones con administradores
	//----------------------------------

    public boolean altaAdministrador(Administrador admin) {
        boolean retorno = false;
    	administradores.alta(admin);
        retorno = fp.altaAdministradorBD(admin);
        return retorno;
    }

    public Administrador obtenerAdministrador(int ci) {
        return administradores.obtiene(ci);
    }

    public boolean existeAdministrador(int ci) {
        return administradores.consulta(ci);
    }


    // Se usa para modificar un administrador
    // dando de baja el registro viejo y luego (en otro método) el alta
    // solo borra el administrador y no borra los registros de AdmControlaAlu
    public void bajaAdministradorSolo(int ci) {
        administradores.baja(ci);
        fp.bajaAdministradorSoloBD(ci);
        
    }

    
    
    public void bajaAdministrador(int ci) {
    	ArrayList<AdmControlaAlu> alACA = new ArrayList<>();
    	ArrayList<Alumno> alAlu = new ArrayList<>();
    	alACA = administradores.obtiene(ci).getSecAdmControlaAlu();
    	for (int i=alACA.size(); i>0; i--) {
    		if(alACA.get(i-1).getAdm().getCi()==ci) { //si es el administrador guardo el alumno y borro el admControlaAlu
    			alAlu.add(alACA.get(i-1).getAlu());
    			alACA.remove(i-1);
    		}
    	}
        administradores.baja(ci);
        // baja de admControlaAlu de los alumnos
        for (int i=alAlu.size(); i>0; i--) {
        	alACA = alAlu.get(i-1).getSecAdmControlaAlu();
        	for (int j=alACA.size(); j>0; j--) {
        		if(alACA.get(j-1).getAdm().getCi()==ci) {
        			alACA.remove(j-1);
        		}
        	}
        }
        
        fp.bajaAdministradorBD(ci);
        
    }

    public void mostrarAdministradores() {
        administradores.mostrarTodos();
    }

    public void altaAdministrador(int ci, String com) {
        Administrador admin = new Administrador(ci, com);
        administradores.alta(admin);
    }
	
	//----------------------------------
    // Otros
	//----------------------------------

    public Boolean asignarAlumnoAAdministrador(int ciAlumno, int ciAdmin, LocalDate fi, LocalDate ff) {
    	Boolean retorno = false;
        Administrador admin = administradores.obtiene(ciAdmin);
        Alumno alumno = alumnos.obtiene(ciAlumno);
        AdmControlaAlu c = new AdmControlaAlu(alumnos.obtiene(ciAlumno), administradores.obtiene(ciAdmin), fi, ff);

        if (admin != null && alumno != null && admin.getSecAdmControlaAlu().size() < Administrador.MAX_ALUMNO 
        		&& alumno.getSecAdmControlaAlu().size() < Alumno.MAX_ADMINISTRADOR) {
            admin.agregarAdmControlaAlu(c);
            alumno.agregarAdmControlaAlu(c);
            fp.asignarAlumnoAAdministradorBD(ciAlumno, ciAdmin, fi, ff);
            retorno = true;
        } else {
            System.out.println("No se pudo asignar: administrador inexistente, alumno inexistente o límite alcanzado.");
        }
        return retorno;
    }
    
    public void desasignarAlumnoAAdministrador(int ciAlumno, int ciAdmin, LocalDate fi, LocalDate ff) {
        Administrador admin = administradores.obtiene(ciAdmin);
        Alumno alumno = alumnos.obtiene(ciAlumno);
        admin.eliminarAdmControlaAlu(ciAlumno, ciAdmin);
        alumno.eliminarAdmControlaAlu(ciAlumno, ciAdmin);
        if (fp.eliminarAdmControlaAluBD(ciAlumno, ciAdmin)) {
        	System.out.println("Eliminación correcta de ACA con alumno " + ciAlumno + " Administrador " + ciAdmin);
        } else {
        	System.out.println("ERROR en eliminación de ACA con alumno " + ciAlumno + " Administrador " + ciAdmin);
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
	// Inicializar objetos y cargar desde la BD
	//----------------------------------------
	public void inicializarConBD() {
		
		// dejo los hashmap vacíos
		alumnos.inicializarAlumnos();
		administradores.inicializarAdministradores();
		
		
		// Alta de administradores
		administradores.setTablaAdministradores(fp.cargaAdministradoresDesdeBD());
    
		// Alta de alumnos internos todos juntos
		alumnos.setTablaAlumnos(fp.cargaInternosDesdeBD());
		
		// agregar externos uno a uno
		HashMap<Integer, Alumno> hmAlu = new HashMap<>();
		hmAlu = fp.cargaExternosDesdeBD();
		for(Integer i: hmAlu.keySet() ) {
			alumnos.alta(hmAlu.get(i));
		}
		
		// agregar administradores por alumno
		ArrayList<AdmControlaAlu> acaList = new ArrayList<AdmControlaAlu>();
		acaList = fp.cargaACADesdeBD();
		int ciAlu =0;
		int ciAdm =0;
		for(int i=0; i<acaList.size(); i++) {
			ciAlu = acaList.get(i).getAlu().getCi();
			ciAdm = acaList.get(i).getAdm().getCi();
			alumnos.obtiene(ciAlu).agregarAdmControlaAlu(acaList.get(i));
			administradores.obtiene(ciAdm).agregarAdmControlaAlu(acaList.get(i));
		}
		
		
		
		
	}


    
	
}
