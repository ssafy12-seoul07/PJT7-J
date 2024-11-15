package com.ssafy.mvc.model.service;

import java.util.List;

import com.ssafy.mvc.model.dto.Video;

public interface VideoService {

	public abstract List<Video> getList();

	public abstract Video getVideo(int id);

	public abstract void writeVideo(Video video);

	public abstract boolean modifyVideo(Video video);

	public abstract boolean removeVideo(int id);

}
