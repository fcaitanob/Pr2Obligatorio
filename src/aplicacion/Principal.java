package aplicacion;

import fachada_logica.FachadaLogica;

public class Principal {

	// Crea atributo de conexión con el modelo utilizando una fachada
	private FachadaLogica fl = new FachadaLogica();
	private MenuPpal mp = new MenuPpal(fl);


	//-----------------------------------------
	// Menú ppal
	//-----------------------------------------
	public static void main(String[] args) {
		// Cargo objetos
		Principal ppal = new Principal();
		ppal.fl.inicializarSinBD();
		ppal.mp.muestroMenu();
		System.out.println("Estoy en el main de clase Principal");
	}		

}
