package pe.edu.sistemas.sismanweb.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import pe.edu.sistemas.sismanweb.dao.AlumnoDAO;
import pe.edu.sistemas.sismanweb.dao.PersonaDAO;
import pe.edu.sistemas.sismanweb.dao.PlanDAO;
import pe.edu.sistemas.sismanweb.dao.TipoAlumnoDAO;
import pe.edu.sistemas.sismanweb.domain.Alumno;
import pe.edu.sistemas.sismanweb.domain.Persona;
import pe.edu.sistemas.sismanweb.services.modelform.AlumnoModelForm;

@Service
@Transactional
public class AlumnoService {
	
	protected final Log logger = LogFactory.getLog(AlumnoService.class);
	public static final ObjectMapper JSON_MAPPER = new ObjectMapper();
	
	@Autowired private AlumnoDAO alumnoDao;	
	@Autowired private PlanDAO planDao;	
	@Autowired private TipoAlumnoDAO tipoAlumnoDao;
	@Autowired private PersonaDAO personaDao;
	
	
	public boolean insertarAlumno(Alumno alumno){
		Persona persona = personaDao.findPersonaByCodigo(alumno.getPersona().getPersonaCodigo());
		if(persona!=null){
			return true;
		}else{
			alumnoDao.save(alumno);
			return false;
		}		
	}
	
	
	public boolean actualizarAlumno(Alumno alumno, Persona persona_codigo){
		if(persona_codigo!=null){
			
			if(persona_codigo.getIdPersona().intValue()!=alumno.getPersona().getIdPersona().intValue() ){
				System.out.println(persona_codigo.getIdPersona());
				System.out.println(alumno.getPersona().getIdPersona());
				//Significa que hay otro usuario con el mismo codigo
				System.out.println("El codigo de persona ya existe en otro alumno");
				return true;
			}else{
				//Ocurrio que: 1. No hubo conflicto de codigo 2.- Persona_id = Persona_codigo
				System.out.println("Alumno actualizado 1");
				alumnoDao.update(alumno);
				return false;
			}
		}else{
			//Ocurrio que: 1. No hubo conflicto de codigo
			System.out.println("Alumno actualizado 2");
			alumnoDao.update(alumno);
			return false;
		}
	}
	
	
	public void eliminarAlumno(Alumno alumno){
		alumnoDao.delete(alumno);
	}

	
	public List<Alumno> obtenerAlumnos(){
		List<Alumno> resultado = alumnoDao.findAll();
		for(Alumno alu: resultado){
			alu.getPersona().getPersonaNombre();
		}		
		return resultado;
	}
	
	
	public Alumno obtenerAlumnoxID(Integer idAlumno){
		Alumno alumno = alumnoDao.findById(idAlumno);
		alumno.getPersona().getPersonaNombre();
		alumno.getPlan().getIdplan();
		return alumno;
	}
	
	public List<Alumno> saveBulk(List<AlumnoModelForm> listaAlumnoModel){
		List<Alumno> alumnosExistentes = new ArrayList<Alumno>();
		Alumno alumno = null;
		Boolean existe;
		for(int i=0; i< listaAlumnoModel.size(); i++){
			alumno = converterToAlumno(listaAlumnoModel.get(i));
			existe = insertarAlumno(alumno);
			if(existe){
				alumnosExistentes.add(alumno);
			}			
		}
		return alumnosExistentes;		
	}
	
	
	public Alumno converterToAlumno(AlumnoModelForm formAlumnoModel){
		Alumno alumno = new Alumno();
		Persona persona = new Persona();
		if(formAlumnoModel.getIdPersona()!=0){
			persona.setIdPersona(formAlumnoModel.getIdPersona());
		}
		persona.setPersonaCodigo(formAlumnoModel.getCodigo());
		persona.setPersonaAppaterno(formAlumnoModel.getApPaterno());
		persona.setPersonaApmaterno(formAlumnoModel.getApMaterno());
		persona.setPersonaNombre(formAlumnoModel.getNombre());
		persona.setPersonaSexo(formAlumnoModel.getSexo());
		persona.setPersonaDni(formAlumnoModel.getDni());
		persona.setPersonaTelefono(formAlumnoModel.getTelefono());
		persona.setPersonaCorreo(formAlumnoModel.getCorreo());
		persona.setPersonaDireccion(formAlumnoModel.getDireccion());
		persona.setPersonaCodigoSistema(formAlumnoModel.getCodigo());
		persona.setPersonaPasswordSistema(formAlumnoModel.getCodigo());
		persona.setPersonaPasswordSistema2(" ");
		alumno.setIdAlumno(formAlumnoModel.getIdAlumno());
		alumno.setPersona(persona);
		alumno.setAlumnoActivo(1);
		alumno.setTipoAlumno(tipoAlumnoDao.findById(1));
		alumno.setPlan(planDao.findById(formAlumnoModel.getIdPlan()));	
		return alumno;
	}
	
	
	public AlumnoModelForm converterToAlumnoModelForm(Alumno alumno){
		AlumnoModelForm formAlumnModel = new AlumnoModelForm();
		Persona persona = alumno.getPersona();
		formAlumnModel.setIdAlumno(alumno.getIdAlumno());
		formAlumnModel.setIdPersona(alumno.getPersona().getIdPersona());
		formAlumnModel.setIdPlan(alumno.getPlan().getIdplan());
		formAlumnModel.setNombre(persona.getPersonaNombre());
		formAlumnModel.setApPaterno(persona.getPersonaAppaterno());
		formAlumnModel.setApMaterno(persona.getPersonaApmaterno());
		formAlumnModel.setCodigo(persona.getPersonaCodigo());
		formAlumnModel.setCorreo(persona.getPersonaCorreo());
		formAlumnModel.setDireccion(persona.getPersonaDireccion());
		formAlumnModel.setDni(persona.getPersonaDni());
		formAlumnModel.setSexo(persona.getPersonaSexo());
		formAlumnModel.setTelefono(persona.getPersonaTelefono());
		
		return formAlumnModel;
	
	}
	

	public List<AlumnoModelForm> buscarAlumnosxParam(String valor, String filtro){
		AlumnoModelForm formAlumnModel;
		
		List<AlumnoModelForm> alumnosFormCodigo = new ArrayList<AlumnoModelForm>();
		switch(filtro){
		case"1":	filtro="personaCodigo";break;
		case"2":	filtro="personaNombre";break;
		case"3":	filtro="personaAppaterno";break;
		case"4":	filtro="personaApmaterno";break;
		
			
		}
		
		List<Alumno> alumnosCodigo = alumnoDao.obtenerAlumnosxCod(valor,filtro);
		
		for(Alumno alumno : alumnosCodigo){
			alumno.getPersona().getPersonaNombre();
			alumno.getPlan().getPlanNombre();
			formAlumnModel = converterToAlumnoModelForm(alumno);
			alumnosFormCodigo.add(formAlumnModel);
		}
		
		return alumnosFormCodigo;
		
	}
	
}
