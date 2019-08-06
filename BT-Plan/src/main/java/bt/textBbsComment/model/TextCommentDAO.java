package bt.textBbsComment.model;

import java.util.List;

public interface TextCommentDAO 
{
	public int textInsertComment(TextCommentDTO dto); // 댓글 작성하기
	public List textListComment(int ccontentindex); // 댓글 리스트 보기
	public int textCommentDelete(int cindex); // 댓글 삭제하기 
	
	
}
