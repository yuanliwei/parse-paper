package com.ylw.parsepaper.doc.engine;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.TranscodingHints;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.apache.batik.transcoder.wmf.tosvg.WMFTranscoder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import net.arnx.wmf2svg.gdi.svg.SvgGdi;
import net.arnx.wmf2svg.gdi.wmf.WmfParser;

public class Wmf2svg {

	private static Log log = LogFactory.getLog(Wmf2svg.class);

	public void parse(String src, String dest) {
		boolean compatible = false;
		try {
			InputStream in = new FileInputStream(src);
			WmfParser parser = new WmfParser();
			final SvgGdi gdi = new SvgGdi(compatible);
			parser.parse(in, gdi);

			Document doc = gdi.getDocument();
			OutputStream out = new FileOutputStream(dest);
			if (dest.endsWith(".svgz")) {
				out = new GZIPOutputStream(out);
			}
			output(doc, out);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	public static void parse(InputStream in, OutputStream out) {
		boolean compatible = false;
		try {
			WmfParser parser = new WmfParser();
			final SvgGdi gdi = new SvgGdi(compatible);
			parser.parse(in, gdi);

			Document doc = gdi.getDocument();
			output(doc, out);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	public static void parse(InputStream in, OutputStream out, int widthInches, int heightInches) {
		try {
			TranscoderOutput output = new TranscoderOutput(out);
			WMFTranscoder transcoder = new WMFTranscoder();
			// transcoder.addTranscodingHint(WMFTranscoder.KEY_ESCAPED, true);
			// transcoder.addTranscodingHint(WMFTranscoder.KEY_WIDTH, new
			// Float(widthInches * 1000));
			// transcoder.addTranscodingHint(WMFTranscoder.KEY_HEIGHT, new
			// Float(heightInches * 1000));
			TranscoderInput input = new TranscoderInput(in);
			transcoder.transcode(input, output);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	/**
	 * @Description: 输出svg文件
	 * @param doc
	 * @param out
	 * @throws Exception
	 *             设定文件
	 */
	private static void output(Document doc, OutputStream out) throws Exception {
		TransformerFactory factory = TransformerFactory.newInstance();
		Transformer transformer = factory.newTransformer();
		transformer.setOutputProperty(OutputKeys.METHOD, "xml");
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, "-//W3C//DTD SVG 1.0//EN");
		transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM,
				"http://www.w3.org/TR/2001/REC-SVG-20010904/DTD/svg10.dtd");
		transformer.transform(new DOMSource(doc), new StreamResult(out));
		out.flush();
		out.close();
		out = null;
	}

	/**
	 * 将svg图片转成png图片
	 * 
	 * @param filePath
	 * @throws Exception
	 */
	public static void svgToPng(String svgPath, String pngFile) throws Exception {
		File svg = new File(svgPath);
		FileInputStream wmfStream = new FileInputStream(svg);
		ByteArrayOutputStream imageOut = new ByteArrayOutputStream();
		int noOfByteRead = 0;
		while ((noOfByteRead = wmfStream.read()) != -1) {
			imageOut.write(noOfByteRead);
		}
		imageOut.flush();
		imageOut.close();
		wmfStream.close();

		ByteArrayOutputStream jpg = new ByteArrayOutputStream();
		FileOutputStream jpgOut = new FileOutputStream(pngFile);

		byte[] bytes = imageOut.toByteArray();
		PNGTranscoder t = new PNGTranscoder();
		TranscoderInput in = new TranscoderInput(new ByteArrayInputStream(bytes));
		TranscoderOutput out = new TranscoderOutput(jpg);
		t.transcode(in, out);
		jpgOut.write(jpg.toByteArray());
		jpgOut.flush();
		jpgOut.close();
		imageOut = null;
		jpgOut = null;
	}

	/**
	 * 将svg图片转成png图片
	 * 
	 * @param filePath
	 * @throws Exception
	 */
	public static void svgToPng(InputStream svgInput, OutputStream pngOut) {
		try {
			ByteArrayOutputStream imageOut = new ByteArrayOutputStream();
			int noOfByteRead = 0;
			while ((noOfByteRead = svgInput.read()) != -1) {
				imageOut.write(noOfByteRead);
			}
			imageOut.flush();
			imageOut.close();
			svgInput.close();

			ByteArrayOutputStream jpg = new ByteArrayOutputStream();

			byte[] bytes = imageOut.toByteArray();
			PNGTranscoder t = new PNGTranscoder();
			TranscoderInput in = new TranscoderInput(new ByteArrayInputStream(bytes));
			TranscoderOutput out = new TranscoderOutput(jpg);
			t.transcode(in, out);
			pngOut.write(jpg.toByteArray());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	/**
	 * 将wmf图片转成png图片(备用方法，即当上面的转换失败时用这个)
	 * 
	 * @param filePath
	 * @throws Exception
	 */
	public static String wmfToJpg(String wmfPath) throws Exception {
		// 先wmf-->svg
		File wmf = new File(wmfPath);
		FileInputStream wmfStream = new FileInputStream(wmf);
		ByteArrayOutputStream imageOut = new ByteArrayOutputStream();
		int noOfByteRead = 0;
		while ((noOfByteRead = wmfStream.read()) != -1) {
			imageOut.write(noOfByteRead);
		}
		imageOut.flush();
		imageOut.close();
		wmfStream.close();

		// WMFHeaderProperties prop = new WMFHeaderProperties(wmf);
		WMFTranscoder transcoder = new WMFTranscoder();
		TranscodingHints hints = new TranscodingHints();
		transcoder.setTranscodingHints(hints);
		TranscoderInput input = new TranscoderInput(new ByteArrayInputStream(imageOut.toByteArray()));
		ByteArrayOutputStream svg = new ByteArrayOutputStream();
		TranscoderOutput output = new TranscoderOutput(svg);
		transcoder.transcode(input, output);

		// 再svg-->png
		ByteArrayOutputStream jpg = new ByteArrayOutputStream();
		String jpgFile = wmfPath.replace("wmf", "png");
		FileOutputStream jpgOut = new FileOutputStream(jpgFile);

		byte[] bytes = svg.toByteArray();
		PNGTranscoder t = new PNGTranscoder();
		TranscoderInput in = new TranscoderInput(new ByteArrayInputStream(bytes));
		TranscoderOutput out = new TranscoderOutput(jpg);
		t.transcode(in, out);
		jpgOut.write(jpg.toByteArray());
		jpgOut.flush();
		jpgOut.close();
		return jpgFile;
	}

	public static void handleWidthHeight(ByteArrayInputStream inputStream, ByteArrayOutputStream outputStream) {
		try {
			int defaultHeight = 300;
			int defaultWidth = 500;

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder;
			builder = factory.newDocumentBuilder();
			builder.setEntityResolver(new IgnoreDTDEntityResolver());
			Document doc = null;
			doc = builder.parse(inputStream);
			// 限制svg图片宽高
			float height = Integer.parseInt(((Element) doc.getElementsByTagName("svg").item(0)).getAttribute("height"));
			float width = Integer.parseInt(((Element) doc.getElementsByTagName("svg").item(0)).getAttribute("width"));

//			log.debug(String.format("+raw - w: %.0f h: %.0f", width, height));
			if (height > defaultHeight) {
				float zoom = height * 1f / defaultHeight;
				height = defaultHeight;
				width = width / zoom;
			}
			if (width > defaultWidth) {
				float zoom = width * 1f / defaultWidth;
				width = defaultWidth;
				height = height / zoom;
			}

			((Element) doc.getElementsByTagName("svg").item(0)).setAttribute("width", String.valueOf((int)width));
			((Element) doc.getElementsByTagName("svg").item(0)).setAttribute("height", String.valueOf((int)height));

//			log.debug(String.format("-raw - w: %.0f h: %.0f", width, height));

			ByteArrayOutputStream outputStream0 = new ByteArrayOutputStream();
			output(doc, outputStream0);

			String svgStr = new String(outputStream0.toByteArray(), "UTF-8");
			svgStr.replaceAll("ø", "");
			outputStream.write(svgStr.getBytes("UTF-8"));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	static class IgnoreDTDEntityResolver implements EntityResolver {
		@Override
		public InputSource resolveEntity(String arg0, String arg1) throws SAXException, IOException {
			return new InputSource(new ByteArrayInputStream("<?xml version='1.0' encoding='UTF-8'?>".getBytes()));
		}
	}
}
