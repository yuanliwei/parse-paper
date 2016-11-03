package com.ylw.parsepaper.logic.main;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.htmlparser.util.ParserException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class HtmlParseTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
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
	public void test() throws DocumentException {
		String html = "<root><h1><span></span><span></span><span></span></h1><h2><span></span><span></span></h2><h3><h2><span></span><span></span></h2><h1><span></span></h1></h3></root>";
		SAXReader reader = new SAXReader();
		Document document = reader.read(new ByteArrayInputStream(html.getBytes()));
		Element root = document.getRootElement();
		List<Element> childs = root.elements();
		for (Element child : childs) {
			// 未知属性名情况下
			/*
			 * List<Attribute> attributeList = child.attributes(); for
			 * (Attribute attr : attributeList) {
			 * System.out.println(attr.getName() + ": " + attr.getValue()); }
			 */

			// 已知属性名情况下
			System.out.println("id: " + child.attributeValue("id"));

			// 未知子元素名情况下
			/*
			 * List<Element> elementList = child.elements(); for (Element ele :
			 * elementList) { System.out.println(ele.getName() + ": " +
			 * ele.getText()); } System.out.println();
			 */

			// 已知子元素名的情况下
			System.out.println("title" + child.elementText("title"));
			System.out.println("author" + child.elementText("author"));
			// 这行是为了格式化美观而存在
			System.out.println();
			// assertTrue("html 解析错误！", root.getChildren().size() > 0);
			// fail("Not yet implemented");
		}
	}

}
