package com.ssafy.mvc.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.mvc.model.dao.ReviewDao;
import com.ssafy.mvc.model.dto.Review;

@Service
public class ReviewServiceImpl implements ReviewService {
	private final ReviewDao dao;

	private ReviewServiceImpl(ReviewDao dao) {
		this.dao = dao;
	}

	@Override
	public List<Review> getReviewList() {
		return dao.selectAll();
	}

	@Override
	public Review getReview(int id) {
		return dao.selectOne(id);
	}

	@Override
	public void writeReview(Review review) {
		dao.insertReview(review);
	}

	@Override
	public boolean modifyReview(Review review) {
		return dao.updateReview(review);
	}

	@Override
	public boolean removeReview(int id) {
		return dao.deleteReview(id);
	}

}
