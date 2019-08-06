package bt.textBbsComment.model;

import java.util.HashMap; 
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

public class TextCommentDAOImple implements TextCommentDAO 
{	
	private SqlSessionTemplate sqlMap;
	
	public TextCommentDAOImple(SqlSessionTemplate sqlMap) {
		super();
		this.sqlMap = sqlMap;
	}

	public int textInsertComment(TextCommentDTO dto) // 댓글 작성하기
	{
		int count = sqlMap.insert("textcommentInsert",dto);
		return count;
	}
	
	public List textListComment(int ccontentindex) // 댓글 리스트보기 
	{
		List listComments = sqlMap.selectList("textcommentList",ccontentindex);
		return listComments;
	}
	
	public int textCommentDelete(int cindex) // 댓글 삭제하기
	{
		int count = sqlMap.delete("textcommentDelete",cindex);
		return count;
	}
	
}
