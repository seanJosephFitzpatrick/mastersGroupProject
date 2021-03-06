package com.mase2.mase2_project.model;

import java.io.Serializable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


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

	//@Pattern(regexp="[a-zA-Z]", message ="{invalid country mcc mnc table}")
	private String country;

	//@Pattern(regexp="[a-zA-Z]", message ="{invalid operator mcc mnc table}" )
	private String operator;


	public MccMncPK getId() {
		return this.id;
	}

	public void setId(final MccMncPK id) {
		this.id = id;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(final String country) {
		this.country = country;
	}

	public String getOperator() {
		return this.operator;
	}

	public void setOperator(final String operator) {
		this.operator = operator;
	}

	public void createRow(final List<String> cells) {
		final MccMncPK mccMncPk = new MccMncPK();
		mccMncPk.setMcc(cells.get(0));
		mccMncPk.setMnc(cells.get(1));
        this.setId(mccMncPk);
        this.setCountry(cells.get(2));
        this.setOperator(cells.get(3));

		
	}

}