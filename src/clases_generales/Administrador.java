package clases_generales;

import java.util.ArrayList;

public class Administrador extends Persona {
	private String comentarioAdm;
    private ArrayList<AdmControlaAlu> secAdmControlaAlu;
    public static final int MAX_ALUMNO = 3;

    public Administrador(int ci) {
        super(ci);
        this.secAdmControlaAlu = new ArrayList<>();
    }

    
    public Administrador(int ci, String comentarioAdm) {
        super(ci);
        this.secAdmControlaAlu = new ArrayList<>();
    	this.comentarioAdm = comentarioAdm;
    }
    
    
    
    public void setComentarioAdm(String comentarioAdm) {
		this.comentarioAdm = comentarioAdm;
	}


	public String getComentarioAdm() {
		return comentarioAdm;
	}


	public ArrayList<AdmControlaAlu> getSecAdmControlaAlu() {
		return secAdmControlaAlu;
	}



	public boolean agregarAdmControlaAlu(AdmControlaAlu c) {
        if (secAdmControlaAlu.size() < MAX_ALUMNO) {
            secAdmControlaAlu.add(c);
            return true;
        } else {
            System.out.println("No se puede agregar más de " + MAX_ALUMNO + " alumnos.");
            return false;
        }
    }

	public boolean eliminarAdmControlaAlu(int ciAlumno, int ciAdmin) {

		for(int i=0; i<secAdmControlaAlu.size();i++) {
			if (secAdmControlaAlu.get(i).getAdm().getCi() == ciAdmin &&
				secAdmControlaAlu.get(i).getAlu().getCi() == ciAlumno) {
			  secAdmControlaAlu.remove(i);
			  return true;
			}
		}
            return false;
    }

	
	
	public void mostrarAlumnos() {
        System.out.println("Administrador CI: " + this.getCi() + " tiene los siguientes alumnos:");
        for (AdmControlaAlu c : secAdmControlaAlu) {
            System.out.println(c.getAlu());
        }
    }

    @Override
    public String toString() {
    	String texto = "";
    	texto += "Administrador [CI=" + this.getCi() + ", Cantidad de alumnos=" + secAdmControlaAlu.size() + "]\n";
    	for (AdmControlaAlu c : secAdmControlaAlu) {
    		texto += "*";
            texto += c.getAlu().toString();
        }
        return texto;
    }	

	
	
}
