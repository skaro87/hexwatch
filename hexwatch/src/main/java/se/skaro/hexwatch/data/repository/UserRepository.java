package se.skaro.hexwatch.data.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import se.skaro.hexwatch.data.entity.User;

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepository extends CrudRepository<User, Long>,  PagingAndSortingRepository<User, Long>  {
	
	 public List<User> findByName(@Param(value = "name") String name);
	   

}
