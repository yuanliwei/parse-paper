/**
 * 
 */
package com.ylw.parsepaper.logic.paper.engine;

import static org.junit.Assert.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ylw.parsepaper.logic.html.engine.FormatHtmlEngine;
import com.ylw.parsepaper.logic.html.engine.SimpleHtmlEngine;
import com.ylw.parsepaper.logic.main.ParseMain;
import com.ylw.parsepaper.logic.utils.FileUtil;
import com.ylw.parsepaper.logic.utils.PropUtils;

/**
 * @author y
 *
 */
public class PaperEngineTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		PropUtils.load();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link com.ylw.parsepaper.logic.paper.engine.PaperEngine#PaperEngine()}.
	 */
	private static Log log = LogFactory.getLog(ParseMain.class);
	public SimpleHtmlEngine simpleHtmlEngine = new SimpleHtmlEngine();
	public FormatHtmlEngine formatHtmlEngine = new FormatHtmlEngine();
	public PaperEngine paperEngine = new PaperEngine();
	public BrainEngine brainEngine = new BrainEngine();

	@Test
	public void testPaperEngine() {
		String outPath = FileUtil.getCurWorkspacePath() + PropUtils.get("temp_out_path") + "temp.html";
		String html = FileUtil.getString(outPath, "GBK");
		simpleHtmlEngine.parse(html);

		formatHtmlEngine.parseStyles(simpleHtmlEngine.getStyle());
		formatHtmlEngine.parseParagraphs(simpleHtmlEngine.getParagraphs());

		String htmlPage = formatHtmlEngine.getHtmlPage();
		paperEngine.parse(formatHtmlEngine.paragraphs);

		fail("Not yet implemented");
	}

}
