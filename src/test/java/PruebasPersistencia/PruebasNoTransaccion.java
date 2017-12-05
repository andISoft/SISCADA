package PruebasPersistencia;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.sistemas.sismanweb.domain.CategoriaDocente;
import pe.edu.sistemas.sismanweb.domain.Plan;
import pe.edu.sistemas.sismanweb.services.CategoriaDocenteService;
import pe.edu.sistemas.sismanweb.services.PlanService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class PruebasNoTransaccion {
	
	@Autowired
	CategoriaDocenteService categoriaDocenteService;
	
	@Autowired
	PlanService planService;
	
	/**
	 * id: Clave unica de la categoria
	 * name: Nombre de la categoria
	 * Docentes: A�adir cd.getDocentes.size().
	 * 			 Para mostrar los docentes se tiene que evitar cerrar la sesion. 
	 */
	
	@Test
	@Transactional
	public void seMuestraCategoriasDocente(){
		List<CategoriaDocente> resultado = categoriaDocenteService.obtenerCategorias();
		int i = 0;
		for(CategoriaDocente cd: resultado){
			System.out.println(cd.getIdecategoriaDocente()+" -- "+cd.getCategoriaDocenteNombre());
			i++;
		}
		
		Assert.assertEquals(i, resultado.size());
		
	}
	
	@Test
	@Transactional
	public void seMuestraPlanes(){
		List<Plan> resultado = planService.obtenerPlanes();
		int i = 0;
		for(Plan pl: resultado){
			System.out.println(pl.getPlanNombre()+" -- "+pl.getEscuelaIdescuela());
			i++;
		}		
		Assert.assertEquals(i, resultado.size());		
	}
	

}
