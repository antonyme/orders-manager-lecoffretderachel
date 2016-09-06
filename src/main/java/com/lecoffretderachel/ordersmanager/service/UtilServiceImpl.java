package com.lecoffretderachel.ordersmanager.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.lecoffretderachel.ordersmanager.dao.UtilDAO;

public class UtilServiceImpl implements UtilService {
	
	UtilDAO utilDAO;
	
	public UtilServiceImpl(UtilDAO utilDAO) {
		this.utilDAO = utilDAO;
	}

	@Transactional
	public List getTables() {
		return utilDAO.getTables();
	}

	@Transactional
	public String showCreateTable(String tableName) {
		return utilDAO.showCreateTable(tableName);
	}

	@Transactional
	public List getColumns(String tableName) {
		return utilDAO.getColumns(tableName);
	}

	@Transactional
	public List getData(String tableName) {
		return utilDAO.getData(tableName);
	}

}
