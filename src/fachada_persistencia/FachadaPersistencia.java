package fachada_persistencia;

import fachada_logica.FachadaLogica;

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

	
	
	
	
}
