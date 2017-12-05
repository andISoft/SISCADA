package pe.edu.sistemas.sismanweb.dao;

import java.util.List;

import pe.edu.sistemas.sismanweb.domain.Alumno;

public interface AlumnoDAO extends AbstractDAO<Alumno, Integer>{
		
	public List<Alumno> obtenerAlumnosxCod(String valor, String filtro);
}
