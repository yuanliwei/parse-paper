package com.ylw.parsepaper.logic.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.util.IOUtils;

public class Res {

	private static Log log = LogFactory.getLog(Res.class);

	public static String getString(String fileName) {
		InputStream inStream = null;
		try {
			inStream = getInputStream(fileName);
			final ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			final byte[] tmp = new byte[4096];
			int l;
			while ((l = inStream.read(tmp)) != -1) {
				buffer.write(tmp, 0, l);
			}
			return new String(buffer.toByteArray(), "utf-8");
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		} finally {
			IOUtils.close(inStream);
		}
		return fileName;
	}

	public static InputStream getInputStream(String fileName) {
		try {
			String name = fileName;
			Class<? extends FileUtil> clazz = new FileUtil().getClass();
			URL url = clazz.getClassLoader().getResource(name);
			log.debug(url.toString());
			return clazz.getClassLoader().getResourceAsStream(name);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}

}
