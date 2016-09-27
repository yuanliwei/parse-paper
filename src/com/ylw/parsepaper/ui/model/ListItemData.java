package com.ylw.parsepaper.ui.model;

import java.text.MessageFormat;

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

	/**
	 * String result = MessageFormat.format(
	 * "At {1,time} on {1,date}, there was {2} on planet {0,number,integer}.",
	 * planet, new Date(), event);
	 * 
	 * @param name
	 * @param jsData
	 * @param arguments
	 */
	public ListItemData(String name, String jsData, Object... arguments) {
		this.name = new SimpleStringProperty(name);
		this.jsData = MessageFormat.format(jsData, arguments);
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
