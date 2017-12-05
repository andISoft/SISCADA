package pe.edu.sistemas.sismanweb.daoimpl;

import org.springframework.stereotype.Repository;

import pe.edu.sistemas.sismanweb.dao.PlanDAO;
import pe.edu.sistemas.sismanweb.domain.Plan;

@Repository
public class PlanDAOImpl extends AbstractDAOImpl<Plan, Integer> implements PlanDAO {

	protected PlanDAOImpl() {
		super(Plan.class);
	}
		
}
