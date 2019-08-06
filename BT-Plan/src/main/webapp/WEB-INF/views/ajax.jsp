<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fieldset>
	<br>
	<hr color="black">
	<h3>&gt댓글 리스트</h3>
	<hr color="black">     
	<table>
		<tr>
			<td colspan="3" align="center"> 
				페이지 영역 
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
					<td colspan="3">	
						<h3>비어 있습니다.!</h3>
					</td>
				</tr>
			</c:when>
		
			<c:otherwise>
				<c:forEach var="dto" items="${commentList}">	
					<tr>
						<td colspan="4"> <hr color="darkblue"> </td>
					</tr>
					<tr>
						<th>작성자:</th>
						<td>${dto.cnickname}</td>
					</tr>
					<tr>
						<td colspan="4" > 
							<hr>
							<c:if test="${!empty dto.ccomment}">
								<div>${dto.ccomment}</div>
							</c:if>
							<div id="empty"></div>
							<hr color="darkblue">
						</td>	
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</table>
</fieldset>