package se.skaro.hexwatch.data.entity;


import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;


@Entity
@Table(name = "events")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
	    name="discriminator",
	    discriminatorType=DiscriminatorType.STRING
	)
public class Event {
	
	

	public Event(Long start, Long end, String user, String description) {
		this.start = start;
		this.end = end;
		this.user = user;
		this.description = description;
	}
	
	public Event(){}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "start", nullable = false)
	private Long start;
	
	@Column(name = "end", nullable = false)
	private Long end;
	
	@Column(name = "user", length = 200, nullable = false)
	private String user;
	
	@Column(name = "description", length = 500, nullable = false)
	private String description;

	public Long getId() {
		return id;
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
		this.id = id;
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
	
	

}
