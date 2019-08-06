<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>
	window.alert('${msg}');
	opener.location.replace='index.BT';
	opener.location.reload();
	self.close();
</script>