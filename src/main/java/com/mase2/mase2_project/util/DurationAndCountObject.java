package com.mase2.mase2_project.util;

public class DurationAndCountObject {
	private String imsi;
	private Long count;
	private Long sum;
	public DurationAndCountObject(String imsi, Long count, Long sum) {
		super();
		this.imsi = imsi;
		this.count = count;
		this.sum = sum;
	}
	public String getImsi() {
		return imsi;
	}
	public Long getCount() {
		return count;
	}
	public Long getSum() {
		return sum;
	}
	public void setImsi(String imsi) {
		this.imsi = imsi;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public void setSum(Long sum) {
		this.sum = sum;
	}
	
	

}
