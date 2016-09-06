package com.lecoffretderachel.ordersmanager.service;

import java.util.List;

public interface UtilService {
	List getTables();
	List getColumns(String tableName);
	String showCreateTable(String tableName);
	List getData(String tableName);
}
