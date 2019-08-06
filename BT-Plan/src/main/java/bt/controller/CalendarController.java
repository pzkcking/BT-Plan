package bt.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import bt.calendar.model.CalendarDAO;
import bt.calendar.model.CalendarDTO;
import bt.imageBbs.model.ImgBbsCommentDTO;
import bt.statistics.model.StatisticsDAO;

@Controller
public class CalendarController 
{

	public static final String SESSION_ROOT_NAME="rootFolder";
	
	@Autowired
	private CalendarDAO calendarDao;
	
	@RequestMapping("/calendarLogin.BT")
	public ModelAndView calendarLogin(
			HttpServletRequest req)
	
	{
		HttpSession session=req.getSession();
		
		ModelAndView mav=new ModelAndView();
		
		// 로그인 체크
		String loginSession_user = (String) session.getAttribute("sid");
		String loginSession_manager = (String) session.getAttribute("manager");
		if ((loginSession_user == null || loginSession_user.equals(""))
				&& (loginSession_manager == null || loginSession_manager.equals(""))) {
			mav.addObject("msg", "로그인부터 하셈");
			mav.setViewName("user/userMsg");
			return mav;
		}
		
		// 팀 계정 세션 체크
		String rootFolder = (String) session.getAttribute(SESSION_ROOT_NAME);
		if (rootFolder == null) rootFolder = "";
		System.out.println("rootFolder: " + rootFolder);
		
		// 팀 계정 로그인
		if (rootFolder.equals("")) {
			// && (wrootName != null && wcodeName != null)
			System.out.println("세션없없");
			mav.setViewName("calendar/calendarLogin");
			return mav;
		} else {
			System.out.println("세션있있");
			mav.addObject("rootName", rootFolder);
			mav.setViewName("calendar/calendar");
			return mav;
		}
	}
	
	@RequestMapping("/calendarLoginOK.BT")
	public ModelAndView calendarLogin(
			HttpServletRequest req,
			@RequestParam("wrootname")String rootname,
			@RequestParam("wcodename")String codename)
	
	{
		HttpSession session=req.getSession();
		
		//
		ModelAndView mav=new ModelAndView();
		if(calendarDao.isLoginCalendarOK(rootname, codename) != 0)
		{
			System.out.println("로그인 성공.!");
			// 팀 계정 세션 생성
			session.setAttribute(SESSION_ROOT_NAME, rootname);
			mav.setViewName("redirect:calendarMain.BT");
		}
		else
		{
			System.out.println("로그인 실패.!");
			session.setAttribute(SESSION_ROOT_NAME, "");
			mav.setViewName("redirect:calendarLogin.BT");
		}
		
		return mav;
	}
	
	@RequestMapping("/calendarMain.BT")
	public ModelAndView calendarMain(
			HttpServletRequest req)
	
	{
		HttpSession session=req.getSession();
		
		String rootName=(String)session.getAttribute(SESSION_ROOT_NAME);
		
		ModelAndView mav=new ModelAndView();
		mav.addObject("rootName", rootName);
		mav.setViewName("calendar/calendar");
		return mav;
	}
	
	@RequestMapping("/calendarData.BT")
	public ModelAndView calendarData(
			HttpServletRequest req,
			@RequestParam("selDate")String selDate)
	
	{
		HttpSession session=req.getSession();
		System.out.println("calendarData.!~~~~");
		System.out.println(selDate);
		
		//
		String rootName=(String)session.getAttribute(SESSION_ROOT_NAME);
		List listCalendarData=calendarDao.calendarList(rootName, selDate);
		
		//
		String jsonData="[";
		int lastIndex=listCalendarData.size() - 1;
		for(int i=0; i < listCalendarData.size(); i++)
		{
			CalendarDTO obj=(CalendarDTO)listCalendarData.get(i);
			jsonData+="{";
				jsonData+="\"_id\":" + obj.getCid() + ",";
				jsonData+="\"title\":" + "\"" + obj.getCtitle() + "\"" + ",";
				jsonData+="\"description\":" + "\"" + obj.getCdescription() + "\"" + ",";
				jsonData+="\"start\":" + "\"" + obj.getCstart() + "\"" + ",";
				jsonData+="\"end\":" + "\"" + obj.getCend() + "\"" + ",";
				jsonData+="\"type\":" + "\"" + obj.getCtype() + "\"" + ",";
				jsonData+="\"username\":" + "\"" + obj.getCusername() + "\"" + ",";
				jsonData+="\"backgroundColor\":" + "\"" + obj.getCbackgroundcolor() + "\"" + ",";
				jsonData+="\"textColor\":" + "\"" + obj.getCtextcolor() + "\"" + ",";
				jsonData+="\"allDay\":" + "false";
			jsonData+="}";
			
			if(i != lastIndex) {
				jsonData+=",";
			}
		}
		jsonData+="]";
		System.out.println(jsonData);
		
		//
		ModelAndView mav=new ModelAndView();
		mav.addObject("jsonData", jsonData);
		mav.setViewName("ajax/ajaxCalendarData");
		return mav;
	}
	
	@RequestMapping("/calendarAdd.BT")
	public ModelAndView calendarAdd(
			HttpServletRequest req,
			CalendarDTO dto)
	
	{
		//
		HttpSession session=req.getSession();
		String rootName=(String)session.getAttribute(SESSION_ROOT_NAME);
		
		//
		System.out.println("calendarAdd.!~~~~");
		
		//
		String startTime=dto.getCstart();
		startTime=startTime.replace(" ", "T");
		dto.setCstart(startTime);
		String endTime=dto.getCend();
		endTime=endTime.replace(" ", "T");
		dto.setCend(endTime);
		String description=dto.getCdescription();
		description=description.replace("\n", " ");
		dto.setCdescription(description);
		
		//
		dto.setCrootname(rootName);
		dto.setCusername(rootName);
		int count=calendarDao.calendarAdd(dto);
		String msg=count > 0 ? "일정 등록 성공" : "일정 등록 실패!";
		
		//
		ModelAndView mav=new ModelAndView();
		mav.addObject("msg", msg);
		mav.setViewName("ajax/ajaxCalendarMsg");
		return mav;
	}
	
	@RequestMapping("/calendarUpdate.BT")
	public ModelAndView calendarUpdate(
			HttpServletRequest req,
			CalendarDTO dto)
	
	{
		//
		System.out.println("calendarUpdate.!~~~~");
		System.out.println(dto.getCid());
		System.out.println(dto.getCtitle());
		System.out.println(dto.getCstart());
		System.out.println(dto.getCcallday());

		//
		String startTime=dto.getCstart();
		startTime=startTime.replace(" ", "T");
		dto.setCstart(startTime);
		String endTime=dto.getCend();
		endTime=endTime.replace(" ", "T");
		dto.setCend(endTime);
		
		//
		int count=calendarDao.calendarUpdate(dto);
		String msg=count > 0 ? "일정 수정 성공" : "일정 수정 실패!";
		
		//
		ModelAndView mav=new ModelAndView();
		mav.addObject("msg", msg);
		mav.setViewName("ajax/ajaxCalendarMsg");
		return mav;
	}
	
	@RequestMapping("/calendarDelete.BT")
	public ModelAndView calendarDelete(
			HttpServletRequest req,
			@RequestParam("cid")String cid)
	{
		//
		int calendarID=Integer.parseInt(cid);
		
		//
		int count=calendarDao.calendarDelete(calendarID);
		String msg=count > 0 ? "일정 삭제 성공" : "일정 삭제 실패!";
		
		//
		ModelAndView mav=new ModelAndView();
		mav.addObject("msg", msg);
		mav.setViewName("ajax/ajaxCalendarMsg");
		return mav;
	}
	
	@RequestMapping("/calendarDropUpdate.BT")
	public ModelAndView calendarDropUpdate(
			HttpServletRequest req,
			CalendarDTO dto)
	{
		//
		System.out.println("calendarDropUpdate.!~~~~");
		System.out.println(dto.getCid());
		System.out.println(dto.getCstart());
		System.out.println(dto.getCend());
		
		//
		String startTime=dto.getCstart();
		startTime=startTime.replace(" ", "T");
		dto.setCstart(startTime);
		String endTime=dto.getCend();
		endTime=endTime.replace(" ", "T");
		dto.setCend(endTime);
		
		//
		int count=calendarDao.calendarDragDropUpdate(dto);
		String msg=count > 0 ? "[Drag&Drop]일정  수정 성공" : "[Drag&Drop]일정 수정 실패!";
		
		//
		ModelAndView mav=new ModelAndView();
		mav.addObject("msg", msg);
		mav.setViewName("ajax/ajaxCalendarMsg");
		return mav;
	}
}
