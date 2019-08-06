package bt.textBbs.model;

import java.sql.*;

public class TextDTO 
{
	private int tindex;
	private String tnickname;
	private String ttitle;
	private String tcontent;
	private int tcategory;
	private int tviewcount;
	private int tlikecount;
	private int tcommnentnumber;
	private Date tpostdate;
	public TextDTO() {
		super();
	}
	public TextDTO(int tindex, String tnickname, String ttitle, String tcontent, int tcategory,
			int tviewcount, int tlikecount, int tcommnentnumber, Date tpostdate) {
		super();
		this.tindex = tindex;
		this.tnickname = tnickname;
		this.ttitle = ttitle;
		this.tcontent = tcontent;
		this.tcategory = tcategory;
		this.tviewcount = tviewcount;
		this.tlikecount = tlikecount;
		this.tcommnentnumber = tcommnentnumber;
		this.tpostdate = tpostdate;
	}
	public int getTindex() {
		return tindex;
	}
	public void setTindex(int tindex) {
		this.tindex = tindex;
	}
	public String getTnickname() {
		return tnickname;
	}
	public void setTnickname(String tnickname) {
		this.tnickname = tnickname;
	}
	public String getTtitle() {
		return ttitle;
	}
	public void setTtitle(String ttitle) {
		this.ttitle = ttitle;
	}
	public String getTcontent() {
		return tcontent;
	}
	public void setTcontent(String tcontent) {
		this.tcontent = tcontent;
	}
	public int getTcategory() {
		return tcategory;
	}
	public void setTcategory(int tcategory) {
		this.tcategory = tcategory;
	}
	public int getTviewcount() {
		return tviewcount;
	}
	public void setTviewcount(int tviewcount) {
		this.tviewcount = tviewcount;
	}
	public int getTlikecount() {
		return tlikecount;
	}
	public void setTlikecount(int tlikecount) {
		this.tlikecount = tlikecount;
	}
	public int getTcommnentnumber() {
		return tcommnentnumber;
	}
	public void setTcommnentnumber(int tcommnentnumber) {
		this.tcommnentnumber = tcommnentnumber;
	}
	public Date getTpostdate() {
		return tpostdate;
	}
	public void setTpostdate(Date tpostdate) {
		this.tpostdate = tpostdate;
	}
}
