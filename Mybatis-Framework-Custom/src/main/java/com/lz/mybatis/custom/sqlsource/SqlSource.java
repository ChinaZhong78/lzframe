package com.lz.mybatis.custom.sqlsource;

public interface SqlSource {
	//需要对SQL文本解析解析
	BoundSql getBoundSql();
}
