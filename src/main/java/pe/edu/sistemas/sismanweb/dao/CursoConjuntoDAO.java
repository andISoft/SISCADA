package pe.edu.sistemas.sismanweb.dao;

import java.util.List;

import pe.edu.sistemas.sismanweb.domain.CursoConjunto;

public interface CursoConjuntoDAO extends AbstractDAO<CursoConjunto, Integer>{
	
	public CursoConjunto findCursoConjuntoByNombre(String nombre);
	
	public Integer findCodigoMaximo();
	
	public List<CursoConjunto> findCursosConjuntos();

}
