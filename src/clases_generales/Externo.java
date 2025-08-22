package clases_generales;


public class Externo extends Alumno {
	private String hobby;

    public Externo(int ci, String nombre, String hobby) {
        super(ci, nombre);
        this.hobby = hobby;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    @Override
    public String toString() {
		String texto;
		texto = super.toString() ;
		texto += "Externo [Hobby=" + getHobby() + "]\n";
		return texto;
    }
    
	// Método abstracto heredado, que se debe implementar en esta subclases
    @Override
    public float calculoCuota() {
    	float cuota = 0;
    	cuota = this.getCuotaMensual() * this.getEdad() / 100;
    	this.setCuotaMensual(cuota);
    	return cuota;
    }

    
}
