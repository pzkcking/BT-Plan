package bt.calendar.model;

import java.util.List;

public interface CalendarDAO 
{
	public List calendarList(String rootName, String selDate);
	public int calendarAdd(CalendarDTO dto);
	public int calendarUpdate(CalendarDTO dto);
	public int calendarDragDropUpdate(CalendarDTO dto);
	public int calendarDelete(int cid);
	public int isLoginCalendarOK(String calendarRootName, String calendarCodeName);
}
