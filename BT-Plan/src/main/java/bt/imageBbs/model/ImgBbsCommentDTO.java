package bt.imageBbs.model;

public class ImgBbsCommentDTO 
{
	private int 	cindex;
	private int 	ccontentindex;
	private String 	cnickname;
	private String  ccomment;
	private String  cpostdate;
	
	public ImgBbsCommentDTO() {
		super();
	}

	public ImgBbsCommentDTO(int cindex, int ccontentindex, String cnickname, String ccomment, String cpostdate) {
		super();
		this.cindex = cindex;
		this.ccontentindex = ccontentindex;
		this.cnickname = cnickname;
		this.ccomment = ccomment;
		this.cpostdate = cpostdate;
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

	public String getCpostdate() {
		return cpostdate;
	}

	public void setCpostdate(String cpostdate) {
		this.cpostdate = cpostdate;
	}

	
}
