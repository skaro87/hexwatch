package se.skaro.hexwatch.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {



	public User(String name, String ign, String twitchChannel, String passwordHash) {
		this.name = name;
		this.ign = ign;
		this.twitchChannel = twitchChannel;
		this.passwordHash = passwordHash;
	}

	public User() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "name", length = 200, nullable = false)
	private String name;

	@Column(name = "ign", length = 200)
	private String ign;

	@Column(name = "channel", length = 200)
	private String twitchChannel;

	@Column(name = "password", length = 500, nullable = false)
	private String passwordHash;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIgn() {
		return ign;
	}

	public void setIgn(String ign) {
		this.ign = ign;
	}

	public String getTwitchChannel() {
		return twitchChannel;
	}

	public void setTwitchChannel(String twitchChannel) {
		this.twitchChannel = twitchChannel;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public Long getId() {
		return id;
	}

	

}
