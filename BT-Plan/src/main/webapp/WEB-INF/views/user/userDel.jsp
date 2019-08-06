<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
function isSame() {
	var pw = document.all.delCheckHidden.value;
	var confirmPW = document.all.delCheck.value;

	if (document.getElementById('delCheckText').value != ''
			&& document.getElementById('delCheckInput').value != '') {
		if (document.getElementById('delCheckText').value == document
				.getElementById('delCheckInput').value) {
			document.getElementById('same').innerHTML = '작성란이 일치합니다.';
			document.getElementById('same').style.color = 'blue';
		} else {
			document.getElementById('same').innerHTML = '작성란이 일치하지 않습니다';
			document.getElementById('delCheckInput').value = "";
			document.getElementById('delCheckText').value = "";
			document.getElementById('same').style.color = 'red';
		}
	}
}
</script>
</head>
<body>
<section>
<fieldset>
<legend>회원 탈퇴</legend>
<form name="userDel" action="userDel_ok.BT" method="post">
<h5>탈퇴를 하기 위해서 아래 입력란에 "탈퇴 하겠습니다."를 입력해주세요.</h5>
<input type="hidden" name="delCheckHidden" id="delCheckText" value="탈퇴 하겠습니다." onchange="isSame()">
<input type="text" name="delCheck" id="delCheckInput" required="required" onchange="isSame()"><span id="same"></span>
<br>
<input type="hidden" name="Uindex" value="${ Uindex }">
<input type="submit" value="탈퇴">
</form>
</fieldset>
</section>
</body>
</html>