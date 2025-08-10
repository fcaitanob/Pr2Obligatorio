package clases_generales;

public abstract class Persona {
	private int ci;

	
	public Persona(int ci) {
		this.ci = ci;
	}

	public int getCi() {
		return ci;
	}

	public void setCi(int ci) {
		this.ci = ci;
	}

    @Override
	public String toString() {
		String texto;
		texto = "Persona [CI=" + getCi() + "]\n";
		return texto;

	}

}
