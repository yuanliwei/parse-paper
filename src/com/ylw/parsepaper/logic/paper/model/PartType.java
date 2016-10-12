package com.ylw.parsepaper.logic.paper.model;

import java.util.HashMap;
import java.util.Map;

import com.ylw.parsepaper.logic.html.model.HtmlParagraph;

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

	// @Override
	// public boolean equals(Object obj) {
	// // TODO Auto-generated method stub
	// return super.equals(obj);
	// }

	public static boolean isBigType(HtmlParagraph p) {
		if (Part.T_PAPER_大标题.equals(p.type) || Part.T_BIG_填空题.equals(p.type) || Part.T_BIG_解答题.equals(p.type)
				|| Part.T_BIG_选择题.equals(p.type))
			return true;
		return false;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + value;
		return result;
	}

	public boolean equals(PartType obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PartType other = (PartType) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (value != other.value)
			return false;
		return true;
	}

}
