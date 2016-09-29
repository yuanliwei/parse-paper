package com.ylw.parsepaper.logic.paper.engine;

import java.util.List;

import com.ylw.parsepaper.logic.paper.model.Part;

public class ElementEngine {
	public PartEngine partEngine = new PartEngine();
	public List<Part> choiceParts;

	public void parse(List<Part> parts) {
		// TODO Auto-generated method stub
		choiceParts = parseChoice(parts);
	}

	private List<Part> parseChoice(List<Part> parts) {
		// parts.subList(fromIndex, toIndex);
		// TODO 解析成层级结构
		int fromIndex = 0;
		int toIndex = 1;
		for (int i = 0; i < parts.size(); i++) {
			Part p = parts.get(i);

			if (p.type == Part.T_BIG_选择题) {
				fromIndex = i;

			}
		}
		return parts;// .subList(fromIndex, toIndex);
	}

}
