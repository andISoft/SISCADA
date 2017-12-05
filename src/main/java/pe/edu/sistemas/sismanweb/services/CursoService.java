package pe.edu.sistemas.sismanweb.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.sistemas.sismanweb.dao.CursoBaseDAO;
import pe.edu.sistemas.sismanweb.dao.CursoConjuntoDAO;
import pe.edu.sistemas.sismanweb.dao.PlanDAO;
import pe.edu.sistemas.sismanweb.domain.CursoBase;
import pe.edu.sistemas.sismanweb.domain.CursoConjunto;
import pe.edu.sistemas.sismanweb.domain.Plan;
import pe.edu.sistemas.sismanweb.services.modelform.CursoModelForm;

@Service
@Transactional
public class CursoService {
	
	@Autowired private CursoBaseDAO cursoBaseDao;
	@Autowired private CursoConjuntoDAO cursoConjuntoDao;
	@Autowired private PlanDAO planDao;
	
	private static final Log logger = LogFactory.getLog(CursoService.class);
	
	public List<CursoBase> obtenerCursosBase(){
		List<CursoBase> resultado = cursoBaseDao.findAll();
		for(CursoBase cb : resultado){
			cb.getPlan().getPlanNombre();
		}
		return resultado;
	}
	
	public boolean insertarCurso(CursoBase cursoBase){
		CursoBase cursoBaseExiste;
		
		cursoBaseExiste = cursoBaseDao.findCursoBaseByCodigoByPlan(cursoBase.getCursobCodigo(), cursoBase.getPlan().getIdplan());
		
		if(cursoBaseExiste != null){
			logger.info("YA EXISTE UN CURSO CON EL MISMO CODIGO Y PLAN");
			return true;
		}else{
			
			cursoBaseDao.save(cursoBase);
			logger.info("--NUEVO CURSO AGREGADO-- ");
			return false;
		}
	}
	
	
	public boolean insertarCursoConjunto(CursoBase cursoBase, Integer idConjunto){
			CursoConjunto cursoConjunto = new CursoConjunto();;
			if(idConjunto==0){ // crea nuevo grupo en curso conjunto
				cursoConjunto.setCursocCodcomun(findCodigoMaximo()+1);
				cursoConjunto.setCursoBase(cursoBase);
				cursoConjunto.setCursocNombre(cursoBase.getCursobNombre());
				cursoConjuntoDao.save(cursoConjunto);
				return true;
			}else{			   // agrega curso base a curso conjunto
				CursoConjunto aux = findCursoCById(idConjunto);
				cursoConjunto.setCursocNombre(aux.getCursocNombre());
				cursoConjunto.setCursocCodcomun(aux.getCursocCodcomun());
				cursoConjunto.setCursoBase(cursoBase);
				logger.info("CURSO CONJUNTO -- "+cursoConjunto.getIdcursoConjunto()+" :: "
						+cursoConjunto.getCursocNombre());
				cursoConjuntoDao.save(cursoConjunto);
				return false;
			}		
	}
	
	public List<CursoBase> findCursoBaseSinConjunto(){
		List<CursoBase> listBase = cursoBaseDao.findCursoBaseSinConjunto();
		for(CursoBase b: listBase){
			b.getPlan().getPlanNombre();
		}
		return listBase;
	}
	
	public List<CursoConjunto> findCursosConjuntos(){
		List<CursoConjunto> listConjunto = cursoConjuntoDao.findCursosConjuntos();
		for(CursoConjunto b: listConjunto){
			b.getCursoBase().getPlan().getPlanNombre();
		}
		return listConjunto;
	}
	
	public CursoBase findCursoBById(Integer idcurso){
		CursoBase cursob = cursoBaseDao.findById(idcurso);
		return cursob;
	}
	
	public CursoConjunto findCursoCById(Integer idcurso){
		CursoConjunto cursoc = cursoConjuntoDao.findById(idcurso);
		return cursoc;
	}
	
	public Integer findCodigoMaximo(){
		return cursoConjuntoDao.findCodigoMaximo();
	}	
	
	
	public List<CursoModelForm> buscarCursosxParam(String valor, String filtro){
		CursoModelForm formCursoModel;
		
		List<CursoModelForm> cursosFormCodigo = new ArrayList<CursoModelForm>();
		switch(filtro){
			case"1":	filtro="cursobCodigo";break;
			case"2":	filtro="cursobNombre";break;
			case"3":	filtro="plan.planNombre";break;			
		}
		
		List<CursoBase> cursosCodigo = cursoBaseDao.obtenerCursosxCod(valor,filtro);
		
		for(CursoBase curso : cursosCodigo){
			curso.getPlan().getPlanNombre();
			formCursoModel = converterToCursoModelForm(curso);
			cursosFormCodigo.add(formCursoModel);
		}
		
		return cursosFormCodigo;	
	}
	
	public CursoBase coverterToCurso(CursoModelForm cursoModelForm){
		CursoBase cursoBase = new CursoBase();
		cursoBase.setCursobCodigo(cursoModelForm.getCodigo());
		cursoBase.setCursobNombre(cursoModelForm.getNombre());
		cursoBase.setCursobCiclo(cursoModelForm.getCiclo());
		cursoBase.setCursobCreditos(cursoModelForm.getCreditos());
		cursoBase.setPlan(planDao.findById(cursoModelForm.getIdPlan()));
		
		return cursoBase;
	}
	
	public CursoModelForm converterToCursoModelForm(CursoBase curso){
		CursoModelForm formCursoModel = new CursoModelForm();
		Plan plan = curso.getPlan();
		formCursoModel.setCiclo(curso.getCursobCiclo());
		formCursoModel.setCodigo(curso.getCursobCodigo());
		formCursoModel.setCreditos(curso.getCursobCreditos());
		formCursoModel.setIdPlan(plan.getIdplan());
		formCursoModel.setNombre(curso.getCursobNombre());
		formCursoModel.setPlanNombre(plan.getPlanNombre());		
		return formCursoModel;	
	}
	
	
}
