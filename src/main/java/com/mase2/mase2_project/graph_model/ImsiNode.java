package com.mase2.mase2_project.graph_model;

import java.util.List;

public class ImsiNode {
	private String name;
	private List<NodeEventIdCouseCode> children;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<NodeEventIdCouseCode> getChildren() {
		return children;
	}

	public void setChildren(List<NodeEventIdCouseCode> children) {
		this.children = children;
	}

}
