package com.ssafy.mvc.model.service;

import java.util.List;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.ssafy.mvc.model.dao.VideoDao;
import com.ssafy.mvc.model.dto.Video;

@Service
public class VideoServiceImpl implements VideoService {
	private final VideoDao dao;

	private VideoServiceImpl(VideoDao dao) {
		this.dao = dao;
	}

	@Override
	@Async
	public List<Video> getList() {
		return dao.selectAll();
	}

	@Override
	public Video getVideo(int id) {
		dao.updateViews(id);
		return dao.selectOne(id);
	}

	@Override
	public void writeVideo(Video video) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean modifyVideo(Video video) {
		return false;
	}

	@Override
	public boolean removeVideo(int id) {
		return false;
	}

}
