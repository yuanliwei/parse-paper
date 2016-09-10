package com.ylw.parsepaper.utils;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PropUtilsTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		PropUtils.load();
	}

	@After
	public void tearDown() throws Exception {
		PropUtils.store();
	}

	@Test
	public void test() {
		PropUtils.put("test-proputil", "test-message");
		PropUtils.put("test-proputi2", "test-message");
		String value = PropUtils.get("test-proputil");
		assertTrue("保存的值没有取到", value != null);
		assertTrue("取出的值不是保存的值", "test-message".equals(value));
	}
}
