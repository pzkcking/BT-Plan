package bt.imageBbs.model;

import java.util.List;

import bt.imageBbs.model.ImageBbsDTO;

public interface ImageBbsDAO 
{
	public int getTotalCnt();
	public List bbsList(int user_current_page, int list_per_page);
	public List bbsContent(int idx);	
	public int bbsWrite(ImageBbsDTO dto);
	
	public List bbsContentImageName(int idx);	
	public int bbsDelete(int idx);
	public int bbsCommentDelete(int idx);
	
	public int getCommentTotalCnt(int contentIndex);
	public List bbsCommentList(int contentIndex, int user_current_page, int list_per_page);
	public int bbsCommentWrite(ImgBbsCommentDTO dto);
	public int bbsIncreaseCommentNumber(int idx);
	
	public int getSearchByNickNameTotalCnt(String nickName);
	public List bbsSearchByNickNameList(String nickName, int user_current_page, int list_per_page);
	
	public int bbsIncreaseViewCount(int idx);
	public int bbsIncreaseLikeCount(int idx);
	
	public List bbsGetLikeTotalCountData(int idx);
}
