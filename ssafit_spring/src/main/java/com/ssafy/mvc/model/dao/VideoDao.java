package com.ssafy.mvc.model.dao;

import java.util.List;

import com.ssafy.mvc.model.dto.Video;

public interface VideoDao {
	public List<Video> selectAll();

	public Video selectOne(int id);

	public void updateViews(int id);

}
