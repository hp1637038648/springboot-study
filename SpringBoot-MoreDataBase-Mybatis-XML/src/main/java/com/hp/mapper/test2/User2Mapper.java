package com.hp.mapper.test2;

import java.util.List;

import com.hp.model.User;

public interface User2Mapper {

	List<User> getAll();
	
	User getOne(Long id);

	void insert(User user);

	void update(User user);

	void delete(Long id);
	
}
