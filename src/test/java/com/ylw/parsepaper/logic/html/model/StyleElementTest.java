package com.ylw.parsepaper.logic.html.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ylw.parsepaper.logic.html.model.StyleElement;

public class StyleElementTest {
	String style;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * <pre>
	 * span.pt-a0-000008 {
			font-family: Calibri;
			font-size: 10.5pt;
			font-style: italic;
			font-weight: normal;
			margin: 0;
			padding: 0;
		}
	 * </pre>
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		style = "\nspan.pt-a0-000008 {\n    font-family: Calibri;\n    font-size: 10.5pt;\n    font-style: italic;\n    font-weight: normal;\n    margin: 0;\n    padding: 0;\n}\n";
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		StyleElement styleElement = new StyleElement();
		styleElement.parseString(style);
		assertTrue("解析结果错误", "span.pt-a0-000008".equals(styleElement.name));
		assertTrue("解析样式属性时出错1", styleElement.attrs.size() == 6);
		assertTrue("解析样式属性时出错2", "10.5pt".equals(styleElement.attrs.get("font-size")));
	}

}
