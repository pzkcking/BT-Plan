package bt.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import bt.statistics.model.StatisticsDTO;
import bt.manager.model.ManagerDAO;
import bt.manager.model.ManagerDTO;

@Controller
public class ManagerController {

	@Autowired
	private ManagerDAO managerDao;
	
	// 관리자 페이지 접속
	@RequestMapping("/manager.BT")
	public String manager() {
		return "manager";
	}
	
	@RequestMapping("/managerInfo.BT")
	public ModelAndView managerPage() {
		List lists=managerDao.managerList();
		ModelAndView mav=new ModelAndView();
		mav.addObject("lists", lists);
		mav.setViewName("manager/managerInfo");
		return mav;
	}
	
	@RequestMapping("/managerBan.BT")
	public ModelAndView managerBan(ManagerDTO dto,HttpServletRequest request) {
		String ban=request.getParameter("ubanned");
		int result=0;
		if(ban.equals("0")) {
			result=managerDao.managerBan(dto);
		}else if(ban.equals("1")) {
			result=managerDao.managerUnban(dto);
		}
		
		String msg=result>0?"처리되었습니다":"처리 실패";
		ModelAndView mav=new ModelAndView();
		mav.addObject("msg",msg);
		mav.setViewName("manager/managerMsg");
		return mav;
	}
	
	@RequestMapping("/managerSearch.BT")
	public ModelAndView managerSearch(HttpServletRequest request) {

		String usersearch = request.getParameter("userSearch");
		String keyword=request.getParameter("keyword");
		
		List lists=null;
		if(usersearch.equals("uname")) {
			lists=managerDao.managerNameSearch(keyword);
		}else if(usersearch.equals("unickname")) {
			lists=managerDao.managerNicknameSearch(keyword);
		}else if(usersearch.equals("uuserid")) {
			lists=managerDao.managerIDSearch(keyword);
		}
			
		ModelAndView mav=new ModelAndView();
		mav.addObject("lists", lists);
		mav.setViewName("manager/managerInfo");
		return mav;
	}
}
