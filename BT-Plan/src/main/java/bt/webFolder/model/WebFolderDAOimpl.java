package bt.webFolder.model;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

// MyBatis
public class WebFolderDAOimpl implements WebFolderDAO {
	private SqlSessionTemplate sqlMap;
	private WebFolderDTO dto;
	
	public WebFolderDAOimpl() {
		super();
	}
	public WebFolderDAOimpl(SqlSessionTemplate sqlMap) {
		super();
		this.sqlMap = sqlMap;
	}
	
	/** 루트 폴더 로그인 확인 */
	public List<WebFolderDTO> rootLogin(String wrootName) {
		List<WebFolderDTO> rootName = sqlMap.selectList("rootLogin", wrootName);
		return rootName;
	}
	
	/** 루트 폴더 생성 */
	public int rootCreate(String wrootName, String wcodeName) {
		dto = new WebFolderDTO();
		dto.setWrootName(wrootName);
		dto.setWcodeName(wcodeName);
		int result = sqlMap.insert("rootCreate", dto);
		return result;
	}
	
	/** 루트 폴더명 중복체크 */
	public int rootNameCheck(String wrootName) {
		int result = sqlMap.selectOne("rootNameCheck", wrootName);
		return result;
	}
	
	/** 코드 찾기 */
	public String findCode(String wrootName) {
		String codeName = sqlMap.selectOne("findCode", wrootName);
		return codeName;
	}
	
	/** 코드 변경 */
	public int changeCode(String wrootName, String wcodeName, String newCodeName) {
		dto = new WebFolderDTO();
		dto.setWrootName(wrootName);
		dto.setWcodeName(newCodeName);
		
		String codeName = sqlMap.selectOne("findCode", wrootName);
		if (codeName.equals(wcodeName)) {
			int result = sqlMap.update("changeCode", dto);
			System.out.println(result);
			return result;
		} else {
			return -1;
		}
	}
}






















