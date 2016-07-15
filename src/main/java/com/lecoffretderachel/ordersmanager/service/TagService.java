package com.lecoffretderachel.ordersmanager.service;

import java.util.List;

import com.lecoffretderachel.ordersmanager.model.Tag;

public interface TagService {
	void persist(Tag tag);
	List list();
	Tag findById(Integer i);
	void update(Tag tag);
	void delete(Tag tag);
}