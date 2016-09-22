package com.ylw.parsepaper.html.engine;

import static org.junit.Assert.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.util.TextUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ylw.parsepaper.utils.FileUtil;
import com.ylw.parsepaper.utils.PropUtils;

public class SimpleHtmlEngineTest {
	private static Log log = LogFactory.getLog(SimpleHtmlEngineTest.class);

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
		SimpleHtmlEngine engine = new SimpleHtmlEngine();
		String sample = "ssdffgfghfghgh";
		String r = engine.parse(sample);
		assertTrue(sample.equals(r));

		sample = "<ha>sdf</sdf>";
		r = engine.parse(sample);
		assertTrue(sample.equals(r));

		sample = "<!-->sd\n\r\ndfgf</sd-->f>";
		log.info("==========begin=============");
		log.debug(sample.replaceAll("(.|[\n\r])", "NNN"));

		log.debug(sample.replaceAll("<!--(.|[\n\r])*?-->", "==="));

		sample = "<!-->sdf</sd-->f>";
		r = engine.parse(sample);
		log.debug(r);
		assertTrue("f>".equals(r));

		// fail();
		String htmlPath = PropUtils.get("test_html_path");
		String html = FileUtil.getString(htmlPath, "GBK");
		log.debug(html);
		String resultPath = PropUtils.get("handled_html_path1");
		FileUtil.saveFullPathFile(resultPath, html, "GBK");
		assertFalse("未找到资源文件：" + htmlPath, TextUtils.isBlank(html));

		String result = engine.parse(html);

		resultPath = PropUtils.get("handled_html_path2");
		FileUtil.saveFullPathFile(resultPath, result, "GBK");

		assertTrue("解析结果还包含有注释内容 <!-- * -->", result.indexOf("<!--") == -1);
	}

}
