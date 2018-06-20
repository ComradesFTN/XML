package ftn.xmlws.dto;

import java.util.List;

// Mora jos slike tu da vrati kao i..ocenu korisnika i cenu
public class SearchResultDTO {
	
	private Long id; // accomodation id
	private String name;
	private String description;
	private String category;
	
	public SearchResultDTO() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	

}
