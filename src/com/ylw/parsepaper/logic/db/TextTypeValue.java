package com.ylw.parsepaper.logic.db;

import org.apache.commons.codec.digest.DigestUtils;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.ylw.parsepaper.logic.paper.model.PartType;

@DatabaseTable(tableName = "text_type_value")
public class TextTypeValue {
	@DatabaseField(id = true, unique = true, columnName = "guid")
	String guid;
	@DatabaseField(columnName = "type")
	int type;
	@DatabaseField(columnName = "feature")
	String feature;
	@DatabaseField(columnName = "text")
	String text;
	@DatabaseField(columnName = "count")
	int count;

	public TextTypeValue() {
		super();
	}

	public TextTypeValue(PartType type2, String text) {
		guid = DigestUtils.md5Hex(text);
		this.type = type2.value;
		this.text = text;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getGuid() {
		return this.guid;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getType() {
		return this.type;
	}

	public PartType getPartType() {
		return PartType.get(this.type);
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public String getFeature() {
		return this.feature;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getText() {
		return this.text;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getCount() {
		return this.count;
	}
}