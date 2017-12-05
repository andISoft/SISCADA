package pe.edu.sistemas.sismanweb.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.sistemas.sismanweb.dao.CategoriaDocenteDAO;
import pe.edu.sistemas.sismanweb.dao.ClaseDocenteDAO;
import pe.edu.sistemas.sismanweb.dao.DepartamentoAcademicoDAO;
import pe.edu.sistemas.sismanweb.dao.DocenteDAO;
import pe.edu.sistemas.sismanweb.dao.PersonaDAO;
import pe.edu.sistemas.sismanweb.domain.Docente;
import pe.edu.sistemas.sismanweb.domain.Persona;
import pe.edu.sistemas.sismanweb.services.modelform.DocenteModelForm;

@Service
@Transactional
public class DocenteService {
	

	@Autowired private DocenteDAO docenteDao;	
	@Autowired private PersonaDAO personaDao;
	@Autowired private ClaseDocenteDAO claseDocenteDao;	
	@Autowired private CategoriaDocenteDAO categoriaDocenteDao;	
	@Autowired private DepartamentoAcademicoDAO departamentoAcademicoDao;
	
	
	public boolean insertarDocente(Docente docente){
		Persona persona = personaDao.findPersonaByCodigo(docente.getPersona().getPersonaCodigo());
		if(persona!=null){
			return true;
		}else{
			docenteDao.save(docente);
			return false;
		}
	}
	
	
	public boolean actualizarDocente(Docente docente, Persona persona_codigo){
		if(persona_codigo!=null){
			
			if(persona_codigo.getIdPersona().intValue()!=docente.getPersona().getIdPersona().intValue() ){
				//Significa que hay otro usuario con el mismo codigo
				System.out.println("El codigo de persona ya existe en otro docente");
				return true;
			}else{
				//Ocurrio que: 1. No hubo conflicto de codigo 2.- Persona_id = Persona_codigo
				System.out.println("Docente actualizado 1");
				docenteDao.update(docente);
				return false;
			}
		}else{
			//Ocurrio que: 1. No hubo conflicto de codigo
			System.out.println("Docente actualizado 2");
			docenteDao.update(docente);
			return false;
		}
	}
	
	public void eliminarDocente(Docente docente){
		docenteDao.delete(docente);
	}
	
	public List<Docente> obtenerDocentes(){
		List<Docente> resultado = docenteDao.findAll();
		for(Docente doc: resultado){
			doc.getPersona().getPersonaNombre();
			if(doc.getDepartamentoAcademico()!=null){
				doc.getDepartamentoAcademico().getDepartamentoAcademicoNombre();
			}
			if(doc.getCategoriaDocente()!=null){
				doc.getCategoriaDocente().getCategoriaDocenteNombre();
			}
			if(doc.getClase()!=null){
				doc.getClase().getClaseNombre();
			}			
		}
		return resultado;
	}
	
	public Docente obtenerDocenteXID(Integer idDocente){
		Docente docente = docenteDao.findById(idDocente);			
		docente.getPersona().getPersonaNombre();

		if(docente.getDepartamentoAcademico()!=null){
			docente.getDepartamentoAcademico().getDepartamentoAcademicoNombre();
		}
		if(docente.getCategoriaDocente()!=null){
			docente.getCategoriaDocente().getCategoriaDocenteNombre();
		}
		if(docente.getClase()!=null){
			docente.getClase().getClaseNombre();
		}
		return docente;
	}
	
	public List<Docente> saveBulk(List<DocenteModelForm> listaDocenteModel){
		List<Docente> docentesExistentes = new ArrayList<Docente>();
		Docente docente = null;
		Boolean existe;
		for(int i=0; i< listaDocenteModel.size(); i++){
			docente = converterToDocente(listaDocenteModel.get(i));
			existe = insertarDocente(docente);
			if(existe){
				docentesExistentes.add(docente);
			}			
		}
		return docentesExistentes;		
	}
	
	public Docente converterToDocente(DocenteModelForm formDocenteModel){
		Docente docente = new Docente();
		Persona persona = new Persona();
		if(formDocenteModel.getIdPersona()!=0){
			persona.setIdPersona(formDocenteModel.getIdPersona());
		}
		persona.setPersonaCodigo(formDocenteModel.getCodigo());
		persona.setPersonaAppaterno(formDocenteModel.getApPaterno());
		persona.setPersonaApmaterno(formDocenteModel.getApMaterno());
		persona.setPersonaNombre(formDocenteModel.getNombre());
		persona.setPersonaSexo(formDocenteModel.getSexo());
		persona.setPersonaDni(formDocenteModel.getDni());
		persona.setPersonaTelefono(formDocenteModel.getTelefono());
		persona.setPersonaCorreo(formDocenteModel.getCorreo());
		persona.setPersonaDireccion(formDocenteModel.getDireccion());
		persona.setPersonaCodigoSistema(formDocenteModel.getCodigo());
		persona.setPersonaPasswordSistema(formDocenteModel.getCodigo());
		persona.setPersonaPasswordSistema2(" ");
		docente.setIddocente(formDocenteModel.getIdDocente());
		docente.setPersona(persona);
		docente.setDocenteClave("");
		docente.setDocenteGrupoOcupacional("Profesional");
		docente.setDocenteRegular(0);		
		docente.setClase(claseDocenteDao.findById(formDocenteModel.getIdClase()));
		docente.setCategoriaDocente(categoriaDocenteDao.findById(formDocenteModel.getIdCategoria()));
		docente.setDepartamentoAcademico(departamentoAcademicoDao.findById(formDocenteModel.getIdDepAcad()));	
		
		return docente;		
	}
	
	
	public DocenteModelForm converterToDocenteModelForm(Docente docente){
		DocenteModelForm formDocenteModel = new DocenteModelForm();
		Persona persona = docente.getPersona();
		formDocenteModel.setIdDocente(docente.getIddocente());
		formDocenteModel.setIdPersona(docente.getPersona().getIdPersona());
		if(docente.getCategoriaDocente()!=null){
			formDocenteModel.setIdCategoria(docente.getCategoriaDocente().getIdecategoriaDocente());
		}
		if(docente.getClase()!=null){
			formDocenteModel.setIdClase(docente.getClase().getIdclase());
		}
		if(docente.getDepartamentoAcademico()!=null){
			formDocenteModel.setIdDepAcad((docente.getDepartamentoAcademico().getIddepartamentoAcademico()));
		}
		formDocenteModel.setNombre(persona.getPersonaNombre());
		formDocenteModel.setApPaterno(persona.getPersonaAppaterno());
		formDocenteModel.setApMaterno(persona.getPersonaApmaterno());
		formDocenteModel.setCodigo(persona.getPersonaCodigo());
		formDocenteModel.setCorreo(persona.getPersonaCorreo());
		formDocenteModel.setDireccion(persona.getPersonaDireccion());
		formDocenteModel.setDni(persona.getPersonaDni());
		formDocenteModel.setSexo(persona.getPersonaSexo());
		formDocenteModel.setTelefono(persona.getPersonaTelefono());
		
		return formDocenteModel;
	
	}
	

	public List<DocenteModelForm> buscarDocentesxParam(String valor, String filtro){
		DocenteModelForm formDocenteModel;
		
		List<DocenteModelForm> docentesFormCodigo = new ArrayList<DocenteModelForm>();
		switch(filtro){
		case"1":	filtro="personaCodigo";break;
		case"2":	filtro="personaNombre";break;
		case"3":	filtro="personaAppaterno";break;
		case"4":	filtro="personaApmaterno";break;
		//default:	filtro="idPersona";	
			
		}
		
		List<Docente> docentesCodigo = docenteDao.obtenerDocentesxCod(valor,filtro);
		
		for(Docente docente : docentesCodigo){
			docente.getPersona().getPersonaNombre();
			
			if(docente.getDepartamentoAcademico()!=null){
				docente.getDepartamentoAcademico().getDepartamentoAcademicoNombre();
			}
			if(docente.getCategoriaDocente()!=null){
				docente.getCategoriaDocente().getCategoriaDocenteNombre();
			}
			if(docente.getClase()!=null){
				docente.getClase().getClaseNombre();
			}
			formDocenteModel = converterToDocenteModelForm(docente);
			docentesFormCodigo.add(formDocenteModel);
		}
		
		return docentesFormCodigo;
		
	}
	
	
	
}
