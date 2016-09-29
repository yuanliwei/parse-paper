package com.ylw.parsepaper.logic.main;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ylw.parsepaper.logic.html.engine.FormatHtmlEngine;
import com.ylw.parsepaper.logic.html.engine.SimpleHtmlEngine;
import com.ylw.parsepaper.logic.html.model.HtmlParagraph;
import com.ylw.parsepaper.logic.paper.engine.BrainEngine;
import com.ylw.parsepaper.logic.paper.engine.PaperEngine;
import com.ylw.parsepaper.logic.utils.CacheUtil;
import com.ylw.parsepaper.logic.utils.FileUtil;
import com.ylw.parsepaper.logic.utils.PropUtils;

public class ParseMain {
	private static Log log = LogFactory.getLog(ParseMain.class);
	public SimpleHtmlEngine simpleHtmlEngine = new SimpleHtmlEngine();
	public FormatHtmlEngine formatHtmlEngine = new FormatHtmlEngine();
	public PaperEngine paperEngine = new PaperEngine();
	public BrainEngine brainEngine = new BrainEngine();

	private String resultPath;

	public static void main(String[] args) {
		log.debug("hello parse paper.");
		PropUtils.load();
		CacheUtil.initCache();

		String htmlPath = PropUtils.get("test_html_path");
		String html = FileUtil.getString(htmlPath);
		log.debug(html);
	}

	public void parse(String docPath) {
		if (!FileUtil.isExistFile(docPath)) {
			throw new IllegalStateException("文件：\"" + docPath + "\" 不存在。。。。。");
		}

		String outPath = PropUtils.get("temp_out_path") + "temp.html";
		FileUtil.delete(outPath);

		openDoc(docPath, outPath);

		if (!FileUtil.isExistFile(outPath)) {
			throw new IllegalStateException("文件：\"" + docPath + "\" 没有转换成html");
		}

		resultPath = PropUtils.get("temp_out_path") + "format" + ".html";
		FileUtil.delete(resultPath);

		parseHtml(outPath, resultPath);

		if (!FileUtil.isExistFile(resultPath)) {
			throw new IllegalStateException("文件：\"" + outPath + "\" 没有转换成  \"" + resultPath + "\"");
		}

		parsePaper();

	}

	private void parsePaper() {
		List<HtmlParagraph> ps = simpleHtmlEngine.getParagraphs();

		brainEngine.guessParagraphType(ps);

		paperEngine.parse(ps);

		brainEngine.guessPartType(paperEngine.partEngine.parts);
	}

	// TODO 可能没用了
	public void parsePaperStruct() {
		List<HtmlParagraph> ps = simpleHtmlEngine.getParagraphs();
		paperEngine.parse(ps);
	}

	public void openDoc(String docPath, String outPath) {
		String doc2html = PropUtils.get("doc_to_html");
		log.info("DOC 3 HTML OUT - " + outPath);

		if (!FileUtil.isExistFile(doc2html)) {
			throw new IllegalStateException("没有找到doc解析器：" + doc2html);
		}

		try {
			String command = MessageFormat.format("{0} \"{1}\" \"{2}\"", doc2html, docPath, outPath);
			log.debug("command - " + command);
			Process p = Runtime.getRuntime().exec(command);
			int code = p.waitFor();
			log.info("parse doc result : " + code);
		} catch (IOException | InterruptedException e) {
			log.error(e.getMessage(), e);
		}
	}

	public void parseHtml(String htmlPath, String formatHtmlPath) {
		log.debug("parseHtml.");
		String html = FileUtil.getString(htmlPath, "GBK");
		if (StringUtils.isBlank(html)) {
			throw new IllegalStateException("未找到资源文件：" + htmlPath);
		}

		parseHtmlText(html, formatHtmlPath);

	}

	public void parseHtmlText(String html, String formatHtmlPath) {
		log.debug("parseHtmlText.");
		simpleHtmlEngine.parse(html);

		formatHtmlEngine.parseStyles(simpleHtmlEngine.getStyle());
		formatHtmlEngine.parseParagraphs(simpleHtmlEngine.getParagraphs());

		String htmlPage = formatHtmlEngine.getHtmlPage();

		FileUtil.saveFullPathFile(formatHtmlPath, htmlPage);
		log.debug("parseHtmlText.");
	}

	public String getHtmlPath() {
		return resultPath;
	}
}
