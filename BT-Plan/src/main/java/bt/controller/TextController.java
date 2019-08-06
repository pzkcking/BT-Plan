package bt.controller;

import java.io.IOException;         
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList; 
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import bt.textBbsComment.model.*;
import bt.statistics.model.StatisticsDAO;
import bt.textBbs.model.*;

@Controller
public class TextController 
{
	@Autowired
	private TextDAO TextDao;
	
	@RequestMapping("/text.BT") 
	public String textIndex() 
	{
		return "text/text_Write";
	}
	
	@RequestMapping("/textWrite.BT")
	public ModelAndView textWrite(TextDTO dto)
	{
		ModelAndView mav = new ModelAndView();
		int result = TextDao.textWrite(dto);
		System.out.println("result="+result);
		String msg = null;
		if(dto.getTcategory()==1)
		{
			msg = "카테고리를 선택해 주세요";
			result = 0;
			mav.addObject("wmsg",msg);
			mav.setViewName("text/ok/text_Write_ok");
			return mav;
		}
		else
		{
			msg = "글쓰기 성공";
			result = 1;
			mav.addObject("dmsg",msg);
			mav.setViewName("text/ok/text_Write_sok");
			return mav;
		}
		
	}

	@RequestMapping("/textContent.BT")
	public ModelAndView textContent(@RequestParam("tindex")int tindex)
	{
		TextDTO dtos = TextDao.textContent(tindex);
		TextDao.textViewCount(tindex);
		ModelAndView mav = new ModelAndView();
		mav.addObject("result",dtos);
		mav.setViewName("text/text_Content");
		return mav; 
	}	
	
	@RequestMapping("/textDelete.BT") 
	public ModelAndView bbsDelete(@RequestParam("tindex")int tindex)
	{
		int result = TextDao.textDelete(tindex);
		System.out.println("Delete="+tindex);
		String msg = result>0?"글삭제 성공":"글삭제 실패";
		ModelAndView mav = new ModelAndView();
		mav.addObject("dmsg",msg);
		mav.setViewName("text/text_Delete"); 
		return mav;
	}
	
	@RequestMapping("/textUpdate.BT") 
	public ModelAndView textUpdateForm(@RequestParam("tindex")int tindex)
	{
		TextDTO dtos = TextDao.textContent(tindex);
		ModelAndView mav = new ModelAndView();
		mav.addObject("result",dtos);
		mav.setViewName("text/text_Update");
		return mav; 
	}	
	 
	@RequestMapping("/textUpdate_ok.BT")
	public ModelAndView textUpdate(TextDTO dto,@RequestParam("Uppostdate")String tpostdate)
	{
		int result = TextDao.textUpdate(dto);
		String msg =result>0?"수정 성공":"수정 실패";
		ModelAndView mav = new ModelAndView();
		mav.addObject("upmsg",msg);
		mav.setViewName("text/ok/text_Update_ok");
		return mav;
	}
	
	@RequestMapping("/textLikeCount.BT")
	public ModelAndView textLikeCount(@RequestParam("tindex")int tindex)
	{
		int result = TextDao.textLikeCount(tindex);
		String msg = result>0?"추천 성공":"추천 실패";
		ModelAndView mav = new ModelAndView();
		mav.addObject("likemsg",msg);
		mav.setViewName("text/ok/text_LikeCount_ok");
		return mav;
	}
	
	
	@RequestMapping("/textList.BT") 
	public ModelAndView textList(
			@RequestParam(value="cp", defaultValue="1") int cp,
			HttpSession session) 
	{
		
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
		
		int ls=5;
		int pagesize=5;
		
		int totalcnt=TextDao.getTotalCnt();
		List lists=TextDao.textList(cp,ls);
		
		String pagename="textList.BT";
		String pageStr=makePage(pagename, totalcnt, ls, pagesize, cp);

		mav.addObject("lists", lists);
		mav.addObject("pageStr", pageStr);
		mav.setViewName("text/text_List");
		return mav;
	}
	
		public static String makePage(String pagename, int totalcnt, int listsize, int pagesize, int cp) 
		{
		if(totalcnt==0)
		{
			totalcnt=1;
		}
		int totalpage = (totalcnt / listsize) + 1;
		if (totalcnt % listsize == 0)
			totalpage--;

		int userGroup = cp / pagesize;
		if (cp % pagesize == 0)
			userGroup--;
		
		StringBuffer sb = new StringBuffer();

		if (userGroup != 0) 
		{
			sb.append("<a href='");
			sb.append(pagename);
			sb.append("?cp=");
			int priGroup = (userGroup - 1) * pagesize + pagesize;
			sb.append(priGroup);
			sb.append("'>&lt;&lt;</a>");
			//<a href='textSearch.BT?cp=priGroup&tag=&search='>&lt;&lt;</a>
		}

		for (int i = (userGroup * pagesize + 1); i <= (userGroup * pagesize + pagesize); i++) 
		{
			sb.append("&nbsp;&nbsp;");
			sb.append("<a href='");
			sb.append(pagename);
			sb.append("?cp=");
			sb.append(i);
		
			sb.append("'>");
			sb.append(i);
			sb.append("</a>");
			sb.append("&nbsp;&nbsp;");
			//&nbsp;&nbsp;<a href='textSearch.BT?cp=i'>i</a>&nbsp;&nbsp;
			if (i == totalpage) 
			{
				break;
			}
		}

		if (userGroup != (totalpage / 5) - (totalpage % 5 == 0 ? 1 : 0)) 
		{
			sb.append("<a href='");
			sb.append(pagename);
			sb.append("?cp=");
			int netGroup = (userGroup + 1) * pagesize + 1;
			sb.append(netGroup);
			sb.append("'>&gt;&gt;</a>");
			//<a href='textSearch.BT?cp=netGroup'>&gt;&gt;</a>
		}
		return sb.toString();
	}	
		
		
		
		
}
