package bt.calendar.model;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

public class CalendarDAOImple implements CalendarDAO 
{
	private SqlSessionTemplate sqlMap;

	public CalendarDAOImple(SqlSessionTemplate sqlMap) {
		super();
		this.sqlMap = sqlMap;
	}
	
	public int isLoginCalendarOK(String calendarRootName, String calendarCodeName)
	{
		HashMap<String, Object> map=new HashMap<String, Object>();
		map.put("wrootname", calendarRootName);
		map.put("wcodename", calendarCodeName);
		
		List lists=sqlMap.selectList("calendarLogin", map);
		return lists.size();
	}
	
	public List calendarList(String rootName, String selDate)
	{
		HashMap<String, Object> map=new HashMap<String, Object>();
		map.put("rootName", rootName);
		map.put("selDate", selDate);
		
		List lists=sqlMap.selectList("calendarList", map);
		return lists;
	}
	
	public int calendarAdd(CalendarDTO dto)
	{
		int count=sqlMap.insert("calendarAdd", dto);
		return count;
	}
	
	public int calendarUpdate(CalendarDTO dto)
	{
		int ret=sqlMap.update("calendarUpdate", dto);
		return ret;
	}
	
	public int calendarDelete(int cid)
	{
		int count=sqlMap.delete("calendarDelete", cid);
		return count;
	}
	
	public int calendarDragDropUpdate(CalendarDTO dto)
	{
		int ret=sqlMap.update("calendarDragDropUpdate", dto);
		return ret;
	}
}
