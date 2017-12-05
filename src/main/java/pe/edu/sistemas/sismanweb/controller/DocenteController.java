package pe.edu.sistemas.sismanweb.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pe.edu.sistemas.sismanweb.domain.Docente;
import pe.edu.sistemas.sismanweb.domain.Persona;
import pe.edu.sistemas.sismanweb.services.CategoriaDocenteService;
import pe.edu.sistemas.sismanweb.services.ClaseDocenteService;
import pe.edu.sistemas.sismanweb.services.DepartamentoAcademicoService;
import pe.edu.sistemas.sismanweb.services.DocenteService;
import pe.edu.sistemas.sismanweb.services.PersonaService;
import pe.edu.sistemas.sismanweb.services.modelform.DocenteModelForm;
import pe.edu.sistemas.sismanweb.util.DeserealizarJSON;
import pe.edu.sistemas.sismanweb.util.Search;

@Controller
@RequestMapping("/docente")
public class DocenteController {
	
	protected final Log logger = LogFactory.getLog(DocenteController.class);
	
	@Autowired DocenteService docenteService;		
	@Autowired CategoriaDocenteService categoriaDocenteService;	
	@Autowired ClaseDocenteService claseService;	
	@Autowired DepartamentoAcademicoService departamentoAcademicoService;	
	@Autowired PersonaService personaService;
	
	List<DocenteModelForm> docentes = new ArrayList<DocenteModelForm>();
	
	@ModelAttribute("titulo")
	public String titulo(){
		return "Docente";
	}	
	
	@GetMapping("/all")
	public String verDocentes(Model model){
		model.addAttribute("search", new Search());
		model.addAttribute("clasesDoc",claseService.obtenerClasesDeDocentes());
		model.addAttribute("categoriasDoc",categoriaDocenteService.obtenerCategorias());
		model.addAttribute("depAcadDoc",departamentoAcademicoService.obtenerDepAcademicos());
		model.addAttribute("listaDocente", docentes);		
		logger.info("SE DEVUELVEN DOCENTES : " + docentes.size());
		docentes=new ArrayList<DocenteModelForm>();
		return "docente/buscador";	
	}
	
	@GetMapping({"/form","/form/{id}"})
	public String formularioDocente(Model model,@RequestParam(name="existe",required=false) String existe,
			@PathVariable(name="id",required=false)String id){
		model.addAttribute("clasesDoc",claseService.obtenerClasesDeDocentes());
		model.addAttribute("categoriasDoc",categoriaDocenteService.obtenerCategorias());
		model.addAttribute("depAcadDoc",departamentoAcademicoService.obtenerDepAcademicos());
		
		if(id!=null){		// formulario con datos a editar
			DocenteModelForm docenteModel;
			logger.info("EDITAR DOCENTE CON ID: "+id);
			docenteModel = docenteService.converterToDocenteModelForm((docenteService.obtenerDocenteXID(Integer.parseInt(id))));
			model.addAttribute("docente", docenteModel);
		}else{				// formaulario vacio
			model.addAttribute("docente", new DocenteModelForm());
		}
		model.addAttribute("existe", existe);		
		logger.info("RETORNANDO FORMULARIO DOCENTE");
		return "docente/registroIndiv";
	}
	
	
	@PostMapping("/add")
	public String agregarDocente(Model model, @ModelAttribute("docente") DocenteModelForm docentePersonaModel){	
		Docente docente = docenteService.converterToDocente(docentePersonaModel);
		logger.info("DATOS RECIBIDOS: "+ docentePersonaModel.getCodigo()+" -- IDDOCENTE:"+docentePersonaModel.getIdDocente()+" -- IDPERSONA:"+docentePersonaModel.getIdPersona());
		logger.info("AGREGANDO DATOS DE: CATEGORIA: "+ docentePersonaModel.getIdCategoria()+" -- CLASE:"+docentePersonaModel.getIdClase()+" -- DEPACAD:"+docentePersonaModel.getIdDepAcad());
		boolean existe;
		if(docentePersonaModel.getIdDocente()==0){ // agregar docente
			existe = docenteService.insertarDocente(docente);
			if(existe){
				logger.info("AGREGAR DOCENTE --- Codigo ya existente");
				return "redirect:/docente/form?existe";
			}
			logger.info("DOCENTE AGREGADO");
			//model.addAttribute("fragmento", "contentDocenteAvisoExitoIndiv");
			return "redirect:/docente/search/"+docentePersonaModel.getCodigo();
		}else{										// editar docente
			Persona persona_codigo = personaService.obtenerPersonaxCodigo(docente.getPersona().getPersonaCodigo());
			existe = docenteService.actualizarDocente(docente, persona_codigo);
			if(existe){
				logger.info("LA ACTUALIZACION NO PROCEDE");
				return "redirect:/docente/form/"+docente.getIddocente()+"?existe";
			}else{
				logger.info("DOCENTE ACTUALIZADO");
				//model.addAttribute("fragmento", "contentDocenteAvisoEdicionIndiv");
				return "redirect:/docente/search/"+docentePersonaModel.getCodigo();
			}
		}	
	}
	
	@GetMapping("/bulk")
	public String bulkDocentes(Model model){
		model.addAttribute("listaClases", claseService.obtenerClasesDeDocentes());
		model.addAttribute("listaCategorias", categoriaDocenteService.obtenerCategorias());
		model.addAttribute("listaDepAcad", departamentoAcademicoService.obtenerDepAcademicos());
		logger.info("RETORNANDO VISTA CARGA MASIVA -- DOCENTE");
		return "docente/registroGrupal";		
	}
	
	@PostMapping("/addBulk")
	public String agregarDocentes(Model model, @RequestBody String listDocente ){
		logger.info("CADENA RECIBIDA: "+listDocente);		
		JSONArray jsonArrayDocente = new JSONArray(listDocente);
		DeserealizarJSON<DocenteModelForm> deserealizador = new DeserealizarJSON<DocenteModelForm>(DocenteModelForm.class);
		List<DocenteModelForm> docentesModel = null;
		List<Docente> resultado = null;
		logger.info("CANTIDAD DE REGISTROS: "+jsonArrayDocente.length());
		
		docentesModel = deserealizador.deserealiza(jsonArrayDocente);
		
		if(jsonArrayDocente.length()!=docentesModel.size()){
			logger.error("ENVIANDO MENSAJE DE ERROR EN REGISTRO: "+(docentesModel.size()+1));
			return "docente/avisosGrupal :: contentDocenteAvisoErrorGrup";
			
		}else{
			try{
				resultado = docenteService.saveBulk(docentesModel);
				
				}catch(Exception e){
					logger.warn("ERROR EN LOS ID's");
					return "docente/avisosGrupal :: contentDocenteAvisoIdsGrup";
				}
				model.addAttribute("cantidadDocentesGuardados",(jsonArrayDocente.length()-resultado.size()));
			if(!resultado.isEmpty()){
				model.addAttribute("listaDocentesRepetidos", resultado);
				logger.warn("EXISTEN "+resultado.size()+" DOCENTES YA REGISTRADOS");
				return "docente/avisosGrupal :: contentDocenteAvisoExistenGrup";
			}else{
				logger.info("SE REGISTRO EXITOSAMENTE DOCENTES");
				return "docente/avisosGrupal :: contentDocenteAvisoExitoGrup";
			}				
		}	
	}	
	
	
	@GetMapping({"/search","/search/{cod}"})
	public String BuscarDocentes(@ModelAttribute("search") Search search,
			@PathVariable(name="cod",required=false) String codigo){		
		if(codigo != null){
			docentes = docenteService.buscarDocentesxParam(codigo,"1");
		}else{
			docentes = docenteService.buscarDocentesxParam(search.getValor(),search.getFiltro());			
		}		
		logger.info("SE ENCONTRO DocenteS: " + docentes.size());
		return "redirect:/docente/all";
	}

}
