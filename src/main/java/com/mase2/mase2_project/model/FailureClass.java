package com.mase2.mase2_project.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.ArrayList;
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

	//bi-directional many-to-one association to BaseData
	@OneToMany(mappedBy="failureClassBean")
	private List<BaseData> baseData;

	public FailureClass() {
	}

	public String getFailureClass() {
		return this.failureClass;
	}

	public void setFailureClass(String failureClass) {
		this.failureClass = failureClass;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<BaseData> getBaseData() {
		return this.baseData;
	}

	public void setBaseData(List<BaseData> baseData) {
		this.baseData = baseData;
	}

	public BaseData addBaseData(BaseData baseData) {
		getBaseData().add(baseData);
		baseData.setFailureClassBean(this);

		return baseData;
	}

	public BaseData removeBaseData(BaseData baseData) {
		getBaseData().remove(baseData);
		baseData.setFailureClassBean(null);

		return baseData;
	}

	public void createRow(ArrayList<String> cells) {
		this.setFailureClass(cells.get(0));
		this.setDescription(cells.get(1));
		
	}

}