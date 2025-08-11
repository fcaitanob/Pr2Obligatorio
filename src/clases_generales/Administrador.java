package clases_generales;

import java.util.ArrayList;

public class Administrador extends Persona {
    private ArrayList<AdmControlaAlu> secAdmControlaAlu;
    public static final int MAX_ALUMNO = 14;

    public Administrador(int ci) {
        super(ci);
        this.secAdmControlaAlu = new ArrayList<>();
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
