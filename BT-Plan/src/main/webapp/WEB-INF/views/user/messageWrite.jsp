<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel='stylesheet' href="css/bootstrap.css">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
</head>
<c:set var="Mreceiver" value="${Mreceiver }"></c:set>

<body>

	<section style="margin: 0px auto;">
	<article>
			<fieldset class="container-fluid">
				<legend>쪽지 보내기</legend>
						<form name="messageWrite" action="messageWrite_ok.BT" method="post">
					<table border="1" class="table table-striped table-hover">
						<tr>
							<td>받는 사람</td>
							<td><input type="text" name="Mreceiver"></td>		
							<td>제목</td>
							<td><input type="text" name="Msubject"></td>
							<td>보내는 사람</td>
							<td><input type="text" name="Msender" value="${Mreceiver }" readonly="readonly"></td>	
							
						</tr>
						<tr>
							<td>내용</td>
							<td colspan="5"><textarea rows="10" cols="100" style="resize: none;" name="Mcontent" maxlength="60"></textarea>	
						<tr>		
						<tr>
							<td colspan="6" style="text-align: center;">
								<input type="submit" value="보내기" class="btn btn-primary btn-xs">
						</tr>
					</table>
							
				</form>
				
			</fieldset>
		</article>
		</section>

</body>
</html>