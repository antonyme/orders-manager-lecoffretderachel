package com.lecoffretderachel.ordersmanager.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import com.lecoffretderachel.ordersmanager.model.Tag;

public class TagDAOImpl implements TagDAO {

	private SessionFactory sessionFactory;

	public TagDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public void persist(Tag tag) {
		sessionFactory.getCurrentSession().persist(tag);
	}

	public List listAll() {
		return sessionFactory.getCurrentSession().createQuery("from Tag").list();
	}

	public Tag findById(Integer id) {
		return (Tag) sessionFactory.getCurrentSession().get(Tag.class, id);
	}

	public void update(Tag tag) {
		sessionFactory.getCurrentSession().update(tag);
	}
	
	public void delete(Tag tag) {
		sessionFactory.getCurrentSession().delete(tag);
	}
}
