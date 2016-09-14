package com.ylw.parsepaper.doc.engine;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.wmf.tosvg.WMFTranscoder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.PicturesManager;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.hwpf.usermodel.PictureType;
 
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
			public String savePicture(byte[] content, PictureType pictureType, String suggestedName, float widthInches,
					float heightInches) {
				if (pictureType == PictureType.WMF) {
					String picName = suggestedName.substring(0, suggestedName.lastIndexOf(".")) + ".svg";
					try {
						OutputStream stream = new FileOutputStream(picPath + picName);
						TranscoderOutput output = new TranscoderOutput(stream);
						WMFTranscoder transcoder = new WMFTranscoder();
						transcoder.addTranscodingHint(WMFTranscoder.KEY_ESCAPED, true);
						transcoder.addTranscodingHint(WMFTranscoder.KEY_WIDTH, new Float(widthInches * 1000));
						transcoder.addTranscodingHint(WMFTranscoder.KEY_HEIGHT, new Float(heightInches * 1000));
						TranscoderInput input = new TranscoderInput(new ByteArrayInputStream(content));
						transcoder.transcode(input, output);
					} catch (TranscoderException | FileNotFoundException e) {
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
	}
}
