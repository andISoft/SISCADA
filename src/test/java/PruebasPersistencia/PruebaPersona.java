package PruebasPersistencia;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.sistemas.sismanweb.dao.PersonaDAO;
import pe.edu.sistemas.sismanweb.domain.Alumno;
import pe.edu.sistemas.sismanweb.domain.Persona;
import pe.edu.sistemas.sismanweb.services.AlumnoService;
import pe.edu.sistemas.sismanweb.services.modelform.AlumnoModelForm;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class PruebaPersona {
	
	@Autowired
	public PersonaDAO personaDAO;
	
	@Autowired
	public AlumnoService alumnoService;
		
	@Test
	@Ignore
	@Transactional
	public void seMuestraPersonas() {	
		
		List<Persona> lista = personaDAO.findAll();
		
		for(Persona p : lista){
			System.out.println(p.getPersonaCodigo()+" : "+p.getPersonaNombre());			
		}	
	}
	
	@Test
	@Ignore
	@Transactional
	public void seAgregaPersona() {
		Persona persona = new Persona();
		persona.setPersonaCodigo("");
		persona.setPersonaNombre("");
		persona.setPersonaCodigoSistema("");
		persona.setPersonaPasswordSistema("");
		persona.setPersonaPasswordSistema2("");
		personaDAO.save(persona);
		System.out.println("Persona Registrada");		
	}
	
	@Test
	@Ignore
	@Transactional
	public void seMuestraPersonarxID() {	
		
		Persona persona = personaDAO.findById(5830);
		if(persona!=null) System.out.println("Se encontro a "+persona.getPersonaNombre()+" "+persona.getPersonaAppaterno());
		else System.out.println("No se encuentra a la persona");
		
	}
	
	@Test
	@Ignore
	@Transactional
	public void seActualizaPersona(){
		
		Persona persona = personaDAO.findById(5831);
		persona.setPersonaAppaterno("");
		persona.setPersonaApmaterno("");
		personaDAO.update(persona);	
		
		System.out.println(persona.getPersonaCodigo()+" : "+persona.getPersonaNombre()+" : "+persona.getPersonaAppaterno()+" : "+persona.getPersonaApmaterno());		
		
	}
	
	@Test
	@Ignore
	@Transactional
	public void seEliminaPersona(){
		Persona personaAntes = personaDAO.findById(5831);
		personaDAO.delete(personaAntes);
		Persona personaDespues = personaDAO.findById(5831);
		if(personaDespues!=null) System.out.println("Se encontro a "+personaDespues.getPersonaNombre());
		else System.out.println("No se encuentra a la persona");
		
	}
	
	
	@Test
	@Ignore
	@Transactional
	public void mostrarAlumnos(){
		List<Alumno> result= alumnoService.obtenerAlumnos();
		for(Alumno al: result){
			System.out.println(al.getIdAlumno()+" -- "+al.getPersona().getPersonaNombre());
		}
		System.out.println("-------------------------------");
	}
	
	@Test
	//@Ignore
	@Transactional
	public void mostrarAlumnosxParam(){
		List<AlumnoModelForm> result =  alumnoService.buscarAlumnosxParam("An","2");
		for(AlumnoModelForm al: result){
			System.out.println(al.getCodigo()+" -- "+al.getNombre());
		}
		System.out.println("-------------------------------");
	}
	


}
