package com.lz.mybatis.custom.reader;

import java.io.InputStream;

import org.dom4j.Document;
import org.dom4j.io.SAXReader;

public class DocumentReader {

	public Document createDocument(InputStream inputStream) {
		Document document = null;
		try {
			SAXReader saxReader = new SAXReader();
			document = saxReader.read(inputStream);
			return document;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
