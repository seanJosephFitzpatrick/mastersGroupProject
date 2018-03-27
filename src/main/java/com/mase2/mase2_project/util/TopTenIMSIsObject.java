package com.mase2.mase2_project.util;

public class TopTenIMSIsObject {
		private String imsi;
		private String description;
		private String failureClassBean;
		private String failureClass;
		private Long count;
		
		public TopTenIMSIsObject(String imsi, String description, String failureClassBean, String failureClass,
				Long count) {
			super();
			this.imsi = imsi;
			this.description = description;
			this.failureClassBean = failureClassBean;
			this.failureClass = failureClass;
			this.count = count;
		}

		public String getImsi() {
			return imsi;
		}

		public void setImsi(String imsi) {
			this.imsi = imsi;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getFailureClassBean() {
			return failureClassBean;
		}

		public void setFailureClassBean(String failureClassBean) {
			this.failureClassBean = failureClassBean;
		}

		public String getFailureClass() {
			return failureClass;
		}

		public void setFailureClass(String failureClass) {
			this.failureClass = failureClass;
		}

		public Long getCount() {
			return count;
		}

		public void setCount(Long count) {
			this.count = count;
		}
		
}
