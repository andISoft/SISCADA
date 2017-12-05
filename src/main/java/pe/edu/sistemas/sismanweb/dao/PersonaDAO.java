package pe.edu.sistemas.sismanweb.dao;

import pe.edu.sistemas.sismanweb.domain.Persona;

public interface PersonaDAO extends AbstractDAO<Persona, Integer>{
	
	public Persona findPersonaByCodigo(String codigo);

}
