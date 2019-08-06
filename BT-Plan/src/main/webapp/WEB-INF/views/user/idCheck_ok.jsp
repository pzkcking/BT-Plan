<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:choose>
	<c:when test="${ flag==1 }">
		<script>
		window.alert('이미 존재하는 아이디 입니다.');
		location.href = 'userJoinPage.BT';
		window.self.close();
		</script>
	</c:when>
	<c:otherwise>
		<script>
		window.alert('아이디로 사용가능 합니다.');
		location.href = 'userJoinPage.BT';
		opener.document.userJoin.Uuserid.value = '${Uuserid}';
		window.self.close();
		</script>
	</c:otherwise>
</c:choose>