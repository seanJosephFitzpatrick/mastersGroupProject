package com.mase2.mase2_project.graph_model;

import java.util.ArrayList;
import java.util.List;

public class NodeDataTime {
	private String name;
	private List<LastNode> children;
	
	public NodeDataTime(String name) {
		this.name = name;
		this.children = new ArrayList<>();
	}
	public NodeDataTime() {
		this.name = "";
		this.children = new ArrayList<>();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<LastNode> getChildren() {
		return children;
	}
	public void setChildren(List<LastNode> childeren) {
		this.children = childeren;
	}
	public void addChild(LastNode lastNode) {
		this.children.add(lastNode);
	}
}
