package com.lz.mybatis.custom.builder;

import java.io.InputStream;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;

import com.lz.mybatis.custom.Configuration;
import com.lz.mybatis.custom.MappedStatement;
import com.lz.mybatis.custom.reader.DocumentReader;
import com.lz.mybatis.custom.sqlsource.DefaultSqlSource;
import com.lz.mybatis.custom.sqlsource.SqlSource;

public class XMLMapperBuilder {
	
	private Configuration configuration;
	
	public XMLMapperBuilder(Configuration configuration) {
		this.configuration = configuration;
	}

	/**
	 * 解析mapper映射文件
	 * @param inputStream
	 */
	public void parse(InputStream inputStream) {
		DocumentReader documentReader = new DocumentReader();
		Document document = documentReader.createDocument(inputStream);
		
		// 解析mapper映射文件根标签
		parseMapperElement(document.getRootElement());
	}

	// 解析mapper映射文件根标签
	private void parseMapperElement(Element rootElement) {
		String namespace = rootElement.attributeValue("namespace");
		List<Element> selectElements = rootElement.elements("select");
		for (Element selectEle : selectElements) {
			parseStatement(namespace, selectEle);
		}
	}

	private void parseStatement(String namespace, Element selectEle) {
		String id = selectEle.attributeValue("id");
		String parameterType = selectEle.attributeValue("parameterType");
		// 获取入参类型（java类型）
		Class<?> parameterTypeClass = getTypeClass(parameterType);
		String resultType = selectEle.attributeValue("resultType");
		// 获取结果映射类型（java类型）
		Class<?> resultTypeClass = getTypeClass(resultType);
		String statementType = selectEle.attributeValue("statementType");
		
		// 未解析#{}的sql文本
		String sqlText = selectEle.getTextTrim();
		SqlSource sqlSource = new DefaultSqlSource(sqlText);
		
		// 将select标签信息封装到MappedStatement对象中，如何将MappedStatement封装到Configuration对象中
		MappedStatement mappedStatement = new MappedStatement(id, parameterTypeClass, resultTypeClass, statementType,
				sqlSource);

		configuration.addMappedStatement(namespace + "." + id, mappedStatement);
	}

	private Class<?> getTypeClass(String parameterType) {
		try {
			Class<?> clazz = Class.forName(parameterType);
			return clazz;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
