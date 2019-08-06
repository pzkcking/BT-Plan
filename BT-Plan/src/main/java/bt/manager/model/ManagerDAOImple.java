package bt.manager.model;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

public class ManagerDAOImple implements ManagerDAO {

	private SqlSessionTemplate sqlMap;
	
	public ManagerDAOImple(SqlSessionTemplate sqlMap) {
		super();
		this.sqlMap = sqlMap;
	}

	public List managerList() {
		List lists=sqlMap.selectList("managerList");
		return lists;
	}

	public List managerNameSearch(String keyword) {
		List lists=sqlMap.selectList("managerNameSearch",keyword);
		return lists;
	}

	public List managerNicknameSearch(String keyword) {
		List lists=sqlMap.selectList("managerNicknameSearch",keyword);
		return lists;
	}

	public List managerIDSearch(String keyword) {
		List lists=sqlMap.selectList("managerIDSearch",keyword);
		return lists;
	}

	public int managerBan(ManagerDTO dto) {
		int count=sqlMap.update("managerBan",dto);
		return count;
	}
	
	public int managerUnban(ManagerDTO dto) {
		int count=sqlMap.update("managerUnban",dto);
		return count;
	}
}
