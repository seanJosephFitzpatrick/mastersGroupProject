package com.mase2.mase2_project.util;

public class TopTenFailuresObject {
		private String country;
		private String operator;
		private String cellId;
		private Long count;
		public TopTenFailuresObject(String country, String operator, String cellId,
				Long count) {
			super();
			this.country = country;
			this.operator = operator;
			this.cellId = cellId;
			this.count = count;
		}
		public String getCountry() {
			return country;
		}
		public void setCountry(String country) {
			this.country = country;
		}
		public String getOperator() {
			return operator;
		}
		public void setOperator(String operator) {
			this.operator = operator;
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
