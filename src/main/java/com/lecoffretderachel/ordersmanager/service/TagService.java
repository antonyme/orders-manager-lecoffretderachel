package com.lecoffretderachel.ordersmanager.service;

import java.util.List;

import com.lecoffretderachel.ordersmanager.model.Tag;

public interface TagService {
	void persistTag(Tag tag);
	List listTags();
	Tag findTagById(Integer i);
	void updateTag(Tag tag);
	void deleteTag(Tag tag);
}