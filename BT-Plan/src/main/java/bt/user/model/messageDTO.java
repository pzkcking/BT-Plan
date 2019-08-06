package bt.user.model;

import java.sql.*;


public class messageDTO {

	private String Mindex;
	private String Msender;
	private String Mreceiver;
	private String Msubject;
	private String Mcontent;
	private Date Mwritedate;
	
	
	
	public messageDTO() {
		super();
		// TODO Auto-generated constructor stub
	}



	public messageDTO(String mindex, String msender, String mreceiver, String msubject, String mcontent,
			Date mwritedate) {
		super();
		Mindex = mindex;
		Msender = msender;
		Mreceiver = mreceiver;
		Msubject = msubject;
		Mcontent = mcontent;
		Mwritedate = mwritedate;
	}



	public String getMindex() {
		return Mindex;
	}



	public void setMindex(String mindex) {
		Mindex = mindex;
	}



	public String getMsender() {
		return Msender;
	}



	public void setMsender(String msender) {
		Msender = msender;
	}



	public String getMreceiver() {
		return Mreceiver;
	}



	public void setMreceiver(String mreceiver) {
		Mreceiver = mreceiver;
	}



	public String getMsubject() {
		return Msubject;
	}



	public void setMsubject(String msubject) {
		Msubject = msubject;
	}



	public String getMcontent() {
		return Mcontent;
	}



	public void setMcontent(String mcontent) {
		Mcontent = mcontent;
	}



	public Date getMwritedate() {
		return Mwritedate;
	}



	public void setMwritedate(Date mwritedate) {
		Mwritedate = mwritedate;
	}

	
	


}	
