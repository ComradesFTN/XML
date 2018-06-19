package ftn.xmlws.domain;

import java.util.ArrayList;
import java.util.HashSet;
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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Accomodation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String name;

	@Column
	private String country;

	@Column
	private String address;

	@JoinColumn(name = "type_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private AccomodationType accomodationType;

	@JoinColumn(name = "category_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Category category;

	@Column
	private String description;

	@OneToMany(mappedBy = "accomodation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<AccomodationImage> images = new ArrayList<AccomodationImage>();

	@Column
	private int capacity;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "accomodation_services", joinColumns = @JoinColumn(name = "accomodation_id"), inverseJoinColumns = @JoinColumn(name = "service_id"))
	private Set<ExtraService> extraServices = new HashSet<ExtraService>();

	@OneToMany(mappedBy = "accomodation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Term> terms = new ArrayList<Term>();
	
	@Column
	private Long agent;
	
	@Column
	@OneToMany(mappedBy = "accomodation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<MonthPrice> pricePlan = new ArrayList<MonthPrice>();

	public List<MonthPrice> getPricePlan() {
		return pricePlan;
	}

	public void setPricePlan(List<MonthPrice> pricePlan) {
		this.pricePlan = pricePlan;
	}

	public Long getAgent() {
		return agent;
	}

	public void setAgent(Long agent) {
		this.agent = agent;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public AccomodationType getAccomodationType() {
		return accomodationType;
	}

	public void setAccomodationType(AccomodationType accomodationType) {
		this.accomodationType = accomodationType;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<AccomodationImage> getImages() {
		return images;
	}

	public void setImages(List<AccomodationImage> images) {
		this.images = images;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public Set<ExtraService> getExtraServices() {
		return extraServices;
	}

	public void setExtraServices(Set<ExtraService> extraServices) {
		this.extraServices = extraServices;
	}

	public List<Term> getTerms() {
		return terms;
	}

	public void setTerms(List<Term> terms) {
		this.terms = terms;
	}

	public Accomodation() {

	}

	public Accomodation(Long id, String name, String country, String address, AccomodationType accomodationType,
			Category category, String description, List<AccomodationImage> images, int capacity,
			Set<ftn.xmlws.domain.ExtraService> extraServices, List<Term> terms, Long agent, List<MonthPrice> pricePlan) {
		super();
		this.id = id;
		this.name = name;
		this.country = country;
		this.address = address;
		this.accomodationType = accomodationType;
		this.category = category;
		this.description = description;
		this.images = images;
		this.capacity = capacity;
		this.extraServices = extraServices;
		this.terms = terms;
		this.agent = agent;
		this.pricePlan = pricePlan;
	}

}
