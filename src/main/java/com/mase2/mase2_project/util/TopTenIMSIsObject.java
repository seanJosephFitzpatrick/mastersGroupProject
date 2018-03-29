package com.mase2.mase2_project.util;

public class TopTenIMSIsObject {
		private String imsi;
		private Long count;
		
		public TopTenIMSIsObject(String imsi, Long count) {
			super();
			this.imsi = imsi;
			this.count = count;
		}

		public String getImsi() {
			return imsi;
		}

		public void setImsi(String imsi) {
			this.imsi = imsi;
		}

		public Long getCount() {
			return count;
		}

		public void setCount(Long count) {
			this.count = count;
		}
		
}
