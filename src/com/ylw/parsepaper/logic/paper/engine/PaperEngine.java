package com.ylw.parsepaper.logic.paper.engine;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.text.StrBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ylw.parsepaper.logic.html.model.HtmlParagraph;
import com.ylw.parsepaper.logic.paper.model.Part;

public class PaperEngine {
	private static Log log = LogFactory.getLog(PaperEngine.class);

	public QuestionEngine questionEngine = new QuestionEngine();
	public ElementEngine elementEngine = new ElementEngine();
	public PartEngine partEngine = new PartEngine();

	private List<HtmlParagraph> paragraph;

	public void parse(List<HtmlParagraph> ps) {
		this.paragraph = ps;
		partEngine.parse(ps);

		StrBuilder strBuilder = new StrBuilder();
		partEngine.parts.forEach((part) -> {
			strBuilder.append(MessageFormat.format("\ntype - {0} : {1}", part.type.value, part.type.name));
		});
		log.debug("partEngine.parts.type : " + strBuilder.toString());

		elementEngine.parse(partEngine.parts);
	}

	public List<Part> getAllChoice() {
		List<Part> choiceParts = new ArrayList<>();

		int fromIndex;
		int toIndex;

		// TODO 把part list 转换成html嵌套标签的形式
		// <big><small><small><big>
		// customTag
		for (Part p : partEngine.parts) {

		}
		// partEngine.parts.forEach(part -> {
		// if (part.type == Part.T_BIG_选择题) {
		// p.x = part.index;
		// }
		// if (part.type < Part.T_SMALL) {
		// p.x = part.index;
		// }
		// });
		// choiceParts.addAll(partEngine.parts.subList(p.x, p.y));
		// return choiceParts;
		return elementEngine.choiceParts;
	}
}
