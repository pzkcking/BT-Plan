package bt.user.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.*;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.transaction.annotation.Transactional;

public class userDAOImple implements userDAO {

	@Autowired
	private JavaMailSender mailSender;
	private SqlSessionTemplate sqlMap;

	public userDAOImple(SqlSessionTemplate sqlMap) {
		super();
		this.sqlMap = sqlMap;
	}

	@Transactional
	public int userJoin(userDTO dto, String Uemail_self, String Uemail_select, Date Ubirthday, String Uemail_input)
			throws Exception {

		if (Uemail_select.equals("9")) {

			String Uemail = Uemail_self + Uemail_input;

			String Uemailkey = new TempKey().getKey(50, false);

			Boolean emailCheck = emailCheck(Uemail);

			Uemail = emailCheck == false ? "fivingo@gmail.com" : Uemail;

			System.out.println(Uemail);

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("Uuserid", dto.getUuserid());
			map.put("Upassword", dto.getUpassword());
			map.put("Uname", dto.getUname());
			map.put("Unickname", dto.getUnickname());
			map.put("Usecondary", dto.getUsecondary());
			map.put("Usex", dto.getUsex());
			map.put("Uemail", Uemail);
			map.put("Ubirthday", Ubirthday);
			map.put("Uphone", dto.getUphone());
			map.put("Uzipcode", dto.getUzipcode());
			map.put("Uaddress1", dto.getUaddress1());
			map.put("Uaddress2", dto.getUaddress2());
			map.put("Uaddress3", dto.getUaddress3());
			map.put("Uemailkey", Uemailkey);

			int count = sqlMap.insert("btJoin", map);

			// mail 작성 관련
			MailUtils sendMail = new MailUtils(mailSender);

			sendMail.setSubject("[BT-Plan] 회원가입 이메일 인증");
			sendMail.setText(new StringBuffer().append("<h1>[이메일 인증]</h1>").append("<p>아래 링크를 클릭하시면 이메일 인증이 완료됩니다.</p>")
					.append("<a href='http://192.168.1.19:9090/BT-Plan/joinConfirm.BT?Uuserid=").append(dto.getUuserid())
					.append("&Uemail=").append(Uemail).append("&Uemailkey=").append(Uemailkey)
					.append("' target='_blenk'>이메일 인증 확인</a>").toString());
			sendMail.setFrom("fivingo", "BT_Plan");
			sendMail.setTo(Uemail);
			sendMail.send();

			return count;

		} else {

			String Uemail = Uemail_self + Uemail_select;

			String Uemailkey = new TempKey().getKey(50, false);

			Boolean emailCheck = emailCheck(Uemail);

			Uemail = emailCheck == false ? "fivingo@gmail.com" : Uemail;

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("Uuserid", dto.getUuserid());
			map.put("Upassword", dto.getUpassword());
			map.put("Uname", dto.getUname());
			map.put("Unickname", dto.getUnickname());
			map.put("Usecondary", dto.getUsecondary());
			map.put("Usex", dto.getUsex());
			map.put("Uemail", Uemail);
			map.put("Ubirthday", Ubirthday);
			map.put("Uphone", dto.getUphone());
			map.put("Uzipcode", dto.getUzipcode());
			map.put("Uaddress1", dto.getUaddress1());
			map.put("Uaddress2", dto.getUaddress2());
			map.put("Uaddress3", dto.getUaddress3());
			map.put("Uemailkey", Uemailkey);

			int count = sqlMap.insert("btJoin", map);

			// mail 작성 관련
			MailUtils sendMail = new MailUtils(mailSender);

			sendMail.setSubject("[BT-Plan] 회원가입 이메일 인증");
			sendMail.setText(new StringBuffer().append("<h1>[이메일 인증]</h1>").append("<p>아래 링크를 클릭하시면 이메일 인증이 완료됩니다.</p>")
					.append("<a href='http://localhost:9090/BT-Plan/joinConfirm.BT?Uuserid=").append(dto.getUuserid())
					.append("&Uemail=").append(Uemail).append("&Uemailkey=").append(Uemailkey)
					.append("' target='_blenk'>이메일 인증 확인</a>").toString());
			sendMail.setFrom("fivingo", "BT_Plan");
			sendMail.setTo(Uemail);
			sendMail.send();

			return count;

		}

	}

	@Override
	public Boolean emailCheck(String Uemail) {

		String regex = "^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$";

		Boolean check_email = Uemail.matches(regex);

		return check_email;
	}

	@Override
	public int idCheck(String Uuserid) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Uuserid", Uuserid);

		int flag = sqlMap.selectOne("btIdcheck", Uuserid);

		return flag;
	}

	@Override
	public int NicknameCheck(String Unickname) {

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("Unickname", Unickname);

		int flag = sqlMap.selectOne("btNickcheck", Unickname);

		return flag;
	}

	// 임의의 authkey 생성

	@Override
	public int updateStaus(userDTO dto) {

		int count = sqlMap.update("emailkey", dto);

		return count;
	}

	@Override
	public int joinConfrim(String Uuserid, String Uemail, String Uemailkey) {

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("Uuserid", Uuserid);
		map.put("Uemail", Uemail);
		map.put("Uemailkey", Uemailkey);

		int flag = sqlMap.selectOne("btConfirm", map);

		if (flag == 1) {

			sqlMap.update("btStatus", map);

			return flag;

		} else {

			flag = 0;

			return flag;
		}

	}

	@Override
	public int Login(String Uuserid, String Upassword) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Uuserid", Uuserid);
		map.put("Upassword", Upassword);

		int flag = sqlMap.selectOne("btLogin", map);

		System.out.println("login : " + flag);

		return flag;
	}

	@Override
	public int CheckStatus(String Uuserid) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Uuserid", Uuserid);
		int status = sqlMap.selectOne("btStatusCheck", map);

		return status;
	}

	@Override
	public List myPage(String Uuserid) {

		System.out.println("imple : " + Uuserid);

		List lists = sqlMap.selectList("myPage", Uuserid);

		return lists;
	}

	@Override
	public int userDelete(int Uindex) {

		int count = sqlMap.delete("btDel", Uindex);

		return count;

	}

	@Override
	public String findId(userDTO dto) throws Exception {

		String Uuserid = sqlMap.selectOne("btIdFindCheck", dto);
		String Uemail = sqlMap.selectOne("btEmailFind", dto);
		System.out.println("찾은 아이디: " + Uuserid);
		System.out.println("찾은 이메일: " + Uemail);

		if (Uemail == null) {

			Uuserid = null;

			return Uemail;
		} else {
			MailUtils sendMail = new MailUtils(mailSender);

			sendMail.setSubject("[BT-Plan] 찾으신 아이디 안내");
			sendMail.setText(new StringBuffer().append("<h4>[아이디]</h4>")
					.append("<p>찾으신 아이디 입니다. 분실하지 않도록 주의해주시길 바랍니다.<p>").append("아이디 : " + Uuserid).toString());
			sendMail.setFrom("fivingo", "BT_Plan");
			sendMail.setTo(Uemail);
			sendMail.send();

			return Uuserid;
		}
	}

	@Override
	public String findPassword(userDTO dto) throws Exception {

		String Uemail = sqlMap.selectOne("btfindpasswordCheck", dto);

		String tempPassword = new TempKey().getKey(12, false);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Uuserid", dto.getUuserid());
		map.put("Upassword", tempPassword);

		if (Uemail == null) {

			System.out.println("임시비밀번호 보낼이메일 값 " + Uemail);

			return Uemail;
		} else {

			sqlMap.update("btTempPassword", map);

			MailUtils sendMail = new MailUtils(mailSender);

			sendMail.setSubject("[BT-Plan] 임시 비밀번호 안내");
			sendMail.setText(new StringBuffer().append("<h4>[임시 비밀번호]</h4>")
					.append("<p>임시 비밀번호 입니다. 홈페이지에 로그인 후에 변경하시길 권장합니다..<p>").append("임시 비밀번호 : " + tempPassword)
					.toString());
			sendMail.setFrom("fivingo", "BT_Plan");
			sendMail.setTo(Uemail);
			sendMail.send();

			return Uemail;
		}

	}

	@Override
	public int UserEdit(userDTO dto, String Uemail, Date Ubirthday) {

		System.out.println("birth33:" + Ubirthday);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Uindex", dto.getUindex());
		map.put("Upassword", dto.getUpassword());
		map.put("Uname", dto.getUname());
		map.put("Unickname", dto.getUnickname());
		map.put("Usecondary", dto.getUsecondary());
		map.put("Uemail", Uemail);
		map.put("Ubirthday", Ubirthday);
		map.put("Uphone", dto.getUphone());
		map.put("Uzipcode", dto.getUzipcode());
		map.put("Uaddress1", dto.getUaddress1());
		map.put("Uaddress2", dto.getUaddress2());
		map.put("Uaddress3", dto.getUaddress3());

		int count = sqlMap.update("btUserEdit", map);

		return count;

	}

	@Override
	public String findQAEmailfind(String Uuserid) {

		String Uemail = sqlMap.selectOne("btQAEmailFind", Uuserid);

		return Uemail;
	}

	@Override
	public String getBirthday(String Uuserid) {

		System.out.println(Uuserid);

		String Ubirthday = sqlMap.selectOne("btGetBirthday", Uuserid);

		System.out.println("imple" + Ubirthday);

		return Ubirthday;
	}

	@Override
	public int emailReSend(String Uuserid, String Uemail) throws Exception {

		String Uemailkey = sqlMap.selectOne("btGetEmailKey", Uuserid);

		System.out.println("1번쨰 받아온 값 : " + Uemail);

		Boolean emailCheck = emailCheck(Uemail);

		System.out.println(emailCheck);

		Uemail = emailCheck == false ? "fivingo@gmail.com" : Uemail;

		System.out.println("2번쨰 받아온 값 : " + Uemail);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Uuserid", Uuserid);
		map.put("Uemail", Uemail);

		int count = sqlMap.update("btNewEmail", map);

		MailUtils sendMail = new MailUtils(mailSender);

		sendMail.setSubject("[BT-Plan] 회원가입 이메일 인증");
		sendMail.setText(new StringBuffer().append("<h1>[이메일 인증]</h1>").append("<p>아래 링크를 클릭하시면 이메일 인증이 완료됩니다.</p>")
				.append("<a href='http://localhost:9090/BT-Plan/joinConfirm.BT?Uuserid=").append(Uuserid)
				.append("&Uemail=").append(Uemail).append("&Uemailkey=").append(Uemailkey)
				.append("' target='_blenk'>이메일 인증 확인</a>").toString());
		sendMail.setFrom("fivingo", "BT_Plan");
		sendMail.setTo(Uemail);
		sendMail.send();

		return count;
	}
	
	@Override
	public int getManager(String Uuserid) {

		int count = sqlMap.selectOne("btManager", Uuserid);

		return count;
	}

	@Override
	public List userMessage(String Uuserid) {
		List lists = sqlMap.selectList("btMessage", Uuserid);
		return lists;
	}

	@Override
	public List messageRead(int Mindex) {
		List lists = sqlMap.selectList("btMessageRead", Mindex);
		return lists;
	}

	@Override
	public int messageSend(messageDTO dto) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Msender", dto.getMsender());
		map.put("Mreceiver", dto.getMreceiver());
		map.put("Msubject", dto.getMsubject());
		map.put("Mcontent", dto.getMcontent());
		int count = sqlMap.insert("btMessageSend", map);
		return count;
	}

	@Override
	public String findUser(String Uuserid) {
		String result = sqlMap.selectOne("btUserCheck", Uuserid);
		return result;
	}

	@Override
	public int messageDelete(int Mindex) {
		int count = sqlMap.delete("btDeleteMessage", Mindex);
		return count;
	}
	
	@Override
	public int messageNotice(String Uuserid) {
		
		String Mreceiver=Uuserid;
		
		int count = sqlMap.selectOne("btMessageNotice", Mreceiver);
		
		return count;
	}

	@Override
	public int getUserBan(String Uuserid) {

		int count = sqlMap.selectOne("btBanLogin", Uuserid);

		return count;
	}
}
