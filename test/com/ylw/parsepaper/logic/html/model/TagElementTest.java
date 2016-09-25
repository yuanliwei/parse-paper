package com.ylw.parsepaper.logic.html.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ylw.parsepaper.logic.html.model.TagElement;

public class TagElementTest {

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
	public void testParse() {
		TagElement tagElement = new TagElement();
		String html = " asf</span> sjdk<h1></h1> <br> <hr/> </span> <span dddddddddddddddhshuhu ></span> ";
		tagElement.parse(html, 0);
		assertTrue(tagElement.type == TagElement.END);
		assertTrue("span".equals(tagElement.text));
		// n> sjdk<h1></h1> <br> <hr/> </span> <span dddddddddddddddhshuhu
		tagElement.parse(html, 9);
		assertTrue(tagElement.type == TagElement.START);
		assertTrue("h1".equals(tagElement.text));
		// <hr/> </span> <span dddddddddddddddhshuhu ></span>
		tagElement.parse(html, 30);
		assertTrue(tagElement.type == TagElement.SINGLE);
		assertTrue("hr".equals(tagElement.text));
		// <br> <hr/> </span> <span dddddddddddddddhshuhu ></span>
		tagElement.parse(html, 23);
		assertTrue(tagElement.type == TagElement.SINGLE);
		assertTrue("br".equals(tagElement.text));

		assertTrue("<br>".equals(tagElement.fullText));
	}

}
