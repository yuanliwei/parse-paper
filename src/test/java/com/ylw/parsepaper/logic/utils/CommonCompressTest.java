package com.ylw.parsepaper.logic.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.commons.compress.archivers.ArchiveException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CommonCompressTest {

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
	public void test() throws IOException, ArchiveException {

		String sPath = "C:\\Users\\y\\Desktop\\Catalog-v3.8.zip";
		String tPath = "C:\\Users\\y\\Desktop\\Catalog-v3.8";

//		String dir = "D:\\workspace\\runtime-com.qz.meta.product\\xxxx\\.image";
//		String zippath = "D:\\test2.zip";
//		ZipUtil.zip(dir, zippath);

		String unzipdir = tPath;
		String unzipfile = sPath;
		ZipUtil.unzip(unzipfile, unzipdir);

		System.out.println("success!");

		// fail("Not yet implemented");
	}

}
