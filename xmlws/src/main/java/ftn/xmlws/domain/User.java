package ftn.xmlws.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "Korisnik")
public class User {
	
	// 0 - admin
	// 1 - agent
	// 2 - klijenti
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "Ime")
	private String name;
	
	@Column(name = "Prezime")
	private String lastName;
	
	@Column(name = "PIB")
	private String businessNo;
	
	@Column(name = "KorisnickoIme")
	private String userName;
	
	@Column(name = "sifra")
	private String password;
	
	@Column(name = "tipKorisnika")
	private int userType;
	
	public User(String name, String lastName, String businessNo, String userName, String password, int userType) {
		this.name = name;
		this.lastName = lastName;
		this.businessNo = businessNo;
		this.userName = userName;
		this.password = password;
		this.userType = userType;
	}

	public User() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getBusinessNo() {
		return businessNo;
	}

	public void setBusinessNo(String businessNo) {
		this.businessNo = businessNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

}
