package com.ylw.parsepaper.doc.engine;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ylw.parsepaper.utils.FileUtil;
import com.ylw.parsepaper.utils.PropUtils;

public class DocEngineTest {

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
	public void testParse() throws IOException, TransformerException, ParserConfigurationException {
		DocEngine docEngine = new DocEngine();
		String docPath = PropUtils.get("test_doc_path");
		String html = docEngine.parse(docPath, "./doc/pics/", "./pics/");
		FileUtil.saveFullPathFile("./doc/generate.html", html);
		// fail("Not yet implemented");
	}

}
