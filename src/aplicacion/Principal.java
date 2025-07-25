package aplicacion;

import java.util.Scanner;


public class Principal {

	// Crea atributo de conexión con el modelo utilizando una fachada
	// falta hacer...
	static Scanner scan = new Scanner(System.in);


	//-------------------------------------
	// Texto menu
	//-------------------------------------
	public static void mostrarMenu() {
		
        System.out.println("Menú principal (v1)");
        System.out.println("-------------------");
        System.out.println("1. Listar alumnos");
        System.out.println("2. Listar administradores");
        System.out.println("3. Consultar alumno");
        System.out.println("4. Alta alumno externo");
        
        System.out.println("90. Fin\n");
		
	}

	
	
	
	
	
	
	//-----------------------------------------
	// Menú ppal
	//-----------------------------------------
	public static void main(String[] args) {
		// Cargo objetos desde la BD
		//inicializar();

		//Menú principal
		int opcion = 0;
		do {
			mostrarMenu();
			opcion = scan.nextInt();
			switch (opcion) {
			case 1: 
				break;
			case 2: 
				break;
			case 3: 
				break;
			case 4: 
				break;
			case 90:
				System.out.println("Fin del programa");
				break;
			default:
				System.out.println("Opción inválida");
			}
			System.out.println();
			
		} while (opcion != 90);

		scan.close();

    }	


}
