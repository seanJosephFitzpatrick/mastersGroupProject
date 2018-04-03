package com.mase2.mase2_project.graph_model;

public class LastNode {
	private String name;
	private String size;

	public LastNode() {
		name ="";
		size="1000";
	}
	
	public LastNode(String name) {
		super();
		this.name = name;
		this.size="1000";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

}
