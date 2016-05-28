package se.skaro.hexwatch.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import se.skaro.hexwatch.data.entity.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
	
	List<Event> findEventsWithParams(Long start, Long end, String user);
	
	List<Event> findTournamentWithParams(Long start, Long end, String user, String format);
	
	List<Event> findStreamWithParams(Long start, Long end, String user, String channel);


}
