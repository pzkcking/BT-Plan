<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BT_TEAM FOLDER</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="js/httpRequest.js"></script>
<script>
window.onload = function() {
	ajaxListShow();
}
</script>
<script>
/* Ajax */
function ajaxListShow(folderPath, folderName) {
	if (folderPath == undefined) {
		folderPath = '${ folderPath }';
	}
	
	if (folderName == undefined) {
		folderName = '';
	}
	
	var param = 'folderPath=' + folderPath + '&folderName=' + folderName;
	sendRequest('ajaxListShow.BT', param, 'POST', ajaxListShowResult);
}
function ajaxListShowResult() {
	if (XHR.readyState == 4 && XHR.status == 200) {
		var data = XHR.responseText;
		var divTag = document.getElementById('ajaxListShow');
		
		divTag.innerHTML = data;
	}
}
function ajaxGoParent(folderPath) {
	var param = 'folderPath=' + folderPath + '&goParent=' + folderPath;
	sendRequest('ajaxListShow.BT', param, 'POST', ajaxGoParentResult);
}
function ajaxGoParentResult() {
	if (XHR.readyState == 4 && XHR.status == 200) {
		var data = XHR.responseText;
		var divTag = document.getElementById('ajaxListShow');
		
		divTag.innerHTML = data;
	}
}
</script>
<script>
function changeCodeShow() {
	$("#changeCode").toggle();
}
/* 
다중 작업
function multiProcess() {
	$("#allCheckBox").toggle();
	$("input[name='checkbox']").toggle();
}
function allChecked() {
	$("input[name='checkbox']").each(function() {
	    $(this).prop('checked', !$(this).prop('checked'));
	});
} 
*/
function addFile() {
	var divTag=document.getElementById('fileUpload');
	
	var inputTag=document.createElement("input");
	inputTag.setAttribute("type", "file");
	inputTag.setAttribute("class", "btn btn-primary btn-xs");
	inputTag.setAttribute("name", "upload");
	
	divTag.appendChild(inputTag);
}
$(function(){
	$("#same").hide();
	$("#noSame").hide();
	$("#ConfirmCodeName").keyup(function(){
		var newCodeName = $("#newCodeName").val();
		var ConfirmCodeName = $("#ConfirmCodeName").val();
		if (newCodeName != "" || ConfirmCodeName != "") {
			if (newCodeName == ConfirmCodeName) {
				$("#same").show();
				$("#noSame").hide();
				$("#submit").removeAttr("disabled");
			} else {
				$("#same").hide();
				$("#noSame").show();
				$("#submit").attr("disabled", "disabled");
			}
		}
	})
});
</script>
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
<a name="top"></a>
<section style="margin: 0px auto;" style="margin: 0px auto;">
<%-- 팀 웹폴더 정보 --%>
<article>
<fieldset style="width: 300px; align-content: center; border: 1px;" class="container-fluid">
<legend>[ 기본정보 ]</legend>
<ul>
<li><label>총 용량: </label>
<meter value="${ totalStorage }" min="0" max="${ totalStorage }"></meter>
${ totalStorage }MB</li>
<li><label>사용 용량: </label>
<meter value="${ usedStorage }" min="0" max="${ totalStorage }"></meter>
${ usedStorage }MB</li>
<li><label>남은 용량: </label>
<meter value="${ emptyStorage }" min="0" max="${ totalStorage }"></meter>
${ emptyStorage }MB</li>
</ul>
</fieldset>
</article>
<article>
<%-- 폴더 생성 --%>
<fieldset style="width: 350px; align-content: center;" class="container-fluid">
<legend>[ 폴더 생성 ]</legend>
<div>
<form name="makeFolder" action="makeFolder.BT">
<input type="text" name="folderName"><br>
<input type="submit" class="btn btn-primary btn-xs" value="폴더 생성">
</form>
</div>
</fieldset>
<br>
<%-- 파일 업로드 --%>
<fieldset style="width: 250px; align-content: center;" class="container-fluid">
<legend>[ 파일 업로드 ]</legend>
<div>
<form name="fileUpload" action="fileUpload.BT" method="post" enctype="multipart/form-data">
<div id="fileUpload" style="align: center;">
<input type="file" class="btn btn-primary btn-xs" name="upload" >
</div>
<br>
<input type="button" class="btn btn-primary btn-xs" value="파일 추가" onclick="addFile()">
<input type="submit" class="btn btn-primary btn-xs" value="파일 업로드">
</form>
</div>
</fieldset><br>
</article>
<%-- 코드 변경 --%>
<article id="changeCode" style="display:none">
<fieldset style="width: 1000px; align-content: center;" class="container-fluid">
<legend>[ CODE 변경]</legend>
<form name="changeCode" action="changeCode.BT">
<input type="text" name="rootName" value="${ rootFolder }" readonly="readonly"><br>
<input type="password" name="codeName" placeholder="Current Code"><br>
<input type="password" name="newCodeName" id="newCodeName" placeholder="New Code"><br>
<input type="password" name="ConfirmCodeName" id="ConfirmCodeName" placeholder="Confirm Code">
<div id="same" style="color:blue">변경할 비밀번호가 일치합니다.</div>
<div id="noSame" style="color:red">변경할 비밀번호가 일치하지 않습니다.</div><br>
<input type="submit" class="btn btn-primary" value="변경">
<input type="button" class="btn btn-primary" value="취소" onclick="changeCodeShow()">
</form>
</fieldset>
</article>
<article>
<div style="text-align: center;">
<input type="button" class="btn btn-primary btn-xs" value="코드 변경" onclick="changeCodeShow()"></div>
<fieldset style="width: 900px; align-content: center;" class="container-fluid">
<br>
<legend>[ ${ rootFolder } ]</legend>
<!-- <div>
<input type="button" class="btn btn-primary btn-xs" value="다중작업" onclick="multiProcess()">
<input id="allCheckBox" type="button" class="btn btn-primary btn-xs" value="전체선택" style="display:none" onclick="allChecked()">
</div> -->
<%-- 파일리스트 --%>
<div id="ajaxListShow">
<%-- Ajax 삽입 위치 --%>
</div>
<a href="#top">TOP</a>
</fieldset>
</article>
</section>
<%@ include file="../footer.jsp"%>
</body>
</html>