package com.lz.mybatis.demo.test;

import java.io.InputStream;

import org.junit.Test;

import com.lz.mybatis.custom.Configuration;
import com.lz.mybatis.custom.builder.XMLConfigBuilder;

public class MybatisTest {
	
	/**
	 * 测试加载全局配置文件
	 * @throws Exception
	 */
	@Test
	public void test() throws Exception {
		//1.根据配置文件路径，读取配置文件（InputStream流）
		String resource = "SqlMapConfig.xml";
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(resource);
		
		XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder();
		Configuration configuration=xmlConfigBuilder.parse(inputStream);
		System.out.println(configuration);
	}
}
