package com.ylw.parsepaper.doc.engine;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ylw.parsepaper.utils.PropUtils;

public class Wmf2svgTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		PropUtils.load();
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
		Wmf2svg wmf2svg = new Wmf2svg();
		String src = "./doc/wmf/17dcd.wmf";
		String dest = "./doc/genpic/17dcd.svg";
		wmf2svg.parse(src, dest);
	}

	@Test
	public void testSvgToPng() throws Exception {
		String src = "./doc/genpic/17dcd.svg";
 		String dest = "./doc/genpic/17dcd.png";
		Wmf2svg.svgToPng(src, dest);
	}

}
