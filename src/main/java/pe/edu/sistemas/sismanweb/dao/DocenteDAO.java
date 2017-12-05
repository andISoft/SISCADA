package pe.edu.sistemas.sismanweb.dao;

import java.util.List;

import pe.edu.sistemas.sismanweb.domain.Docente;

public interface DocenteDAO extends AbstractDAO<Docente, Integer>{
	
	public List<Docente> obtenerDocentesxCod(String valor, String filtro);

}
