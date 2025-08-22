package clases_generales;

import java.time.LocalDate;

public class AdmControlaAlu {
    private Alumno alu;
    private Administrador adm;
    private LocalDate fchInicio;
    private LocalDate fchFin;
    
	public Alumno getAlu() {
		return alu;
	}
	public Administrador getAdm() {
		return adm;
	}
	public LocalDate getFchInicio() {
		return fchInicio;
	}
	public LocalDate getFchFin() {
		return fchFin;
	}
	public AdmControlaAlu(Alumno alu, Administrador adm, LocalDate fchInicio, LocalDate fchFin) {
		this.alu = alu;
		this.adm = adm;
		this.fchInicio = fchInicio;
		this.fchFin = fchFin;
	}
	
    @Override
    public String toString() {
    	String texto = "";
    	texto += "Documento de control\n" ;
   		texto += "--------------------\n";
    	texto += "Administrador [CI=" + adm.getCi() + "]\n";
    	texto += "Alumno [CI=" + alu.getCi() + " Nombre= " + alu.getNombre() +"]\n";
    	texto += "Fecha Inicio = " + this.getFchInicio() +"]\n";
    	texto += "Fecha Fin = " + this.getFchFin() +"]\n";
        return texto;
    }	

	
	
    
    
}
