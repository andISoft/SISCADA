package pe.edu.sistemas.sismanweb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.sistemas.sismanweb.dao.PersonaDAO;
import pe.edu.sistemas.sismanweb.domain.Persona;

@Service
@Transactional
public class PersonaService {
	
	@Autowired
	PersonaDAO personaDao;
	
	public void insertarPersona(Persona persona){
		personaDao.save(persona);
	}
	
	public Persona obtenerPersonaxCodigo(String codigo){
		return personaDao.findPersonaByCodigo(codigo);
	}
	
	public void actualizarPersona(Persona persona){
		personaDao.update(persona);
	}
	
	public void eliminarPersona(Persona persona){
		personaDao.delete(persona);
	}
	
	public List<Persona> obtenerPersonas(){
		return personaDao.findAll();
	}
	
	public Persona obtenerPersonaXID(Integer idPersona){
		return personaDao.findById(idPersona);
	}
	
	

}
