package com.lz.mybatis.custom;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

public class Configuration {

	private DataSource dataSource;
	
	private Map<String, MappedStatement> mappedStatements = new HashMap<>();

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public MappedStatement getMappedStatement(String statementId) {
		return mappedStatements.get(statementId);
	}

	public void addMappedStatement(String statementId, MappedStatement mappedStatement) {
		this.mappedStatements.put(statementId, mappedStatement);
	}

	@Override
	public String toString() {
		return "Configuration [dataSource=" + dataSource + ", mappedStatements=" + mappedStatements + "]";
	}
	
}
