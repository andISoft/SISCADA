package pe.edu.sistemas.sismanweb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.sistemas.sismanweb.dao.DepartamentoAcademicoDAO;
import pe.edu.sistemas.sismanweb.domain.DepartamentoAcademico;

@Service
@Transactional
public class DepartamentoAcademicoService {
	
	@Autowired
	DepartamentoAcademicoDAO departamentoAcademicoDao;
	
	public List<DepartamentoAcademico> obtenerDepAcademicos(){
		return departamentoAcademicoDao.findAll();
	}
	
	public DepartamentoAcademico obtenerDepAcadXID(Integer idDepartamentoAcademico){
		return departamentoAcademicoDao.findById(idDepartamentoAcademico);
	}	

}
