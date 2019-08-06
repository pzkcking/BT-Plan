package bt.textBbsComment.model;

import java.sql.Date;

public class TextCommentDTO 
{
	private int cindex;
	private int ccontentindex;
	private String cnickname;
	private String ccomment;
	private Date cpostdate;
	private int cbannedl;
	
	public TextCommentDTO() {
		super();
	}
	public TextCommentDTO(int cindex, int ccontentindex, String cnickname, String ccomment, Date cpostdate, int cbannedl) {
		super();
		this.cindex = cindex;
		this.ccontentindex = ccontentindex;
		this.cnickname = cnickname;
		this.ccomment = ccomment;
		this.cpostdate = cpostdate;
		this.cbannedl = cbannedl;
	}
	public int getCindex() {
		return cindex;
	}
	public void setCindex(int cindex) {
		this.cindex = cindex;
	}
	public int getCcontentindex() {
		return ccontentindex;
	}
	public void setCcontentindex(int ccontentindex) {
		this.ccontentindex = ccontentindex;
	}
	public String getCnickname() {
		return cnickname;
	}
	public void setCnickname(String cnickname) {
		this.cnickname = cnickname;
	}
	public String getCcomment() {
		return ccomment;
	}
	public void setCcomment(String ccomment) {
		this.ccomment = ccomment;
	}
	public Date getCpostdate() {
		return cpostdate;
	}
	public void setCpostdate(Date cpostdate) {
		this.cpostdate = cpostdate;
	}
	public int getCbannedl() {
		return cbannedl;
	}
	public void setCbannedl(int cbannedl) {
		this.cbannedl = cbannedl;
	}

	
}
