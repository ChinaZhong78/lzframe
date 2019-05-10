package com.lz.mybatis.custom.sqlsource;

public class DefaultSqlSource implements SqlSource {

	//存储未解析的SQL文本
	private String sqlText;
	
	public DefaultSqlSource(String sqlText) {
		this.sqlText = sqlText;
	}
	
	//执行sqlsession的时候，再去调用
	@Override
	public BoundSql getBoundSql() {
		return null;
	}

}
