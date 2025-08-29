package fachada_persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionBD {

	private String dbName = "p2pruebas01";
	private String url = "jdbc:mysql://localhost:3306/" + dbName;
    private String usuario = "root"; 
    private String password = "tuclave"; 
    private Connection con;

	
    
	public Connection getCon() {
		return con;
	}

	public Connection conectar() {
		
	    try {
			// Establecemos la conexión con la base de datos.
			con = DriverManager.getConnection (url,usuario, password);	
			//System.out.println("Conexión exitosa");
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return con;

	}

	public void desconectar() {
	    if (con != null) {
	        try {
	            con.close();
	            //System.out.println("Conexión cerrada");
	        } catch (SQLException e) {
	            System.out.println("Error al cerrar la conexión:");
	            e.printStackTrace();
	        }
	    }
	}

	
	// ejecuta consultas select
	public ResultSet ejecutarConsulta(String consultaSQL) {
	    ResultSet resultado = null;

	    try {
	        if (con == null || con.isClosed()) {
	            con = conectar();
	        }
	        Statement sentencia = con.createStatement();
	        resultado = sentencia.executeQuery(consultaSQL);
	    } catch (SQLException e) {
	        System.out.println("Error al ejecutar la consulta:");
	        e.printStackTrace();
	    }

	    return resultado;
	}
	
	/*
	public static void main(String[] args) {
		ConexionBD x = new ConexionBD();
		x.conectar();
		ResultSet rs;
		rs = x.ejecutarConsulta("select * from alumnos");
		try {
			while (rs.next()) {
			    System.out.println("CI: " + rs.getInt("ci") + " - Nombre: " + rs.getString("nombre"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		x.desconectar();	
	}
	*/
}



