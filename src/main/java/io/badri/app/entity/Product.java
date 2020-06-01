package io.badri.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "products")
@JsonIdentityInfo(property="@id", generator = ObjectIdGenerators.IntSequenceGenerator.class) //add this annotation because user also has products.
public class Product{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long pid;
	private String proudctName;
	private String productDesc;
	private double price;
	private int quantity;

	@ManyToOne(optional = false) //fetch = FetchType.LAZY, 
	@JoinColumn(name="user_id" , referencedColumnName="user_id" , nullable=false)
	//@JoinColumn(name = "user_id", nullable = false) //manytoone , [ MTO-S= manytoone source entuty ]
	//using fk mapping , fk is in source entity [ product in this case]
	private User user;

	public Product() {
	}

	public Product(String proudctName, String productDesc, double price, int quantity) {

		this.proudctName = proudctName;
		this.productDesc = productDesc;
		this.price = price;
		this.quantity = quantity;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String getProudctName() {
		return proudctName;
	}

	public void setProudctName(String proudctName) {
		this.proudctName = proudctName;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		
		this.user = user;
	}

	@Override
	public String toString() {
		return "Product [pid=" + pid + ", proudctName=" + proudctName + ", productDesc=" + productDesc + ", price="
				+ price + ", quantity=" + quantity + "]";
	}

}
