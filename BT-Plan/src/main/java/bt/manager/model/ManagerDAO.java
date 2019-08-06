package bt.manager.model;

import java.util.*;

public interface ManagerDAO {

	public List managerList();
	public List managerNameSearch(String keyword);
	public List managerNicknameSearch(String keyword);
	public List managerIDSearch(String keyword);
	public int managerBan(ManagerDTO dto);
	public int managerUnban(ManagerDTO dto);
}
