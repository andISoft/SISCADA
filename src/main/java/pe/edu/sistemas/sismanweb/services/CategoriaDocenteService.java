package pe.edu.sistemas.sismanweb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.sistemas.sismanweb.dao.CategoriaDocenteDAO;
import pe.edu.sistemas.sismanweb.domain.CategoriaDocente;

@Service
@Transactional
public class CategoriaDocenteService {
	
	@Autowired
	CategoriaDocenteDAO categoriaDocenteDao;
	
	public List<CategoriaDocente> obtenerCategorias(){
		return categoriaDocenteDao.findAll();
	}
	
	public CategoriaDocente obtenerCategoriaDocXID(Short idCategoriaDocente){
		return categoriaDocenteDao.findById(idCategoriaDocente);
		
	}

}
