package org.openmrs.module.procedureroom.model;

public class SimplifiedConcept {
	
	private Integer concept_id;
	
	public Integer getConcept_id() {
		return concept_id;
	}
	
	public void setConcept_id(Integer concept_id) {
		this.concept_id = concept_id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	private String name;
}
