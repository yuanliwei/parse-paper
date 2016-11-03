package com.ylw.parsepaper.logic.utils;

import java.text.ChoiceFormat;
import java.text.MessageFormat;
import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MessageFormatTest {

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
	public void test() {
		int planet = 7;
		String event = "a disturbance in the Force";

		String result = MessageFormat.format("At {1,time} on {1,date}, there was {2} on planet {0,number,integer}.",
				planet, new Date(), event);

		System.out.println(result);

		int fileCount = 1273;
		String diskName = "MyDisk";
		Object[] testArgs = { new Long(fileCount), diskName };

		MessageFormat form = new MessageFormat("The disk \"{1}\" contains {0} file(s).");

		System.out.println(form.format(testArgs));

		form = new MessageFormat("The disk \"{1}\" contains {0}.");
		double[] filelimits = { 0, 1, 2 };
		String[] filepart = { "no files", "one file", "{0,number} files" };
		ChoiceFormat fileform = new ChoiceFormat(filelimits, filepart);
		form.setFormatByArgumentIndex(0, fileform);

		fileCount = 1273;
		diskName = "MyDisk";
		Object[] testArgs1 = { new Long(fileCount), diskName };

		System.out.println(form.format(testArgs1));
	}

}
