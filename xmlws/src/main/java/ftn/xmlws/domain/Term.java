package ftn.xmlws.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Term {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	@Temporal(TemporalType.DATE)
	private Date startDate;

	@Column
	@Temporal(TemporalType.DATE)
	private Date endDate;

	@Column
	private float price;

	@JoinColumn(name = "accomodation_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Accomodation accomodation;

	@JoinColumn(name = "user_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;

	@Column
	private boolean confirmed;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isconfirmed() {
		return confirmed;
	}

	public void setconfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}

	public Term(Long id, Date startDate, Date endDate, float price, Accomodation accomodation, User user,
			boolean confirmed) {
		super();
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.price = price;
		this.accomodation = accomodation;
		this.user = user;
		this.confirmed = confirmed;
	}
	
	public Term() {
		
	}
	

}
