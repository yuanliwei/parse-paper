package com.ylw.parsepaper.ui.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ListItemData {
	private final StringProperty name;
	String jsData;

	public ListItemData(String name, String jsData) {
		super();
		this.name = new SimpleStringProperty(name);
		this.jsData = jsData;
	}

	public String getName() {
		return name.get();
	}

	public void setName(String name) {
		this.name.set(name);
	}

	public String getJsData() {
		return jsData;
	}

	public void setJsData(String jsData) {
		this.jsData = jsData;
	}

}
