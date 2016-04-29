package se.skaro.hexwatch.data.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Stream")
public class StreamEvent extends Event {
	
	
	
	public StreamEvent(Long start, Long end, String user, String name, String channel) {
		super(start, end, user, name);
		this.channel = channel;
	}

	public StreamEvent(){};
	
	@Column(name = "channel", length = 200)
	private String channel;

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}
	
	
	

}
