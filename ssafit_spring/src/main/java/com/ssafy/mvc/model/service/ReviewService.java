package com.ssafy.mvc.model.service;

import java.util.List;

import com.ssafy.mvc.model.dto.Review;

public interface ReviewService {

	public abstract List<Review> getReviewList();

	public abstract Review getReview(int id);

	public abstract void writeReview(Review review);

	public abstract boolean modifyReview(Review review);

	public abstract boolean removeReview(int id);

}
