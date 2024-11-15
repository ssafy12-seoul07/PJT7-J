package com.ssafy.mvc.model.dao;

import java.util.List;

import com.ssafy.mvc.model.dto.User;

public interface UserDao {
	public List<User> selectAll();

	public User selectOne(int id);

	public boolean registerUser(User user);

	public boolean updateUser(User user);

	public boolean deleteUser(int id);

}
