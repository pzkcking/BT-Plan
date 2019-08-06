package bt.imageBbs.model;

import java.util.ArrayList;
import java.util.List;

public class ImageBbsSearchPager 
{
	private ImageBbsDAO bbsDao;
	private final int listPerPage;
	private final int pagePerGroup;
	
	private int totalCount;
	private int totalPage;
	private int userCurrentPage;
	private int userCurrentGroup;
	private int lastGroup;
	
	private List lists;
	
	public ImageBbsSearchPager(ImageBbsDAO bbsDao, int listPerPage, int pagePerGroup) {
		super();
		this.bbsDao = bbsDao;
		this.listPerPage = listPerPage;
		this.pagePerGroup = pagePerGroup;
		
		this.totalCount=0;
		this.totalPage=0;
		this.userCurrentPage=0;
		this.userCurrentGroup=0;
		this.lastGroup=0;
		
		this.lists=null;
	}
	
	public List getLists() {
		return lists;
	}

	/**
	 * Make content's url.!
	 * 
	 * */
	public ArrayList<String> makeContentURL()
	{
		try 
		{
			ArrayList<String> arrUrl=new ArrayList<String>();
			for(int i=0; i < lists.size(); i++)
			{
				ImageBbsDTO obj=(ImageBbsDTO)lists.get(i);
				String url="imageBbsContent.BT?idx=" + obj.getIindex();
				arrUrl.add(url);
			}
			return arrUrl;
		}
		catch (Exception e) 
		{
			return null;
		}
	}

	/**
	 * Make LeftPager.!
	 * 		Setting the pager by the calculated page info.!
	 * */
	public String makeLeftPagerURL()
	{
		try 
		{
			if(totalCount <= 0) {
				return "";
			}
			
			String leftPager="";
			if(userCurrentGroup != 0)
			{
				int page=userCurrentGroup*pagePerGroup;
				String strPage=Integer.toString(page);
				leftPager="imageBbsSearchList.BT?cp=";
				leftPager+=strPage;
			}
			return leftPager;
		}
		catch (Exception e) 
		{
			return "";
		}
	}
	
	/**
	 * Make RightPager.!
	 * 		Setting the pager by the calculated page info.!
	 * */
	public String makeRightPagerURL()
	{
		try 
		{
			if(totalCount <= 0) {
				return "";
			}
			
			String rightPager="";
			if(userCurrentGroup != lastGroup)
			{
				int page=(userCurrentGroup+1)*pagePerGroup+1;
				String strPage=Integer.toString(page);
				rightPager="imageBbsSearchList.BT?cp=";
				rightPager+=strPage;
			}
			return rightPager;
		}
		catch (Exception e) 
		{
			return "";
		}
	}
	
	/**
	 * Make MidPager.!
	 * 		Setting the pager by the calculated page info.! 
	 * */
	public boolean makeMidPagerURL(ArrayList<String> arrMidPager, 
								   ArrayList<String> arrMidPagerIndex)
	{
		try 
		{
			if(totalCount <= 0) {
				arrMidPager.clear();
				arrMidPagerIndex.clear();
				return false;
			}
			
			//ArrayList<String> arrMidPager=new ArrayList<String>();
			//ArrayList<String> arrMidPagerIndex=new ArrayList<String>();
			for(int i=(userCurrentGroup*pagePerGroup + 1); i <= (userCurrentGroup*pagePerGroup+pagePerGroup); i++)
			{
				String midPager="";
				String strIdx=Integer.toString(i);
				midPager="imageBbsSearchList.BT?cp=";
				midPager+=strIdx;
				arrMidPager.add(midPager);
				arrMidPagerIndex.add(strIdx);
				if(i==totalPage){ break; }
			}
			return true;
		}
		catch (Exception e) 
		{
			return false;
		}
	}
	
	/**
	 * Preprocess for pager.!
	 * 
	 * */
	public void preProcess(String crp, String nickName)
	{
		//#1.Calculate page-info.!
			//1.TotalCount.!
			totalCount=bbsDao.getSearchByNickNameTotalCnt(nickName);
			System.out.println(totalCount);
			
			//2.Init-Setting.!	
			totalPage=(totalCount / listPerPage) +1;
			if(totalCount % listPerPage==0){totalPage--;}
			
			//3.Cal User's Current-Page.
			userCurrentPage=Integer.parseInt(crp);
			
			//4.Cal User's Current-Group.
			userCurrentGroup=userCurrentPage / pagePerGroup;
			if(userCurrentPage%pagePerGroup==0)userCurrentGroup--;		
		
			//5.Cal Last-Group.
			lastGroup=((totalPage/pagePerGroup) - (totalPage%pagePerGroup==0?1:0));
			
		//#2.Get info from DB.!
			//1.Get the page lists from DB.!
			lists=bbsDao.bbsSearchByNickNameList(nickName, userCurrentPage, listPerPage);
	}
}
