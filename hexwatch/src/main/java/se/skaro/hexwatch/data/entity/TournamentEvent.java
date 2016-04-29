package se.skaro.hexwatch.data.entity;


import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Tournament")
public class TournamentEvent extends Event {
	
	
	
	public TournamentEvent(Long start, Long end, String user, String name, String type, String format) {
		super(start, end, user, name);
		this.type = type;
		this.format = format;
	}
	
	public TournamentEvent(){}

	@Column(name = "type", length = 200)
	private String type;
	
	@Column(name = "format", length = 200)
	private String format;

	public String getType() {
		return type;
	}

	public String getFormat() {
		return format;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setFormat(String format) {
		this.format = format;
	}
	
	
	
	
	
}
