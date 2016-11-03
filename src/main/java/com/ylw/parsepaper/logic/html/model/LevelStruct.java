package com.ylw.parsepaper.logic.html.model;

import java.util.ArrayList;
import java.util.List;

import com.ylw.parsepaper.logic.paper.model.Part;

class LevelStruct {
	public int level;
	public Part part;
	public Part pPart;
	public List<Part> childs;

	public LevelStruct() {
		super();
		childs = new ArrayList<>();
	}

	public void add(Part part) {
		childs.add(part);
	}

	public boolean hasChild() {
		return childs.size() > 0;
	}

}