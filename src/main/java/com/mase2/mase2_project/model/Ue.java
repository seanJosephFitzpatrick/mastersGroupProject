package com.mase2.mase2_project.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the ue database table.
 * 
 */
@Entity
@Table(name="ue")
@NamedQuery(name="Ue.findAll", query="SELECT u FROM Ue u")
public class Ue implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int tac;

	@Column(name="access_capability", length=120)
	private String accessCapability;

	@Column(length=45)
	private String manufacturer;

	@Column(name="marketing_name", length=45)
	private String marketingName;

	//bi-directional many-to-one association to BaseData
	@OneToMany(mappedBy="ue")
	private List<BaseData> baseData;

	public Ue() {
	}

	public int getTac() {
		return this.tac;
	}

	public void setTac(int tac) {
		this.tac = tac;
	}

	public String getAccessCapability() {
		return this.accessCapability;
	}

	public void setAccessCapability(String accessCapability) {
		this.accessCapability = accessCapability;
	}

	public String getManufacturer() {
		return this.manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getMarketingName() {
		return this.marketingName;
	}

	public void setMarketingName(String marketingName) {
		this.marketingName = marketingName;
	}

}