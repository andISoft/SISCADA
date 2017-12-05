package pe.edu.sistemas.sismanweb.daoimpl;

import org.springframework.stereotype.Repository;

import pe.edu.sistemas.sismanweb.dao.TipoAlumnoDAO;
import pe.edu.sistemas.sismanweb.domain.TipoAlumno;

@Repository
public class TipoAlumnoDAOImpl extends AbstractDAOImpl<TipoAlumno, Integer> implements TipoAlumnoDAO {

	protected TipoAlumnoDAOImpl() {
		super(TipoAlumno.class);
	}	

}
