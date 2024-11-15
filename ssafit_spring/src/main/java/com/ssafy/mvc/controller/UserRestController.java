package com.ssafy.mvc.controller;

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

import com.ssafy.mvc.model.dto.User;
import com.ssafy.mvc.model.service.UserService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserRestController {
	private final UserService service;

	public UserRestController(UserService userService) {
		this.service = userService;
	}

	// 사용자 프로필 조회 (ID로)
	@GetMapping("/{userId}")
	public <T extends User> ResponseEntity<?> getUser(@PathVariable int userId) {
		User user = service.getUser(userId);
		return (user != null) ? ResponseEntity.ok(user) : ResponseEntity.badRequest().build();
	}

	// 새 사용자 등록(회원가입)
	@PostMapping("/regist")
	public ResponseEntity<Boolean> registUser(@ModelAttribute User user) {
		try {
			boolean isRegistered = service.registerUser(user);
			return (isRegistered) ? ResponseEntity.ok(isRegistered) : ResponseEntity.badRequest().body(isRegistered);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(false);
		}
	}

	// 사용자 정보 업데이트
	@PutMapping("/{userId}")
	public ResponseEntity<Boolean> updateUser(@PathVariable String id, @RequestBody User user) {
		user.setId(id);
		service.modifyUser(user);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	// 사용자 삭제
	@DeleteMapping("/{userId}")
	public ResponseEntity<Boolean> deleteUser(@PathVariable int userId) {
		service.removeUser(userId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	// 로그인
	@PostMapping("/login")
	public ResponseEntity<?> login(@ModelAttribute User user, HttpSession session) {
		// 현재 로그인 유효성 검증은 입력된 정보로 User객체를 구성하고 객체 내부의 속성을 다시 가져와 비교하는 비효율적인 로직

		// UserService의 getUser는 유저 정보를 정수형 id 인풋으로 가져오는 구조이며
		// 백엔드 내에 id를 저장하는 시점이 있다면 db에서 유저 정보를 효율적으로 가져올 수 있겠지만,
		// 어떤 유저를 검증하게 될지 미리 알 수 없다는 점에서 저장 시점과 위치를 정하기가 어렵고, 필요한지도 의문

		// 이하 로직에서는 전체 유저 정보를 가져와 각각의 유저 객체 단위로 id, password를 조회하고 있고, session에서도 순회할
		// 때마다 매번 Attribute를 가져와 조회하고 있음
		// DB에 등록된 유저 수가 많을 경우 서버가 터질 수도 있을 것 같고, 일시적으로 BE에서 모든 유저 정보에 접근할 수 있게 되기 때문에
		// 보안 측면에서도 좋지 않을 것 같음

		// 로그인 시에는 일반적으로 id, password로 유저 정보를 가져올 것이므로,
		// User객체를 구성해 속성을 또 가져와 비교하는 것보다는 구분가능하고 special한 정보로 DB단에서부터 조회하는 방식이 더 효율적일
		// 것이라 예상됨
		// 따라서 리팩토링한다면 BE에서는 최소한의 정보를 이용해 요청하고, DB에서는 정확한 데이터를 찾을 수 있는 최적의 쿼리문을 작성해야 할 것

		User user = service.getUser(0);

		// db에서 가져올때부터 하나만 가져오는 방식이 효율적일듯
		List<User> list = service.getList();
		for (User u : list) {
			if (session.getAttribute("loginUser") == null && u.getId().equals(user.getId())
					&& u.getPassword().equals(user.getPassword())) {
				session.setAttribute("loginUser", u);
				System.out.println("login");
				return new ResponseEntity<>(HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	// 로그아웃
	@PostMapping("/logout")
	public ResponseEntity<?> logout(HttpSession session) {
		if (session != null && session.getAttribute("loginUser") != null) {
			session.invalidate();
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
