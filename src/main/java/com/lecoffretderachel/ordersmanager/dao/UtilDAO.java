package com.lecoffretderachel.ordersmanager.dao;

import java.util.List;

public interface UtilDAO {
	List getTables();
	List getColumns(String tableName);
	String showCreateTable(String tableName);
	List getData(String tableName);
}
