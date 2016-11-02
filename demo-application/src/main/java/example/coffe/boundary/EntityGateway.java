package example.coffe.boundary;

import java.util.List;

public interface EntityGateway<T> {

	T findById(String id);

	void store(T entity);
	
	List<T> findAll();

}