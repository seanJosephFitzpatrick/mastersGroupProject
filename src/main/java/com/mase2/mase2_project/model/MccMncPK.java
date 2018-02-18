package com.mase2.mase2_project.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the mcc_mnc database table.
 * 
 */
@Embeddable
public class MccMncPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private String mcc;

	private String mnc;

	public MccMncPK() {
	}
	public String getMcc() {
		return this.mcc;
	}
	public void setMcc(String mcc) {
		this.mcc = mcc;
	}
	public String getMnc() {
		return this.mnc;
	}
	public void setMnc(String mnc) {
		this.mnc = mnc;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof MccMncPK)) {
			return false;
		}
		MccMncPK castOther = (MccMncPK)other;
		return 
			this.mcc.equals(castOther.mcc)
			&& this.mnc.equals(castOther.mnc);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.mcc.hashCode();
		hash = hash * prime + this.mnc.hashCode();
		
		return hash;
	}
}