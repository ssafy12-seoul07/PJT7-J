package com.ssafy.mvc.model.service;

import java.util.List;

import com.ssafy.mvc.model.dto.User;

public interface UserService {

	public abstract List<User> getList();

	public abstract User getUser(int id);

	public abstract boolean registerUser(User user);

	public abstract boolean modifyUser(User user);

	public abstract boolean removeUser(int id);

}
