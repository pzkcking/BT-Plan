package bt.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import bt.user.model.*;

@Controller
public class UserController {

	@Autowired
	private userDAO userDAO;

	@RequestMapping("/userJoin.BT")
	public String userJoinForm() {

		return "user/userJoin";
	}

	@RequestMapping("/userJoinPage.BT")
	public String userJoinPage() {

		return "user/userJoinPage";
	}

	@Transactional
	@RequestMapping("/userJoin_ok.BT")
	public ModelAndView userJoin_ok(userDTO dto, String Uemail_self, String Uyear, String Umonth, String Udate,
			String Uemail_select, String Uemail_input) throws Exception {

		System.out.println(dto.getUuserid());

		String date = Uyear + "-" + Umonth + "-" + Udate;

		Date Ubirthday = Date.valueOf(date);

		System.out.println(Ubirthday);

		int count = userDAO.userJoin(dto, Uemail_self, Uemail_select, Ubirthday, Uemail_input);

		String msg = count > 0 ? "등록하신 메일로 인증키를 보냈습니다. 이메일을 확인 바랍니다." : "회원가입에 실패했습니다.";
		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", msg);
		mav.setViewName("user/userMsg");
		return mav;

	}

	@RequestMapping("/idCheck.BT")
	public String idCheckForm() {

		return "user/idCheck";
	}

	@RequestMapping("/idCheck_ok.BT")
	public ModelAndView idCheck(String Uuserid) {

		System.out.println(Uuserid);

		int flag = userDAO.idCheck(Uuserid);

		ModelAndView mav = new ModelAndView();

		mav.addObject("flag", flag);
		mav.addObject("Uuserid", Uuserid);
		mav.setViewName("/user/idCheck_ok");
		return mav;

	}

	@RequestMapping("/nickCheck.BT")
	public String nickCheckForm() {

		return "user/nickCheck";
	}

	@RequestMapping("/nickCheck_ok.BT")
	public ModelAndView nickCheck_ok(String Unickname, String Uuserid) {

		System.out.println(Unickname);

		int flag = userDAO.NicknameCheck(Unickname);

		ModelAndView mav = new ModelAndView();

		mav.addObject("flag", flag);
		mav.addObject("Unickname", Unickname);
		mav.addObject("Uuserid", Uuserid);
		mav.setViewName("user/nickCheck_ok");
		return mav;

	}

	@RequestMapping("/joinConfirm.BT")
	public ModelAndView joinConfirm(String Uuserid, String Uemail, String Uemailkey) {

		ModelAndView mav = new ModelAndView();

		int flag = userDAO.joinConfrim(Uuserid, Uemail, Uemailkey);
		String msg = flag > 0 ? "인증이 완료되었습니다.! 회원이 되신걸 축하합니다!." : "인증에 실패했습니다. 관리자에 문의바랍니다. ";
		mav.addObject("msg", msg);
		mav.setViewName("/user/userMsg");

		return mav;
	}

	@RequestMapping("/loginForm.BT")
	public String loginForm() {

		return "user/login";
	}

	@RequestMapping("/login.BT")
	public ModelAndView loginPage(@CookieValue(value = "save", defaultValue = "") String saveid) {

		String checked = null;
		ModelAndView mav = new ModelAndView();
		if (saveid == null || saveid.equals("")) {
			checked = "";
		} else {
			checked = "checked";
		}
		mav.addObject("checked", checked);
		mav.addObject("saveid", saveid);
		mav.setViewName("user/login");
		return mav;
	}

	@RequestMapping("/login_ok.BT")
	public ModelAndView login(String Uuserid, String Upassword, HttpServletRequest request, String save,
			HttpServletResponse response) {

		String msg = null;
		System.out.println(save);
		int result = userDAO.Login(Uuserid, Upassword);

		System.out.println("아이디 틀릴경우 : " + result);

		ModelAndView mav = new ModelAndView();

		if (result == 0) {

			msg = "아이디 또는 비밀번호가 일치하지 않습니다.";

			mav.addObject("msg", msg);
			mav.setViewName("user/login_ok");
			return mav;

		}

		int status = userDAO.CheckStatus(Uuserid);
		System.out.println("status : " + status);
		if (result == 1 && status == 0) {

			msg = "이메일 인증이 완료되지 않았습니다. 인증을 하셔야 이용가능 합니다.";

		} else if (result == 1 && status == 1) {

			int count=0;
			
			try {
				count = userDAO.getManager(Uuserid);
			} catch (Exception e) {
				count=-1;
			}
			
			if (count > 0) {

				HttpSession session = request.getSession();
				session.setAttribute("manager", Uuserid);
				msg = "이 사이트의 주인님 환영합니다!";
			
				mav.addObject("msg", msg);

				// mav.setViewName("user/index2");
				mav.setViewName("user/userMsg");
				return mav;

			}
			
			int bancount=0;
			try {
				bancount = userDAO.getUserBan(Uuserid);
				
			} catch (Exception e) {
				bancount=-1;
			}
			if (bancount > 0) {
				
				msg = "이용정지 회원입니다.";
				mav.addObject("msg", msg);
				mav.setViewName("user/login_ok");
				return mav;
			}
			
			
			int notice = userDAO.messageNotice(Uuserid);

			HttpSession session = request.getSession();
			session.setAttribute("notice", notice);
			session.setAttribute("sid", Uuserid);
			msg = Uuserid + "님 환영합니다!";
			if (save != null) {

				Cookie ck = new Cookie("save", Uuserid);
				ck.setMaxAge(60 * 60 * 24);
				response.addCookie(ck);

			} else {

				Cookie ck = new Cookie("save", Uuserid);
				ck.setMaxAge(0);
				response.addCookie(ck);

			}

		}

		mav.addObject("id", Uuserid);
		mav.addObject("msg", msg);
		mav.setViewName("user/login");
		mav.setViewName("user/login_ok");
		return mav;
	}

	@RequestMapping("logout.BT")
	public ModelAndView logout(HttpSession session) {

		ModelAndView mav = new ModelAndView();
		session.invalidate();

		mav.setViewName("user/logout");

		return mav;

	}

	@RequestMapping("/myPage.BT")
	public ModelAndView myPage(HttpServletRequest request) {

		HttpSession session = request.getSession();

		String Uuserid = (String) session.getAttribute("sid");

		// System.out.println(Uuserid);

		List lists = userDAO.myPage(Uuserid);

		ModelAndView mav = new ModelAndView();
		mav.addObject("lists", lists);
		mav.setViewName("user/myPage");
		return mav;
	}

	@RequestMapping("/userDelForm.BT")
	public ModelAndView userDelForm(int Uindex) {

		ModelAndView mav = new ModelAndView();
		mav.addObject("Uindex", Uindex);
		mav.setViewName("user/userDel");

		return mav;
	}

	@RequestMapping("/userDel_ok.BT")
	public ModelAndView userDel(String Uuserid, int Uindex, HttpSession session, String delCheck,
			HttpServletResponse response) {

		ModelAndView mav = new ModelAndView();

		if (delCheck.equals("탈퇴 하겠습니다.")) {

			int flag = userDAO.userDelete(Uindex);

			session.invalidate();

			Cookie ck = new Cookie("save", Uuserid);
			ck.setMaxAge(0);
			response.addCookie(ck);

			String msg = flag > 0 ? "탈퇴처리 되었습니다. 안녕히가십시오." : "탈퇴에 실패했습니다. 관리자에 문의바랍니다. ";
			mav.addObject("msg", msg);
			mav.setViewName("user/userDelMsg");

			return mav;

		} else {

			String msg = "탈퇴 확인란을 정확히 입력해주세요.";
			mav.addObject("msg", msg);
			mav.setViewName("user/userDelMsg");

			return mav;
		}
	}

	@RequestMapping("/findId.BT")
	public String findIdForm() {

		return "user/findId";
	}

	@RequestMapping("/findPassword.BT")
	public String findPasswordForm() {

		return "user/findPassword";
	}

	@RequestMapping("/findId_ok.BT")
	public ModelAndView findId(userDTO dto) throws Exception {

		ModelAndView mav = new ModelAndView();

		String Uuserid = userDAO.findId(dto);

		String msg = Uuserid != null ? "등록한 메일로 아이디를 발송했습니다." : "존재하지 않는 회원입니다.";
		mav.addObject("msg", msg);
		mav.setViewName("user/userMsg");

		return mav;
	}

	@RequestMapping("/findPassword_ok.BT")
	public ModelAndView findPassword(userDTO dto) throws Exception {

		ModelAndView mav = new ModelAndView();

		String Upassword = userDAO.findPassword(dto);

		String msg = Upassword != null ? "등록한 메일로 임시비밀번호를 발송했습니다." : "입력한 정보로 찾을 수 없습니다.";
		mav.addObject("msg", msg);
		mav.setViewName("user/userMsg");

		return mav;
	}

	@RequestMapping("/userEditForm.BT")
	public ModelAndView userEditForm(HttpServletRequest request) {

		HttpSession session = request.getSession();

		String Uuserid = (String) session.getAttribute("sid");

		ModelAndView mav = new ModelAndView();
		mav.addObject("Uuserid", Uuserid);
		mav.setViewName("user/userEditForm");

		return mav;
	}

	@RequestMapping("/userEdit.BT")
	public ModelAndView userEdit(String Uuserid, String Upassword) {

		ModelAndView mav = new ModelAndView();
		int result = userDAO.Login(Uuserid, Upassword);

		if (result == 1) {

			List lists = userDAO.myPage(Uuserid);

			mav.addObject("lists", lists);
			mav.setViewName("user/userPageEdit");
			return mav;

		} else {

			String msg = "비밀번호가 일치하지 않습니다.";

			mav.addObject("msg", msg);
			mav.setViewName("user/userMsg");

			return mav;

		}

	}

	@RequestMapping("/userEdit_ok.BT")
	public ModelAndView userEdit(userDTO dto, String Uemail, String Uyear, String Umonth, String Udate,
			HttpSession session) {

		ModelAndView mav = new ModelAndView();

		if (Uyear == null || Uyear.equals("") || Umonth == null || Umonth.equals("") || Udate == null
				|| Udate.equals("")) {

			String date = userDAO.getBirthday(dto.getUuserid());
			Date Ubirthday = Date.valueOf(date);

			System.out.println("controller: " + Ubirthday);

			int count = userDAO.UserEdit(dto, Uemail, Ubirthday);
			session.invalidate();
			String msg = count > 0 ? "회원정보가 수정되었습니다." : "수정에 실패하였습니다.";

			mav.addObject("msg", msg);
			mav.setViewName("user/userMsg");
			return mav;
		} else {

			String date = Uyear + "-" + Umonth + "-" + Udate;

			Date Ubirthday = Date.valueOf(date);
			int count = userDAO.UserEdit(dto, Uemail, Ubirthday);
			System.out.println(Ubirthday);
			session.invalidate();
			String msg = count > 0 ? "회원정보가 수정되었습니다." : "수정에 실패하였습니다.";

			mav.addObject("msg", msg);
			mav.setViewName("user/userMsg");
			return mav;
		}
	}

	@RequestMapping("/UserQAMail")
	public ModelAndView sendEmail(HttpServletRequest request) {

		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();

		String Uuserid = (String) session.getAttribute("sid");
		String Uemail = userDAO.findQAEmailfind(Uuserid);

		mav.addObject("Uemail", Uemail);
		mav.addObject("Uuserid", Uuserid);
		mav.setViewName("user/UserQAMail");
		return mav;

	}

	@RequestMapping("/mailSend.BT")
	public ModelAndView sendEmailQA(String to, String from, String subject, String msgText, String Uuserid) {

		ModelAndView mav = new ModelAndView();

		String Uemail = from;

		MailSend send = new MailSend();
		send.MailSend(subject, msgText, Uuserid, Uemail);

		String msg = "문의메일을 발송하였습니다. 확인 후에 답변드리겠습니다. 감사합니다.";
		mav.addObject("msg", msg);
		mav.setViewName("user/userMsg");
		return mav;

	}

	@RequestMapping("/nickEditCheck.BT")
	public String nickEditCheckForm() {

		return "user/nickEditCheck";
	}

	@RequestMapping("/nickEditCheck_ok.BT")
	public ModelAndView nicknameEditCheck(String Unickname, String Uuserid) {

		ModelAndView mav = new ModelAndView();

		int flag = userDAO.NicknameCheck(Unickname);

		mav.addObject("flag", flag);
		mav.addObject("Unickname", Unickname);
		mav.addObject("Uuserid", Uuserid);
		mav.setViewName("user/UserEditNickCheck_ok");
		return mav;
	}

	@RequestMapping("/emailReSend.BT")
	public String emailReSendForm() {

		return "user/emailReSend";
	}

	@RequestMapping("/emailReSend_ok.BT")
	public ModelAndView emailReSend(String Uuserid, String Uemail) throws Exception {

		ModelAndView mav = new ModelAndView();

		int count = userDAO.emailReSend(Uuserid, Uemail);

		String msg = count > 0 ? "인증 메일을 재발송 하였습니다." : "재발송에 실패하였습니다. 아이디나 이메일을 확인해주세요.";
		mav.addObject("msg", msg);
		mav.setViewName("user/userMsg");
		return mav;
	}

	@RequestMapping("/worldweather.BT")
	public String worldweather() {

		return "user/worldweather";
	}
	
	@RequestMapping("/worldtime.BT")
	public String worldtime() {

		return "user/worldtime";
	}

	@ResponseBody
	@RequestMapping(value = "VerifyRecaptcha", method = RequestMethod.POST)
	public int VerifyRecaptcha(HttpServletRequest request) {
		VerifyRecaptcha.setSecretKey("6Lfmm64UAAAAABjGo7yVO7VNNbuf9bDAxEc_75ID");
		String gRecaptchaResponse = request.getParameter("recaptcha");
		System.out.println(gRecaptchaResponse);
		// 0 = 성공, 1 = 실패, -1 = 오류
		try {
			if (VerifyRecaptcha.verify(gRecaptchaResponse))
				return 0;
			else
				return 1;
		} catch (IOException e) {
			e.printStackTrace();
			return -1;
		}
	}

	/** Message part */
	@RequestMapping("/message.BT")
	public ModelAndView userMessage(HttpServletRequest request, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		session = request.getSession();
		String Uuserid = (String) session.getAttribute("sid");
		int notice = userDAO.messageNotice(Uuserid);
		session.setAttribute("notice", notice);
		List lists = userDAO.userMessage(Uuserid);
		mav.addObject("lists", lists);
		mav.addObject("Uuserid", Uuserid);
		mav.setViewName("user/message");
		return mav;
	}

	@RequestMapping("/messageRead.BT")
	public ModelAndView messageRead(int Mindex) {
		ModelAndView mav = new ModelAndView();
		List lists = userDAO.messageRead(Mindex);
		mav.addObject("lists", lists);
		mav.setViewName("user/messageRead");
		return mav;
	}

	@RequestMapping("/messageAnswer.BT")
	public ModelAndView messageAnswerForm(String Msender, String Mreceiver) {

		ModelAndView mav = new ModelAndView();

		mav.addObject("Msender", Msender);
		mav.addObject("Mreceiver", Mreceiver);
		mav.setViewName("user/messageAnswer");
		return mav;
	}

	@RequestMapping("/messageAnswer_ok.BT")
	public ModelAndView messageAnswer_ok(messageDTO dto) {

		ModelAndView mav = new ModelAndView();
		int count = userDAO.messageSend(dto);
		String msg = count > 0 ? "쪽지를 보냈습니다." : "발송에 실패하습니다.";
		mav.addObject("msg", msg);
		mav.setViewName("user/messageResult");
		return mav;
	}

	@RequestMapping("/messageWrite")
	public ModelAndView messageWrite(String Msender) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("Mreceiver", Msender);
		mav.setViewName("user/messageWrite");
		return mav;
	}

	@RequestMapping("/messageWrite_ok")
	public ModelAndView messageWrite_ok(messageDTO dto) {
		ModelAndView mav = new ModelAndView();
		String Uuserid = dto.getMreceiver();
		String result = userDAO.findUser(Uuserid);
		if (result == null || result.equals("")) {
			String msg = "존재하지 않는 회원입니다.";
			mav.addObject("msg", msg);
			mav.setViewName("user/messageResult");
			return mav;
		} else {
			int count = userDAO.messageSend(dto);
			String msg = count > 0 ? "쪽지를 보냈습니다." : "발송에 실패하 습니다.";
			mav.addObject("msg", msg);
			mav.setViewName("user/messageResult");
			return mav;
		}
	}

	@RequestMapping("/messageDelete")
	public ModelAndView messageDelete(int Mindex, HttpSession session, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		int count = userDAO.messageDelete(Mindex);
		/** 쪽지함 세션을 불러와서 메시지가 삭제할때 -1 해준다 */
		int notice = (int) session.getAttribute("notice") - 1;
		session.setAttribute("notice", notice);
		String msg = count > 0 ? "쪽지를 삭제했습니다." : "삭제에 실패하습니다.";
		mav.setViewName("redirect:message.BT");
		return mav;
	}

}
