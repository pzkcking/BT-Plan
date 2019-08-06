package bt.webFolder.model;

import java.util.List;

public interface WebFolderDAO {
	public List<WebFolderDTO> rootLogin(String wrootName);
	public int rootCreate(String wrootName, String wcodeName);
	public int rootNameCheck(String wrootName);
	public String findCode(String wrootName);
	public int changeCode(String wrootName, String wcodeName, String newCodeName);
}
