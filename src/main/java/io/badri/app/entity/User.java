package io.badri.app.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity

@Table(name = "users") // user
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class) //add this annotation because user also has products.
public class User {
	
	//System.out.println(date); //Prints 26/10/2015
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@JsonIgnore
	@Column(name = "user_id")
	private int user_id; //

	private String firstName;

	private String lastName;

	private LocalDate dob;

	@Email
	private String email;

	private String username;

	private String password;

	private String phoneNumber;
	
	private boolean enabled = false;

	// bet token and user.
	@OneToOne(mappedBy = "user")
	private VerificationToken verificationToken;

	// bet user and products.
	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL, orphanRemoval = true)
	//for oneToMany uni-dir using fk, fk is in target entity , in products in this case.[OTM-T- onetomany - target entity]
    @JoinColumn( name = "user_id") //user_id as fk in Proudct table [products in this case ],
	private List<Product> products;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable( name = "user_role", joinColumns = @JoinColumn( name = "user_id"), 
	inverseJoinColumns = @JoinColumn( name = "role_id"))
	private Set<Role> role; // "role" :[{}]

	public User() {

	}

	public User(String firstName, String lastName, LocalDate dob, String email, String username, String password,
			String phoneNumber, boolean isEnabled,Set<Role> role) {

		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob; 

		this.email = email;
		this.username = username;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.enabled = isEnabled;
	    this.role = role;
	}
	

	public int getId() {
		return user_id;
	}

	public void setId(int id) {
		this.user_id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getDob() {
		return dob; // 2020-05-24 

	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
		// return passwordEncoder.encode(password);
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public VerificationToken getVerificationToken() {
		return verificationToken;
	}

	public void setVerificationToken(VerificationToken verificationToken) {
		this.verificationToken = verificationToken;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	

	public Set<Role> getRole() {
		return role;
	}

	public void setRole(Set<Role> role) {
		this.role = role;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}


	public void add(Product product) {
		if(products == null) {
			products = new ArrayList<Product>();
		}
		products.add(product);
		
		product.setUser(this); //this == User, 	bidirectional relationship [if needed, here products of user no longer exist without User ]
		
	}
	
	@Override
	public String toString() {
		return "User [id=" + user_id + ", firstName=" + firstName + ", lastName=" + lastName + ", dob=" + dob + ", email="
				+ email + ", username=" + username + ", password=" + password + ", enabled=" + enabled + ", phoneNumber =" + phoneNumber + "]";
	}

}
