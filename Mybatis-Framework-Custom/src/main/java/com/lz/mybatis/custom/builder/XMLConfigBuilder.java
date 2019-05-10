package com.lz.mybatis.custom.builder;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;

import com.lz.mybatis.custom.Configuration;
import com.lz.mybatis.custom.reader.DocumentReader;

/**
 * 解析全局配置文件
 * @author lizhong
 *
 */
public class XMLConfigBuilder {
	private Configuration configuration;
	
	public XMLConfigBuilder() {
		this.configuration = new Configuration();
	}
	
	/**
	 * 解析全局配置文件
	 * @param inputStream
	 * @return
	 */
	public Configuration parse(InputStream inputStream) {
		// 创建Document对象（没有对mybatis语义进行解析）
		DocumentReader documentReader = new DocumentReader();
		Document document = documentReader.createDocument(inputStream);
		
		// 根据mybatis语义去解析Document对象，将解析结果封装到一个对象（Configuration）
		// 解析全局配置文件的根路径
		parserConfiguration(document.getRootElement());
		return configuration;
	}

	/**
	 * 解析configuration根标签
	 * @param rootElement
	 */
	private void parserConfiguration(Element rootElement) {
		//解析environments标签
		parserEnvironmentsElement(rootElement.element("environments"));
		//解析mappers标签
		parserMappersElement(rootElement.element("mappers"));
		
	}

	// 解析environments标签
	private void parserEnvironmentsElement(Element element) {
		// 解析数据源信息(dom4j + xpath)
		// 将数据源信息封装到Configuration对象中

		// 获取默认的环境对象的ID
		String defaultEnvId = element.attributeValue("default");
		if (defaultEnvId == null || defaultEnvId.equals("")) {
			return;
		}
		
		//获取所有environment标签,创建数据源
		List<Element> elements = element.elements();
		for (Element envElement : elements) {
			String id = envElement.attributeValue("id");
			if (defaultEnvId.equals(id)) {
				//创建数据源
				createDataSource(envElement);
			}
		}
		
	}
	
	private void parserMappersElement(Element element) {
		List<Element> mapperElements = element.elements();
		for (Element mapperElement : mapperElements) {
			parseMapperElement(mapperElement);
		}
		
	}

	private void parseMapperElement(Element mapperElement) {
		// 1.根据映射文件路径，读取映射文件（InputStream流）
		String resource = mapperElement.attributeValue("resource");
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(resource);
		// 2.创建Document对象（没有对mybatis语义进行解析）
		XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(configuration);
		
		xmlMapperBuilder.parse(inputStream);
	}

	private void createDataSource(Element element) {
		// 获取数据源信息
		Element dataSourceElement = element.element("dataSource");
		// 获取连接池类型
		String dataSourceType = dataSourceElement.attributeValue("type");
		//获取连接属性信息
		List<Element> propertyElements = dataSourceElement.elements("property");
		
		Properties properties = new Properties();
		for (Element propertyElement : propertyElements) {
			String name = propertyElement.attributeValue("name");
			String value = propertyElement.attributeValue("value");
			properties.setProperty(name, value);
		}
		
		if (dataSourceType.equals("DBCP")) {
			BasicDataSource dataSource = new BasicDataSource();
			dataSource.setDriverClassName(properties.getProperty("driver"));
			dataSource.setUrl(properties.getProperty("url"));
			dataSource.setUsername(properties.getProperty("username"));
			dataSource.setPassword(properties.getProperty("password"));
			configuration.setDataSource(dataSource);
		}
	}
}
