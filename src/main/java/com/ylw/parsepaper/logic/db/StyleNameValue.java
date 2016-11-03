package com.ylw.parsepaper.logic.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "style_name_value")
public class StyleNameValue {
	@DatabaseField( unique = true, generatedId = true, columnName = "id")
	int id;
	@DatabaseField(columnName = "name")
	String name;
	@DatabaseField(columnName = "value")
	String value;

	public StyleNameValue() {
		super();
	}

	public StyleNameValue(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}
}