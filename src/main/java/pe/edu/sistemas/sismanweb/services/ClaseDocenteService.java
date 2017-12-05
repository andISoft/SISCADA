package pe.edu.sistemas.sismanweb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.sistemas.sismanweb.dao.ClaseDocenteDAO;
import pe.edu.sistemas.sismanweb.domain.Clase;

@Service
@Transactional
public class ClaseDocenteService {
	
	@Autowired
	ClaseDocenteDAO claseDocenteDao;

	public List<Clase> obtenerClasesDeDocentes(){
		return claseDocenteDao.findAll();
	}
	
	public Clase obtenerClaseDeDocenteXID(Short idClaseDocente){
		return claseDocenteDao.findById(idClaseDocente);
	}
	
}
