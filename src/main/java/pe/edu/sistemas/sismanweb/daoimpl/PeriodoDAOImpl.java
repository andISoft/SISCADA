package pe.edu.sistemas.sismanweb.daoimpl;

import org.springframework.stereotype.Repository;

import pe.edu.sistemas.sismanweb.dao.PeriodoDAO;
import pe.edu.sistemas.sismanweb.domain.Periodo;

@Repository
public class PeriodoDAOImpl extends AbstractDAOImpl<Periodo, Integer> implements PeriodoDAO {

	protected PeriodoDAOImpl() {
		super(Periodo.class);
	}	

}
