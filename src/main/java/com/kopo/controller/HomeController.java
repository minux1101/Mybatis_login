package com.kopo.controller;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kopo.dto.MemberVO;
import com.kopo.service.MemberServiceImpl;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	@Inject
	MemberServiceImpl ms = new MemberServiceImpl();

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) throws ParseException {
		SimpleDateFormat sdt = new SimpleDateFormat("yyyy-MM-dd");
		LocalDate now = LocalDate.now();
		String format = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		Date date = sdt.parse(format);
		
		model.addAttribute("joinDate", date);
		return "MemberJoin";
	}

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 * 
	 * @throws Exception
	 */
	@PostMapping("/memberList")
	public String insert(HttpServletRequest request, Model model) throws Exception {

		request.setCharacterEncoding("UTF-8");

		// String regExp =
		// "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&])[A-Za-z[0-9]$@$!%*#?&]{8,20}$";

		// 이름, 성별, 나이, 휴대폰번호, 비밀번호 필수 입력
		// 이름의 길이가 20글자 초과시 안됨
		// 휴대폰번호 12글자 초과시 안됨
		// 비밀번호에 영문, 숫자, 특수문자 포함
		if (request.getParameter("memberId").equals("") || request.getParameter("passwd").equals("") || request.getParameter("name").equals("") || request.getParameter("gender").equals("")
				|| request.getParameter("age").equals("")) {
			return "result_fail";
		} else {

			String id = request.getParameter("memberId");
			String passwd = request.getParameter("passwd");
			String name = request.getParameter("name");
			String gender = request.getParameter("gender");
			int age = Integer.parseInt(request.getParameter("age"));
			String joinDate = request.getParameter("joinDate");

			MemberVO mv = new MemberVO();

			MessageDigest messageDigest = null;
			try {
				messageDigest = MessageDigest.getInstance("SHA-256");
			} catch (NoSuchAlgorithmException e) {
				// TODO: handle exception
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			messageDigest.update(passwd.getBytes());
			String after = String.format("%064x", new BigInteger(1, messageDigest.digest()));

			mv.setId(id);
			mv.setPasswd(after);
			mv.setName(name);
			mv.setGender(gender);
			mv.setAge(age);
			mv.setJoinDate(joinDate);

			int insert = ms.insert(mv);

			model.addAttribute("name", name);
			model.addAttribute("gender", gender);
			model.addAttribute("age", age);
			model.addAttribute("id", id);
			model.addAttribute("passwd", passwd);
			model.addAttribute("joinDate", joinDate);

			return "result";
		}
	}

	@RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
	public String login(Locale locale, Model model) {
		return "login";
	}

	@RequestMapping(value = "/loginresult", method = { RequestMethod.GET, RequestMethod.POST })
	public String login_success(HttpServletRequest request, Model model) throws Exception {

		request.setCharacterEncoding("UTF-8");

		if (request.getParameter("idField").equals("") || request.getParameter("idField") == null || request.getParameter("pwField").equals("") || request.getParameter("pwField") == null) {
			return "login";
		}

		MemberVO mem = ms.selectOne(request.getParameter("idField"));
		String passwd = request.getParameter("pwField");

		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		messageDigest.update(passwd.getBytes());
		String after = String.format("%064x", new BigInteger(1, messageDigest.digest()));

		if (mem.getId() == null) {
			return "login_fail";
		} else {
			if (mem.getId().equals(request.getParameter("idField")) && mem.getPasswd().equals(after)) {
				model.addAttribute("member", mem);
				return "login_success";
			} else {
				return "login_fail";
			}
		}
	}
}