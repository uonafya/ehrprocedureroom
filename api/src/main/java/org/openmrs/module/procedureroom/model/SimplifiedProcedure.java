package org.openmrs.module.procedureroom.model;

import java.util.Date;

public class SimplifiedProcedure {
	
	private Integer patientId;
	
	private String patientIdentifier;
	
	public Integer getPatientId() {
		return patientId;
	}
	
	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}
	
	public String getPatientIdentifier() {
		return patientIdentifier;
	}
	
	public void setPatientIdentifier(String patientIdentifier) {
		this.patientIdentifier = patientIdentifier;
	}
	
	public String getNames() {
		return names;
	}
	
	public void setNames(String names) {
		this.names = names;
	}
	
	public String getProcedure() {
		return procedure;
	}
	
	public void setProcedure(String procedure) {
		this.procedure = procedure;
	}
	
	public String getFrom() {
		return from;
	}
	
	public void setFrom(String from) {
		this.from = from;
	}
	
	public Date getTimeOrdered() {
		return timeOrdered;
	}
	
	public void setTimeOrdered(Date timeOrdered) {
		this.timeOrdered = timeOrdered;
	}
	
	private String names;
	
	private String procedure;
	
	private String from;
	
	private Date timeOrdered;
}
