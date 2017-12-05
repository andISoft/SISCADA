package pe.edu.sistemas.sismanweb.daoimpl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.sistemas.sismanweb.dao.AbstractDAO;

public abstract class AbstractDAOImpl<Entity, I extends Serializable> implements AbstractDAO<Entity, I> {
	//Probar sin try - catch
	private Class<Entity> entityClass;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	protected AbstractDAOImpl(Class<Entity> entityClass){
		this.entityClass = entityClass;
	}
	
	public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

	@Override
	@Transactional(propagation=Propagation.MANDATORY)
	public void save(Entity entity) {
		try{
			getCurrentSession().save(entity);
		}catch(HibernateException he){
			he.printStackTrace();
		}	
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation=Propagation.MANDATORY)
	public I saveWithReturnId(Entity entity) {
		I result = null;
		try{
			result = (I) getCurrentSession().save(entity);
		}catch(HibernateException he){
			he.printStackTrace();
		}
		return result;
	}

	@Override
	@Transactional(propagation=Propagation.MANDATORY)
	public void update(Entity entity) {
		try{
			getCurrentSession().update(entity);
		}catch(HibernateException he){
			he.printStackTrace();
		}			
	}

	@Override
	@Transactional(propagation=Propagation.MANDATORY)
	public void delete(Entity entity) {
		try{
			getCurrentSession().delete(entity);
		}catch(HibernateException he){
			he.printStackTrace();
		}	
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation=Propagation.MANDATORY)
	public Entity findById(I id) {
		Entity result = null;
		try{
			result = (Entity) getCurrentSession().get(entityClass, id);
		}catch(HibernateException he){
			he.printStackTrace();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation=Propagation.MANDATORY)
	public List<Entity> findAll() {
		List<Entity> result = null;
		try{
			result = (List<Entity>)getCurrentSession().createQuery("from "+entityClass.getName()).setMaxResults(20).list();
		}catch(HibernateException he){
			he.printStackTrace();
		}
		return result;		
	}	

}
