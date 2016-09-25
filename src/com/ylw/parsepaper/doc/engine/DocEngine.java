package com.ylw.parsepaper.doc.engine;

<<<<<<< master
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
=======
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.PicturesManager;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.hwpf.usermodel.PictureType;

import com.alibaba.fastjson.util.IOUtils;

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
		HWPFDocument wordDocument = new HWPFDocument(new FileInputStream(fileName));
		WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(
				DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());
		wordToHtmlConverter.setPicturesManager(new PicturesManager() {
			int i = 0;
			public String savePicture(byte[] content, PictureType pictureType, String suggestedName, float widthInches,
					float heightInches) {
				if (pictureType == PictureType.WMF) {
					String picName = suggestedName.substring(0, suggestedName.lastIndexOf(".")) + ".png";
					picName = "image" + i++ + ".svg";
					try {
						OutputStream stream = new FileOutputStream(picPath + picName);
						InputStream inputStream = new ByteArrayInputStream(content);
						ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
						Wmf2svg.parse(inputStream, outputStream);
						ByteArrayOutputStream outputStream1 = new ByteArrayOutputStream();
						Wmf2svg.handleWidthHeight(new ByteArrayInputStream(outputStream.toByteArray()), outputStream1);
						Wmf2svg.svgToPng(new ByteArrayInputStream(outputStream1.toByteArray()), stream);
						IOUtils.close(stream);
						IOUtils.close(inputStream);
						IOUtils.close(outputStream1);
					} catch (FileNotFoundException e) {
						picName = suggestedName;
						log.error(e.getMessage(), e);
					}

					return imgSrcPath + picName;
				} else {
					Picture picture = new Picture(0, content, true);
					try {
						picture.writeImageContent(new FileOutputStream(picPath + suggestedName));
					} catch (IOException e) {
						log.error(e.getMessage(), e);
					}
					return imgSrcPath + suggestedName;
				}
			}
		});
		wordToHtmlConverter.processDocument(wordDocument);

		org.w3c.dom.Document htmlDocument = wordToHtmlConverter.getDocument();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		DOMSource domSource = new DOMSource(htmlDocument);
		StreamResult streamResult = new StreamResult(out);

		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer serializer = tf.newTransformer();
		serializer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		serializer.setOutputProperty(OutputKeys.INDENT, "yes");
		serializer.setOutputProperty(OutputKeys.METHOD, "html");
		serializer.transform(domSource, streamResult);
		out.close();
		return new String(out.toByteArray());
>>>>>>> 341cfaa Signed-off-by: yuanliwei <891789592@qq.com>
	}
}