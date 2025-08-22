package clases_generales;


public class Interno extends Alumno {
	public static final int PORCENTAJE_DTO = 15;
	private String regAlim;  // Régimen alimentario

    public Interno(int ci, String nombre, String regAlim) {
        super(ci, nombre);
        this.regAlim = regAlim;
    }

    public String getRegAlim() {
        return regAlim;
    }

    public void setRegAlim(String regAlim) {
        this.regAlim = regAlim;
    }

    @Override
    public String toString() {
		String texto;
		texto = super.toString() ;
    	texto += "Interno [RegAlim=" + getRegAlim() + "]\n";
    	return texto;
    }	
	
	// Método abstracto heredado, que se debe implementar en esta subclases
    @Override
    public float calculoCuota() {
    	float cuota = 0;
    	cuota = this.getCuotaMensual() * PORCENTAJE_DTO / 100;
    	this.setCuotaMensual(cuota);
    	return cuota;
    }

	
}
