package se.skaro.hexwatch.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import se.skaro.hexwatch.data.entity.StreamEvent;

@RepositoryRestResource(collectionResourceRel = "streams", path = "streams")
public interface StreamRepository extends CrudRepository<StreamEvent, Long>, PagingAndSortingRepository<StreamEvent, Long> {

}
