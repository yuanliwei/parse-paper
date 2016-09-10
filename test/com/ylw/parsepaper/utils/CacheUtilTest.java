package com.ylw.parsepaper.utils;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CacheUtilTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		CacheUtil.initCache();
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
	public void testPutGet() {
		CacheUtil.put("key1", "value1");
		String value = (String) CacheUtil.get("key1");
		assertTrue("取出的值跟保存的值不一样", "value1".equals(value));
	}
 }
