package com.lecoffretderachel.ordersmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lecoffretderachel.ordersmanager.dao.TagDAO;
import com.lecoffretderachel.ordersmanager.model.Tag;

@Service("tagService")
public class TagServiceImpl implements TagService{

	@Autowired
	TagDAO tagDAO;
	
	@Transactional
	public void persistTag(Tag tag) {
		tagDAO.persist(tag);
	}

	@Transactional
	public List listTags() {
		return tagDAO.listAll();
	}

	@Transactional
	public void updateTag(Tag tag) {
		tagDAO.update(tag);
	}
	
	@Transactional
	public Tag findTagById(Integer id) {
		return tagDAO.findById(id);
	}

	@Transactional
	public void deleteTag(Tag tag) {
		tagDAO.delete(tag);
	}
}
