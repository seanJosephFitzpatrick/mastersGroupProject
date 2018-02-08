package com.mase2.mase2_project.model;

import java.awt.List;
import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the mcc_mnc database table.
 * 
 */
@Entity
@Table(name="mcc_mnc")
@NamedQuery(name="MccMnc.findAll", query="SELECT m FROM MccMnc m")
public class MccMnc implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MccMncPK id;

	private String country;

	private String operator;

	public MccMnc() {
	}

	public MccMncPK getId() {
		return this.id;
	}

	public void setId(MccMncPK id) {
		this.id = id;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

}