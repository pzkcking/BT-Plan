package bt.controller;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import bt.imageBbs.model.ImageBbsCommentPager;
import bt.imageBbs.model.ImageBbsDAO;
import bt.imageBbs.model.ImageBbsDTO;
import bt.imageBbs.model.ImageBbsListPager;
import bt.imageBbs.model.ImageBbsSearchPager;
import bt.imageBbs.model.ImgBbsCommentDTO;

import java.util.Calendar;

@Controller
public class ImageController 
{
	public static final String ROOT_PATH="C:/Users/LIsto/Documents/Programming/BT-Plan/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/BT-Plan/";
	public static final String STORAGE_PATH=ROOT_PATH + "storage";
	public static final String STORAGE_BUFFER_PATH=STORAGE_PATH + "/buffer";
	
	public static final String SESSION_USER_ID="sid";	//Uuserid
	
	public static final int LIST_PER_PAGE=3;
	public static final int PAGE_PER_GROUP=3;
	
	public static final int RESIZE_Width=500;
	public static final int RESIZE_HEIGHT=500;
	
	@Autowired
	private ImageBbsDAO bbsDao;
	
	public void printInitPath(HttpServletRequest req)
	{
		String PATH_INIT=req.getSession().getServletContext().getRealPath("/storage").replace("/", "/");
		String PATH_ROOT=PATH_INIT;
		int selEndIndex=PATH_ROOT.lastIndexOf("/");
		PATH_ROOT=PATH_ROOT.substring(0, selEndIndex);
		System.out.println(PATH_ROOT);
		System.out.println(PATH_INIT);
	}
	
	/**
	 * #Processor-BBS-ImageList Page.!
	 * 
	 * */
	@RequestMapping("/imageBbsList.BT")
	public ModelAndView imageBbsList(
			HttpServletRequest req,
			@RequestParam(value="cp",required=false, defaultValue="1")String crp)
	{
		//#1.
		HttpSession session=req.getSession();
		clearImageBuffer(session);
		clearImageSessionInfo(session);
		session.setAttribute("searchType", "");
		session.setAttribute("searchValue", "");
		
		//#2. 
		List bbsList=null;
		String leftPager="";
		String rightPager="";
		ArrayList<String> arrMidPager=new ArrayList<String>();
		ArrayList<String> arrMidPagerIndex=new ArrayList<String>();
		ArrayList<String> arrUrl=new ArrayList<String>();
		
			//
			ImageBbsListPager imgBbsPager=new ImageBbsListPager(bbsDao, LIST_PER_PAGE, PAGE_PER_GROUP);
			imgBbsPager.preProcess(crp);
			
			//
			bbsList=imgBbsPager.getLists();
			arrUrl=imgBbsPager.makeContentURL();
			leftPager=imgBbsPager.makeLeftPagerURL();
			rightPager=imgBbsPager.makeRightPagerURL();
			imgBbsPager.makeMidPagerURL(arrMidPager, arrMidPagerIndex);
			
		//#3. Parse and push image-path to array.! 
		ArrayList<String> imagePathList=new ArrayList<String>();
		for(int i=0; i < bbsList.size(); i++)
		{
			ImageBbsDTO obj=(ImageBbsDTO)bbsList.get(i);
			String bufferImageNames=obj.getIimagename();
			if(bufferImageNames != null &&
				(!bufferImageNames.equals("")) )
			{
				String[] imageNames=bufferImageNames.split(",");
				if(imageNames.length > 0) {
					imagePathList.add(imageNames[0]);
				}
			}
		}
			
		//#4. Setting MAV.!
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
		
		mav.addObject("lists", bbsList);
		mav.addObject("imagePathList", imagePathList);
		mav.addObject("listUrl", arrUrl);
		mav.addObject("leftPager", leftPager);
		mav.addObject("rightPager", rightPager);
		mav.addObject("midPager", arrMidPager);
		mav.addObject("midPagerIndex", arrMidPagerIndex);
		mav.setViewName("imageBbs/imageBbsList");
		return mav;
	}


	/**
	 * #Processor-BBS-imageBbsCommentList.!
	 * 
	 * */
	@RequestMapping("/imageBbsCommentList.BT")
	public ModelAndView imageBbsCommentList(
			HttpServletRequest req,
			@RequestParam(value="cp",required=false, defaultValue="1")String crp)
	{	
		//#0.
		HttpSession session=req.getSession();
		
		//#1. Insert the new bbs's comment to DB.!
		String curContentIndex=(String)session.getAttribute("currentImageBbsIndex");
		int selContentIndex=Integer.parseInt(curContentIndex);	
			
		//#2. Get BBS's comment from DB.!
		List commentList=null;
		String leftPager="";
		String rightPager="";
		ArrayList<String> arrMidPager=new ArrayList<String>();
		ArrayList<String> arrMidPagerIndex=new ArrayList<String>();
			
			//
			ImageBbsCommentPager commentPager=new ImageBbsCommentPager(bbsDao, selContentIndex, LIST_PER_PAGE, PAGE_PER_GROUP);
			commentPager.preProcess(crp);
			
			//
			commentList=commentPager.getLists();
			leftPager=commentPager.makeLeftPagerURL();
			rightPager=commentPager.makeRightPagerURL();
			commentPager.makeMidPagerURL(arrMidPager, arrMidPagerIndex);
			
		//#3. Process comment's <br> tag.!
		for(int i=0; i < commentList.size(); i++)
		{
			ImgBbsCommentDTO obj=(ImgBbsCommentDTO)commentList.get(i);
			String bufComment=obj.getCcomment();
			bufComment=bufComment.replace("\n", "<br>");
			obj.setCcomment(bufComment);
		}
			
		//#4. Out.!
		ModelAndView mav=new ModelAndView();
		mav.addObject("commentList", commentList);
		mav.addObject("leftPager", leftPager);
		mav.addObject("rightPager", rightPager);
		mav.addObject("midPager", arrMidPager);
		mav.addObject("midPagerIndex", arrMidPagerIndex);
		mav.setViewName("ajax/ajaxImgBbsCommentResult");
		return mav;
	}
	
	/**
	 * #Processor-BBS-Search.!
	 * 
	 * */
	@RequestMapping("/imageBbsSearch.BT")
	public String imageBbsSearch(
			HttpServletRequest req,
			@RequestParam(value="searchType",required=false, defaultValue="-1")String searchType,
			@RequestParam(value="searchValue",required=false, defaultValue="")String searchValue)
	{
		//#1.
		HttpSession session=req.getSession();
		clearImageBuffer(session);
		clearImageSessionInfo(session);
		
		//#2.
		session.setAttribute("searchType", searchType);
		session.setAttribute("searchValue", searchValue);
		
		//#3. Setting MAV.!
		return "redirect:imageBbsSearchList.BT";
	}
	
	/**
	* #Processor-BBS-SearchList.!
	* 
	* */
	@RequestMapping("/imageBbsSearchList.BT")
	public ModelAndView imageBbsSearchList(
			HttpServletRequest req,
			@RequestParam(value="cp",required=false, defaultValue="1")String crp)
	{
		//#1.
		HttpSession session=req.getSession();
		clearImageBuffer(session);
		clearImageSessionInfo(session);
		
		//#2.
		String searchType="";
		String searchValue="";
		searchType=(String)session.getAttribute("searchType");
		searchValue=(String)session.getAttribute("searchValue");
		
		//#3. 
		List bbsList=null;
		String leftPager="";
		String rightPager="";
		ArrayList<String> arrMidPager=new ArrayList<String>();
		ArrayList<String> arrMidPagerIndex=new ArrayList<String>();
		ArrayList<String> arrUrl=new ArrayList<String>();
		
			//
			ImageBbsSearchPager imgBbsPager=new ImageBbsSearchPager(bbsDao, LIST_PER_PAGE, PAGE_PER_GROUP);
			imgBbsPager.preProcess(crp, searchValue);
			
			//
			bbsList=imgBbsPager.getLists();
			arrUrl=imgBbsPager.makeContentURL();
			leftPager=imgBbsPager.makeLeftPagerURL();
			rightPager=imgBbsPager.makeRightPagerURL();
			imgBbsPager.makeMidPagerURL(arrMidPager, arrMidPagerIndex);
		
		//#3. Parse and push image-path to array.! 
		ArrayList<String> imagePathList=new ArrayList<String>();
		for(int i=0; i < bbsList.size(); i++)
		{
			ImageBbsDTO obj=(ImageBbsDTO)bbsList.get(i);
			String bufferImageNames=obj.getIimagename();
			if(bufferImageNames != null &&
				(!bufferImageNames.equals("")) )
			{
				String[] imageNames=bufferImageNames.split(",");
				if(imageNames.length > 0) {
					imagePathList.add(imageNames[0]);
				}
			}
		}
				
		//#4. Setting MAV.!
		ModelAndView mav=new ModelAndView();
		mav.addObject("lists", bbsList);
		mav.addObject("imagePathList", imagePathList);
		mav.addObject("listUrl", arrUrl);
		mav.addObject("leftPager", leftPager);
		mav.addObject("rightPager", rightPager);
		mav.addObject("midPager", arrMidPager);
		mav.addObject("midPagerIndex", arrMidPagerIndex);
		mav.setViewName("imageBbs/imageBbsList");
		return mav;
	}
	
	/**
	 * #Processor-BBS-Writing Page.!
	 * 
	 * */
	@RequestMapping("/imageBbsWrite.BT")
	public ModelAndView imageBbsWrite(
			HttpServletRequest req)
	{
		//
		HttpSession session=req.getSession();
		clearImageBuffer(session);
		clearImageSessionInfo(session);
		
		//
		String userID=(String)session.getAttribute(SESSION_USER_ID);
		
		//
		ModelAndView mav=new ModelAndView();
		mav.addObject("userID",userID);
		mav.setViewName("imageBbs/imageBbsWrite");
		return mav;
	}

	/**
	 * #Processor-BBS-WritingOK Page.!
	 * 
	 * */
	@RequestMapping("/imageBbsWriteOK.BT")
	public ModelAndView imageBbsWriteOK(
			ImageBbsDTO dto,
			HttpServletRequest req)
	{
		//
		HttpSession session=req.getSession();
		moveImageFileToStorage(session);
		
		//
		String imageFileNames=(String)session.getAttribute("imageFileNames");
		if(imageFileNames != null &&
			(!imageFileNames.equals("")) ){
			dto.setIimagename(imageFileNames);
		}
		clearImageSessionInfo(session);
		
		//
		int count=bbsDao.bbsWrite(dto);
		String msg=count > 0 ? "게시글 성공 성공 성공 성공!" : "게시글 실패!";
		
		//
		ModelAndView mav=new ModelAndView();
		mav.addObject("msg", msg);
		mav.setViewName("imageBbs/imageBbsMsg");
		return mav;
	}

	/**
	 * #Processor-BBS-ImgUpload Page.!
	 * 
	 * */
	@RequestMapping("/imageBbsUploadImage.BT")
	public ModelAndView bbsUploadImageForm()
	{
		ModelAndView mav=new ModelAndView();
		mav.setViewName("imageBbs/imageBbsUploadFile");
		return mav;
	}
	
	/**
	 * #Processor-BBS-ImgUploadOK Page.!
	 * 
	 * */
	@RequestMapping("/imageBbsUploadImageOK.BT")
	public ModelAndView imageBbsUploadImageOK(
			HttpServletRequest req,
			@RequestParam("upload")MultipartFile[] uploads) 
	{
		HttpSession session=req.getSession();
		String userID=(String)session.getAttribute(SESSION_USER_ID);
		
		//
		File newImgBufferFolder=new File(STORAGE_BUFFER_PATH+"/"+userID);
		if(!newImgBufferFolder.exists()){
			newImgBufferFolder.mkdir();
		}
		
		//
		if(checkExtension(uploads)==false) {
			ModelAndView mav=new ModelAndView();
			mav.addObject("msg", "jpg, png, gif, bmp만 가능 합니다.!");
			mav.addObject("fileNameList","");
			mav.setViewName("imageBbs/imageBbsUploadMsg");
			return mav;
		}
		
		//
		String crPath=STORAGE_BUFFER_PATH+"/"+userID;
		ArrayList<String> imgFileFullNameArr=uploadFileToStorage(crPath, uploads);
		
		//
		String imageFileNames=(String)session.getAttribute("imageFileNames");
		String bufferImageFileNames=(String)session.getAttribute("bufferImageFileNames");
		int lastIndex=imgFileFullNameArr.size() - 1;
		if(imgFileFullNameArr != null)
		{
			if(!imageFileNames.equals("") &&
				(imgFileFullNameArr.size() > 0) ){
				imageFileNames+=",";
				bufferImageFileNames+=",";
			}
			
			for(int i=0; i < imgFileFullNameArr.size(); i++)
			{
				imageFileNames+="storage/";
				imageFileNames+=imgFileFullNameArr.get(i);
				
				bufferImageFileNames+=("storage/buffer/" + userID + "/");
				bufferImageFileNames+=imgFileFullNameArr.get(i);
				
				if(i < lastIndex) {
					imageFileNames+=",";
					bufferImageFileNames+=",";
				}
				
				String bufImgFileFullName="storage/buffer/" + userID + "/" + imgFileFullNameArr.get(i);
				imgFileFullNameArr.set(i, bufImgFileFullName);
			}
		}
		session.setAttribute("imageFileNames", imageFileNames);
		session.setAttribute("bufferImageFileNames", bufferImageFileNames);
		System.out.println(imageFileNames);
		System.out.println(bufferImageFileNames);
		
		//
		ModelAndView mav= new ModelAndView();
		mav.addObject("msg","파일 올리기 성공.!");
		mav.addObject("fileNameList",imgFileFullNameArr);
		mav.setViewName("imageBbs/imageBbsUploadMsg");
		return mav;
	}

	/**
	 * #Processor-BBS-Content
	 * 
	 * */
	@RequestMapping("/imageBbsContent.BT")
	public ModelAndView imageBbsContent(
			HttpServletRequest req,
			@RequestParam("idx")String idx,
			@RequestParam(value="cp",required=false, defaultValue="1")String crp)
	{
		//#1. Get BBS's content from DB.!
		int selContentIndex=Integer.parseInt(idx);
		List contentList=bbsDao.bbsContent(selContentIndex);
		
		//#2. Get BBS's comment from DB.!
		List commentList=null;
		String leftPager="";
		String rightPager="";
		ArrayList<String> arrMidPager=new ArrayList<String>();
		ArrayList<String> arrMidPagerIndex=new ArrayList<String>();
			
			//
			ImageBbsCommentPager commentPager=new ImageBbsCommentPager(bbsDao, selContentIndex, LIST_PER_PAGE, PAGE_PER_GROUP);
			commentPager.preProcess(crp);
			
			//
			commentList=commentPager.getLists();
			leftPager=commentPager.makeLeftPagerURL();
			rightPager=commentPager.makeRightPagerURL();
			commentPager.makeMidPagerURL(arrMidPager, arrMidPagerIndex);
			
		//#3. 
		HttpSession session=req.getSession();
		session.setAttribute("currentImageBbsIndex", idx);
		session.setAttribute("currentPageIndex", crp);
		String userID=(String)session.getAttribute(SESSION_USER_ID);
		
		//#4. Parse and push image-path to array.! 
		ArrayList<String> imagePathList=new ArrayList<String>();
		for(int i=0; i < contentList.size(); i++)
		{
			ImageBbsDTO obj=(ImageBbsDTO)contentList.get(i);
			String bufferImageNames=obj.getIimagename();
			if(bufferImageNames != null &&
				(!bufferImageNames.equals("")) )
			{
				String[] imageNames=bufferImageNames.split(",");
				for(int k=0; k < imageNames.length; k++)
				{
					imagePathList.add(imageNames[k]);
				}
			}
		}
		
		//#5. Extract subject & writer from DTO.!
		String subject="";
		String writer="";
		int likeCount=0;
		for(int i=0; i < contentList.size(); i++)
		{
			ImageBbsDTO obj=(ImageBbsDTO)contentList.get(i);
			subject=obj.getItitle();
			writer=obj.getInickname();
			likeCount=obj.getIlikecount();
		}
		
		//#6. Process comment's <br> tag.!
		for(int i=0; i < commentList.size(); i++)
		{
			ImgBbsCommentDTO obj=(ImgBbsCommentDTO)commentList.get(i);
			String bufComment=obj.getCcomment();
			System.out.println(bufComment);
			bufComment=bufComment.replace("\n", "<br>");
			System.out.println(bufComment);
			obj.setCcomment(bufComment);
		}
		
		//#7. 
		String isWritedByOneSelf="";
		for(int i=0; i < contentList.size(); i++)
		{
			ImageBbsDTO obj=(ImageBbsDTO)contentList.get(i);
			String bufferUserID=obj.getInickname();
			if(bufferUserID.equals(userID)) {
				isWritedByOneSelf="true";
				break;
			}
		}
		int ret=bbsDao.bbsIncreaseViewCount(selContentIndex);
		
		//#8. Out.!
		ModelAndView mav=new ModelAndView();
		mav.addObject("idx", idx);
		mav.addObject("userID",userID);
		mav.addObject("subject", subject);
		mav.addObject("writer", writer);
		mav.addObject("likeCount", likeCount);
		mav.addObject("contentList", contentList);
		mav.addObject("imagePathList", imagePathList);
		mav.addObject("commentList", commentList);
		
		mav.addObject("isWritedByOneSelf",isWritedByOneSelf);
		
		mav.addObject("leftPager", leftPager);
		mav.addObject("rightPager", rightPager);
		mav.addObject("midPager", arrMidPager);
		mav.addObject("midPagerIndex", arrMidPagerIndex);
		
		mav.setViewName("imageBbs/imageBbsContent");
		return mav;
	}
	
	/**
	 * #Processor-BBS-CommentWriteOK
	 * 
	 * */
	@RequestMapping("/imageBbsCommentWriteOK.BT")
	public ModelAndView imageBbsCommentWriteOK(
			HttpServletRequest req,
			ImgBbsCommentDTO dto)
	{	
		//#0.
		HttpSession session=req.getSession();
		
		//#1. Insert the new bbs's comment to DB.!
		String curContentIndex=(String)session.getAttribute("currentImageBbsIndex");
		int selContentIndex=Integer.parseInt(curContentIndex);	
			
			//Increase comment's count.! 
			int ret=bbsDao.bbsIncreaseCommentNumber(selContentIndex);
			
			//Insert the new bbs's comment to DB.!
			dto.setCcontentindex(selContentIndex);
			int count=bbsDao.bbsCommentWrite(dto);
			String msg=count > 0 ? "댓글 성공 성공 성공 성공!" : "댓글 실패!";
		
		//#2. Get BBS's comment from DB.!
		List commentList=null;
		String crp="1";
		String leftPager="";
		String rightPager="";
		ArrayList<String> arrMidPager=new ArrayList<String>();
		ArrayList<String> arrMidPagerIndex=new ArrayList<String>();
			
			//
			ImageBbsCommentPager commentPager=new ImageBbsCommentPager(bbsDao, selContentIndex, LIST_PER_PAGE, PAGE_PER_GROUP);
			commentPager.preProcess(crp);
			
			//
			commentList=commentPager.getLists();
			leftPager=commentPager.makeLeftPagerURL();
			rightPager=commentPager.makeRightPagerURL();
			commentPager.makeMidPagerURL(arrMidPager, arrMidPagerIndex);
			
		//#3. Process comment's <br> tag.!
		for(int i=0; i < commentList.size(); i++)
		{
			ImgBbsCommentDTO obj=(ImgBbsCommentDTO)commentList.get(i);
			String bufComment=obj.getCcomment();
			bufComment=bufComment.replace("\n", "<br>");
			obj.setCcomment(bufComment);
		}
			
		//#4. Out.!
		ModelAndView mav=new ModelAndView();
		mav.addObject("commentList", commentList);
		mav.addObject("leftPager", leftPager);
		mav.addObject("rightPager", rightPager);
		mav.addObject("midPager", arrMidPager);
		mav.addObject("midPagerIndex", arrMidPagerIndex);
		mav.setViewName("ajax/ajaxImgBbsCommentResult");
		return mav;
		//return "redirect:imageBbsContent.BT?idx="+currentImageBbsIndex;
	}
	
	/**
	 * #Processor-BBS-Delete
	 * 
	 * */
	@RequestMapping("/imageBbsDelete.BT")
	public String imageBbsDelete(
			HttpServletRequest req)
	{
		System.out.println("imageBbsDelete.bt");
		
		//#
		HttpSession session=req.getSession();
		
		//#
		int selCurrentContentIndex=Integer.parseInt((String)session.getAttribute("currentImageBbsIndex"));
		int selCurrentPageIndex=Integer.parseInt((String)session.getAttribute("currentPageIndex"));
		
		//#
		List bbsImageNameList=null;
		bbsImageNameList=bbsDao.bbsContentImageName(selCurrentContentIndex);

		int count=0;
		String msg="";
		count=bbsDao.bbsCommentDelete(selCurrentContentIndex);
		msg=count > 0 ? "삭제 성공!" : "삭제 실패!";
		count=bbsDao.bbsDelete(selCurrentContentIndex);
		msg=count > 0 ? "삭제 성공!" : "삭제 실패!";

		System.out.println(count);
		System.out.println(msg);
		
		for(int i=0; i < bbsImageNameList.size(); i++)
		{
			ImageBbsDTO obj=(ImageBbsDTO)bbsImageNameList.get(i);
			String bufImageNames=obj.getIimagename();
			
			if(bufImageNames != null &&
				(!bufImageNames.equals("")) )
			{
				String[] imageNames=bufImageNames.split(",");
				for(int k=0; k < imageNames.length; k++)
				{
					File file=new File(ROOT_PATH + imageNames[k]);
					if(file.exists()){
						if(file.isFile()) { file.delete();}
						else{ deleteSubFolder(file); }
					}
				}
			}
		}
		
		//#
		return "redirect:imageBbsList.BT";
	}
	
	/**
	 * #Processor-BBS-Like
	 * 
	 * */
	@RequestMapping("/imageBbsLike.BT")
	public ModelAndView imageBbsLike(
			HttpServletRequest req)
	{
		//#1.
		HttpSession session=req.getSession();
		
		//#2. Increase LikeCount to DB.!
		String curContentIndex=(String)session.getAttribute("currentImageBbsIndex");
		int selContentIndex=Integer.parseInt(curContentIndex);	
			
			//Increase like's count.! 
			int ret=bbsDao.bbsIncreaseLikeCount(selContentIndex);
		
		//#3.
		List listBbsLikeTotalCount=bbsDao.bbsGetLikeTotalCountData(selContentIndex);
		int likeCount=0;
		for(int i=0; i < listBbsLikeTotalCount.size(); i++)
		{
			ImageBbsDTO obj=(ImageBbsDTO)listBbsLikeTotalCount.get(i);
			likeCount=obj.getIlikecount();
		}
		
		//#4. Setting MAV.!
		ModelAndView mav=new ModelAndView();
		mav.addObject("likeCount", likeCount);
		mav.setViewName("ajax/ajaxImgBbsIncreaseLikeCount");
		return mav;
	}
	
	/**
	 * #Method-BBS-checkExtension
	 * 
	 * */
	public boolean checkExtension(MultipartFile[] uploads)
	{
		HashMap<String, Object> mapAbleFormats=new HashMap<String, Object>();
		mapAbleFormats.put("jpg", "1");
		mapAbleFormats.put("jpeg", "1");
		mapAbleFormats.put("bmp", "1");
		mapAbleFormats.put("png", "1");
		mapAbleFormats.put("gif", "1");
		
		try 
		{
			for(int i=0; i < uploads.length; i++)
			{
				//Format.!
				String imgFormat=uploads[i].getOriginalFilename();
				int selPointIndex=imgFormat.lastIndexOf(".");
				imgFormat=imgFormat.substring(selPointIndex + 1);
				
				String ret=(String)mapAbleFormats.get(imgFormat);
				if(ret==null) {
					return false;
				}
			}
				
			return true;
		} 
		catch (Exception e) 
		{
			return false;
		}
	}
	
	/**
	 * #Method-BBS-uploadFileToStorage
	 * 
	 * */
	public ArrayList<String> uploadFileToStorage(String crpath, MultipartFile[] uploads)
	{
		try 
		{
			ArrayList<String> arr=new ArrayList<String>();
			for(int i=0; i < uploads.length; i++)
			{
				//
				String extension=uploads[i].getOriginalFilename();
				int selIndex=extension.lastIndexOf(".");
				extension=extension.substring(selIndex);
				System.out.println("extension : " + extension);
				
				//Format.!
				String imgFormat=uploads[i].getOriginalFilename();
				int selPointIndex=imgFormat.lastIndexOf(".");
				imgFormat=imgFormat.substring(selPointIndex + 1);
				System.out.println("imgFormat : " + imgFormat);
				
				//
				Calendar calendar=Calendar.getInstance();
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
				String currentTimeName=sdf.format(calendar.getTime());
				currentTimeName+=".";
				currentTimeName+=i;
				currentTimeName+=extension;
				System.out.println(currentTimeName);
				
				//
				String fileName=currentTimeName;
				arr.add(fileName);
				System.out.println(fileName);
				
				//
				File outFile=new File(crpath + "/" + currentTimeName);
				byte bytes[]=uploads[i].getBytes();
				FileOutputStream fos=new FileOutputStream(outFile);
				fos.write(bytes);
				fos.close();
				
				//Resize.!
				BufferedImage newResizedImg=reSizeImage(crpath + "/" + currentTimeName, RESIZE_Width, RESIZE_HEIGHT);
				File outResizedImgFile=new File(crpath + "/" + currentTimeName);
				ImageIO.write(newResizedImg, "png", outResizedImgFile);
			}
			return arr;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * #Method-BBS-reSizeImage
	 * 
	 * */
	public BufferedImage reSizeImage(String imgFilePath, int width, int height)
	{
		try 
		{
			//
			File input=new File(imgFilePath);
			BufferedImage img=ImageIO.read(input);
			Image buffer=img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			
			//
			BufferedImage newResizedImg=new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
			
			//
			Graphics2D g2d=newResizedImg.createGraphics();
			g2d.drawImage(buffer, 0, 0, null);
			g2d.dispose();
			return newResizedImg;
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
			return null;
		}
	}
	
	/**
	 * #Method-BBS-clearImageSessionInfo.
	 * 
	 * */
	public boolean clearImageSessionInfo(HttpSession session)
	{
		try 
		{
			session.setAttribute("imageFileNames", "");
			session.setAttribute("bufferImageFileNames", "");
			return true;
		}
		catch (Exception e) 
		{
			return false;
		}
	}
	
	/**
	 * #Method-BBS-clearImageBuffer.
	 * 
	 * */
	public boolean clearImageBuffer(HttpSession session)
	{
		try 
		{
			String userID=(String)session.getAttribute(SESSION_USER_ID);
			File folder=new File(STORAGE_BUFFER_PATH+"/"+userID);
			if(folder.exists()){
				if(folder.isFile()) { folder.delete();}
				else{ deleteSubFolder(folder); }
			}
			return true;
		}
		catch (Exception e) 
		{
			return false;
		}
	}
	
	/**
	 * #Method-BBS-deleteSubFolder
	 * 
	 * */
	public void deleteSubFolder(File f)
	{
		File files[]=f.listFiles();
		
		for(int i=0; i < files.length; i++)
		{
			if(files[i].isFile())
			{	
				files[i].delete();
			}
			else
			{
				deleteSubFolder(files[i]);
			}
		}
		
		f.delete();
	}
	
	/**
	 * #Method-BBS-moveImageFileToStorage.
	 * 
	 * */
	public void moveImageFileToStorage(HttpSession session)
	{
		String userID=(String)session.getAttribute(SESSION_USER_ID);
		String imageFileNames=(String)session.getAttribute("imageFileNames");
		String bufferImageFileNames=(String)session.getAttribute("bufferImageFileNames");		
		
		String[] imageFileNameArr=imageFileNames.split(",");
		String[] bufferImageFileNameArr=bufferImageFileNames.split(",");
		
		System.out.println("\n");
		System.out.println("\n");
		
		for(int i=0; i < bufferImageFileNameArr.length; i++)
		{
			System.out.println(ROOT_PATH + bufferImageFileNameArr[i]);
			System.out.println(ROOT_PATH + imageFileNameArr[i]);
			
			File bufImgFile=new File(ROOT_PATH + bufferImageFileNameArr[i]);
			File imgFile=new File(ROOT_PATH + imageFileNameArr[i]);
			bufImgFile.renameTo(imgFile);
		}
	}
	
}
