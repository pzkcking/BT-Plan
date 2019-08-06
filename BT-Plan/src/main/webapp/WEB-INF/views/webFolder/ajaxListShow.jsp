<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<table style="margin-left: auto; margin-right: auto; border: 1px;
				text-align: left; width: 800px" class="table table-striped table-hover table-bordered">
<%-- 상위 폴더로 --%>
<c:if test="${ !empty upPath }">
	<tr>
	<td colspan="4" align="left"><a onclick="ajaxGoParent('${ folderPath }')">&uarr;&uarr;&uarr;&uarr;&uarr;</a></td>
	</tr>
</c:if>
<%-- 파일 리스트 유무 확인 --%>
<c:if test="${ empty fileList }">
	<tr><td>등록된 파일이 없습니다.</td></tr>
</c:if>
<%-- 폴더 리스트 --%>
<c:forEach var="file" items="${ fileList }">
	<c:if test="${ !file.isFile() }">
		<tr>
		<td width="51"><input name="checkbox" type="checkbox" style="display:none"></td>
		<%-- 폴더 진입 --%>
		<td colspan="2">[DIR] <a onclick="ajaxListShow('${ folderPath }', '${ file.name }')">${ file.name }</a></td>
		<%-- 폴더 삭제 --%>
		<c:url var="deleteFile" value="delete.BT">
			<c:param name="folderPath" value="${ folderPath }"/>
			<c:param name="folderName" value="${ file.name }"/>
		</c:url>
		<td width="139"><a href="${ deleteFile }" onClick="if(!confirm('삭제 하시겠습니까?')){return false;}">삭제</a></td>
		</tr>
	</c:if>
</c:forEach>
</table>

<%-- 파일 리스트 --%>
<table style="margin-left: auto; margin-right: auto; border: 1px;
				text-align: left; width: 800px" class="table table-striped table-hover table-bordered" id="fileList">
<c:forEach var="file" items="${ fileList }">
	<c:if test="${ file.isFile() }">
		<tr>
		<td><input name="checkbox" type="checkbox" style="display:none"></td>
		<%-- 파일 다운로드 --%>
		<c:url var="downUrl" value="download.BT">
			<c:param name="folderPath" value="${ folderPath }"/>
			<c:param name="fileName" value="${ file.name }"/>
		</c:url>
		<td colspan="2">[FILE] <a href="${ downUrl }">${ file.name }</a></td>
		<%-- 파일 삭제 --%>
		<c:url var="deleteFile" value="delete.BT">
			<c:param name="folderPath" value="${ folderPath }"/>
			<c:param name="fileName" value="${ file.name }"/>
		</c:url>
		<td><a href="${ deleteFile }" onClick="if(!confirm('삭제 하시겠습니까?')){return false;}">삭제</a></td>
		</tr>
	</c:if>
</c:forEach>
</table>
