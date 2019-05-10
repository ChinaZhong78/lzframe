package com.lz.mybatis.custom;

import com.lz.mybatis.custom.sqlsource.SqlSource;

public class MappedStatement {

	private String id;
	private Class<?> parameterTypeClass;
	private Class<?> resultTypeClass;
	private String statementType;
	private SqlSource sqlSource;
	
	public MappedStatement(String id, Class<?> parameterTypeClass, Class<?> resultTypeClass, String statementType,
			SqlSource sqlSource) {
		super();
		this.id = id;
		this.parameterTypeClass = parameterTypeClass;
		this.resultTypeClass = resultTypeClass;
		this.statementType = statementType;
		this.sqlSource = sqlSource;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Class<?> getParameterTypeClass() {
		return parameterTypeClass;
	}

	public void setParameterTypeClass(Class<?> parameterTypeClass) {
		this.parameterTypeClass = parameterTypeClass;
	}

	public Class<?> getResultTypeClass() {
		return resultTypeClass;
	}

	public void setResultTypeClass(Class<?> resultTypeClass) {
		this.resultTypeClass = resultTypeClass;
	}

	public String getStatementType() {
		return statementType;
	}

	public void setStatementType(String statementType) {
		this.statementType = statementType;
	}

	public SqlSource getSqlSource() {
		return sqlSource;
	}

	public void setSqlSource(SqlSource sqlSource) {
		this.sqlSource = sqlSource;
	}

	@Override
	public String toString() {
		return "MappedStatement [id=" + id + ", parameterTypeClass=" + parameterTypeClass + ", resultTypeClass="
				+ resultTypeClass + ", statementType=" + statementType + ", sqlSource=" + sqlSource + "]";
	}
}
