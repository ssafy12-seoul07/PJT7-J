package com.ssafy.mvc.controller;

import java.net.URI;
import java.util.List;

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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ssafy.mvc.model.dto.Video;
import com.ssafy.mvc.model.service.VideoService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/ssafit/video")
@CrossOrigin("*")
public class VideoRestController {
	private final VideoService service;

	public VideoRestController(VideoService videoService) {
		this.service = videoService;
	}

	// 비디오 전체 조회
	@GetMapping
	private ResponseEntity<List<Video>> getAllVideoList() {
		List<Video> list = service.getList();
		// ResponseEntity를 빌더 패턴으로 변경
		if (list.size() > 0) {
			return ResponseEntity.ok().body(list);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	// 조회수기준 비디오 조회

//	@GetMapping("/list/viewCnt")

	// 부위에 해당하는 비디오 조회

	// 비디오 상세정보
	@GetMapping("/{videoId}")
	@Operation(summary = "비디오 상세정보 조회", description = "")
	private ResponseEntity<Video> getVideoDetail(@PathVariable int videoId) {
		Video video = service.getVideo(videoId);
		return (video == null) ? ResponseEntity.ok(video) : ResponseEntity.notFound().build();
	}

	// 영상 등록
	@PostMapping
	@Operation(summary = "비디오 등록", description = "")
	private ResponseEntity<Video> addVideo(@ModelAttribute Video video) {
		service.writeVideo(video);

		int id = video.getVideoId();

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/" + id)
				.buildAndExpand(video.getVideoId()).toUri();

		return ResponseEntity.created(location).body(video);
	}

	// 영상 삭제
	@DeleteMapping("/{videoId}")
	private ResponseEntity<Boolean> deleteVideo(@PathVariable int VideoId) {
		boolean isDeleted = service.removeVideo(VideoId);

		if (isDeleted) {
			return ResponseEntity.ok(isDeleted);
		} else {
			return ResponseEntity.internalServerError().body(isDeleted);
		}
	}

	// 영상 수정
	@PutMapping("/{videoId}")
	@Operation(summary = "비디오 수정", description = "")
	private ResponseEntity<Boolean> update(@PathVariable int videoId, @RequestBody Video video) {
		video.setVideoId(videoId);

		boolean isDeleted = service.modifyVideo(video);

		return (isDeleted) ? ResponseEntity.ok(isDeleted) : ResponseEntity.internalServerError().body(isDeleted);

	}

}
