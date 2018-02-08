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

	private int mcc;

	private int mnc;

	public MccMncPK() {
	}
	public int getMcc() {
		return this.mcc;
	}
	public void setMcc(int mcc) {
		this.mcc = mcc;
	}
	public int getMnc() {
		return this.mnc;
	}
	public void setMnc(int mnc) {
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
			(this.mcc == castOther.mcc)
			&& (this.mnc == castOther.mnc);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.mcc;
		hash = hash * prime + this.mnc;
		
		return hash;
	}
}