package bt.imageBbs.model;

import java.util.ArrayList;
import java.util.List;

public class ImageBbsCommentPager 
{
	private ImageBbsDAO bbsDao;
	private final int contentIndex;
	private final int listPerPage;
	private final int pagePerGroup;
	
	private int totalCount;
	private int totalPage;
	private int userCurrentPage;
	private int userCurrentGroup;
	private int lastGroup;
	
	private List lists;
	
	public ImageBbsCommentPager(ImageBbsDAO bbsDao, int contentIndex, int listPerPage, int pagePerGroup) {
		super();
		this.bbsDao = bbsDao;
		this.contentIndex = contentIndex;
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
				//leftPager="imageBbsContent.BT?idx="+contentIndex+"&"+"cp="+strPage;
				leftPager="javascript:OnCommentPageEvent('idx="+contentIndex+"&cp="+strPage+"')";
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
				//rightPager="imageBbsContent.BT?idx="+contentIndex+"&"+"cp="+strPage;
				rightPager="javascript:OnCommentPageEvent('idx="+contentIndex+"&cp="+strPage+"')";
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
			
			for(int i=(userCurrentGroup*pagePerGroup + 1); i <= (userCurrentGroup*pagePerGroup+pagePerGroup); i++)
			{
				String midPager="";
				String strIdx=Integer.toString(i);
				//midPager="imageBbsContent.BT?idx="+contentIndex+"&"+"cp="+strIdx;
				midPager="javascript:OnCommentPageEvent('idx="+contentIndex+"&cp="+strIdx+"')";
				
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
	public void preProcess(String crp)
	{
		//#1.Calculate page-info.!
			//1.TotalCount.!
			totalCount=bbsDao.getCommentTotalCnt(contentIndex);
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
			lists=bbsDao.bbsCommentList(contentIndex, userCurrentPage, listPerPage);
	}
}
