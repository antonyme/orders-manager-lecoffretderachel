package com.lecoffretderachel.ordersmanager.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.lecoffretderachel.ordersmanager.dao.TagDAO;
import com.lecoffretderachel.ordersmanager.model.Tag;

public class TagServiceImpl implements TagService, ListService {

	TagDAO tagDAO;
	
	public TagServiceImpl(TagDAO tagDAO) {
		this.tagDAO = tagDAO;
	}
	
	@Transactional
	public void persist(Tag tag) {
		tagDAO.persist(tag);
	}

	@Transactional
	public List list() {
		return tagDAO.listAll();
	}

	@Transactional
	public void update(Tag tag) {
		tagDAO.update(tag);
	}
	
	@Transactional
	public Tag findById(Integer id) {
		return tagDAO.findById(id);
	}

	@Transactional
	public void delete(Tag tag) {
		tagDAO.delete(tag);
	}
}
