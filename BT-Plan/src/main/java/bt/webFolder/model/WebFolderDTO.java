package bt.webFolder.model;

public class WebFolderDTO {
	private int windex;
	private String wrootName;
	private String wcodeName;
	
	public WebFolderDTO() {
		super();
	}
	public WebFolderDTO(int windex, String wrootName, String wcodeName) {
		super();
		this.windex = windex;
		this.wrootName = wrootName;
		this.wcodeName = wcodeName;
	}
	
	public int getWindex() {
		return windex;
	}
	public void setWindex(int windex) {
		this.windex = windex;
	}
	public String getWrootName() {
		return wrootName;
	}
	public void setWrootName(String wrootName) {
		this.wrootName = wrootName;
	}
	public String getWcodeName() {
		return wcodeName;
	}
	public void setWcodeName(String wcodeName) {
		this.wcodeName = wcodeName;
	}
}
