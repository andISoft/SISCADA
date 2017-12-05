package pe.edu.sistemas.sismanweb.daoimpl;

import org.springframework.stereotype.Repository;

import pe.edu.sistemas.sismanweb.dao.CursoPeriodoDAO;
import pe.edu.sistemas.sismanweb.domain.CursoPeriodo;

@Repository
public class CursoPeriodoDAOImpl extends AbstractDAOImpl<CursoPeriodo, Integer> implements CursoPeriodoDAO{

	protected CursoPeriodoDAOImpl() {
		super(CursoPeriodo.class);
	}
	

}
