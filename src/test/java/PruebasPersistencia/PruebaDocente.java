package PruebasPersistencia;

import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.sistemas.sismanweb.dao.CursoBaseDAO;
import pe.edu.sistemas.sismanweb.dao.CursoConjuntoDAO;
import pe.edu.sistemas.sismanweb.dao.PlanDAO;
import pe.edu.sistemas.sismanweb.domain.CursoBase;
import pe.edu.sistemas.sismanweb.domain.CursoConjunto;
import pe.edu.sistemas.sismanweb.domain.Docente;
import pe.edu.sistemas.sismanweb.domain.Plan;
import pe.edu.sistemas.sismanweb.services.DocenteService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class PruebaDocente {
	
	@Autowired public DocenteService docenteService;
	
	@Autowired public CursoBaseDAO cursoBaseDao;
	
	@Autowired public CursoConjuntoDAO cursoConjuntoDao;
	
	@Autowired public PlanDAO planDao;
		

	@Test
	@Ignore
	@Transactional
	public void seMuestraPersonas() {	
		
		List<Docente> lista = docenteService.obtenerDocentes();
		
		for(Docente p : lista){
			System.out.print(p.getPersona().getPersonaCodigo()+" : ");
			System.out.print(p.getCategoriaDocente()+" : ");
			System.out.print(p.getClase()+" : ");
			System.out.print(p.getDepartamentoAcademico().getDepartamentoAcademicoNombre()+" : ");
			System.out.println(p.getPersona().getPersonaNombre());
		}	
		
		Assert.assertEquals(10,lista.size());

	}
	
	@Test
	@Ignore
	@Transactional
	public void seAgregaDocente(){
		
	}
	
	@Test
	@Ignore
	@Transactional
	public void seMuestraCursoBase(){
		List<Plan> planes = planDao.findAll();
		System.out.println(planes.size());
		
		CursoBase cb = new CursoBase();
		cb.setCursobCodigo("405");
		cb.setCursobNombre("miCurso");
		cb.setCursobCiclo(1);
		cb.setCursobCreditos(4);
		cb.setPlan(planes.get(0));
		
		CursoConjunto cc = new CursoConjunto();
		cc.setCursoBase(cb);
		cc.setCursocNombre(cb.getCursobNombre());
		cc.setCursocCodcomun(1);
		
		int id = cursoConjuntoDao.saveWithReturnId(cc);
		//int id = cursoBaseDao.agregarCursoBase(cb);
		System.out.println(id);
	
	}
	
	
	@Test
	@Ignore
	@Transactional
	public void mostrarCurso(){
		List<CursoBase> cursoBase = cursoBaseDao.findCursoBaseByNombre("Curso");
		
		System.out.println(cursoBase.size());
		
		for(CursoBase cb: cursoBase){
			System.out.print(cb.getIdcursoGeneral()+" -- "+cb.getCursobCodigo()+" -- "+cb.getCursobNombre()+" -- "+cb.getCursobCreditos()+" -- "+cb.getPlan().getPlanNombre());
			System.out.println(" -- " +cb.getCursoConjuntos().size());
			
		}	
		
		
	}
	
	@Test
	@Ignore
	@Transactional
	public void PlanVsDepAcad(){
		List<CursoBase> cursoBase = cursoBaseDao.findCursoBaseByNombre("Curso");
		
		System.out.println(cursoBase.size());
		
		for(CursoBase cb: cursoBase){
			System.out.print(cb.getIdcursoGeneral()+" -- "+cb.getCursobCodigo()+" -- "+cb.getCursobNombre()+" -- "+cb.getCursobCreditos()+" -- "+cb.getPlan().getPlanNombre());
			System.out.println(" -- " +cb.getCursoConjuntos().size());
			
		}	
		
		
	}
	
	
	
	

}
