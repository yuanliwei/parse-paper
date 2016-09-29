package com.ylw.parsepaper.ui.model;

import java.util.Map;

import org.apache.commons.lang3.text.StrBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ylw.parsepaper.logic.paper.model.PartType;

public class ColorMapTest {
	private static Log log = LogFactory.getLog(ColorMapTest.class);

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
		// "paragraphsType_#{type}"
		Map<PartType, String> map = ColorMap.colorMap;
		StrBuilder strBuilder = new StrBuilder();

		map.forEach((type, color) -> {
			strBuilder.append("p.paragraphsType_").append(type).append("{\n");
			strBuilder.append("\tbackground: ").append(color).append(";\n");
			strBuilder.append("}\n");
		});
		log.debug("color style - \n " + strBuilder.toString() + " \n ====================");
	}

}
