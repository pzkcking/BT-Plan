package bt.calendar.model;

public class CalendarDTO 
{
	private int cid;	
	private String crootname;
	private String ctitle;	
	private String cstart;
	private String cend;
	private String cdescription;
	private String ctype;
	private String cusername;
	private String cbackgroundcolor;
	private String ctextcolor;
	private boolean ccallday;
	
	public CalendarDTO() {
		super();
		ccallday=false;
	}

	public CalendarDTO(int cid, String ctitle, String cstart, String cend, String cdescription, String ctype,
			String cusername, String cbackgroundcolor, String ctextcolor, boolean ccallday) {
		super();
		this.cid = cid;
		this.ctitle = ctitle;
		this.cstart = cstart;
		this.cend = cend;
		this.cdescription = cdescription;
		this.ctype = ctype;
		this.cusername = cusername;
		this.cbackgroundcolor = cbackgroundcolor;
		this.ctextcolor = ctextcolor;
		this.ccallday = ccallday;
	}

	public String getCrootname() {
		return crootname;
	}

	public void setCrootname(String crootname) {
		this.crootname = crootname;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getCtitle() {
		return ctitle;
	}

	public void setCtitle(String ctitle) {
		this.ctitle = ctitle;
	}

	public String getCstart() {
		return cstart;
	}

	public void setCstart(String cstart) {
		this.cstart = cstart;
	}

	public String getCend() {
		return cend;
	}

	public void setCend(String cend) {
		this.cend = cend;
	}

	public String getCdescription() {
		return cdescription;
	}

	public void setCdescription(String cdescription) {
		this.cdescription = cdescription;
	}

	public String getCtype() {
		return ctype;
	}

	public void setCtype(String ctype) {
		this.ctype = ctype;
	}

	public String getCusername() {
		return cusername;
	}

	public void setCusername(String cusername) {
		this.cusername = cusername;
	}

	public String getCbackgroundcolor() {
		return cbackgroundcolor;
	}

	public void setCbackgroundcolor(String cbackgroundcolor) {
		this.cbackgroundcolor = cbackgroundcolor;
	}

	public String getCtextcolor() {
		return ctextcolor;
	}

	public void setCtextcolor(String ctextcolor) {
		this.ctextcolor = ctextcolor;
	}

	public boolean getCcallday() {
		return ccallday;
	}

	public void setCcallday(boolean ccallday) {
		this.ccallday = ccallday;
	}
	
}
