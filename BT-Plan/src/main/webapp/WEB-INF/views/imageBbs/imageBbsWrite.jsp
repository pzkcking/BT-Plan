<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		
		<script>
			function OnUploadImageFileEvent()
			{
				window.open('imageBbsUploadImage.BT', 'ImageUpload', 'width=550, height=250');
			}
			
			function OnLoadEvent()
			{
				window.onpageshow=function(event)
				{
					//in case of clicking navigator-button.(forward.!)
					if(event.persisted ||
					  (window.performance &&
					  window.performance.navigation.type==2))
					{
						window.alert('navigator-forward.!');
					}
					//in case of loading new-page.!
					else if(window.performance &&
							window.performance.navigation.type==1)
					{
						window.alert('reload.!');
					}
				}
				
				//in case of clicking navigator-button.(backward.!)
				window.onbeforeunload=function(event)
				{
					window.alert('navigator-backward.!');
				}
			}
			
			function OnCheckBeforeWritingOK()
			{
				if(document.bbsWrite.ititle.value=='')
				{
					window.alert('컨텐츠 이름을 기입해 주세요.!');
					event.returnValue=false;
				}
			}
		</script>
	</head>
	<style>
fieldset {
	position: relative;
	text-align: center;
	margin: 0px auto;
}
</style>
	<body onload="OnLoadEvent()">
<%@ include file="../header.jsp"%>
	<h1 style="text-align: center; opacity: 0.8">앨범게시판</h1>
<section style="margin: 0px auto;">
	<fieldset style="width: 700px; align-content: center;" class="container-fluid">	
				<form name="bbsWrite" action="imageBbsWriteOK.BT">
					<fieldset>
						
						<table style="margin-left: auto; margin-right: auto; border: 1px;
				text-align: left; width: 500px" class="table table-striped table-hover">
							<thead>
								<tr>
									<th>작성자</th>
									<td><input type="text" name="inickname" value="${userID}" readonly></td> 
								</tr>
							
								<tr>
									<th>제목</th>
									<td><input type="text" name="ititle">&nbsp;
									<input type="button" class="btn btn-primary btn-xs" value="사진올리기" onclick="OnUploadImageFileEvent()"> </td>
								</tr>
								<tr>
									<td id="idContentParent" colspan="4"></td>	
								</tr>
								<br>
								<tr>
									<td colspan="4" align="center"> 
										<input type="submit" class="btn btn-primary btn-xs" value="작성 완료" onclick="OnCheckBeforeWritingOK()">
										<input type="reset" class="btn btn-primary btn-xs" name="초기화">
									</td>	
								</tr>
							</thead>
						</table>
						
					
				</form>
				</fieldset>
		</section>
		<br>
		<br>
	<%@ include file="../footer.jsp"%>
	</body>
</html>