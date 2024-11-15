package com.ssafy.mvc.model.dto;

public class User {
	private int userId;
	private String id;
	private String password;
	private String name;
	private String email;
	private int status;
	private String createdAt;

	public User() {
	}

	public User(int userId, String id, String password, String name, String email, int status, String createdAt) {
		super();
		this.userId = userId;
		this.id = id;
		this.password = password;
		this.name = name;
		this.email = email;
		this.status = status;
		this.createdAt = createdAt;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", id=" + id + ", password=" + password + ", name=" + name + ", email="
				+ email + ", status=" + status + ", createdAt=" + createdAt + "]";
	}

}
