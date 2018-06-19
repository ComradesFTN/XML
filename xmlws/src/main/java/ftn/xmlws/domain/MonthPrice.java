package ftn.xmlws.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class MonthPrice {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String month;
	
	@Column
	private float price;
	
	@JoinColumn(name = "accomodation_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Accomodation accomodation;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}	

	public Accomodation getAccomodation() {
		return accomodation;
	}

	public void setAccomodation(Accomodation accomodation) {
		this.accomodation = accomodation;
	}

	public MonthPrice(Long id, String month, float price, Accomodation accomodation) {
		super();
		this.id = id;
		this.month = month;
		this.price = price;
		this.accomodation=accomodation;
	}
	
	public MonthPrice(){
		
	}
	
	
	
	

}
