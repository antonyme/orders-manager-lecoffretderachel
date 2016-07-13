package com.lecoffretderachel.ordersmanager.dao;

import java.util.List;

import com.lecoffretderachel.ordersmanager.model.Tag;

public interface TagDAO {
	void persist(Tag tag);
	Tag findById(Integer id);
	List listAll();
	void update(Tag tag);
	void delete(Tag tag);
}
