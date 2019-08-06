<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script>
// 새로고침 금지
function noRefresh() {
	if (event.keyCode == 116) {
		alert("새로고침을 할 수 없습니다.");
		event.keyCode = 2;
		return false;
	} else if (event.ctrlKey && (event.keyCode == 78 || event.keyCode == 82)) {
		return false;
	}
}
document.onkeydown = noRefresh;
</script>
<script>
	window.alert('${msg}');
	location.href = 'index.BT';
	opener.location.reload();
	self.close();
</script>
