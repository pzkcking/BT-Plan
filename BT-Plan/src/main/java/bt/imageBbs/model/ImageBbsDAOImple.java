package bt.imageBbs.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

public class ImageBbsDAOImple implements ImageBbsDAO 
{

	private SqlSessionTemplate sqlMap;
	
	public ImageBbsDAOImple(SqlSessionTemplate sqlMap) 
	{
		super();
		this.sqlMap = sqlMap;
	}

	public int getTotalCnt() 
	{
		List lists=sqlMap.selectList("imageBbsTotalCnt");
		return lists.size();
	}

	public List bbsList(int user_current_page, int list_per_page) 
	{
		int startnum=(user_current_page-1)*list_per_page+1;
		int endnum=user_current_page * list_per_page;
		
		HashMap<String, Object> map=new HashMap<String, Object>();
		map.put("startnum", startnum);
		map.put("endnum", endnum);
		
		List lists=sqlMap.selectList("imageBbsList", map);
		return lists;
	}

	public List bbsContent(int idx)
	{
		List lists=sqlMap.selectList("imageBbsContent", idx);
		return lists;
	}
	
	public int bbsWrite(ImageBbsDTO dto)
	{
		int count=sqlMap.insert("imageBbsWrite", dto);
		return count;
	}

	
	
	
	public int getCommentTotalCnt(int contentIndex)
	{
		List lists=sqlMap.selectList("imageBbsCommentTotalCnt", contentIndex);
		return lists.size();
	}
	
	public List bbsCommentList(int contentIndex, int user_current_page, int list_per_page)
	{
		int startnum=(user_current_page-1)*list_per_page+1;
		int endnum=user_current_page * list_per_page;
		
		HashMap<String, Object> map=new HashMap<String, Object>();
		map.put("contentIndex", contentIndex);
		map.put("startnum", startnum);
		map.put("endnum", endnum);
		
		List lists=sqlMap.selectList("imageBbsCommentList", map);
		return lists;
	}
	
	public int bbsCommentWrite(ImgBbsCommentDTO dto)
	{
		int count=sqlMap.insert("imageBbsCommentWrite", dto);
		return count;
	}
	
	public int bbsIncreaseCommentNumber(int idx)
	{
		int ret=sqlMap.update("imageBbsIncreaseCommentNumber", idx);
		return ret;
	}
	
	
	
	
	
	public List bbsContentImageName(int index)
	{
		List lists=sqlMap.selectList("imageBbsContentImageName", index);
		return lists;
	}
	
	public int bbsDelete(int index)
	{
		int count=sqlMap.delete("imageBbsDelete", index);
		return count;
	}
	
	public int bbsCommentDelete(int contentindex)
	{
		int count=sqlMap.delete("imageBbsCommentDelete", contentindex);
		return count;
	}
	
	
	
	public int getSearchByNickNameTotalCnt(String nickName)
	{
		List lists=sqlMap.selectList("imageBbsSearchByNickNameTotalCnt", nickName);
		return lists.size();
	}
	
	public List bbsSearchByNickNameList(String nickName, int user_current_page, int list_per_page)
	{
		int startnum=(user_current_page-1)*list_per_page+1;
		int endnum=user_current_page * list_per_page;
		
		HashMap<String, Object> map=new HashMap<String, Object>();
		map.put("nickName", nickName);
		map.put("startnum", startnum);
		map.put("endnum", endnum);
		
		List lists=sqlMap.selectList("imageBbsSearchByNickNameList", map);
		return lists;
	}
	
	public int bbsIncreaseViewCount(int idx)
	{
		int ret=sqlMap.update("imageBbsIncreaseViewCount", idx);
		return ret;
	}
	
	public int bbsIncreaseLikeCount(int idx)
	{
		int ret=sqlMap.update("imageBbsIncreaseLikeCount", idx);
		return ret;
	}
	
	public List bbsGetLikeTotalCountData(int idx)
	{
		List lists=sqlMap.selectList("imageBbsGetLikeTotalCountData", idx);
		return lists;
	}
}
