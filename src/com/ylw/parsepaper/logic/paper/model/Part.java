package com.ylw.parsepaper.logic.paper.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ylw.parsepaper.logic.html.model.HtmlParagraph;

public class Part {

	public static final int T_TYPE_NONE = -1; // 未知类型
	// 试卷
	public static final int T_PAPER = 0; // 试卷大题分割线
	public static final int T_PAPER_大标题 = T_PAPER + 1; // 试卷大标题
	public static final int T_PAPER_说明文本 = T_PAPER + 2; // 试卷说明文本

	// 大题
	public static final int T_BIG = 100; // 大题小题分割线
	public static final int T_BIG_选择题 = T_BIG + 1; // 大题 - 选择题
	public static final int T_BIG_解答题 = T_BIG + 2; // 大题 - 解答题

	// 小题
	public static final int T_SMALL = 200; // 小题 - 题干
	public static final int T_SMALL_题干 = T_SMALL + 1; // 小题 - 题干
	public static final int T_SMALL_选项 = T_SMALL + 2; // 小题 - 选项
	public static final int T_SMALL_答案 = T_SMALL + 3; // 小题 - 答案
	public static final int T_SMALL_解析 = T_SMALL + 4; // 小题 - 解析
	public static final int T_SMALL_点评 = T_SMALL + 5; // 小题 - 点评
	public static final int T_SMALL_难度 = T_SMALL + 6; // 小题 - 难度

	public int type;
	public int start;
	public int end;
	public int index;

	private List<HtmlParagraph> paragraphs = new ArrayList<>();

	public Part(HtmlParagraph... paragraphs_) {
		this.paragraphs.addAll(Arrays.asList(paragraphs_));
		type = paragraphs_[0].type;
		start = paragraphs_[0].index;
		end = paragraphs_[paragraphs_.length - 1].index;
	}

	public Part(int index, HtmlParagraph... paragraphs_) {
		this.index = index;
		this.paragraphs.addAll(Arrays.asList(paragraphs_));
		type = paragraphs_[0].type;
		start = paragraphs_[0].index;
		end = paragraphs_[paragraphs_.length - 1].index;
	}

	public void combine(Part part) {
		paragraphs.addAll(part.paragraphs);
		paragraphs.sort((h, l) -> h.index - l.index);
		type = paragraphs.get(0).type;
		start = paragraphs.get(0).index;
		end = paragraphs.get(paragraphs.size() - 1).index;
	}

	/**
	 * 是否试卷元素
	 * 
	 * @param type
	 * @return
	 */
	public static boolean isPaper(int type) {
		return type > T_TYPE_NONE && type < T_BIG;
	}

	/**
	 * 是否大题元素
	 * 
	 * @param type
	 * @return
	 */
	public static boolean isBig(int type) {
		return type > T_BIG && type < T_SMALL;
	}

	/**
	 * 是否小题元素
	 * 
	 * @param type
	 * @return
	 */
	public static boolean isSmall(int type) {
		return type > T_SMALL;
	}
}