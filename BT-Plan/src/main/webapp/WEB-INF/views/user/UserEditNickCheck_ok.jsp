<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:choose>
	<c:when test="${flag==1 }">
		<script>
		window.alert('이미 존재하는 닉네임 입니다.');
		location.href = 'userEdit.BT?Uuserid=${Uuserid}';
		window.self.close();
		</script>
	</c:when>
	<c:otherwise>
		<script>
		window.alert('사용가능한 닉네임 입니다.');
		location.href = 'userEdit.BT?Uuserid=${Uuserid}';
		opener.document.userUpdate.Unickname.value = '${Unickname}';
		window.self.close();
		</script>
	</c:otherwise>
</c:choose>