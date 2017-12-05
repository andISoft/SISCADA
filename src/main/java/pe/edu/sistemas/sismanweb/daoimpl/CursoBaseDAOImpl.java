package pe.edu.sistemas.sismanweb.daoimpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.sistemas.sismanweb.dao.CursoBaseDAO;
import pe.edu.sistemas.sismanweb.domain.CursoBase;

@Repository
public class CursoBaseDAOImpl extends AbstractDAOImpl<CursoBase, Integer> implements CursoBaseDAO{
		
	protected CursoBaseDAOImpl() {
		super(CursoBase.class);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	@Transactional(propagation=Propagation.MANDATORY)
	public List<CursoBase> findCursoBaseByNombre(String nombre) {
		List<CursoBase> listaCursoBase = null;
		Query query = null;
		try{
			query = getCurrentSession().createQuery("from CursoBase where cursobNombre like '%"+nombre+"%'");
			listaCursoBase = (List<CursoBase>)query.list();					
		}catch (HibernateException he) {
			he.printStackTrace();
		}
		return listaCursoBase;
	}

	@Override
	@Transactional(propagation=Propagation.MANDATORY)
	public CursoBase findCursoBaseByCodigoByPlan(String codigo, Integer idplan) {
		CursoBase cursoBase = null;
		Query query = null;
		try{
			query = getCurrentSession().createQuery("from CursoBase where cursobCodigo = :codigo and plan.idplan = :idplan").setMaxResults(1);
			query.setParameter("codigo", codigo);
			query.setParameter("idplan", idplan);
			cursoBase = (CursoBase)query.uniqueResult();
		}catch (HibernateException he) {
			he.printStackTrace();
		}
		return cursoBase;
	}
	
	@Override
	@SuppressWarnings("unchecked")	
	@Transactional(propagation=Propagation.MANDATORY)
	public List<CursoBase> findCursoBaseSinConjunto(){
		List<CursoBase> listBase= null;
		Query query = null;
		try{
		query = getCurrentSession().createQuery("select b FROM CursoConjunto c RIGHT JOIN c.cursoBase b  WHERE b.idcursoGeneral NOT IN (select b.cursoBase.idcursoGeneral from CursoConjunto b  ) ");
		
			listBase = (List<CursoBase>)query.list();	
		}catch(HibernateException he){
			he.printStackTrace();
		}
		return listBase;
	}

	@Override
	@SuppressWarnings("unchecked")	
	@Transactional(propagation=Propagation.MANDATORY)
	public List<CursoBase> obtenerCursosxCod(String valor, String filtro) {
		List<CursoBase> curso = null;
		Query query = null;
		
		try{
			query = getCurrentSession().createQuery("select c from CursoBase as c where c." + filtro + " LIKE '%"+valor+"%'");
			
		curso = (List<CursoBase>)query.list();	
		}catch(HibernateException he){
			he.printStackTrace();
		}		
		return curso;
	}

}
