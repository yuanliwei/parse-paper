package com.ylw.parsepaper.html.model;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class HtmlElementTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	private String html;
	private String html1;

	@Before
	public void setUp() throws Exception {
		  html = "<p\n        dir=\"ltr\"\n"
		  		+ "        class=\"pt-a-000004\">\n"
		  		+ "        <span\n"
		  		+ "          xml:space=\"preserve\"\n"
		  		+ "          class=\"pt-a0-000005\">    A．</span>\n"
		  		+ "        <span\n"
		  		+ "          lang=\"zh-CN\"\n"
		  		+ "          class=\"pt-a0-000003\">公式:rId88</span>\n"
		  		+ "        <span\n"
		  		+ "          xml:space=\"preserve\"\n"
		  		+ "          class=\"pt-a0-000005\">    B．</span>\n"
		  		+ "        <span\n"
		  		+ "          lang=\"zh-CN\"\n"
		  		+ "          class=\"pt-a0-000003\">公式:rId90</span>\n"
		  		+ "        <span\n"
		  		+ "          xml:space=\"preserve\"\n"
		  		+ "          class=\"pt-a0-000005\">      C．</span>\n"
		  		+ "        <span\n"
		  		+ "          lang=\"zh-CN\"\n"
		  		+ "          class=\"pt-a0-000003\">公式:rId92</span>\n"
		  		+ "        <span\n"
		  		+ "          xml:space=\"preserve\"\n"
		  		+ "          class=\"pt-a0-000005\">      D．</span>\n"
		  		+ "        <span\n"
		  		+ "          lang=\"zh-CN\"\n"
		  		+ "          class=\"pt-a0-000003\">公式:rId94</span>\n"
		  		+ "      </p>\n";
		  html1 = "        <span\n"
				  + "          xml:space=\"preserve\"\n"
				  + "          class=\"pt-a0-000005\">    A．</span>\n"
				  + "        <span\n"
				  + "          lang=\"zh-CN\"\n"
				  + "          class=\"pt-a0-000003\">公式:rId88</span>\n"
				  + "        <span\n"
				  + "          xml:space=\"preserve\"\n"
				  + "          class=\"pt-a0-000005\">    B．</span>\n"
				  + "        <span\n"
				  + "          lang=\"zh-CN\"\n"
				  + "          class=\"pt-a0-000003\">公式:rId90</span>\n"
				  + "        <span\n"
				  + "          xml:space=\"preserve\"\n"
				  + "          class=\"pt-a0-000005\">      C．</span>\n"
				  + "        <span\n"
				  + "          lang=\"zh-CN\"\n"
				  + "          class=\"pt-a0-000003\">公式:rId92</span>\n"
				  + "        <span\n"
				  + "          xml:space=\"preserve\"\n"
				  + "          class=\"pt-a0-000005\">      D．</span>\n"
				  + "        <span\n"
				  + "          lang=\"zh-CN\"\n"
				  + "          class=\"pt-a0-000003\">公式:rId94</span>\n"
				  + "    hgfhfgf  \n";
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSplitTag() {
		List<String> tagStrs = HtmlElement.splitTag(html);
		assertTrue("标签截取错误", tagStrs.size() == 1);
		for (String s : tagStrs) {
			if (!s.startsWith("<") || !s.endsWith(">")) {
				fail("标签截取错误");
			}
		}
		tagStrs = HtmlElement.splitTag(html1);
		assertTrue("标签截取错误", tagStrs.size() == 9);
	}

	@Test
	public void testParse() {
		// fail("Not yet implemented");
	}

}
