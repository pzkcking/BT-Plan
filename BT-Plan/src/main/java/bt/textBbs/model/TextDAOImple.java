package bt.textBbs.model;
 
import java.util.*;   

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.web.bind.annotation.RequestParam;

public class TextDAOImple implements TextDAO 
{

	private SqlSessionTemplate sqlMap;
	
	public TextDAOImple(SqlSessionTemplate sqlMap) 
	{
		super();
		this.sqlMap = sqlMap;
	}
	
	public int textWrite(TextDTO dto) 
	{
		int count; 
		if(dto.getTcategory()==1)
		{
			count=0;	
		}
		else
		{
			count=1;
			sqlMap.insert("textInsert",dto);
		}
		return count;
	}
	
	public TextDTO textContent(int tindex)
	{
		TextDTO dto = sqlMap.selectOne("textContent",tindex);
		return dto;
	}
	
	public int textDelete(int tindex) 
	{
		int count = sqlMap.delete("textDelete",tindex);
		System.out.println("count="+count);
		return count;
	}
	
	public TextDTO textUpdate(int tindex) // 수정하기 from
	{
		TextDTO dto = sqlMap.selectOne("textUpdateView",tindex);
		return dto;
	}
	
	public int textUpdate(TextDTO dto) // 수정하기
	{
		int count = sqlMap.update("textUpdate",dto);
		return count;
	}
	
	public void textViewCount(int tindex) 
	{
		sqlMap.update("textViewCount",tindex);
	}
	
	public int textLikeCount(int tindex) 
	{
		int count = sqlMap.update("textLikeCount",tindex);
		return count;
	}
	
	public int getTotalCnt() 
	{
		int count = sqlMap.selectOne("textTotalCnt");
		return count;
	}
	
	public List textList(int cp, int ls) 
	{
		int startnum=(cp-1)*ls+1;
		int endnum=cp*ls;
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("startnum", startnum);
		map.put("endnum", endnum);
		List lists=sqlMap.selectList("textList", map);
		return lists;
	}
	
	public int getTotalCntCategoty(int tag) 
	{
		int count = sqlMap.selectOne("textcategoryList",tag);
		return count;
	}
	

}
