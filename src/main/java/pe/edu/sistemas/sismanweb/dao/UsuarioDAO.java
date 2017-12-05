package pe.edu.sistemas.sismanweb.dao;

import java.util.List;

import pe.edu.sistemas.sismanweb.domain.Usuario;

public interface UsuarioDAO {
	
	public List<Usuario> obtenerUsuarioxCodSis(String codigo);

}
