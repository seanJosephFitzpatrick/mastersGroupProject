package com.mase2.mase2_project.model;

import java.io.Serializable;

import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the failure_class database table.
 * 
 */
@Entity
@Table(name="failure_class")
@NamedQuery(name="FailureClass.findAll", query="SELECT f FROM FailureClass f")
public class FailureClass implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="failure_class")
	private String failureClass;

	private String description;

	public String getFailureClass() {
		return this.failureClass;
	}

	public void setFailureClass(final String failureClass) {
		this.failureClass = failureClass;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}



	public void createRow(final List<String> cells) {
		this.setFailureClass(cells.get(0));
		this.setDescription(cells.get(1));
		
	}

}