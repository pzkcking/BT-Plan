<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
    <c:if test="${!empty fileNameList}">
		<c:forEach var="file" items="${fileNameList}" varStatus="status">
			<script>
				var contentParentTag=opener.document.getElementById('idContentParent');
				var imgTag=opener.document.createElement("img");
				var brTag=opener.document.createElement("br");
				imgTag.setAttribute("src", "${file}");
				imgTag.setAttribute("width", "300");
				imgTag.setAttribute("height", "300");
				contentParentTag.appendChild(imgTag);
				contentParentTag.appendChild(brTag);
			</script>
		</c:forEach>
	</c:if>
	
<script>
   	window.alert('${msg}');
   	window.self.close();
</script>