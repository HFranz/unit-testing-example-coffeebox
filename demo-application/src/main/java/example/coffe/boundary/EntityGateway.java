package example.coffe.boundary;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

public interface EntityGateway<T> {

	Optional<T> findById(UUID id);

	void store(T entity);
	
	Stream<T> findAll();
	
	UUID getNewId();

}