package com.ylw.parsepaper.html.engine;

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

import com.ylw.parsepaper.html.model.HtmlParagraph;
import com.ylw.parsepaper.utils.FileUtil;
import com.ylw.parsepaper.utils.PropUtils;

/**
 * @author ylw
 *
 */
public class SimpleHtmlEngineTest {
	private static Log log = LogFactory.getLog(SimpleHtmlEngineTest.class);
	private static SimpleHtmlEngine engine;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		PropUtils.load();
		engine = new SimpleHtmlEngine();
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
	public void testReplaceStr() {
		String string = "qwertyuioppasdfghjklzxcvbnm";
		assertTrue("替换字符串方法出错",
				"nnnnnnnnnnioppasdfghjklzxcvbnm".equals(engine.replaceStr(string, "qwertyu", "nnnnnnnnnn")));

		string = "qwertyuioppasdfghjklzxcvbnm";
		assertTrue("替换字符串方法出错", "qwertyuioppabbbbbbjklzxcvbnm".equals(engine.replaceStr(string, "sdfgh", "bbbbbb")));

		string = "qwertyuioppasdfghjklzxcvbnm";
		assertTrue("替换字符串方法出错", "qwertyuioppasdfghjklzmmmmmm".equals(engine.replaceStr(string, "xcvbnm", "mmmmmm")));
	}

	@Test
	public void testDeleteStr() {
		String string = "qwertyuioppasdfghjklzxcvbnm";
		assertTrue("删除字符串方法出错", "qwertyuisdfghjklzxcvbnm".equals(engine.deleteStr(string, "oppa")));

		string = "qwertyuioppasdfghjklzxcvbnm";
		assertTrue("删除字符串方法出错", "yuioppasdfghjklzxcvbnm".equals(engine.deleteStr(string, "qwert")));

		string = "qwertyuioppasdfghjklzxcvbnm";
		assertTrue("删除字符串方法出错", "qwertyuioppasdfghjklzx".equals(engine.deleteStr(string, "cvbnm")));

		string = "qwertyuioppasdfghjklzxcvbnm";
		assertTrue("删除字符串方法出错", "qwertyuioasdfghjklzxcvbnm".equals(engine.deleteStr(string, "p")));

		string = "qwertyuioppasdfghjklzxcvbnm";
		assertTrue("删除字符串方法出错", "qwerbnm".equals(engine.deleteStr(string, "tyuioppasdfghjklzxcv")));
	}

	@Test
	public void testDeleteRange() {
		String string = "assfggythfz<!-- jdj --> >xcvbnmkj";
		String prefix = "<!--";
		String endfix = "-->";
		int maxLength = 0;

		// normal test
		assertTrue("删除指定范围字符串方法出错",
				"assfggythfz >xcvbnmkj".equals(engine.deleteRange(string, prefix, endfix, maxLength)));

		// maxLength test
		maxLength = 0;
		assertTrue("删除指定范围字符串方法出错",
				"assfggythfz >xcvbnmkj".equals(engine.deleteRange(string, prefix, endfix, maxLength)));

		maxLength = 13;
		assertTrue("删除指定范围字符串方法出错",
				"assfggythfz >xcvbnmkj".equals(engine.deleteRange(string, prefix, endfix, maxLength)));

		maxLength = 12;
		assertTrue("删除指定范围字符串方法出错",
				"assfggythfz<!-- jdj --> >xcvbnmkj".equals(engine.deleteRange(string, prefix, endfix, maxLength)));

		// start position test
		maxLength = 0;
		string = "<!-- ";
		assertTrue("删除指定范围字符串方法出错", "<!-- ".equals(engine.deleteRange(string, prefix, endfix, maxLength)));

		// end position test
		string = "--> ";
		assertTrue("删除指定范围字符串方法出错", "--> ".equals(engine.deleteRange(string, prefix, endfix, maxLength)));

		string = "wer -->";
		assertTrue("删除指定范围字符串方法出错", "wer -->".equals(engine.deleteRange(string, prefix, endfix, maxLength)));

		string = "rtrt --> dfgdfg";
		assertTrue("删除指定范围字符串方法出错", "rtrt --> dfgdfg".equals(engine.deleteRange(string, prefix, endfix, maxLength)));

		string = "rtrt --> dfgdfg";
		assertTrue("删除指定范围字符串方法出错", "rtrt --> dfgdfg".equals(engine.deleteRange(string, prefix, endfix, maxLength)));

		string = "<!-- jdj -->rtrt --><!-- jdj --> dfgdfg";
		assertTrue("删除指定范围字符串方法出错", "rtrt --> dfgdfg".equals(engine.deleteRange(string, prefix, endfix, maxLength)));

		string = "<!-- jdj -->rtrt --><!-- jdj --> dfgdfg<!-- jdj -->";
		assertTrue("删除指定范围字符串方法出错", "rtrt --> dfgdfg".equals(engine.deleteRange(string, prefix, endfix, maxLength)));

		string = "<!-- jdj -->rtrt --><!-- jdj --> dfgdfg<!-- jdj --> <!-- dfjgkfjg";
		assertTrue("删除指定范围字符串方法出错",
				"rtrt --> dfgdfg <!-- dfjgkfjg".equals(engine.deleteRange(string, prefix, endfix, maxLength)));

		string = "\nmso-style-noshow:yes;\n";
		assertTrue("删除指定范围字符串方法出错", "\n\n".equals(engine.deleteRange(string, "mso-", ";", 80)));

		string = "\nmso-st<o:p></o:p>yle-noshow:yes;\n";
		assertTrue("删除指定范围字符串方法出错",
				"\nmso-style-noshow:yes;\n".equals(engine.deleteRange(string, "<o:p>", "</o:p>", 30)));
	}

	@Test
	public void testReplaceRange() {
		String string = "assfggythfz<!-- jdj --> >xcvbnmkj";
		String prefix = "<!--";
		String endfix = "-->";
		int maxLength = 30;

		assertTrue("替换指定范围字符串方法出错",
				"assfggythfzPPPPP >xcvbnmkj".equals(engine.replaceRange(string, prefix, endfix, maxLength, "PPPPP")));
	}

	@Test
	public void testDeleteComment() {
		String string = "<!-- jdj -->rtrt --><!-- jdj --> dfgdfg<!-- jdj -->";
		assertTrue("删除指定范围字符串方法出错", "rtrt --> dfgdfg".equals(engine.deleteComment(string)));
	}

	@Test
	public void testFindRanges() {
		String string = "<!-- rtyty -->assf<!-- dfgk -->ggy<!--thfz<!--23465767--> >x-->cvbnmkj<!--ERTYUIO-->";
		String prefix = "<!--";
		String endfix = "-->";
		int maxLength = 30;

		List<String> results = engine.findRanges(string, prefix, endfix, maxLength);
		assertTrue("查找指定范围字符串方法出错", results.size() == 4);
		assertTrue("查找指定范围字符串方法出错", "<!-- rtyty -->".equals(results.get(0)));
		assertTrue("查找指定范围字符串方法出错", "<!-- dfgk -->".equals(results.get(1)));
		assertTrue("查找指定范围字符串方法出错", "<!--thfz<!--23465767-->".equals(results.get(2)));
		assertTrue("查找指定范围字符串方法出错", "<!--ERTYUIO-->".equals(results.get(3)));

	}

	@Test
	public void testParse() {
		String sample = "ssdffgfghfghgh";
		String r = engine.parse(sample);
		log.debug("========|" + r + "|");
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
		// log.debug(html);
		String resultPath = PropUtils.get("temp_out_path") + "handled1.html";

		// 原始文件另存为...
		// FileUtil.saveFullPathFile(resultPath, html, "GBK");
		assertFalse("未找到资源文件：" + htmlPath, TextUtils.isBlank(html));

		String result = engine.parse(html);

		resultPath = PropUtils.get("temp_out_path") + "handled2.html";
		FileUtil.saveFullPathFile(resultPath, result, "GBK");

		assertTrue("解析结果还包含有注释内容 <!-- * -->", result.indexOf("<!--") == -1);
	}

	@Test
	public void testGetParagraphs() {
		String htmlPath = PropUtils.get("test_html_path");
		String html = FileUtil.getString(htmlPath, "GBK");
		assertFalse("未找到资源文件：" + htmlPath, TextUtils.isBlank(html));

		List<HtmlParagraph> results = engine.getParagraphs(html);
		StringBuilder result = new StringBuilder();
		for (HtmlParagraph htmlParagraph : results) {
			if (htmlParagraph.html.startsWith("<") && htmlParagraph.html.endsWith(">")) {
				result.append("<p " + htmlParagraph.pStyle + ">" + htmlParagraph.html + "</p>");
			} else {
				fail("解析的段落格式不对！" + htmlParagraph.raw);
			}
		}

		String resultPath = PropUtils.get("temp_out_path") + "Paragraphs.html";
		FileUtil.saveFullPathFile(resultPath, result.toString(), "GBK");

	}

}
