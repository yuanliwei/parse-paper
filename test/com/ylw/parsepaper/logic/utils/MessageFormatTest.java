package com.ylw.parsepaper.logic.utils;

import java.text.ChoiceFormat;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sun.javafx.binding.StringFormatter;

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
		
	     
  String templ0 = "'{'\n" +
           "     bookId : \"{0}\",\n" +
           "     bookName : \"{1}\",\n" +
           "     isFree: \"{2}\",      // [Number] 0  不能用  1   可以用\n" +
           "     studyPro: \"{3}\",    // [String] 正本书的学习进度\n" +
           "     authDate: \"{4,date}\",    // [Number] 有效期，还剩多少天\n" +
           "     price: \"{5}\",       // [String] 价格，因为涉及到价格请传字符串    \n" +
           "     productId: \"{6}\",   // 产品ID\n" +
           "     children : [\n" +
           "         {7}\n" +
           "     ],\n" +
           "'}";
    String result10 = MessageFormat.format(templ0, new Object[]{"booooookId","BOOKNNNNNNAME",2,34,new Date(),3.5,34,"{},{},{}"});
    System.out.println(result10);
    
String templ = "'{\n" +
       "     bookId : \"{0}\",\n" +
       "     bookName : \"{1}\",\n" +
       "     isFree: \"{2}\",      // [Number] 0  不能用  1   可以用\n" +
       "     studyPro: \"{3}\",    // [String] 正本书的学习进度\n" +
       "     authDate: \"{4,date}\",    // [Number] 有效期，还剩多少天\n" +
       "     price: \"{5}\",       // [String] 价格，因为涉及到价格请传字符串    \n" +
       "     productId: \"{6}\",   // 产品ID\n" +
       "     children : [\n" +
       "         {7}\n" +
       "     ],\n" +
       "'}";
String result1 = MessageFormat.format(templ, new Object[]{"booooookId","BOOKNNNNNNAME",2,34,new Date(),3.5,34,"{},{},{}"});
System.out.println(result1);
//StringUtils.join(elements)
	}

}
