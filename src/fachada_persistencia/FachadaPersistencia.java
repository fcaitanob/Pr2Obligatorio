package fachada_persistencia;

import fachada_logica.FachadaLogica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import clases_generales.*;

public class FachadaPersistencia {
	private ConexionBD cbd = new ConexionBD();
	private FachadaLogica fl = null;
	
	public FachadaPersistencia (FachadaLogica fl) {
		this.fl = fl;
	}
	

	
	public boolean eliminarAdmControlaAluBD(int ciAlumno, int ciAdmin) {
		
		boolean retorno = false;
		PreparedStatement ps = null;
		Connection con = null;
		int cantidadFilas = 0;
		
		con = cbd.conectar();
		//DELETE FROM p2pruebas01.administra WHERE (cialumno = 111) and (ciadmin = 99999999);


		String sql =  "DELETE FROM p2pruebas01.administra ";
		sql += "WHERE cialumno = ? and ciadmin = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, ciAlumno);
			ps.setInt(2, ciAdmin);
			//System.out.println(ps.toString());
			cantidadFilas = ps.executeUpdate();
			retorno = (cantidadFilas == 1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		cbd.desconectar();

		return retorno;
	}

	
	//-------------------------------------
	// Alta Alumno Interno a la BD
	//-------------------------------------
	public boolean altaAluIntBD(Interno aluInt) {
		
		boolean retorno = false;
		PreparedStatement ps = null;
		Connection con = null;
		int cantidadFilas = 0;
		
		con = cbd.conectar();
		String sql =  "INSERT INTO p2pruebas01.alumnos (CI, nombre, edad, direccion, cuotaMensual, cuotaReal) VALUES (?, ?, ?, ?, ?, ?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, aluInt.getCi());
			ps.setString(2, aluInt.getNombre());
			ps.setInt(3, aluInt.getEdad());
			ps.setString(4, aluInt.getDireccion());
			ps.setFloat(5, aluInt.getCuotaMensual());
			ps.setFloat(6,  aluInt.getCuotaReal());
			cantidadFilas = ps.executeUpdate();
			retorno = (cantidadFilas == 1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		sql =  "INSERT INTO p2pruebas01.aluinterno (CI, regAlim) VALUES (?, ?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, aluInt.getCi());
			ps.setString(2, aluInt.getRegAlim());
			cantidadFilas = ps.executeUpdate();
			retorno = (cantidadFilas == 1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		cbd.desconectar();
		return retorno;
	}
	
	//-------------------------------------
	// Alta Alumno Externo en la BD
	//-------------------------------------
	public boolean altaAluExtBD(Externo aluExt) {
		
		boolean retorno = false;
		PreparedStatement ps = null;
		Connection con = null;
		int cantidadFilas = 0;
		
		con = cbd.conectar();
		String sql =  "INSERT INTO p2pruebas01.alumnos (CI, nombre, edad, direccion, cuotaMensual, cuotaReal) VALUES (?, ?, ?, ?, ?, ?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, aluExt.getCi());
			ps.setString(2, aluExt.getNombre());
			ps.setInt(3, aluExt.getEdad());
			ps.setString(4, aluExt.getDireccion());
			ps.setFloat(5, aluExt.getCuotaMensual());
			ps.setFloat(6,  aluExt.getCuotaReal());
			cantidadFilas = ps.executeUpdate();
			retorno = (cantidadFilas == 1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		sql =  "INSERT INTO p2pruebas01.aluexterno (CI, hobby) VALUES (?, ?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, aluExt.getCi());
			ps.setString(2, aluExt.getHobby());
			cantidadFilas = ps.executeUpdate();
			retorno = (cantidadFilas == 1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		cbd.desconectar();
		return retorno;
	}

	
	
	//-------------------------------------
	// Alta Administrador a la BD
	//-------------------------------------
	public boolean altaAdministradorBD(Administrador a) {
		
		boolean retorno = false;
		PreparedStatement ps = null;
		Connection con = null;
		int cantidadFilas = 0;
		
		con = cbd.conectar();
		String sql =  "INSERT INTO p2pruebas01.administradores (CI, Comentario) VALUES (?, ?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, a.getCi());
			ps.setString(2, a.getComentarioAdm());
			cantidadFilas = ps.executeUpdate();
			retorno = (cantidadFilas == 1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		sql =  "INSERT INTO personas (CI) VALUES (?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, a.getCi());
			cantidadFilas = ps.executeUpdate();
			retorno = (cantidadFilas == 1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		cbd.desconectar();
		return retorno;
	}

	
	public boolean asignarAlumnoAAdministradorBD(int ciAlumno, int ciAdmin, LocalDate fi, LocalDate ff) {
		
		boolean retorno = false;
		PreparedStatement ps = null;
		Connection con = null;
		int cantidadFilas = 0;
		
		con = cbd.conectar();
		//INSERT INTO p2pruebas01.administra (`cialumno`, `ciadmin`, `FechaInicio`, `FechaFin`) VALUES ('11111111', '99999999', '2024-12-23', '2025-12-23');

		String sql =  "INSERT INTO p2pruebas01.administra";
		sql += "(cialumno, ciadmin, FechaInicio, FechaFin) ";
		sql += "VALUES (?, ?, ?, ?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, ciAlumno);
			ps.setInt(2, ciAdmin);
			ps.setDate(3, java.sql.Date.valueOf(fi));
			ps.setDate(4, java.sql.Date.valueOf(ff));
			
			cantidadFilas = ps.executeUpdate();
			retorno = (cantidadFilas == 1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		cbd.desconectar();

		return retorno;
	}


	
	
	
	
	//-------------------------------------
	// Baja Administrador SOLO de la BD
	// se usa para modificar no afecta la tabla administra
	//-------------------------------------
	public boolean bajaAdministradorSoloBD(int CI) {
		
		boolean retorno = false;
		PreparedStatement ps = null;
		Connection con = null;
		int cantidadFilas = 0;
		
		con = cbd.conectar();
		String sql =  "DELETE FROM p2pruebas01.administradores where CI = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, CI);
			cantidadFilas = ps.executeUpdate();
			retorno = (cantidadFilas == 1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		cbd.desconectar();

		return retorno;
	}


	//-------------------------------------
	// Baja Alumno SOLO de la BD
	// se usa para modificar no afecta la tabla administra
	//-------------------------------------
	public boolean bajaAlumnoSoloBD(int CI) {
		
		boolean retorno = false;
		PreparedStatement ps = null;
		Connection con = null;
		int cantidadFilas = 0;
		
		con = cbd.conectar();
		String sql =  "DELETE FROM p2pruebas01.alumnos where CI = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, CI);
			cantidadFilas = ps.executeUpdate();
			retorno = (cantidadFilas == 1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		sql =  "DELETE FROM p2pruebas01.aluinterno where CI = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, CI);
			cantidadFilas = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		sql =  "DELETE FROM p2pruebas01.aluexterno where CI = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, CI);
			cantidadFilas = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		cbd.desconectar();

		return retorno;
	}

	
	//-------------------------------------
	// Baja Administrador de la BD
	//-------------------------------------
	public boolean bajaAdministradorBD(int CI) {
		
		boolean retorno = false;
		PreparedStatement ps = null;
		Connection con = null;
		int cantidadFilas = 0;
		
		con = cbd.conectar();
		String sql =  "DELETE FROM p2pruebas01.administradores where CI = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, CI);
			cantidadFilas = ps.executeUpdate();
			retorno = (cantidadFilas == 1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		cbd.desconectar();

		ps = null;
		con = null;
		cantidadFilas = 0;
		
		con = cbd.conectar();
		sql =  "DELETE FROM p2pruebas01.administra where ciadmin = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, CI);
			cantidadFilas = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		cbd.desconectar();
		
		
		return retorno;
	}
	
	
	//-------------------------------------
	// Administradores desde la BD
	//-------------------------------------
	public HashMap<Integer, Administrador> cargaAdministradoresDesdeBD() {
		cbd.conectar();
		String sql = "SELECT * FROM p2pruebas01.administradores a ";
		ResultSet rs = cbd.ejecutarConsulta(sql);
		HashMap<Integer, Administrador> hmAdm = new HashMap<Integer, Administrador>();
		try {
			while (rs.next()) {
					Administrador admin = new Administrador(1);
					admin.setCi(rs.getInt("CI"));
					admin.setComentarioAdm(rs.getString("Comentario"));
					hmAdm.put(admin.getCi(), admin);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		cbd.desconectar();
		return hmAdm;
	}

	
	
	
	//-------------------------------------
	// Carga de Alumnos internos desde la BD
	//-------------------------------------
	public HashMap<Integer, Alumno> cargaInternosDesdeBD() {
		cbd.conectar();
		String sql = "SELECT * FROM p2pruebas01.alumnos a "
				+ "join p2pruebas01.aluinterno b "
				+ "on a.ci = b.ci "
				+ "order by a.ci ";
		ResultSet rs = cbd.ejecutarConsulta(sql);
		HashMap<Integer, Alumno> hmInt = new HashMap<Integer, Alumno>();
		try {
			while (rs.next()) {
					Interno aluInt = new Interno(1, "", "");
					aluInt.setCi(rs.getInt("ci"));
					aluInt.setNombre(rs.getString("nombre"));
					aluInt.setEdad(rs.getInt("edad"));
					aluInt.setDireccion(rs.getString("direccion"));
					aluInt.setCuotaMensual(rs.getFloat("cuotaMensual"));
					aluInt.setCuotaReal(rs.getFloat("cuotaReal"));
					aluInt.setRegAlim(rs.getString("regalim"));
					hmInt.put(aluInt.getCi(), aluInt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		cbd.desconectar();
		return hmInt;
	}

	//-------------------------------------
	// Carga de Alumnos externos desde la BD
	//-------------------------------------
	public HashMap<Integer, Alumno> cargaExternosDesdeBD() {
		cbd.conectar();
		String sql = "SELECT * FROM p2pruebas01.alumnos a "
				+ "join p2pruebas01.aluexterno b "
				+ "on a.ci = b.ci "
				+ "order by a.ci ";
		ResultSet rs = cbd.ejecutarConsulta(sql);
		HashMap<Integer, Alumno> hmExt = new HashMap<Integer, Alumno>();
		try {
			while (rs.next()) {
					Externo aluExt = new Externo(1, "", "");
					aluExt.setCi(rs.getInt("ci"));
					aluExt.setNombre(rs.getString("nombre"));
					aluExt.setEdad(rs.getInt("edad"));
					aluExt.setDireccion(rs.getString("direccion"));
					aluExt.setCuotaMensual(rs.getFloat("cuotaMensual"));
					aluExt.setCuotaReal(rs.getFloat("cuotaReal"));
					aluExt.setHobby(rs.getString("hobby"));
					hmExt.put(aluExt.getCi(), aluExt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		cbd.desconectar();
		return hmExt;
	}

	//-------------------------------------
	// Carga de AdmControlaAlu desde la BD
	//-------------------------------------
	public ArrayList<AdmControlaAlu> cargaACADesdeBD() {
		cbd.conectar();
		int aluCI = 0;
		int admCI = 0;
		String sql = "SELECT * FROM p2pruebas01.administra a "
				+ "order by a.cialumno ";
		ResultSet rs = cbd.ejecutarConsulta(sql);
		ArrayList<AdmControlaAlu> acaList = new ArrayList<AdmControlaAlu>();
		try {
			while (rs.next()) {
					aluCI = rs.getInt("cialumno");
					admCI = rs.getInt("ciadmin");
					AdmControlaAlu aca = new AdmControlaAlu(fl.getAlumnos().obtiene(aluCI),
							fl.getAdministradores().obtiene(admCI),
							rs.getDate("FechaInicio").toLocalDate(),
							rs.getDate("FechaFin").toLocalDate());
					acaList.add(aca);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		cbd.desconectar();
		return acaList;
	}

	
/*	
 // Pruebas con main
public static void main(String[] ar) {
	HashMap<Integer, Administrador> x = new HashMap<>();
	FachadaLogica z = new FachadaLogica();
	FachadaPersistencia y = new FachadaPersistencia(z);
	
	x = y.cargaAdministradoresDesdeBD();
	
	for(Integer i: x.keySet()) {
		System.out.print(x.get(i).toString());
		System.out.println(x.get(i).getComentarioAdm());
		System.out.println("--------------------------------------");
	}
			
}
*/	
	
}
