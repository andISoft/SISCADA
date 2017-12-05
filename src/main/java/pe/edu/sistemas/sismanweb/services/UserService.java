package pe.edu.sistemas.sismanweb.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.sistemas.sismanweb.dao.UsuarioDAO;
import pe.edu.sistemas.sismanweb.domain.Persona;
import pe.edu.sistemas.sismanweb.domain.Usuario;

@Service
@Transactional
public class UserService implements UserDetailsService{
	
	@Autowired
	UsuarioDAO usuarioDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<Usuario> usersByPersona = usuarioDao.obtenerUsuarioxCodSis(username);
		List<GrantedAuthority> authorities = buildAuthorities(usersByPersona);
		Persona usuario = usersByPersona.get(0).getPersona();
		User user = new User(usuario.getPersonaCodigoSistema()
				, usuario.getPersonaPasswordSistema(), true, true, true, true, authorities);
		return user;
	}
	
	private List<GrantedAuthority> buildAuthorities(List<Usuario> usersByPersona){
		List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
		for(Usuario usu : usersByPersona){
			roles.add(new SimpleGrantedAuthority(usu.getTipoUsuario().getNombreTipoUsuario()));
		}
		return roles;
	}

}
