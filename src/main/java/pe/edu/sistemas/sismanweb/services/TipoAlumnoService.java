package pe.edu.sistemas.sismanweb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.sistemas.sismanweb.dao.TipoAlumnoDAO;
import pe.edu.sistemas.sismanweb.domain.TipoAlumno;

@Service
@Transactional
public class TipoAlumnoService {
	
	@Autowired
	TipoAlumnoDAO tipoAlumnoDao;
	
	public List<TipoAlumno> obtenerTiposDeAlumno(){
		return tipoAlumnoDao.findAll();
	}

	public TipoAlumno obtenerTipoAlumnoXID(Integer idTipoAlumno){
		return tipoAlumnoDao.findById(idTipoAlumno);
	}
	
}
