package com.ylw.parsepaper.logic.main;

import static org.junit.Assert.fail;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ylw.parsepaper.logic.utils.FileUtil;
import com.ylw.parsepaper.logic.utils.PropUtils;

public class ParseMainTest {
	private static Log log = LogFactory.getLog(ParseMainTest.class);

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
	public void testOpenDoc() {
		ParseMain parseMain = new ParseMain();
		String docPath = PropUtils.get("test_doc_path");

		if (!FileUtil.isExistFile(docPath)) {
			fail("文件：\"" + docPath + "\" 不存在。。。。。");
		}

		String outPath = PropUtils.get("temp_out_path") + "temp.html";
		FileUtil.delete(outPath);

		parseMain.openDoc(docPath, outPath);

		if (!FileUtil.isExistFile(outPath)) {
			fail("文件：\"" + docPath + "\" 没有转换成html");
		}
	}

	@Test
	public void testParseHtml() {
		ParseMain parseMain = new ParseMain();
		String docPath = PropUtils.get("test_doc_path");

		if (!FileUtil.isExistFile(docPath)) {
			fail("文件：\"" + docPath + "\" 不存在。。。。。");
		}

		String outPath = PropUtils.get("temp_out_path") + "temp.html";
		FileUtil.delete(outPath);

		parseMain.openDoc(docPath, outPath);

		if (!FileUtil.isExistFile(outPath)) {
			fail("文件：\"" + docPath + "\" 没有转换成html");
		}

		String resultPath = PropUtils.get("temp_out_path") + "format.html";
		FileUtil.delete(resultPath);

		parseMain.parseHtml(outPath, resultPath);

		if (!FileUtil.isExistFile(resultPath)) {
			fail("文件：\"" + outPath + "\" 没有转换成  \"" + resultPath + "\"");
		}

	}

}
