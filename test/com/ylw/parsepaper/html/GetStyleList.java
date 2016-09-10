package com.ylw.parsepaper.html;

import static org.junit.Assert.*;

import org.apache.http.util.TextUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ylw.parsepaper.utils.FileUtil;
import com.ylw.parsepaper.utils.PropUtils;

public class GetStyleList {

	private static String html;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		PropUtils.load();
		String htmlPath = "";
		html = FileUtil.getString(htmlPath);
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
	public void getStyleList() {

	}
}
