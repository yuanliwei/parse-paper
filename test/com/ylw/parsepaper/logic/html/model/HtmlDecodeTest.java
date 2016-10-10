package com.ylw.parsepaper.logic.html.model;

import static org.junit.Assert.*;

import org.apache.commons.lang3.StringEscapeUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class HtmlDecodeTest {

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
	public void test() {
		String input = "本试卷分第&#8544;卷（选择题）和第&#8545;卷（非选择题）两部分，第&#8544;卷1至8页，第&#8545;卷9至16页，共300分。";
		String output = StringEscapeUtils.unescapeHtml4(input);
		System.out.println(output);
		input = "A．研发中心&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;B．一般零部件厂";
		output = StringEscapeUtils.unescapeHtml4(input);
		System.out.println(output);
	}

}
