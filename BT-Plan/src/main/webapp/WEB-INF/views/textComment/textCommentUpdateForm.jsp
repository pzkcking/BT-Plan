<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var = "dto" value = "${result}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
fieldset {
	position: relative;
	text-align: center;
	margin: 0px auto;
}
</style>
<body>
<section style="margin: 0px auto;">

	<fieldset style="width: 900px; align-content: center;" class="container-fluid">
		<form name = "commentUpdate" action = "commentUpdate_ok.BT">
		<table style="margin-left: auto; margin-right: auto; border: 1px;
				text-align: left; width: 500px" class="table table-striped table-hover table-bordered">
			<thead>
			<tr>
				<td>댓글 번호</td>
				<td>닉네임</td>
				<td>내용</td>
				<td>비고</td>
			</tr>
			</thead>
				<tbody>
				<tr>
					<td><input type = "text" name = "cindex" value = "${dto.cindex}" readonly="readonly"></td>
					<td><input type = "text" name = "cnickname" value = "${dto.cnickname}" readonly="readonly"></td>
					<td><textarea rows="10" cols="100" name = "ccomment">${dto.ccomment}</textarea></td>
					<td><input type = "text" name = "cpostdate" value = "${dto.cpostdate}"></td>
					<!--<td><input type = "submit" value = "수정"></td>-->
				</tr>
				</tbody>
		</table>
		</form>
		</fieldset>
		</section>
</body>
</html>