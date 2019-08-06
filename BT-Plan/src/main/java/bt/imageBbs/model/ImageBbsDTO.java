package bt.imageBbs.model;

public class ImageBbsDTO 
{
	private int iindex;
	private String inickname;
	private String ititle;
	private String iimagename;
	private String ipostdate;
	private int iviewcount;
	private int ilikecount;
	private int icommentnumber;
	private int ibanned;
	
	private String url;
	
	public ImageBbsDTO() {
		super();
		iindex=0;
		inickname="";
		ititle="";
		iimagename="";
		ipostdate="";
		
		iviewcount=0;
		ilikecount=0;
		icommentnumber=0;
		
		ibanned=0;
	}

	public ImageBbsDTO(int iindex, String inickname, String ititle, String iimagename, String ipostdate, int iviewcount,
			int ilikecount, int icommentnumber, int ibanned) {
		super();
		this.iindex = iindex;
		this.inickname = inickname;
		this.ititle = ititle;
		this.iimagename = iimagename;
		this.ipostdate = ipostdate;
		this.iviewcount = iviewcount;
		this.ilikecount = ilikecount;
		this.icommentnumber = icommentnumber;
		this.ibanned = ibanned;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getIindex() {
		return iindex;
	}

	public void setIindex(int iindex) {
		this.iindex = iindex;
	}

	public String getInickname() {
		return inickname;
	}

	public void setInickname(String inickname) {
		this.inickname = inickname;
	}

	public String getItitle() {
		return ititle;
	}

	public void setItitle(String ititle) {
		this.ititle = ititle;
	}

	public String getIimagename() {
		return iimagename;
	}

	public void setIimagename(String iimagename) {
		this.iimagename = iimagename;
	}

	public String getIpostdate() {
		return ipostdate;
	}

	public void setIpostdate(String ipostdate) {
		this.ipostdate = ipostdate;
	}

	public int getIviewcount() {
		return iviewcount;
	}

	public void setIviewcount(int iviewcount) {
		this.iviewcount = iviewcount;
	}

	public int getIlikecount() {
		return ilikecount;
	}

	public void setIlikecount(int ilikecount) {
		this.ilikecount = ilikecount;
	}

	public int getIcommentnumber() {
		return icommentnumber;
	}

	public void setIcommentnumber(int icommentnumber) {
		this.icommentnumber = icommentnumber;
	}


	public int getIbanned() {
		return ibanned;
	}

	public void setIbanned(int ibanned) {
		this.ibanned = ibanned;
	}
	
	
}