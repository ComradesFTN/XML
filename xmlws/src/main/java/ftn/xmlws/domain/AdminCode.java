package ftn.xmlws.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "Sifarnik")
public class AdminCode {
	
	// 0 - tip smestaja (hote, bad&breakfest, apartman...)
	// 1 - Kategorija smeštaja (nekategorisan ili 1-5 zvezdica)
	// 2 - Dodatne usluge koje očekuje da su na raspolaganju: Parking, WiFi, Doručak, Polupansion, Pansion, TV, Mini kuhinja/kuhinja ,Privatno kupatilo
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "Ime")
	private String name;
	
	@Column(name = "Kategorisan")
	private boolean categorized;
	
	@Column(name = "Zvezde")
	private int starNo;
	
	@Column(name = "TipUsluge")
	private int codeType;

	public AdminCode(long id, String name, boolean categorized, int starNo, int codeType) {
		this.id = id;
		this.name = name;
		this.categorized = categorized;
		this.starNo = starNo;
		this.codeType = codeType;
	}

	public AdminCode() {
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

	public boolean isCategorized() {
		return categorized;
	}

	public void setCategorized(boolean categorized) {
		this.categorized = categorized;
	}

	public int getStarNo() {
		return starNo;
	}

	public void setStarNo(int starNo) {
		this.starNo = starNo;
	}

	public int getCodeType() {
		return codeType;
	}

	public void setCodeType(int codeType) {
		this.codeType = codeType;
	}

}
