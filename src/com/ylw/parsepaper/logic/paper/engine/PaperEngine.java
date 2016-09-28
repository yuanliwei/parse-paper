package com.ylw.parsepaper.logic.paper.engine;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import com.ylw.parsepaper.logic.html.model.HtmlParagraph;
import com.ylw.parsepaper.logic.paper.model.Part;

public class PaperEngine {
	public QuestionEngine questionEngine = new QuestionEngine();
	public ElementEngine elementEngine = new ElementEngine();
	public PartEngine partEngine = new PartEngine();

	private List<HtmlParagraph> paragraph;

	public void parse(List<HtmlParagraph> ps) {
		this.paragraph = ps;
		partEngine.parse(ps);
		elementEngine.parse(partEngine.parts);
	}

	public List<Part> getAllChoice() {
		List<Part> choiceParts = new ArrayList<>();
		
		
 		int fromIndex;
		int toIndex;
		
		//TODO 把part list 转换成html嵌套标签的形式
//		<big><small><small><big>
//		customTag
		for(Part p : partEngine.parts){
			
		}
//		partEngine.parts.forEach(part -> {
//			if (part.type == Part.T_BIG_选择题) {
//				p.x = part.index;
//			}
//			if (part.type < Part.T_SMALL) {
//				p.x = part.index;
//			}
//		});
//		choiceParts.addAll(partEngine.parts.subList(p.x, p.y));
//		return choiceParts;
		return elementEngine.choiceParts;
	}
}
