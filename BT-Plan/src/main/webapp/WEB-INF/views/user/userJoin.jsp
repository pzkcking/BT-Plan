<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>BT-Plan</title>
<meta charset="UTF-8">
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta name="description" content="" />
<meta name="keywords" content="" />
<link href='http://fonts.googleapis.com/css?family=Roboto:400,100,300,700,500,900' rel='stylesheet' type='text/css'>
<link href="style/userStyle.css" rel="stylesheet" type="text/css"/>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="js/skel.min.js"></script>
<script src="js/skel-panels.min.js"></script>
<script src="js/init.js"></script>
<link rel="stylesheet" href="css/skel-noscript.css" />
<link rel="stylesheet" href="css/style.css" />
<link rel="stylesheet" href="css/style-desktop.css" />
</head>
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
function check() {
	var req = document.agreeCheck.agree.checked;
	var num = 0;
	if (req == true) {
		num = 1;
	}
	if (num == 1) {
		location.href = "userJoinPage.BT";
	} else {
		alert("개인정보 약관에 동의하셔야 합니다.");
	}
}
function nochkeck() {
	alert("동의하지 않으면 가입하실 수 없습니다");
	location.href = "index.BT";
}
</script>
</head>
<style>
.green-border-focus .form-control:focus {
	border: 1px solid #8bc34a;
	box-shadow: 0 0 0 0.2rem rgba(139, 195, 74, .25);
}
</style>
<body>
<%@ include file="../header.jsp" %>
<div id="featured" >
<div id="content" class="container" >
<b>BT-Plan 약관동의</b>
   <br>
   <div class="form-group green-border-focus">
   <textarea rows="20" cols="150" style="resize: none;" class="form-control" readonly="readonly">
         첫째, 수집하는 개인정보의 항목첫째, 회사는 회원가입, 원활한 고객상담, 각종 서비스의 제공을 위해 최초 회원가입 당시 아래와 같은 최소한의 개인정보를 필수항목으로 수집하고 있습니다.
     
	   회원가입
		- 이름, 생년월일, 성별, 아이디, 비밀번호, 별명, 연락처(메일주소, 휴대폰 번호), 
		- 이름, 생년월일, 성별, 법정대리인 정보, 아이디, 비밀번호, 연락처 (메일주소, 휴대폰 번호 중 선택)
		
	 둘째, 서비스 이용과정이나 사업처리 과정에서 아래와 같은 정보들이 자동으로 생성되어 수집될 수 있습니다.
	 
		- IP Address, 쿠키, 방문 일시, 서비스 이용 기록, 불량 이용 기록
		
	 셋째, 홈페이지 아이디를 이용한 부가 서비스 및 맞춤식 서비스 이용 등 과정에서 해당 서비스의 이용자에 한해서만 개인정보 추가 수집이 발생할 수 있으며, 이러한 경우 별도의 동의를 받습니다. 
	 
	 넷째, 성인컨텐츠, 유료/게임 등 일부 서비스 이용시 관련 법률 준수를 위해 본인인증이 필요한 경우, 아래와 같은 정보들이 수집될 수 있습니다. 
	 
		- 이름, 생년월일, 성별, 중복가입확인정보(DI), 암호화된 동일인 식별정보(CI), 휴대폰 번호(선택), 아이핀 번호(아이핀 이용시), 내/외국인 정보
		
	 다섯째, 유료 서비스 이용 과정에서 아래와 같은 결제 정보들이 수집될 수 있습니다.
	  
		- 신용카드 결제시 : 카드사명, 카드번호 등
		- 휴대전화 결제시 : 이동전화번호, 통신사, 결제승인번호 등
		- 계좌 이체시 : 은행명, 계좌번호 등
		- 상품권 이용시 : 상품권 번호
		
	 여섯째, 개인정보 수집방법회사는 다음과 같은 방법으로 개인정보를 수집합니다. 
	 
		- 홈페이지, 서면양식, 팩스, 전화, 상담 게시판, 이메일, 이벤트 응모, 배송요청
		- 협력회사로부터의 제공 
		- 생성정보 수집 툴을 통한 수집
   </textarea>
   </div>
   <br><br>
   <form name="agreeCheck">
   <input type="checkbox" name="agree"> 개인정보 수집 및 이용에 동의합니다. 
   <br>
   <hr>
   <input type="button" class="btn btn-primary btn-lg" value="동의" onclick="check()">&nbsp;&nbsp;&nbsp;
   <input type="button" class="btn btn-primary btn-lg" value="동의 안함" onclick="nochkeck()">   
</form>
</div>
</div>
<%@include file="../footer.jsp" %>
</body>
</html>