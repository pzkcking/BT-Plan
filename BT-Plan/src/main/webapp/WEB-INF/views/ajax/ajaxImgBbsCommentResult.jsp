<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fieldset>
	<br>
	<h3>&gt댓글 리스트</h3>
	<table style="margin-left: auto; margin-right: auto; border: 1px;
				text-align: left; width: 800px" class="table table-striped table-hover table-bordered">
		<tr>
			<td colspan="4" width="800" align="center"> 
			
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
						<h3>비어 있습니다.!</h3>
					</td>
				</tr>
			</c:when>
		
			<c:otherwise>
				<c:forEach var="dto" items="${commentList}">	
					<tr>
						<td colspan="4" width="800"></td>
					</tr>
					<tr>
						<th width="200" align="center">작성자:</th>
						<td width="200" align="center">${dto.cnickname}</td>
						<th width="200" align="center">날짜:</th>
						<td width="200" align="center">${dto.cpostdate}</td>
					</tr>
					<tr>
						<td colspan="4" width="800" align="center"> 
							<c:if test="${!empty dto.ccomment}">
								<div>${dto.ccomment}</div>
							</c:if>
							<div id="empty"></div>
						</td>	
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</table>
</fieldset>