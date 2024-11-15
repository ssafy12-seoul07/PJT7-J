package com.ssafy.mvc.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.mvc.model.dto.Review;
import com.ssafy.mvc.model.service.ReviewService;

@RestController
@RequestMapping("/ssafit/video/{video_id}/reviews")
@CrossOrigin("*")
public class ReviewRestController {
	private final ReviewService service;

	public ReviewRestController(ReviewService service) {
		this.service = service;
	}

	// 영상 id를 통해 해당 영상 내 리뷰를 가져옴
	@GetMapping
	private ResponseEntity<List<Review>> getReviews(@PathVariable int videoId) {
		List<Review> list = service.getReviewList();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	// id로 리뷰를 반환
	@GetMapping("/{reviewId}")
	private ResponseEntity<?> getReview(@PathVariable int reviewId) {
		Review review = service.getReview(reviewId);
		return new ResponseEntity<>(review, HttpStatus.OK);
	}

	// 리뷰 등록
	@PostMapping
	private ResponseEntity<?> doWrite(@ModelAttribute Review review) {
		service.writeReview(review);

		return new ResponseEntity<Review>(review, HttpStatus.OK);
	}

	// 리뷰 수정
	@PutMapping("/{reviewId}")
	private ResponseEntity<Boolean> doUpdate(@RequestBody Review review, @PathVariable int reviewId)
			throws IOException {
		service.modifyReview(review);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	// 리뷰 삭제
	@DeleteMapping("/{reviewId}")
	private ResponseEntity<Boolean> doRemove(@PathVariable int reviewId) {
		service.removeReview(reviewId);

		boolean isDeleted = service.removeReview(reviewId);

		return isDeleted ? ResponseEntity.ok().body(isDeleted) : ResponseEntity.badRequest().body(isDeleted);

	}
}
