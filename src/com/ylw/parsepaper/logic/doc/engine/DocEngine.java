package com.ylw.parsepaper.logic.doc.engine;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DocEngine {
	private static Log log = LogFactory.getLog(DocEngine.class);

	public String parse(String docPath, String picPath, String imgSrcPath) {

		try {
			return convert2Html(docPath, picPath, imgSrcPath);
		} catch (IOException | ParserConfigurationException | TransformerException e) {
			log.error(e.getMessage(), e);
		}
		return null;

	}

	public static String convert2Html(String fileName, String picPath, String imgSrcPath)
			throws FileNotFoundException, IOException, ParserConfigurationException, TransformerException {

		return "html";
	}
}
