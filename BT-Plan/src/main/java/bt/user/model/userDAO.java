package bt.user.model;

import java.sql.*;
import java.util.List;

public interface userDAO {

	public int userJoin(userDTO dto,String Uemail_self,String Uemail_select,Date Ubirthday,String Uemail_input) throws Exception;
	
	public int idCheck(String Uuserid);
	
	public int NicknameCheck(String Unickname);
	
	public int joinConfrim(String Uuserid,String Uemail,String Uemailkey);
	
	public int updateStaus(userDTO dto);
	
	public int Login(String Uuserid,String Upassword);
	
	public int CheckStatus(String Uuserid);
	
	public List myPage(String Uuserid);
	
	public int userDelete(int Uindex);
	
	public String findId(userDTO dto) throws Exception;
	
	public String findPassword(userDTO dto) throws Exception;
	
	public int UserEdit(userDTO dto,String Uemail,Date Ubirthday);
	
	public String findQAEmailfind(String Uuserid);
	
	public String getBirthday(String Uuserid);
	
	public Boolean emailCheck(String Uemail);
	
	public int emailReSend(String Uuserid,String Uemail) throws Exception;
	
	public int getManager(String Uuserid);
	
	public List userMessage(String Uuserid);
	
	public List messageRead(int Mindex);
	
	public int messageSend(messageDTO dto);
	
	public String findUser(String Uuserid);
	
	public int messageDelete(int Mindex);
	
	public int messageNotice(String Uuserid);
	
	public int getUserBan(String Uuserid);
}
