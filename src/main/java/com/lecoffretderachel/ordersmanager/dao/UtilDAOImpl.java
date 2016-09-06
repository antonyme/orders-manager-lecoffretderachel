package com.lecoffretderachel.ordersmanager.dao;

import java.util.List;

import org.hibernate.SessionFactory;

public class UtilDAOImpl implements UtilDAO {

	private SessionFactory sessionFactory;
	
	public UtilDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List getTables() {
		return sessionFactory.getCurrentSession()
		.createSQLQuery("SELECT table_name "
				+ "FROM information_schema.tables "
				+ "WHERE table_schema = database()")
		.list();
	}
	
	public String showCreateTable(String tableName) {
		return ((Object[]) sessionFactory.getCurrentSession()
				.createSQLQuery("SHOW CREATE TABLE `" + tableName + "`")
				.uniqueResult())[1]
				.toString();
	}

	public List getColumns(String tableName) {
		return sessionFactory.getCurrentSession()
		.createSQLQuery("SELECT column_name "
				+ "FROM information_schema.columns "
				+ "WHERE table_schema = database() "
				+ "AND table_name = :tableName")
		.setParameter("tableName", tableName)
		.list();
	}

	public List getData(String tableName) {
		return sessionFactory.getCurrentSession()
				.createSQLQuery("SELECT * "
						+ "FROM " + tableName)
				.list();
	}
}
