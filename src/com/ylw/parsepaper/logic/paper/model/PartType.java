package com.ylw.parsepaper.logic.paper.model;

import java.util.HashMap;
import java.util.Map;

public class PartType {
	public final int value;
	public final String name;
	private static Map<Integer, PartType> map = new HashMap<>();

	public PartType(int value, String name) {
		super();
		this.value = value;
		this.name = name;
		map.put(value, this);
	}

	public static PartType get(int type) {
		PartType t = map.get(type);
		if (t == null) {
			throw new IllegalStateException("未找到Part类型 ： " + type);
		}
		return t;
	}

}
