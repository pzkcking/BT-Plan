<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		
		<script src="js/httpRequest.js"></script>
		<script src="js/jquery-3.4.1.js"></script>
		<script type="text/javascript"></script>
		
		<script>
			function OnCommentPageEvent(param)
			{
				sendRequest('imageBbsCommentList.BT', param, 'POST', OnCommentPageResultEvent);
			}
		
			function OnCommentPageResultEvent()
			{
				if(XHR.readyState==4 &&
				   XHR.status==200)
				{
					
					
					var Tag=document.getElementById('idCommentList');
					Tag.innerHTML=XHR.responseText;
				}
			}
		
			function OnWriteCommentEvent()
			{
				event.returnValue=false;

				var params=$("#bbsCommentWrite").serialize();
				sendRequest('imageBbsCommentWriteOK.BT', params, 'POST', OnWriteCommentResultEvent);
			}

			function OnWriteCommentResultEvent()
			{
				if(XHR.readyState==4 &&
					XHR.status==200)
				{
					window.alert('댓글 달기 성공.!!');
					
					var Tag=document.getElementById('idCommentList');
					Tag.innerHTML=XHR.responseText;
				}
			}
			
			function OnIncreaseLikeCountEvent()
			{
				sendRequest('imageBbsLike.BT', null, 'POST', OnIncreaseLikeCountResultEvent);
			}
			
			function OnIncreaseLikeCountResultEvent()
			{
				if(XHR.readyState==4 &&
					XHR.status==200)
				{
					window.alert('좋아요 !!');
					
					var Tag=document.getElementById('idLikeCount');
					Tag.innerHTML=XHR.responseText;
				}
			}
		</script>
		
	</head>
	<style>
fieldset {
	position: relative;
	text-align: center;
	margin: 0px auto;
}
</style>
	<body>
	<%@ include file="../header.jsp"%>
	<h1 style="text-align: center; opacity: 0.8">앨범 게시판</h1>
		<section style="margin: 0px auto;">
			<article>
				<fieldset style="width: 1000px; align-content: center;" class="container-fluid">
				
					<table style="margin-left: auto; margin-right: auto; border: 1px;
				text-align: left; width: 900px" class="table table-striped table-hover table-bordered">
						<tr>
							<th align="center">작성자:</th>
							<td align="center">${writer}</td> 
						</tr>
						<tr>
							<th align="center">제목:</th>
							<td colspan="2" align="center"> ${subject} </td> 
						</tr>
							<th align="center">좋아요:</th>
							<td colspan="2" align="center">
							<input type="button" class="btn btn-primary" value="좋아요" onclick="OnIncreaseLikeCountEvent()"></td> 
						<tr>
						<tr>
							<th align="center">좋아요 갯수:</th>
							<td colspan="2" align="center"><div id="idLikeCount"><h5>${likeCount}</h5></div></td>
						</tr>
						<c:if test="${!empty isWritedByOneSelf}">
							<tr>
								<th align="center">삭제:</th>
								<td colspan="2" align="center"><form name="deleteBBS" action="imageBbsDelete.BT" method="post">
								<input type="submit" class="btn btn-primary" value="게시글 삭제"></form></td> 
							</tr>
						</c:if>
						<tr>
							<c:if test="${!empty imagePathList}">
								<td colspan="2" align="center">
								<c:forEach var="imagePath" items="${imagePathList}">
									<img src="${imagePath}" width="500" height="500" align="center">
								</c:forEach>
								</td>
							</c:if>
						</tr>
						<tr height="50" align="center">
							<td colspan="2" valign="top" align="left"> 
								<c:if test="${!empty contentList}">
									<c:forEach var="dto" items="${contentList}">
										
									</c:forEach>
								</c:if>
							</td>	
						</tr>
						<tr>
							<td colspan="2" align="center">
								<a href="imageBbsList.BT">목록으로</a>
							</td>
						</tr>
					</table>
				</fieldset>
			</article>
			
			<article>
				<fieldset>
	
					<h3>&gt댓글 달기</h3>

					<form id="bbsCommentWrite" name="bbsCommentWrite" action="imageBbsCommentWriteOK.BT">
						<table style="margin-left: auto; margin-right: auto; border: 1px;
				text-align: left; width: 900px" class="table table-striped table-hover table-bordered">
							<tr>
								<th>글쓴이</th>
								<td> <input id="idWriter" type="text" name="cnickname" value="${userID}" readonly> </td>
							</tr>
							<tr>
								<td colspan="2"> 
									<textarea id="idComment" name="ccomment" rows="5" cols="90"> </textarea>
								</td>	
							</tr>
							<tr>
								<td colspan="2" align="right"> 
									<input type="submit" class="btn btn-primary" value="확인" onclick="OnWriteCommentEvent()">
								</td>
							</tr>
						</table>
					</form>
				</fieldset>
			</article>
			<article>
				<div id="idCommentList">
					<fieldset style="width: 1000px; align-content: center;" class="container-fluid">
						<h3>&gt댓글 리스트</h3>
						<table style="margin-left: auto; margin-right: auto; border: 1px;
				text-align: left; width: 900px" class="table table-striped table-hover table-bordered">
							<tr>
								<td colspan="4" width="800" style="text-align:center;"> 
									<c:if test="${!empty leftPager}">
										<a href="${leftPager}"> &lt; &lt; </a>
									</c:if>
									
									<c:if test="${!empty midPager}">
										<c:forEach var="mid" items="${midPager}" varStatus="status">
											<a href="${mid}"> ${midPagerIndex[status.index]} </a>
										</c:forEach>
									</c:if>
									
									<c:if test="${!empty rightPager}">
										<a href="${rightPager}"> &gt; &gt; </a>
									</c:if>
								</td>
							</tr>
								
							<c:choose>
								<c:when test="${empty commentList}">
									<tr>
										<td colspan="3" width="800">	
											<h3 style="text-align: center;">등록된 댓글이 없습니다.</h3>
										</td>
									</tr>
								</c:when>
							
								<c:otherwise>
									<c:forEach var="dto" items="${commentList}">	
									
										<tr>
											<th width="200" style="text-align: center;">작성자:</th>
											<td width="200" style="text-align: center;">${dto.cnickname}</td>
											<th width="200" style="text-align: center;">날짜:</th>
											<td width="200" style="text-align: center;">${dto.cpostdate}</td>
										</tr>
										<tr>
											<td colspan="4" width="800" style="text-align: center;"> 
												
												<c:if test="${!empty dto.ccomment}">
													<div>${dto.ccomment}</div>
												</c:if>
											
											</td>	
										</tr>
									</c:forEach>
								</c:otherwise>
							</c:choose>
						</table>
					</fieldset>
				</div>
			</article>
			
		</section>
		<%@ include file="../footer.jsp"%>
	</body>
</html>