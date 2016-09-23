package com.ylw.parsepaper.html.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class HtmlParagraphTest {

	private static String html;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		html = "<p class=MsoNormal  style=\"layout-grid-mode:char;text-align:left;\" >"
				+ "<span style=\"font-family:宋体;font-size:10.5000pt;\" >&#9313;海水淡化。"
				+ "该国经济比较发达，四周环海，可选适当地点，建海水淡化厂，生产淡水。</span>"
				+ "<span style=\"font-family:宋体;font-size:10.5000pt;\" ></span></p>";
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testHtmlParagraph() {
		HtmlParagraph htmlParagraph = new HtmlParagraph(html);

		String html_ = "<span style=\"font-family:宋体;font-size:10.5000pt;\" >&#9313;海水淡化。"
				+ "该国经济比较发达，四周环海，可选适当地点，建海水淡化厂，生产淡水。</span>"
				+ "<span style=\"font-family:宋体;font-size:10.5000pt;\" ></span>";
		assertTrue("解析段落内容出错！", html_.equals(htmlParagraph.html));

		String text = "&#9313;海水淡化。该国经济比较发达，四周环海，可选适当地点，建海水淡化厂，生产淡水。";
		assertTrue("解析段落内容出错！", text.equals(htmlParagraph.text));

		String style = "class=MsoNormal  style=\"layout-grid-mode:char;text-align:left;\"";
		assertTrue("解析段落内容出错！", style.equals(htmlParagraph.pStyle));
	}

}
