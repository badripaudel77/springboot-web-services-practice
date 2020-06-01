package io.badri.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Role {

	@Id //id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="role_id")
	private int role_id;
	
	private String role;
	
	public Role() {	
	}
	
	public Role(int id , String role) {
		this.role_id = id;
		this.role = role;
	}

	public int getId() {
		return role_id;
	}

	public void setId(int id) {
		this.role_id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Role [id=" + role_id + ", role=" + role + "]";
	}
	
	
}
