<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>문의 메일 보내기</title>
</head>
<style>




 table{
 
  border: 1px;
  text-align: center;
 
 
 }

fieldset {
 
 		position:relative;
		text-align: center;
		margin: 0px auto;
 		
 }


</style>
<body>
<%@ include file="../header.jsp" %>

<div id="main">
		<section style="margin: 0px auto;">
		
<fieldset style="width: 500px; align-content: center;" class="container-fluid">
<h3 style="text-align: cener;">* 문의 메일 보내기 *</h3>
<form name='mailForm' method='post' action="mailSend.BT">
<input type="hidden" value="${Uuserid }" name="Uuserid" >
<table border="0" align="center" style="margin-left: auto; margin-right: auto; border: 1px; text-align: left;" class="table table-striped">

<tr>
  <th bgcolor='silver'>To</th>
  <td><input type="text" readonly size="50" name="to" value="BT-Plan"></td>
</tr>
<tr>
  <th bgcolor="silver">From</th>
  <td><input type="text" name="from" size="50" value="${Uemail }"></td>
</tr>
<tr>
  <th bgcolor='silver'>제 목</th>
  <td><input type="text" name="subject" size="50"></td>
</tr>
<tr>
  <th bgcolor='silver'>편지 내용</th>
  <td><textarea name="msgText" rows="15" cols="60"></textarea></td>
</tr>

</table> 
<div  align="center">
    <input type="submit" class="btn btn-primary btn" value="보내기">
    <input type="reset"  class="btn btn-primary btn" value="재입력">
    <input type="button" class="btn btn-primary" value="이전 페이지" onclick="location.href='index.BT'">
</div>
</form>

</fieldset>
</section>
</div>
<%@ include file="../footer.jsp" %>
</body>
</html>

