package com.mase2.mase2_project.model;

import java.io.Serializable;

import javax.persistence.*;
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
	
	@Column(name="marketing_name")
	private String marketingName;

	@Column(name="manufacturer")
	private String manufacturer;

	@Column(name="access_capability")
	private String accessCapability;
	
	@Column(name="model")
	private String model;
	
	@Column(name="vendor_name")
	private String vendorName;
	
	@Column(name="ue_type")
	private String ueType;
	
	@Column(name="os")
	private String os;
	
	@Column(name="input_mode")
	private String inputType;


	public String getTac() {
		return this.tac;
	}

	public void setTac(final String tac) {
		this.tac = tac;
	}

	public String getAccessCapability() {
		return this.accessCapability;
	}

	public void setAccessCapability(final String accessCapability) {
		this.accessCapability = accessCapability;
	}

	public String getManufacturer() {
		return this.manufacturer;
	}

	public void setManufacturer(final String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getMarketingName() {
		return this.marketingName;
	}

	public void setMarketingName(final String marketingName) {
		this.marketingName = marketingName;
	}

	public String getModel() {
		return model;
	}

	public void setModel(final String model) {
		this.model = model;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(final String vendorName) {
		this.vendorName = vendorName;
	}

	public String getUeType() {
		return ueType;
	}

	public void setUeType(final String ueType) {
		this.ueType = ueType;
	}

	public String getOs() {
		return os;
	}

	public void setOs(final String os) {
		this.os = os;
	}

	public String getInputType() {
		return inputType;
	}

	public void setInputType(final String inputType) {
		this.inputType = inputType;
	}

	public void createRow(final List<String> cells) {
		this.setTac(cells.get(0));
		this.setMarketingName(cells.get(1));
		this.setManufacturer(cells.get(2));
		this.setAccessCapability(cells.get(3));
		this.setModel(cells.get(4));
		this.setVendorName(cells.get(5));
		this.setUeType(cells.get(6));
		this.setOs(cells.get(7));
		this.setInputType(cells.get(8));
	}

}