package com.ylw.parsepaper.logic.html;

import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.util.TextUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ylw.parsepaper.logic.html.ParseHtml;
import com.ylw.parsepaper.logic.html.model.StyleElement;
import com.ylw.parsepaper.logic.utils.FileUtil;
import com.ylw.parsepaper.logic.utils.PropUtils;

public class GetStyleList {

	private static Log log = LogFactory.getLog(GetStyleList.class);
	private static String html;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		PropUtils.load();

		String htmlPath = PropUtils.get("test_html_path");
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
		if (TextUtils.isBlank(html)) {
			fail("文本内容为空");
		}
		ParseHtml parseHtml = new ParseHtml();
		parseHtml.parse(html);
		
		Map<String, StyleElement> styleElements = parseHtml.getStyleElements();
		Map<String, String> styleMap = new HashMap<>();
		for (Entry<String, StyleElement> e : styleElements.entrySet()) {
			StyleElement v = e.getValue();
			for (Entry<String, String> ee : v.attrs.entrySet()) {
				styleMap.put(ee.getKey(), "");
			}
		}
		StringBuilder sb = new StringBuilder("\n");
		for (Entry<String, String> e : styleMap.entrySet()) {
			sb.append("\t\t").append(e.getKey()).append("\n");
		}
		log.debug(sb.toString());
	}
}
