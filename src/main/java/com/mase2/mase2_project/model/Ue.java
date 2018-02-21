package com.mase2.mase2_project.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

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

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getUeType() {
		return ueType;
	}

	public void setUeType(String ueType) {
		this.ueType = ueType;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getInputType() {
		return inputType;
	}

	public void setInputType(String inputType) {
		this.inputType = inputType;
	}

	public void createRow(ArrayList<String> cells) {
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