package com.mase2.mase2_project.util;

import java.util.ArrayList;

public class InvalidEntity {
	private ArrayList<String> cells;
	String errorDescription;
	
	public InvalidEntity(){
		
	}
	
	
	

//	public ArrayList<String> getCells() {
//		return cells;
//	}

	public void setCells(ArrayList<String> cells) {
		this.cells = cells;
	}

//	public String getErrorDescription() {
//		return errorDescription;
//	}

	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}



	@Override
	public String toString() {
		return errorDescription+"\r\n\ndateTime: "+cells.get(0)+"\r\nevent_id: "+cells.get(1)+"\r\nfailure_class: "+cells.get(2)
				+"\r\nue_type: "+cells.get(3)+"\r\nmarket: "+cells.get(4)+"\r\nOperator: "+cells.get(5)+"\r\ncell_id: "+cells.get(6)
				+"\r\nduration: "+cells.get(7)+"\r\ncause_code: "+cells.get(8)+"\r\nne_version: "+cells.get(9)+"\r\nimsi: "+cells.get(10)
				+"\r\nhier3_id: "+cells.get(11)+"\r\nhier32_id: "+cells.get(12)+"\r\nhier321_id: "+cells.get(13)+"\r\n\r\n";
	}
	
	


}
