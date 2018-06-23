package ftn.xmlws.dto;

import javax.persistence.Column;

public class TermDTO {

	private String startDate;
	private String endDate;
	private String userId;
	private String accomodationId; // za sada
	private String accomodationName;
	private Long termId;
	private boolean confirmed;
	private boolean rated;
	private String userQuestion;
	private String agentAnswer;

	public String getUserQuestion() {
		return userQuestion;
	}

	public void setUserQuestion(String userQuestion) {
		this.userQuestion = userQuestion;
	}

	public String getAgentAnswer() {
		return agentAnswer;
	}

	public void setAgentAnswer(String agentAnswer) {
		this.agentAnswer = agentAnswer;
	}

	public boolean isRated() {
		return rated;
	}

	public void setRated(boolean rated) {
		this.rated = rated;
	}

	public boolean isConfirmed() {
		return confirmed;
	}

	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}

	public Long getTermId() {
		return termId;
	}

	public void setTermId(Long termId) {
		this.termId = termId;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAccomodationId() {
		return accomodationId;
	}

	public void setAccomodationId(String accomodationId) {
		this.accomodationId = accomodationId;
	}

	public String getAccomodationName() {
		return accomodationName;
	}

	public void setAccomodationName(String accomodationName) {
		this.accomodationName = accomodationName;
	}

	public TermDTO() {

	}

}
