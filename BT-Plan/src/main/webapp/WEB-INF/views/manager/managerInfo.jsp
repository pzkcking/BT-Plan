<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ include file="../header.jsp" %>
<h1>회원 정보</h1>
<table border="2" cellspacing="0">
	<thead>
		<tr>
			<th>회원번호</th>
			<th>회원아이디</th>
			<th>회원이름</th>
			<th>회원별명</th>
			<th>성별</th>
			<th>E-mail</th>
			<th>생년월일</th>
			<th>연락처</th>
			<th>주소</th>
			<th>가입일</th>
			<th>밴여부</th>
			<th>회원구분</th>
			<th>추방</th>
		</tr>
	</thead>
	<tbody>
		<c:if test="${empty lists }">
			<tr>
				<td colspan="13" align="center">
				등록된 사원이 없습니다.
				</td>
			</tr>
		</c:if>
		<c:forEach var="dto" items="${lists }">
			<tr>
				<td>${dto.uindex }</td>
				<td>${dto.unickname}</td>
				<td>${dto.uname }</td>
				<td>${dto.unickname }</td>
				<td>${dto.usex }</td>
				<td>${dto.uemail }</td>
				<td>${dto.ubirthday }</td>
				<td>${dto.uphone }</td>
				<td>[${dto.uzipcode }]${dto.uaddress1 }${dto.uaddress2 }${dto.uaddress3 }</td>
				<td>${dto.ujoindate }</td>
				<td>${dto.ubanned }</td>
				<td>${dto.umanager }</td>
				<td><input type="button" class="btn btn-primary" value="처리" onclick="location.href='managerBan.BT?uindex=${dto.uindex}&ubanned=${dto.ubanned }'"></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
	<h5>회원 검색</h5>
	<hr>
	<form action="managerSearch.BT">
		<table>
			<tr>
				<th>
					<select name="userSearch">
					<option value="uname">회원이름</option>
					<option value="unickname">회원별명</option>
					<option value="uuserid">회원아이디</option>
					</select>
				</th>
				<td><input type="text" name="keyword"></td>
				<td><input class="btn btn-primary" type="submit" value="검색하기"></td>
			</tr>
		</table>
	</form>	
<%@ include file="../footer.jsp" %>
</body>
</html>