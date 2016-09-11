package org.apache.batik.transcoder.wmf.tosvg;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class WMFTranscoderTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
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
	public void testTranscode() throws FileNotFoundException {

		TranscoderInput input = new TranscoderInput(new FileInputStream("./doc/wmf/2692.wmf"));
		String outputFile = "./doc/wmf/2692.svg";
		OutputStream stream = new FileOutputStream(outputFile);
		TranscoderOutput output = new TranscoderOutput(stream);
		WMFTranscoder transcoder = new WMFTranscoder();
		transcoder.addTranscodingHint(WMFTranscoder.KEY_ESCAPED, true);
		transcoder.addTranscodingHint(WMFTranscoder.KEY_WIDTH, new Float(4000));
		try {
			transcoder.transcode(input, output);
		} catch (TranscoderException e) {
			e.printStackTrace();
		}
	}

}
