package bt.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import bt.webFolder.model.WebFolderDAO;
import bt.webFolder.model.WebFolderDTO;

@Controller
public class WebFolderController {
	public static final String BASE_PATH = 
				"C:/Users/LIsto/Documents/Programming/BT-Plan/.metadata/.plugins/"
				+ "org.eclipse.wst.server.core/tmp0/wtpwebapps/BT-Plan/WEB-INF/storage/";
	private String rootPath;	// 루트 폴더가 포함된 기본 경로
	private String folderPath;	// 현재 폴더 위치 경로 정보
	
	/* 용량 관련 필드 */
	private double totalStorage = 1024 * 1024 * 10;	// 총 용량 (10mb)
	private double usedStorage;		// 사용중인 용량
	private double emptyStorage;	// 남은 용량
	
	public String msg = null;	// 결과 관련 메세지 필드
	
	// getter / setter
	public String getRootPath() {
		return rootPath;
	}
	public void setRootPath(String rootPath) {
		this.rootPath = rootPath;
	}
	public String getFolderPath() {
		return folderPath;
	}
	public void setFolderPath(String folderPath) {
		this.folderPath = folderPath;
	}
	public double getTotalStorage() {
		return totalStorage;
	}
	public void setTotalStorage(double totalStorage) {
		this.totalStorage = totalStorage;
	}
	public double getUsedStorage() {
		return usedStorage;
	}
	public void setUsedStorage(double usedStorage) {
		this.usedStorage = usedStorage;
	}
	public double getEmptyStorage() {
		return emptyStorage;
	}
	public void setEmptyStorage(double emptyStorage) {
		this.emptyStorage = emptyStorage;
	}
	
	@Autowired
	public WebFolderDAO dao;

	/** 팀 로그인 처리 */
	@RequestMapping(value="/teamLogin.BT", method=RequestMethod.POST)
	public ModelAndView webFolderLogin(
			@RequestParam(value="wrootName", required=false) String wrootName,
			@RequestParam(value="wcodeName", required=false) String wcodeName,
			HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		// 세션 가져오기
		String rootFolder = (String) session.getAttribute("rootFolder");
		if (rootFolder == null) rootFolder = "";
		
		// 로그인 처리
		List<WebFolderDTO> rootName = dao.rootLogin(wrootName);
		try {
			if (rootName.get(0).getWcodeName().equals(wcodeName)) {
				session.setAttribute("rootFolder", wrootName);	// 세션 생성
				msg = "팀폴더 로그인 되었습니다.";
			} else {
				msg = "팀폴더 이름이나 코드가 잘못되었습니다.";
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = "예외발생: 팀폴더 이름이나 코드가 잘못되었습니다.";
		}
		
		mav.addObject("msg", msg);
		mav.setViewName("webFolder/webFolderMsg");
		return mav;
	}
	
	/** 기본 웹폴더 화면 */
	@RequestMapping("/webFolder.BT")
	public ModelAndView webFolder(
			@RequestParam(value="folderPath", defaultValue=BASE_PATH) String folderPath,
			@RequestParam(value="folderName", required=false) String folderName,
			@RequestParam(value="goParent", required=false) String goParent,
			HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		// 로그인 체크
		String loginSession_user = (String) session.getAttribute("sid");
		String loginSession_manager = (String) session.getAttribute("manager");
		if ((loginSession_user == null || loginSession_user.equals(""))
				&& (loginSession_manager == null || loginSession_manager.equals(""))) {
			mav.addObject("msg", "로그인 먼저 해주세요");
			mav.setViewName("user/userMsg");
			return mav;
		}
		
		// 세션 가져오기
		String rootFolder = (String) session.getAttribute("rootFolder");
		if (rootFolder == null) rootFolder = "";
		
		// 경로 파라미터값 관련 재설정
		if (folderPath.equals(BASE_PATH)) {
			folderPath = folderPath + rootFolder;
		} else if (folderName != null){
			folderPath = folderPath + "/" + folderName;
		}
			
		// 로그인 됐을시 (session o)
		if (!rootFolder.equals("")) {
			// 기본 경로 설정
			rootPath = BASE_PATH + rootFolder;
			
			if (folderPath.equals(BASE_PATH)) {
				// 경로 파라미터가 기본값일 경우 기본 루트 경로로 지정
				this.folderPath = rootPath;
			} else if (!(goParent == null || goParent.equals(""))) {
				// 상위 폴더로 경로 지정
				this.folderPath = setParentPath(goParent);
			} else {
				// 기본경로나 상위경로 지정 아닌 경우 기존 경로 유지
				this.folderPath = folderPath;
			}
			
			mav.addObject("rootFolder", rootFolder);
			mav.addObject("folderPath", this.folderPath);	// 현재 경로
			
			// 용량 구하기
			try {
				getCapacity(rootPath);
			} catch(Exception e) {
				e.printStackTrace();
				
				// 루트폴더가 없거나 할때
				mav.addObject("msg", "알 수 없는 예외발생 (루트폴더없음)");
				mav.setViewName("user/userMsg");
				return mav;
			}
			
			// 용량 단위 MB, 소수점 둘째자리
			mav.addObject("totalStorage", Math.round(totalStorage / 1024 / 1024 * 100) / 100.0);
			mav.addObject("usedStorage", Math.round(usedStorage / 1024 / 1024 * 100) / 100.0);
			mav.addObject("emptyStorage", Math.round(emptyStorage / 1024 / 1024 * 100) / 100.0);
			
			mav.setViewName("webFolder/webFolder");
			return mav;
		}
		
		// 비로그인 시 팀 로그인 화면으로
		mav.setViewName("webFolder/teamLogin");
		return mav;
	}
	
	/** 리스트 ajax */
	@RequestMapping("/ajaxListShow.BT")
	public ModelAndView ajaxListShow(
			@RequestParam(value="folderPath", defaultValue=BASE_PATH) String folderPath,
			@RequestParam(value="folderName", required=false) String folderName,
			@RequestParam(value="goParent", required=false) String goParent,
			HttpSession session) {
		if (folderPath.equals("")) folderPath = BASE_PATH;
		//System.out.println("folderPath " + folderPath);
		//System.out.println("folderName " + folderName);
		ModelAndView mav = new ModelAndView();
		
		// 세션 가져오기
		String rootFolder = (String) session.getAttribute("rootFolder");
		if (rootFolder == null) {
			mav.setViewName("webFolder/teamLogin");
			return mav;
		}
		
		File[] fileList = null;
		
		// 기본 경로 설정
		rootPath = BASE_PATH + rootFolder;
		
		if (folderPath.equals(BASE_PATH)) {
			// 경로 파라미터가 기본값일 경우 기본 루트 경로로 지정
			this.folderPath = rootPath;
		} else if (!(folderName == null || folderName.equals(""))) {
			// 폴더 진입시 경로 지정
			this.folderPath = folderPath + "/" + folderName;
		} else if (!(goParent == null || goParent.equals(""))) {
			// 상위 폴더로 경로 지정
			this.folderPath = setParentPath(goParent);
		} else {
			// 기본경로나 상위경로 지정 아닌 경우 기존 경로 유지
			this.folderPath = folderPath;
		}
		
		//System.out.println("this.folderPath " + this.folderPath);
		//System.out.println();
		
		// 상위폴더 버튼 활성화 위한 더미 데이터
		if (!this.folderPath.equals(rootPath)) {
			mav.addObject("upPath", "DUMMY");
		}
		
		// 파일 리스트
		fileList = getFileList();
		
		mav.addObject("fileList", fileList);
		mav.addObject("rootFolder", rootFolder);
		mav.addObject("folderPath", this.folderPath);	// 현재 경로
		mav.setViewName("webFolder/ajaxListShow");
		return mav;
	}
	
	/** 파일 목록 관련 메서드 */
	public File[] getFileList() {
		File file = new File(folderPath);
		File[] files = file.listFiles();
		return files;
	}
	
	/** 상위 폴더로 가기 관련 메서드 */
	public String setParentPath(String path) {
		int endIndex = path.lastIndexOf("/");
		String parentPath = path.substring(0, endIndex);
		return parentPath;
	}
	
	/** 팀 루트 폴더 생성  */
	@RequestMapping("/rootCreate.BT")
	public ModelAndView rootCreate(String wrootName, String wcodeName) {
		// 설정한 루트폴더명 존재 확인
		int result = dao.rootNameCheck(wrootName);
		if (result < 1) {
			String rootPath = BASE_PATH + wrootName;
			File file = new File(rootPath);
			boolean result_mkdirs = file.mkdirs();
			if (result_mkdirs) {
				msg = "팀폴더 생성 완료";
				dao.rootCreate(wrootName, wcodeName);	// DB에 저장
			} else {
				msg = "팀폴더 생성 실패실패실패실패실패";
			}
		} else {
			msg = "팀폴더 이름이 중복됩니다";
		}
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("folderPath", this.folderPath);
		mav.addObject("msg", msg);
		mav.setViewName("webFolder/webFolderMsg");
		return mav;
	}
	
	/** 팀폴더 코드 찾기 */
	@RequestMapping("/findCode.BT")
	public ModelAndView findCode(@RequestParam("rootName") String wrootName) {
		// 루트폴더명을 통해 코드(비밀번호) 찾기
		String rootCode = dao.findCode(wrootName);
		if (rootCode != null) {
			// 문자열 자르고 특수문자로 변환
			int length = rootCode.length();
			String str = rootCode.substring(0, length - 3) + "###";
			msg = wrootName + " 팀폴더의 코드는 \"" + str + "\" 입니다.";
		} else {
			msg = "없는 팀폴더 입니다.";
		}
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", msg);
		mav.addObject("folderPath", this.folderPath);
		mav.setViewName("webFolder/webFolderMsg");
		return mav;
	}
	
	/** 용량 관련 정보 구하는 메서드*/
	public void getCapacity(String rootPath) {
		File file = new File(rootPath);
		usedStorage = 0; // 일단 0으로 초기화
		getUsedCapacity(file);
		emptyStorage = totalStorage - usedStorage;
	}
	
	/** 사용중인 용량 구하는 메서드 */
	public void getUsedCapacity(File file) {
		File[] files = file.listFiles();
		
		for (int i = 0; i < files.length; i++) {
			if (files[i].isFile()) {
				usedStorage += files[i].length();
			} else {
				getUsedCapacity(files[i]);
			}
		}
	}
	
	/** 파일 업로드 */
	@RequestMapping("/fileUpload.BT")
	public ModelAndView fileUpload(
			@RequestParam("upload") List<MultipartFile> upload) {
		ModelAndView mav = new ModelAndView();
		
		if (!upload.isEmpty()) {
			// 다중 파일 업로드
			for (int i = 0; i < upload.size(); i++) {
				copyFile(upload.get(i));
			}
			
			mav.addObject("folderPath", this.folderPath);
			mav.addObject("msg", "파일 업로드 완료");
			mav.setViewName("webFolder/webFolderMsg");
		} else {
			mav.addObject("folderPath", this.folderPath);
			mav.addObject("msg", "파일이 지정되지 않았습니다.");
			mav.setViewName("webFolder/webFolderMsg");
		}
		return mav;
	}
	
	/** 파일 업로드(파일 복사) 관련 메서드 */
	public void copyFile(MultipartFile upload) {
		try {
			File userFile = new File(folderPath + "/" + upload.getOriginalFilename());
			byte[] bytes = upload.getBytes();
			FileOutputStream fos = new FileOutputStream(userFile);
			fos.write(bytes);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 파일 다운로드 */
	@RequestMapping("/download.BT")
	public ModelAndView fileDownload(
			@RequestParam("folderPath") String folderPath,
			@RequestParam("fileName") String fileName) {
		File file = new File(folderPath + "/" + fileName);
		ModelAndView mav = new ModelAndView("fileDownload", "downloadFile", file);
		return mav;
	}

	
	/** 폴더 생성하기 */
	@RequestMapping("/makeFolder.BT")
	public ModelAndView makeFolder(@RequestParam("folderName") String folderName) {
		//System.out.println("folderName: " + folderName);
		//System.out.println("this.folderPath: " + this.folderPath);
		//System.out.println("folderPath: " + folderPath);
		File file = new File(this.folderPath + "/" + folderName);
		boolean result = file.mkdir();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("folderPath", this.folderPath);
		mav.addObject("msg", result ? "폴더 생성 완료" : "폴더 생성 실패실패실패");
		mav.setViewName("webFolder/webFolderMsg");
		return mav;
	}
	
	/** 파일,폴더 삭제 */
	@RequestMapping("/delete.BT")
	public ModelAndView delete(
			@RequestParam(value="folderPath") String folderPath,
			@RequestParam(value="fileName", required=false) String fileName,
			@RequestParam(value="folderName", required=false) String folderName) {
		String deletePath = null;
		boolean result = false;
		if (fileName != null && folderName == null) {
			deletePath = folderPath + "/" + fileName;
			File file = new File(deletePath);
			result = deleteFile(file);
			this.folderPath = setParentPath(deletePath);	// 삭제 후 경로 재지정
		} else if (fileName == null && folderName != null) {
			deletePath = folderPath + "/" + folderName;
			File file = new File(deletePath);
			result = deleteFolder(file);
			this.folderPath = setParentPath(deletePath);	// 삭제 후 경로 재지정
		}
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("folderPath", this.folderPath);
		mav.addObject("msg", result ? "삭제 되었습니다." : "삭제 실패");
		mav.setViewName("webFolder/webFolderMsg");
		return mav;
	}
	
	/** 파일 삭제 관련 메서드 */
	public boolean deleteFile(File file) {
		return file.delete();
	}
	
	/** 폴더 삭제 관련 메서드 */
	public boolean deleteFolder(File file) {
		boolean result = false;
		File[] files = file.listFiles();
		for (int i = 0; i < files.length; i++) {
			if (files[i].isDirectory()) {
				result = files[i].delete();
				if (!result) {	// 폴더안에 파일있어서 삭제안되면 재귀 호출
					deleteFolder(files[i]);
				}
			} else {
				files[i].delete();
			}
		}
		result = file.delete();	// 폴더 내용 모두 삭제 후 폴더 삭제
		return result;
	}
	
	/** 코드 변경 */
	@RequestMapping("/changeCode.BT")
	public ModelAndView changeCode(
			@RequestParam("rootName") String rootName,
			@RequestParam("codeName") String codeName,
			@RequestParam("newCodeName") String newCodeName) {
		int result = 0;
		try {
			result = dao.changeCode(rootName, codeName, newCodeName);
		} catch (Exception e) {
			e.printStackTrace();
			msg = "알 수 없는 예외 발생";
		}
		if (result == -1) {
			msg = "팀폴더가 존재하지 않거나 코드가 틀렸습니다.";
		} else if (result == 0) {
			msg = "뭔가 알 수 없는 오류 발생";
		} else {
			msg = "코드가 변경되었습니다.";
		}

		ModelAndView mav = new ModelAndView();
		mav.addObject("folderPath", this.folderPath);
		mav.addObject("msg", msg);
		mav.setViewName("webFolder/webFolderMsg");
		return mav;
	}
}
