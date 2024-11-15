package com.ssafy.mvc.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.mvc.model.dao.UserDao;
import com.ssafy.mvc.model.dto.User;

@Service
public class UserServiceImpl implements UserService {
	private final UserDao dao;

	public UserServiceImpl(UserDao dao) {
		this.dao = dao;
	}

	@Override
	public List<User> getList() {
		return dao.selectAll();
	}

	@Override
	public User getUser(int id) {
		return dao.selectOne(id);
	}

	@Override
	public boolean registerUser(User user) {
		return dao.registerUser(user);
	}

	@Override
	public boolean modifyUser(User user) {
		return dao.updateUser(user);
	}

	@Override
	public boolean removeUser(int id) {
		return dao.deleteUser(id);
	}

}