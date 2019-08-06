package bt.textBbs.model;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

public interface TextDAO  
{
	public int textWrite(TextDTO dto); // 글쓰기
	public TextDTO textContent(int tindex); // 본문보기
	public int textDelete(int tindex); // 삭제하기
	public TextDTO textUpdate(int tindex); // 수정하기 (Form)
	public int textUpdate(TextDTO dto); // 수정하기
	public void textViewCount(int tindex); // 조회수
	public int textLikeCount(int tindex); // 추천수
	public int getTotalCnt(); // 모든 페이지 수
	public List textList(int cp, int ls); // 리스트 보기
	
}
