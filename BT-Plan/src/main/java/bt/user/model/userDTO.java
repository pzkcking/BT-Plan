package bt.user.model;

import java.sql.*;

public class userDTO {

	private int Uindex;
	private String Uuserid;
	private String Upassword;
	private String Uname;
	private String Unickname;
	private String Usecondary;
	private String Usex;
	private String Uemail;
	private Date Ubirthday;
	private String Uphone;
	private String Uzipcode;
	private String Uaddress1;
	private String Uaddress2;
	private String Uaddress3;
	private int Utextbbscount;
	private int Uimagebbscount;
	private int Ucommentbbscount;
	private int Ulogincount;
	private Date Ujoindate;
	private String Uemailkey;
	private int Ustatus;
	private int Ubanned;
	private int Umanger;
	
	public userDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
	public userDTO(int uindex, String uuserid, String upassword, String uname, String unickname, String usecondary,
			String usex, String uemail, Date ubirthday, String uphone, String uzipcode, String uaddress1,
			String uaddress2, String uaddress3, int utextbbscount, int uimagebbscount, int ucommentbbscount,
			int ulogincount, Date ujoindate, String uemailkey, int ustatus, int ubanned, int umanger) {
		
		super();
		Uindex = uindex;
		Uuserid = uuserid;
		Upassword = upassword;
		Uname = uname;
		Unickname = unickname;
		Usecondary = usecondary;
		Usex = usex;
		Uemail = uemail;
		Ubirthday = ubirthday;
		Uphone = uphone;
		Uzipcode = uzipcode;
		Uaddress1 = uaddress1;
		Uaddress2 = uaddress2;
		Uaddress3 = uaddress3;
		Utextbbscount = utextbbscount;
		Uimagebbscount = uimagebbscount;
		Ucommentbbscount = ucommentbbscount;
		Ulogincount = ulogincount;
		Ujoindate = ujoindate;
		Uemailkey = uemailkey;
		Ustatus = ustatus;
		Ubanned = ubanned;
		Umanger = umanger;
	}




	public int getUindex() {
		return Uindex;
	}

	public void setUindex(int uindex) {
		Uindex = uindex;
	}

	public String getUuserid() {
		return Uuserid;
	}

	public void setUuserid(String uuserid) {
		Uuserid = uuserid;
	}

	public String getUpassword() {
		return Upassword;
	}

	public void setUpassword(String upassword) {
		Upassword = upassword;
	}

	public String getUname() {
		return Uname;
	}

	public void setUname(String uname) {
		Uname = uname;
	}

	public String getUnickname() {
		return Unickname;
	}

	public void setUnickname(String unickname) {
		Unickname = unickname;
	}

	public String getUsecondary() {
		return Usecondary;
	}

	public void setUsecondary(String usecondary) {
		Usecondary = usecondary;
	}

	public String getUsex() {
		return Usex;
	}

	public void setUsex(String usex) {
		Usex = usex;
	}

	public String getUemail() {
		return Uemail;
	}

	public void setUemail(String uemail) {
		Uemail = uemail;
	}

	public Date getUbirthday() {
		return Ubirthday;
	}

	public void setUbirthday(Date ubirthday) {
		Ubirthday = ubirthday;
	}

	public String getUphone() {
		return Uphone;
	}

	public void setUphone(String uphone) {
		Uphone = uphone;
	}

	public String getUzipcode() {
		return Uzipcode;
	}

	public void setUzipcode(String uzipcode) {
		Uzipcode = uzipcode;
	}

	public String getUaddress1() {
		return Uaddress1;
	}

	public void setUaddress1(String uaddress1) {
		Uaddress1 = uaddress1;
	}

	public String getUaddress2() {
		return Uaddress2;
	}

	public void setUaddress2(String uaddress2) {
		Uaddress2 = uaddress2;
	}

	public String getUaddress3() {
		return Uaddress3;
	}

	public void setUaddress3(String uaddress3) {
		Uaddress3 = uaddress3;
	}

	public int getUtextbbscount() {
		return Utextbbscount;
	}

	public void setUtextbbscount(int utextbbscount) {
		Utextbbscount = utextbbscount;
	}

	public int getUimagebbscount() {
		return Uimagebbscount;
	}

	public void setUimagebbscount(int uimagebbscount) {
		Uimagebbscount = uimagebbscount;
	}

	public int getUcommentbbscount() {
		return Ucommentbbscount;
	}

	public void setUcommentbbscount(int ucommentbbscount) {
		Ucommentbbscount = ucommentbbscount;
	}

	public int getUlogincount() {
		return Ulogincount;
	}

	public void setUlogincount(int ulogincount) {
		Ulogincount = ulogincount;
	}

	public Date getUjoindate() {
		return Ujoindate;
	}

	public void setUjoindate(Date ujoindate) {
		Ujoindate = ujoindate;
	}

	public String getUemailkey() {
		return Uemailkey;
	}

	public void setUemailkey(String uemailkey) {
		Uemailkey = uemailkey;
	}

	public int getUstatus() {
		return Ustatus;
	}

	public void setUstatus(int ustatus) {
		Ustatus = ustatus;
	}

	public int getUbanned() {
		return Ubanned;
	}

	public void setUbanned(int ubanned) {
		Ubanned = ubanned;
	}

	public int getUmanger() {
		return Umanger;
	}

	public void setUmanger(int umanger) {
		Umanger = umanger;
	}
	
	
	
	
}
