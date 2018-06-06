package ftn.xmlws.domain;

public class User {
	
	// 0 - admin
	// 1 - agent
	// 2 - klijenti
	
	private String name;
	private String lastName;
	private String businessNo;
	private String userName;
	private String password;
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
