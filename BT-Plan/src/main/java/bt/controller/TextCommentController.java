package bt.controller;

import java.io.InputStream;    
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import bt.textBbsComment.model.*;
import bt.textBbs.model.TextDTO;
import bt.textBbsComment.model.TextCommentDAO;
import bt.textBbsComment.model.TextCommentDTO;

@Controller
public class TextCommentController 
{	
	@Autowired
	private TextCommentDAO textcommentDao;

	@RequestMapping("/textCommentWriteForm.BT") 
	public ModelAndView textCommentForm(@RequestParam("ccontentindex") int ccontentindex)
	{
		ModelAndView mav = new ModelAndView();
		mav.addObject("ccontentindex",ccontentindex);
		mav.setViewName("textComment/textCommentForm");
		return mav;
	}

	@RequestMapping("/textCommentWrite.BT") 
	public ModelAndView textCommentWrite(TextCommentDTO dto)
	{
		int result = textcommentDao.textInsertComment(dto);
		String msg = result>0?"작성 성공":"작성 실패";
		ModelAndView mav = new ModelAndView();
		mav.addObject("cwmsg",msg);
		mav.setViewName("textComment/ok/textComment_ok");
		return mav;
	}
	
	@RequestMapping("/textCommentList.BT") 
	public ModelAndView textCommentList(@RequestParam("ccontentindex")int ccontentindex)
	{
		ModelAndView mav = new ModelAndView();
		List result = textcommentDao.textListComment(ccontentindex);
		
		mav.addObject("textcomment",result);
		mav.setViewName("textComment/textCommentList");
		return mav;
	}
	
	@RequestMapping("/textCommentDelete.BT") 
	public ModelAndView textCommentDelete(@RequestParam("cindex")int cindex)
	{
		int result = textcommentDao.textCommentDelete(cindex);
		String msg = result>0?"삭제 성공":"삭제 실패";
		ModelAndView mav = new ModelAndView();
		mav.addObject("cdmsg",msg);
		mav.setViewName("textcomment/ok/textCommentDelete_ok");
		return mav;
	}	
	
	
	
}
