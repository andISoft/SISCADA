package pe.edu.sistemas.sismanweb.dao;

import java.io.Serializable;
import java.util.List;

public interface AbstractDAO<Entity, I extends Serializable> {
	
	public void save(Entity entity);
	
	public I saveWithReturnId(Entity entity);
	
	public void update(Entity entity);
	
	public void delete(Entity entity);
	
	public Entity findById(I id);
	
	public List<Entity> findAll();

}
