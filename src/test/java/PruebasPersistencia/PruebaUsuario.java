package PruebasPersistencia;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.sistemas.sismanweb.dao.UsuarioDAO;
import pe.edu.sistemas.sismanweb.domain.Usuario;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class PruebaUsuario {
	
	@Autowired
	UsuarioDAO usuarioDao;
	
	@Test
	//@Ignore
	@Transactional
	public void buscarUsuario(){
		List<Usuario> usuario = usuarioDao.obtenerUsuarioxCodSis("12200059");
		for(Usuario u : usuario){
			System.out.println("["+u.getIdUsuario()+" -- "+u.getUsuarioActivo()+" -- "+u.getSistemaIdSistema()+" -- "+u.getTipoUsuario().getIdtipoUsuario()
					+" -- "+u.getPersona().getIdPersona()+" -- "+u.getPersona().getPersonaNombre()+" -- "+u.getPersona().getPersonaCodigoSistema()+"]");
		}
		
	}

}
