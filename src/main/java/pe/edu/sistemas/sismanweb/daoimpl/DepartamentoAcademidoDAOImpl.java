package pe.edu.sistemas.sismanweb.daoimpl;

import org.springframework.stereotype.Repository;

import pe.edu.sistemas.sismanweb.dao.DepartamentoAcademicoDAO;
import pe.edu.sistemas.sismanweb.domain.DepartamentoAcademico;

@Repository
public class DepartamentoAcademidoDAOImpl extends AbstractDAOImpl<DepartamentoAcademico, Integer> implements DepartamentoAcademicoDAO {

	protected DepartamentoAcademidoDAOImpl() {
		super(DepartamentoAcademico.class);
		// TODO Auto-generated constructor stub
	}
	

}
