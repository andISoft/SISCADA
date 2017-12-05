package pe.edu.sistemas.sismanweb.daoimpl;

import org.springframework.stereotype.Repository;

import pe.edu.sistemas.sismanweb.dao.CategoriaDocenteDAO;
import pe.edu.sistemas.sismanweb.domain.CategoriaDocente;

@Repository
public class CategoriaDocenteDAOImpl extends AbstractDAOImpl<CategoriaDocente, Short> implements CategoriaDocenteDAO{

	protected CategoriaDocenteDAOImpl() {
		super(CategoriaDocente.class);
	}
	
	

}
