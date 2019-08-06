<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<script>
			function getCurrentImageFileSelectorCount()
			{
				var uploadTableTag=document.getElementById('idUploadTable');
				var childList=uploadTableTag.childNodes;
				
				var count=0;
				for(var i=0; i < childList.length; i++)
				{
					console.log(childList.item(i).nodeName);
					if(childList.item(i).nodeName=='TR'){count++;}
				}
				return count;
			}
			
			function OnAddImageFileEvent()
			{
				var totalTrTagCount=getCurrentImageFileSelectorCount();
				console.log(totalTrTagCount);
				
				var uploadTableTag=document.getElementById('idUploadTable');
				
				var trTag=document.createElement("tr");
				var tdTag=document.createElement("td");
				var textTag=document.createTextNode("이미지 파일 : ")
				var inputTag=document.createElement("input");
				inputTag.setAttribute("type", "file");
				inputTag.setAttribute("name", "upload");
				
				trTag.appendChild(tdTag);
				tdTag.appendChild(textTag);
				tdTag.appendChild(inputTag);
				
				uploadTableTag.appendChild(trTag);
			}
		</script>
	</head>
	<body>
		<form name="bbsUploadImgFileForm" 
					method="post" 
					enctype="multipart/form-data"
					action="imageBbsUploadImageOK.BT">
			<fieldset>
				<legend>파일 올리기</legend>
				<table id="idUploadTable">
					<tr>
						<td>이미지 파일 : <input type="file" class="btn btn-primary" name="upload"> </td>
					</tr>
				</table>
			</fieldset>
			<input type="button" class="btn btn-primary" value="이미지 추가" onclick="OnAddImageFileEvent()">
			<input type="submit" class="btn btn-primary" value="올리기">
		</form>
	</body>
</html>