<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<c:if test="${empty sid }">
	
		<script>
		
		window.alert('로그인해 주세요!');
		location.replace('index2.BT');
		
		</script>
	
	</c:if>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
<c:import url="../header.jsp"></c:import>

	<section style="margin: 0px auto;">
	<article>
			<fieldset style="width: 500px; align-content: center;" class="container-fluid">
				<legend>쪽지함</legend>
						<form name="message" action="messageRead.BT">
					<table border="1" class="table table-striped table-hover">
						<thead>
							<tr>
								<th style="text-align: center;">보낸 사람</th>
								<th style="text-align: center;">제목</th>
								<th style="text-align: center;">보낸 날짜</th>
								<th style="text-align: center;">답장</th>
								<th style="text-align: center;">삭제</th>
							</tr>
						</thead>
						<tbody>
								<c:set var="arr" value="${lists }"></c:set>
								<c:if test="${empty arr }">
								<tr>
									<td colspan="5" align="center" >
									
										쪽지가 없습니다.
									</td>
								</tr>
								</c:if>
								<c:forEach var="dto" items="${arr }">			
								<tr>					
									<td style="text-align: center;">${dto.msender }</td>				
									<td><a onclick="window.open('messageRead.BT?Mindex=${dto.mindex}','popup','scrollbars=yes,width=860,height=360,top=200,left=500')">${dto.msubject }</a></td>			
									<td style="text-align: center;">${dto.mwritedate }</td>	
									
									
									<td><a onclick="window.open('messageAnswer.BT?Msender=${dto.msender}&Mreceiver=${dto.mreceiver }','popup','scrollbars=yes,width=900,height=400,top=200,left=500')"><input type="button" class="btn btn-primary btn-xs" value="답장"></a></td>				
									<td><a href="messageDelete.BT?Mindex=${dto.mindex }"><input type="button" class="btn btn-primary btn-xs" value="삭제"></a></td>				
								</tr>
								
										
								</c:forEach>	
								
								<tr>
								<td colspan="5"style="text-align: center;">
								
								<a onclick="window.open('messageWrite.BT?Msender=${Uuserid}','popup','scrollbars=yes,width=900,height=400,top=200,left=300')"><input type="button" class="btn btn-primary btn-xs" value="쪽지보내기"></a>
								
								<a onclick="location.href='index2.BT'"><input type="button" class="btn btn-primary btn-xs" value="메인페이지"></a>
								</td>
								</tr>
																				
						</tbody>
			</table>

				</form>
			</fieldset>
		</article>
		</section>
<c:import url="../footer.jsp"></c:import>
</body>
</html>