package com.ylw.parsepaper.html.model;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class HtmlElementTest {
	private static Log log = LogFactory.getLog(HtmlElementTest.class);

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
		html = "<p\n        dir=\"ltr\"\n" + "        class=\"pt-a-000004\">\n" + "        <span\n"
				+ "          xml:space=\"preserve\"\n" + "          class=\"pt-a0-000005\">    A．</span>\n"
				+ "        <span\n" + "          lang=\"zh-CN\"\n"
				+ "          class=\"pt-a0-000003\">公式:rId88</span>\n" + "        <span\n"
				+ "          xml:space=\"preserve\"\n" + "          class=\"pt-a0-000005\">    B．</span>\n"
				+ "        <span\n" + "          lang=\"zh-CN\"\n"
				+ "          class=\"pt-a0-000003\">公式:rId90</span>\n" + "        <span\n"
				+ "          xml:space=\"preserve\"\n" + "          class=\"pt-a0-000005\">      C．</span>\n"
				+ "        <span\n" + "          lang=\"zh-CN\"\n"
				+ "          class=\"pt-a0-000003\">公式:rId92</span>\n" + "        <span\n"
				+ "          xml:space=\"preserve\"\n" + "          class=\"pt-a0-000005\">      D．</span>\n"
				+ "        <span\n" + "          lang=\"zh-CN\"\n"
				+ "          class=\"pt-a0-000003\">公式:rId94</span>\n" + "      </p>\n";
		html1 = "        <span\n" + "          xml:space=\"preserve\"\n"
				+ "          class=\"pt-a0-000005\">    A．</span>\n" + "        <span\n" + "          lang=\"zh-CN\"\n"
				+ "          class=\"pt-a0-000003\">公式:rId88</span>\n" + "        <span\n"
				+ "          xml:space=\"preserve\"\n" + "          class=\"pt-a0-000005\">    B．</span>\n"
				+ "        <span\n" + "          lang=\"zh-CN\"\n"
				+ "          class=\"pt-a0-000003\">公式:rId90</span>\n" + "        <span\n"
				+ "          xml:space=\"preserve\"\n" + "          class=\"pt-a0-000005\">      C．</span>\n"
				+ "        <span\n" + "          lang=\"zh-CN\"\n"
				+ "          class=\"pt-a0-000003\">公式:rId92</span>\n" + "        <span\n"
				+ "          xml:space=\"preserve\"\n" + "          class=\"pt-a0-000005\">      D．</span>\n"
				+ "        <span\n" + "          lang=\"zh-CN\"\n"
				+ "          class=\"pt-a0-000003\">公式:rId94</span>\n" + "    hgfhfgf  \n";
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSplitTag() {
		List<LevelTag> lTags = HtmlElement.splitTag(html);
		assertTrue("标签截取错误", lTags.size() == 1);
		for (LevelTag t : lTags) {
			String s = t.substr(html);
			log.debug("\n=================\n" + s + "\n========================\n");
			if (!s.startsWith("<") || !s.endsWith(">")) {
				fail("标签截取错误");
			}
			for (LevelTag t1 : t.subs) {
				String s1 = t1.substr(html);
				if (!s1.startsWith("<") || !s1.endsWith(">")) {
					fail("标签截取错误");
				}
				log.debug("\n=================\n" + s1 + "\n========================\n");
			}
		}
		lTags = HtmlElement.splitTag(html1);
		assertTrue("标签截取错误", lTags.size() == 9);
	}

	@Test
	public void testParse() {
		// fail("Not yet implemented");
		List<LevelTag> lTags = HtmlElement.splitTag(html);
		HtmlElement root = new HtmlElement();
		root.name = "root";

		for (LevelTag lTag : lTags) {
			HtmlElement htmlElement = new HtmlElement();
			htmlElement.parse(html, lTag);
			root.childs.add(htmlElement);
		}

		assertTrue("解析到的子标签数量不对",root.childs.size() == 1);
		HtmlElement c1 = root.childs.get(0);
		assertTrue("解析到的子标签数量不对",c1.childs.size() == 8);
		assertTrue("解析到的标签名不对","p".equals(c1.name));
		assertTrue("解析到的标签属性数量错误",c1.attrs.size()==2);
	}

}
