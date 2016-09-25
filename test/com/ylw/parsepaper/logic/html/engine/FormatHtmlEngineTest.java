package com.ylw.parsepaper.logic.html.engine;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.util.TextUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ylw.parsepaper.logic.html.engine.FormatHtmlEngine;
import com.ylw.parsepaper.logic.html.engine.SimpleHtmlEngine;
import com.ylw.parsepaper.logic.html.model.HtmlParagraph;
import com.ylw.parsepaper.logic.utils.FileUtil;
import com.ylw.parsepaper.logic.utils.PropUtils;

public class FormatHtmlEngineTest {
	private static Log log = LogFactory.getLog(FormatHtmlEngineTest.class);

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
	public void test() {
		SimpleHtmlEngine simpleHtmlEngine = new SimpleHtmlEngine();
		FormatHtmlEngine formatHtmlEngine = new FormatHtmlEngine();

		String htmlPath = PropUtils.get("test_html_path");
		String html = FileUtil.getString(htmlPath, "GBK");
		assertFalse("未找到资源文件：" + htmlPath, TextUtils.isBlank(html));

		simpleHtmlEngine.parse(html);

		formatHtmlEngine.parseStyles(simpleHtmlEngine.getStyle());
		formatHtmlEngine.parseParagraphs(simpleHtmlEngine.getParagraphs());

		String htmlPage = formatHtmlEngine.getHtmlPage();

		String resultPath = PropUtils.get("temp_out_path") + "format.html";
		FileUtil.saveFullPathFile(resultPath, htmlPage);
	}

}
