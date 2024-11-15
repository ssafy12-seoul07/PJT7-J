package com.ssafy.mvc.model.dao;

import java.util.List;

import com.ssafy.mvc.model.dto.Review;

public interface ReviewDao {
	public List<Review> selectAll();

	public Review selectOne(int id);

	public void insertReview(Review review);

	public boolean updateReview(Review review);

	public boolean deleteReview(int id);

}
