package se.skaro.hexwatch.data.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.springframework.hateoas.ResourceSupport;


@Entity
@Table(name = "events")
@NamedQueries({
@NamedQuery(name = "Event.findOngoingAndFutureEvents", query = "SELECT e FROM Event e WHERE e.end >= ?1"),
@NamedQuery(name = "Event.findEventsWithParams", query = "SELECT e FROM Event e WHERE e.end >= ?1 AND e.start <=?2 AND UPPER(e.user) LIKE '%' || UPPER(?3) || '%'"),
@NamedQuery(name = "Event.findTournamentWithParams", query = "SELECT e FROM Event e WHERE e.type LIKE 'tournament' AND e.end >= ?1 AND e.start <=?2 AND UPPER(e.user) LIKE '%' || UPPER(?3) || '%' AND UPPER(e.format) LIKE '%' || UPPER(?4) || '%'"),
@NamedQuery(name = "Event.findStreamWithParams", query = "SELECT e FROM Event e WHERE e.type LIKE 'stream' AND e.end >= ?1 AND e.start <=?2 AND UPPER(e.user) LIKE '%' || UPPER(?3) || '%' AND UPPER(e.channel) LIKE '%' || UPPER(?4) || '%'")

})
public class Event extends ResourceSupport{
	
	

	public Event(Long start, Long end, String user, String description, String channel, String type, String format) {
		this.start = start;
		this.end = end;
		this.user = user;
		this.description = description;
		this.channel = channel;
		this.type = type;
		this.format = format;
	}
	
	public Event(){}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long eventId;

	@Column(name = "start", nullable = false)
	private Long start;
	
	@Column(name = "end", nullable = false)
	private Long end;
	
	@Column(name = "user", length = 200, nullable = false)
	private String user;
	
	@Column(name = "description", length = 500, nullable = false)
	private String description;
	
	@Column(name = "channel", length = 200)
	private String channel;
	
	@Column(name = "type", length = 200)
	private String type;
	
	@Column(name = "format", length = 200)
	private String format;
	
	public Long getEventId() {
		return eventId;
	}

	public Long getStart() {
		return start;
	}

	public Long getEnd() {
		return end;
	}

	public String getUser() {
		return user;
	}

	public String getDescription() {
		return description;
	}

	public void setId(Long id) {
		this.eventId = id;
	}

	public void setStart(Long start) {
		this.start = start;
	}

	public void setEnd(Long end) {
		this.end = end;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void setDescription(String name) {
		this.description = name;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}
	
	

}
