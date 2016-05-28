package se.skaro.hexwatch.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import se.skaro.hexwatch.data.entity.Event;
import se.skaro.hexwatch.data.repository.EventRepository;

@RestController
@ExposesResourceFor(Event.class)
@RequestMapping("/events")
public class EventController {

	private static final Long END_DEFAULT_MILLIS = 2419200000L; //28 days

	@Autowired
	private EventRepository repository;

	// GET All Events
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Map<String, List<Event>>> getAll(@RequestParam Optional<Long> start,
			@RequestParam Optional<Long> end, @RequestParam Optional<String> user) {

		List<Event> eventList = repository.findEventsWithParams(getStartOptionalAsLong(start), getEndOptionalAsLong(end),
				getStringOptionalAsString(user));
		eventList.forEach(event -> {
			addHATEOASLinks(event);
		});
		return new ResponseEntity<>(Collections.singletonMap("events", eventList), HttpStatus.OK);
	}

	// GET Event by ID
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Event> getById(@PathVariable("id") Long id) {
		Event event = repository.findOne(id);
		if (event != null) {
			addHATEOASLinks(event);
			return new ResponseEntity<>(event, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}

	// GET All Tournaments
	@RequestMapping(value = "/tournaments", method = RequestMethod.GET)
	public ResponseEntity<Map<String, List<Event>>> getAllTournaments(@RequestParam Optional<Long> start,
			@RequestParam Optional<Long> end, @RequestParam Optional<String> user,
			@RequestParam Optional<String> format) {
		List<Event> events = repository.findTournamentWithParams(getStartOptionalAsLong(start),
				getEndOptionalAsLong(end), getStringOptionalAsString(user), getStringOptionalAsString(format));

		events.forEach(event -> {
			addHATEOASLinks(event);
			addHATEOASLinksEventType(event, "tournaments");
		});
		return new ResponseEntity<>(Collections.singletonMap("events", events), HttpStatus.OK);
	}

	// GET All Streams
	@RequestMapping(value = "/streams", method = RequestMethod.GET)
	public ResponseEntity<Map<String, List<Event>>> getAllStreams(@RequestParam Optional<Long> start,
			@RequestParam Optional<Long> end, @RequestParam Optional<String> user,
			@RequestParam Optional<String> channel) {
		List<Event> events = repository.findStreamWithParams(getStartOptionalAsLong(start), getEndOptionalAsLong(end),
				getStringOptionalAsString(user), getStringOptionalAsString(channel));

		events.forEach(event -> {
			addHATEOASLinks(event);
			addHATEOASLinksEventType(event, "streams");
			addHATEOASLinkTwitchChannel(event);
		});
		return new ResponseEntity<>(Collections.singletonMap("events", events), HttpStatus.OK);
	}

	// PUT Tournament
	@RequestMapping(value = "/tournaments", method = RequestMethod.POST)
	public ResponseEntity<Event> putTournament(@RequestParam String user, @RequestParam String format,
			@RequestParam Long start, @RequestParam Long end, @RequestParam String description) {
		Event event = new Event(start, end, user, description, null, "tournament", format);
		event = repository.save(event);
		addHATEOASLinks(event);
		return new ResponseEntity<>(event, HttpStatus.CREATED);
	}

	// PUT Stream
	@RequestMapping(value = "/streams", method = RequestMethod.POST)
	public ResponseEntity<Event> putStream(@RequestParam String user, @RequestParam String channel,
			@RequestParam Long start, @RequestParam Long end, @RequestParam String description) {
		Event event = new Event(start, end, user, description, channel, "stream", null);
		event = repository.save(event);
		addHATEOASLinks(event);
		return new ResponseEntity<>(event, HttpStatus.CREATED);
	}

	// Add HATEOAS links to Event
	private Event addHATEOASLinks(Event event) {
		Link self = linkTo(EventController.class).slash(event.getEventId()).withSelfRel();
		Link events = linkTo(EventController.class).slash(event.getId()).withRel("events");
		event.add(self);
		event.add(events);
		return event;
	}
	
	private Event addHATEOASLinksEventType(Event event, String type) {
		Link tournaments = linkTo(EventController.class).slash(type).withRel(type);
		event.add(tournaments);
		return event;
	}
	
	private Event addHATEOASLinkTwitchChannel (Event event){
		Link channel = new Link("http://www.twitch.tv/"+event.getChannel().toLowerCase()).withRel("channel");
		event.add(channel);
		return event;
	}

	// Optional Checkers
	private Long getStartOptionalAsLong(Optional<Long> start) {
		if (start.isPresent()) {
			return start.get();
		}
		return 0L;
	}

	private Long getEndOptionalAsLong(Optional<Long> end) {
		if (end.isPresent()) {
			return end.get();
		}
		return (System.currentTimeMillis() + (END_DEFAULT_MILLIS));
	}

	private String getStringOptionalAsString(Optional<String> stringOptional) {
		if (stringOptional.isPresent()) {
			return stringOptional.get();
		}
		return "";
	}

}
