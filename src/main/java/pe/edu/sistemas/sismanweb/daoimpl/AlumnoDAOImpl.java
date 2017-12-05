package pe.edu.sistemas.sismanweb.daoimpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.sistemas.sismanweb.dao.AlumnoDAO;
import pe.edu.sistemas.sismanweb.domain.Alumno;

@Repository
public class AlumnoDAOImpl extends AbstractDAOImpl<Alumno, Integer> implements AlumnoDAO {

	protected AlumnoDAOImpl() {
		super(Alumno.class);
	}	
	
	@Override
	@SuppressWarnings("unchecked")	
	@Transactional(propagation=Propagation.MANDATORY)
	public List<Alumno> obtenerAlumnosxCod(String valor, String filtro) {
		List<Alumno> alumno = null;
		Query query = null;
		try{
			//query = session.createQuery("select u from Usuario as u join u.persona as p where u.sistemaIdSistema=4 and p.personaCodigoSistema=:codigo");
			query = getCurrentSession().createQuery("select a from Alumno as a where a.persona." + filtro + " LIKE '%"+valor+"%'");
			//query.setParameter("codigo", codigo);
			alumno = (List<Alumno>)query.list();	
		}catch(HibernateException he){
			he.printStackTrace();
		}		
		return alumno;
	}
	


}
