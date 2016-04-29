package se.skaro.hexwatch.data.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import se.skaro.hexwatch.data.entity.TournamentEvent;

@RepositoryRestResource(collectionResourceRel = "tournaments", path = "tournaments")
public interface TournamentRepository
		extends CrudRepository<TournamentEvent, Long>, PagingAndSortingRepository<TournamentEvent, Long> {

	List<TournamentEvent> findByFormat(@Param("format") String format);

}
