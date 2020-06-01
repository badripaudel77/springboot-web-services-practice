package io.badri.app.entity;

import static javax.persistence.FetchType.LAZY;

import static javax.persistence.GenerationType.IDENTITY;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "token")
public class VerificationToken {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	private String token;

	//one user can have one token and fetch token only on demand
	@OneToOne(fetch = LAZY)
	@JoinColumn( name = "user_id")
	private User user;

	private Instant expiryDate;

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
	
		this.id = id;
	}
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Instant getExpiryDate() {
		return expiryDate;
				//.plusSeconds(86400L);
	}

	public void setExpiryDate(Instant expiryDate) {
		this.expiryDate = expiryDate;
	}

	@Override
	public String toString() {
		return "VerificationToken [id=" + id + ", token=" + token + ", user=" + user + ", expiryDate=" + expiryDate
				+ "]";
	}

	public VerificationToken() {

	}

	public VerificationToken(Long id, String token, User user, Instant expiryDate) {	
		//this.id = id;
		this.token = token;
		this.user = user;
		this.expiryDate = expiryDate;
	}
}