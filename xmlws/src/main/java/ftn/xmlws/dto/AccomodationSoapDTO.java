package ftn.xmlws.dto;

import java.util.HashSet;
import java.util.Set;



public class AccomodationSoapDTO {
	
	private Long id;
	private String name;
	private String country;
	private String address;
	private Long accomodationTypeId;	
	private Long categoryId;
	private String description;
	//private List<AccomodationImageSoap> imagesSoap;
	private int capacity;
	private Set<Long> extraServicesIds = new HashSet<Long>();
	//private List<TermSoap> termsSoap;
	public Long getAccomodationTypeId() {
		return accomodationTypeId;
	}
	public void setAccomodationTypeId(Long accomodationTypeId) {
		this.accomodationTypeId = accomodationTypeId;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public Set<Long> getExtraServicesIds() {
		return extraServicesIds;
	}
	public void setExtraServicesIds(Set<Long> extraServicesIds) {
		this.extraServicesIds = extraServicesIds;
	}
	public AccomodationSoapDTO(String name, String country, String address, Long accomodationTypeId, Long categoryId,
			String description, int capacity, Set<Long> extraServicesIds) {
		super();
		this.name = name;
		this.country = country;
		this.address = address;
		this.accomodationTypeId = accomodationTypeId;
		this.categoryId = categoryId;
		this.description = description;
		this.capacity = capacity;
		this.extraServicesIds = extraServicesIds;
	}
	
	public AccomodationSoapDTO(){
		
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
}
