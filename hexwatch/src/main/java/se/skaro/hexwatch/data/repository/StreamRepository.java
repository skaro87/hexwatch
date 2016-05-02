package se.skaro.hexwatch.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import se.skaro.hexwatch.data.entity.StreamEvent;

@RepositoryRestResource(collectionResourceRel = "streams", path = "streams")
public interface StreamRepository extends CrudRepository<StreamEvent, Long>, PagingAndSortingRepository<StreamEvent, Long> {
	
	/*
	@Query("SELECT s FROM Event s where s.end >= :_")
	List<StreamEvent> findStreamsAfter(@Param ("_") Long currentTime);
	*/
}
