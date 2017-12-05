package pe.edu.sistemas.sismanweb.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pe.edu.sistemas.sismanweb.domain.Persona;
import pe.edu.sistemas.sismanweb.services.PersonaService;
import pe.edu.sistemas.sismanweb.util.VariablesGlobales;

@Controller
public class LoginController {
	
	private static final Log logger = LogFactory.getLog(LoginController.class);
	
	@Autowired
	PersonaService personaService;
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public ModelAndView mostrarlogin(@RequestParam(name="error",required=false) String error,
			@RequestParam(name="logout",required=false) String logout){
		ModelAndView mav = new ModelAndView("login");
		mav.addObject("persona", new Persona());
		mav.addObject("error", error);
		mav.addObject("logout", logout);
		logger.info("-- Retornando vista Login --"+error);
		return mav;
	}
/*
	@RequestMapping(value="/validar",method=RequestMethod.POST)
	public RedirectView validarLogin(@ModelAttribute("persona") Persona persona){
		Persona resultado = personaService.obtenerPersonaXID(5237); //comprobar nulidad 4504
		logger.info("Recibiendo persona: "+persona.getPersonaCodigoSistema()+" -- Se obtuvo una persona :"+resultado.getPersonaCodigoSistema());
		if(!resultado.getPersonaCodigoSistema().equals(persona.getPersonaCodigoSistema())){
			return new RedirectView("/sismanweb/login?error");			
		}else{			
			return new RedirectView("/sismanweb/home");
		}	
	}
*/	
	@RequestMapping(value="/home",method=RequestMethod.GET)
	public String home(Model model){
		model.addAttribute("titulo","Inicio");
		return VariablesGlobales.HOME;
	}
	
}
