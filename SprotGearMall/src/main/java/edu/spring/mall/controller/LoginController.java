package edu.spring.mall.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.spring.mall.domain.MemberVO;
import edu.spring.mall.security.CustomUserDetails;
import edu.spring.mall.service.MemberService;

@Controller
@RequestMapping(value = "member")

public class LoginController {
	private final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private MemberService service;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserDetailsService userService;

	@GetMapping("/loginForm")
	public void loginGet(Model model, @RequestParam(name = "error", required = false) String error,
			@RequestParam(name = "state", required = false) String state,
			@CookieValue(name = "targeturl", required = false) String targeturl) {
		logger.info("loginGet 호출");
		if (error != null) {
			model.addAttribute("error", "error");
		}
		if (state != null) {
			model.addAttribute("state", state);
		}

		if (targeturl != null) {
			model.addAttribute("targeturl", targeturl);
		}

	}

	@GetMapping("/register")
	public void registerGET() {
		logger.info("loginGet 호출");
	}

	@PostMapping("/register")
	public String registerPOST(@ModelAttribute MemberVO vo) {
		logger.info("registerPOST 호출");
		try {
			int result = service.create(vo);
			if (result == 1) {
				logger.info("회원가입 성공");

				return "redirect:/member/loginForm?state=success";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/member/register";
	}

	@GetMapping("/mypage")
	public void mypageGET(Model model) {
		logger.info("마이페이지 호출");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
		model.addAttribute("user", user);

	}

	@GetMapping("/update")
	public void update(Model model) {
		logger.info("updateGet 호출");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
		String name = user.getName();
		String phone = user.getPhone();
		String email = user.getEmail();
		model.addAttribute("zonecode", user.getZonecode());
		model.addAttribute("address", user.getAddress());
		model.addAttribute("detailAddress", user.getDetailAddress());

		model.addAttribute("isOauthLogin", user.getIsOauthLogin());
		model.addAttribute("name", name);
		model.addAttribute("phone", phone);
		model.addAttribute("email", email);

	}

	@PostMapping("/update")
	public String updatePOST(@ModelAttribute MemberVO vo) {
		logger.info("updatePOST 호출 vo : " + vo.toString());
		Map<String, Object> userDetail = new HashMap<String, Object>();
		userDetail.put("memberId", vo.getMemberId());
		userDetail.put("name", vo.getName());
		userDetail.put("phone", vo.getPhone());
		userDetail.put("email", vo.getEmail());
		userDetail.put("zonecode", vo.getZonecode());
		userDetail.put("address", vo.getAddress());
		userDetail.put("detailAddress", vo.getDetailAddress());

		int result = 0;
		try {
			result = service.update(userDetail);
			if (result == 1) {
				logger.info("update 성공");

				return "redirect:/member/mypage";
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.info("예외 발생");
			return "redirect:/member/update?error";

		}
		logger.info("업데이트 실패");

		return "redirect:/member/update?error";

	}

	@PostMapping("/updatePassword")
	public String updatePasswordPOST(@RequestParam("memberId") String memberId,
			@RequestParam("newPassword") String newPassword) {
		logger.info("updatePasswordPOST 호출");

		Map<String, String> user = new HashMap<String, String>();
		user.put("memberId", memberId);
		user.put("password", newPassword);
		try {
			int result = service.update(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/member/mypage";
	}

	@GetMapping("/delete")
	public void deleteGET() {
	}

	@PostMapping("delete")
	public String deletePOST(@RequestParam("memberId") String memberId, @RequestParam("password") String password,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		logger.info("deletePOST호출");
		// 비밀번호 검증을 위한 변수
		UserDetails user = userService.loadUserByUsername(memberId);
		String encodedPassword = user.getPassword();
		// 비밀번호 검증
		if (!passwordEncoder.matches(password, encodedPassword)) {
			logger.info("비밀번호 틀림");

			return "redirect:/member/delete?error=password";
		}

		try {
			if (service.delete(memberId) != 1) {
				return "redirect:/member/delete?error"; // 탈퇴 처리 실패
			}
			// 로그아웃 처리

			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (auth != null) {
				new SecurityContextLogoutHandler().logout(request, response, auth);
			}
			return "redirect:/"; // 탈퇴 및 로그아웃 성공

		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/member/delete?error";
		}
	}

}