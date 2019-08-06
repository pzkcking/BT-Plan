<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
var pw_passed = true;

function fn_pw_check() {
	var id = document.getElementById("Unickname").value; // 아이디

	pw_passed = true;

	var pattern1 = /[0-9]/;
	var pattern2 = /[a-z]/;
	var pattern3 = /[_]/; // 원하는 특수문자 추가 제거
	var pattern4 = /['']/; // 원하는 특수문자 추가 제거
	var pw_msg = "";

	if (!pattern1.test(id) || !pattern2.test(id) || !pattern3.test(id)
			|| !pattern4.test(id) || pw.length<5||pw.length>12) {
		alert("아이디는 5자이상 12자 이하만 가능합니다 특수문자는 _만 사용가능합니다.");
		return false;
	}
	return true;
}

function isSame() {
	var Uuserid = document.all.Unickname.value;
	var id = document.getElementById("Unickname").value;

	if (document.all.Unickname.value.indexOf(" ") >= 0) {
		alert("닉네임에 공백을 사용할 수 없습니다.")
		document.getElementById('Unickname').value = "";
		return false;
	}

	var pattern3 = /[!@#$%^&*()+=/\?/'<">:,;.~`]/;
	var pw_msg = "";

	if (document.getElementById('Unickname').value != '') {
		if (pattern3.test(id) || id.length < 5) {
			alert("아이디는 5자이상 12자 이하만 가능합니다 특수문자는 _만 사용가능합니다.");
			document.getElementById('same').innerHTML = '잘못된 아이디 유형입니다';
			document.getElementById('same').style.color = 'red';
			document.getElementById('Unickname').value = "";
			return false;
		} else {
			document.getElementById('same').innerHTML = '사용가능합니다.';
			document.getElementById('same').style.color = 'blue';
		}
	}
}
</script>
</head>
<body>
<section>
<form name="userNickCheck" action="nickEditCheck_ok.BT" method="post">
<div style="border:2px solid white;width:205px;height:230px; text-align:center;"> 
<h3 style="text-align:center;">닉네임 중복 검사</h3>
<table>
	<tr>
	<th>닉네임:</th>
	<td>
	<input type="text" name="Unickname" size="8" required="required" id="Unickname" onchange="isSame()">
	<span id="same"></span>
	</td>
	<td><p style="text-align:center;"><input type="submit" value="확인"></p></td>
	</tr>
</table>
</div>
</form>
</section>
</body>