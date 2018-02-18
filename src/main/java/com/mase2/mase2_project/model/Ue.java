package com.mase2.mase2_project.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the ue database table.
 * 
 */
@Entity
@NamedQuery(name="Ue.findAll", query="SELECT u FROM Ue u")
public class Ue implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String tac;

	@Column(name="access_capability")
	private String accessCapability;

	private String manufacturer;

	@Column(name="marketing_name")
	private String marketingName;

	//bi-directional many-to-one association to BaseData
	@OneToMany(mappedBy="ue")
	private List<BaseData> baseData;

	public Ue() {
	}

	public String getTac() {
		return this.tac;
	}

	public void setTac(String tac) {
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

	public List<BaseData> getBaseData() {
		return this.baseData;
	}

	public void setBaseData(List<BaseData> baseData) {
		this.baseData = baseData;
	}

	public BaseData addBaseData(BaseData baseData) {
		getBaseData().add(baseData);
		baseData.setUe(this);

		return baseData;
	}

	public BaseData removeBaseData(BaseData baseData) {
		getBaseData().remove(baseData);
		baseData.setUe(null);

		return baseData;
	}

	public void createRow(ArrayList<String> cells) {
		this.setTac(cells.get(0));
		this.setMarketingName(cells.get(1));
		this.setManufacturer(cells.get(2));
		this.setAccessCapability(cells.get(3));
		
	}

}