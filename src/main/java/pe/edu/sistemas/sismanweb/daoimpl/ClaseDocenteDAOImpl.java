package pe.edu.sistemas.sismanweb.daoimpl;

import org.springframework.stereotype.Repository;

import pe.edu.sistemas.sismanweb.dao.ClaseDocenteDAO;
import pe.edu.sistemas.sismanweb.domain.Clase;

@Repository
public class ClaseDocenteDAOImpl extends AbstractDAOImpl<Clase, Short> implements ClaseDocenteDAO{

	protected ClaseDocenteDAOImpl() {
		super(Clase.class);
		// TODO Auto-generated constructor stub
	}

}
