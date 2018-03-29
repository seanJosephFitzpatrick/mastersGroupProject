package com.mase2.mase2_project.graph_model;

import java.util.ArrayList;
import java.util.List;

public class NodeEventIdCouseCode {
	private String name;
	private List<NodeDataTime> children;

	public NodeEventIdCouseCode(String description) {
		super();
		this.name = description;
		this.children = new ArrayList<>();
	}
	public NodeEventIdCouseCode() {
		
		this.name = "";
		this.children = new ArrayList<>();
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<NodeDataTime> getChildren() {
		return children;
	}

	public void setChildren(List<NodeDataTime> children) {
		this.children = children;
	}

	public void addChildren(NodeDataTime child) {
		children.add(child);
	}

}
