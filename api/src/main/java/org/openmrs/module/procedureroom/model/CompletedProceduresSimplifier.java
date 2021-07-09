package org.openmrs.module.procedureroom.model;

import java.util.Date;

public class CompletedProceduresSimplifier {
	
	private String patientName;
	
	private String procedureOrdered;
	
	public String getPatientName() {
		return patientName;
	}
	
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	
	public String getProcedureOrdered() {
		return procedureOrdered;
	}
	
	public void setProcedureOrdered(String procedureOrdered) {
		this.procedureOrdered = procedureOrdered;
	}
	
	public Date getDatePerformed() {
		return datePerformed;
	}
	
	public void setDatePerformed(Date datePerformed) {
		this.datePerformed = datePerformed;
	}
	
	public String getAction() {
		return action;
	}
	
	public void setAction(String action) {
		this.action = action;
	}
	
	public String getComments() {
		return comments;
	}
	
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	private Date datePerformed;
	
	private String action;
	
	private String comments;
	
}
