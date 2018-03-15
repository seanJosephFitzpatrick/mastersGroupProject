package com.mase2.mase2_project.util;

public class TopTenFailuresObject {
		private String mcc;
		private String mnc;
		private String cellId;
		private Long count;
		public TopTenFailuresObject(String mcc, String mnc, String cellId,
				Long count) {
			super();
			this.mcc = mcc;
			this.mnc = mnc;
			this.cellId = cellId;
			this.count = count;
		}
		public String getMcc() {
			return mcc;
		}
		public void setMcc(String mcc) {
			this.mcc = mcc;
		}
		public String getMnc() {
			return mnc;
		}
		public void setMnc(String mnc) {
			this.mnc = mnc;
		}
		public String getCellId() {
			return cellId;
		}
		public void setCellId(String cellId) {
			this.cellId = cellId;
		}
		public Long getCount() {
			return count;
		}
		public void setCount(Long count) {
			this.count = count;
		}
		
		
		
}
