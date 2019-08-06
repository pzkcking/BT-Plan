package bt.calendar.model;

public class CalendarLoginDTO 
{
	String wrootname;
	String wcodename;
	
	public CalendarLoginDTO() {
		super();
	}

	public CalendarLoginDTO(String wrootname, String wcodename) {
		super();
		this.wrootname = wrootname;
		this.wcodename = wcodename;
	}

	public String getWrootname() {
		return wrootname;
	}

	public void setWrootname(String wrootname) {
		this.wrootname = wrootname;
	}

	public String getWcodename() {
		return wcodename;
	}

	public void setWcodename(String wcodename) {
		this.wcodename = wcodename;
	}
	
	
}
