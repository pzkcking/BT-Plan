package bt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import bt.statistics.model.StatisticsDAO;

@Controller
public class StatisticsController {

	@Autowired
	private StatisticsDAO statisticsDao;
	
	@RequestMapping("/statisticspage.BT")
	public ModelAndView connectionCount() {
		
		List lists=statisticsDao.wholeconnection();
		List lists2=statisticsDao.countMember();
		List lists3=statisticsDao.bancount();
		List lists4=statisticsDao.ageCalculate();
		int textCount=statisticsDao.textCount();
		int imageCount=statisticsDao.imageCount();
		
		int age_10=statisticsDao.age_10();
		int age_20=statisticsDao.age_20();
		int age_30=statisticsDao.age_30();
		int age_40=statisticsDao.age_40();
		int age_50=statisticsDao.age_50();
		int age_60=statisticsDao.age_60();
		int age_etc=statisticsDao.age_etc();
		int gender_male=statisticsDao.gender_male();
		int gender_female=statisticsDao.gender_female();
		
		ModelAndView mav=new ModelAndView();
		mav.addObject("connectionCount",lists);
		mav.addObject("lists2",lists2);
		mav.addObject("lists3",lists3);
		mav.addObject("lists4",lists4);
		mav.addObject("textCount",textCount);
		mav.addObject("imageCount",imageCount);
		mav.addObject("age_10",age_10);
		mav.addObject("age_20",age_20);
		mav.addObject("age_30",age_30);
		mav.addObject("age_40",age_40);
		mav.addObject("age_50",age_50);
		mav.addObject("age_60",age_60);
		mav.addObject("age_etc",age_etc);
		mav.addObject("male",gender_male);
		mav.addObject("female",gender_female);
		mav.setViewName("statistics/statisticsPage");
		return mav;
	}
}
