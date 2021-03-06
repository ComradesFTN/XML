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
	
	@Column(name = "adresa")
	private String adress;
	
	@Column(name = "tipKorisnika")
	private int userType;
	
	@Column(name = "Blokiran")
	private boolean banned=false;
	
	@Column(name = "Email")
	private String email;
	
	@Column(name = "Potvrdjen")
	private boolean confirmed=false;
	
	public User(long id, String name, String lastName, String businessNo, String userName, String password,
			String adress, int userType, boolean banned, String email, boolean confirmed) {
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.businessNo = businessNo;
		this.userName = userName;
		this.password = password;
		this.adress = adress;
		this.userType = userType;
		this.banned = banned;
		this.email = email;
		this.confirmed = confirmed;
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

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public boolean isbanned() {
		return banned;
	}

	public void setbanned(boolean banned) {
		this.banned = banned;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isConfirmed() {
		return confirmed;
	}

	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}
	
	public String getRole(){
		if(this.getUserType()==0){
			return "ADMIN";
		}
		else if(this.getUserType()==1){
			return "AGENT";
		}
		else return "USER";
	}

}
