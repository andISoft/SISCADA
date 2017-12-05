package pe.edu.sistemas.sismanweb.daoimpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.sistemas.sismanweb.dao.UsuarioDAO;
import pe.edu.sistemas.sismanweb.domain.Usuario;

@Repository
public class UsuarioDAOImpl implements UsuarioDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
	
	@Override
	@SuppressWarnings("unchecked")	
	@Transactional(propagation=Propagation.MANDATORY)
	public List<Usuario> obtenerUsuarioxCodSis(String codigo) {
		List<Usuario> usuario = null;
		Query query = null;
		try{
			//query = session.createQuery("select u from Usuario as u join u.persona as p where u.sistemaIdSistema=4 and p.personaCodigoSistema=:codigo");
			query = getCurrentSession().createQuery("select u from Usuario as u where u.sistemaIdSistema=4 and u.persona.personaCodigoSistema=:codigo");
			query.setParameter("codigo", codigo);
			usuario = (List<Usuario>)query.list();	
		}catch(HibernateException he){
			he.printStackTrace();
		}		
		return usuario;
	}

}
