<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
<style>
fieldset {
	position: relative;
	text-align: center;
	margin: 0px auto;
}
</style>
</head>
	<body>
	<%@ include file="../header.jsp"%>
	<section style="margin: 0px auto;">
			<article>
				<fieldset  style="width: 800px; align-content: center;" class="container-fluid">
					<h2>앨범 게시판</h2>
					<table style="margin-left: auto; margin-right: auto; border: 1px;
				text-align: left; width: 800px" class="table table-striped table-hover table-bordered">
						<thead>
							<tr>
								<th width="100" align="center" style="text-align: center;">번호</th>
								<th width="100" align="center" style="text-align: center;">미리보기</th>
								<th width="300" align="center" style="text-align: center;">제목</th>
								<th width="150" align="center" style="text-align: center;">글쓴이</th>
								<th width="90" 	align="center" style="text-align: center;">좋아요</th>
								<th width="90" 	align="center" style="text-align: center;">조회수</th>
								<th width="250" align="center" style="text-align: center;">날짜</th>
							</tr>
						</thead>
						
						<tbody>
							<c:if test="${empty lists}">
								<tr>
									<td colspan="5" align="center">
										등록된 글이 없습니다.!
									</td>
								</tr>
							</c:if>
							
							<c:forEach var="dto" items="${lists}" varStatus="status">
								<tr>
									<td align="center">${dto.iindex}</td>
									<c:choose>
										<c:when test="${!empty imagePathList}">
											<td><img src="${imagePathList[status.index]}" width="90" height="90"></td>
										</c:when>
									
										<c:otherwise>
											<td align="center">이미지 없음.!</td>
										</c:otherwise>
									</c:choose>
									<td align="center"><a href="${listUrl[status.index]}">${dto.ititle}[${dto.icommentnumber}]</a></td>
									<td align="center">${dto.inickname}</td>
									<td align="center">${dto.ilikecount}</td>
									<td align="center">${dto.iviewcount}</td>
									<td align="center">${dto.ipostdate}</td>
								</tr>
							</c:forEach>
						</tbody>
						
						<tfoot>
							<tr>
								<td colspan="6" align="center"> 
								
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
								<td style="text-align: center;"> <a href="imageBbsWrite.BT">글쓰기</a> </td>
							
							</tr>
						</tfoot>
					</table>
					<form name="searchForm" action="imageBbsSearch.BT" method="post">
						<select name="searchType">
							<option value="1">닉네임</option>
						</select>
						<input type="text" name="searchValue">
						<input type="submit" class="btn btn-primary" value="검색">
					</form>
					<form name="viewAllForm" action="imageBbsList.BT" method="post">
						<input type="submit" class="btn btn-primary btn-ms" value="전체보기">
					</form>
					<br>
					<br>
				</fieldset>
			</article>
		</section>
	<%@ include file="../footer.jsp"%>
	</body>
</html>