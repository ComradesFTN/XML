package ftn.xmlws.dto;

import java.util.List;

public class SearchDTO {

	private String country;
	private String capacity;
	private String accomodationType;
	private String category;
	private List<String> extraServices;
	private String startDate;
	private String endDate;

	public SearchDTO() {

	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	public String getAccomodationType() {
		return accomodationType;
	}

	public void setAccomodationType(String accomodationType) {
		this.accomodationType = accomodationType;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public List<String> getExtraServices() {
		return extraServices;
	}

	public void setExtraServices(List<String> extraServices) {
		this.extraServices = extraServices;
	}

}
