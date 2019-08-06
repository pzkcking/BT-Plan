<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta name="description" content="" />
<meta name="keywords" content="" />
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.slidertron-1.3.js"></script>
<link rel='stylesheet' href="css/bootstrap.css">
<link
	href='http://fonts.googleapis.com/css?family=Roboto:400,100,300,700,500,900'
	rel='stylesheet' type='text/css'>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script src="js/skel.min.js"></script>
<script src="js/skel-panels.min.js"></script>
<script src="js/init.js"></script>
<noscript>
	<link rel="stylesheet" href="css/skel-noscript.css" />
	<link rel="stylesheet" href="css/style.css" />
	<link rel="stylesheet" href="css/style-desktop.css" />
</noscript>
</head>

<script type="text/javascript">
	$('.btn-example').click(function() {
		var $href = $(this).attr('href');
		layer_popup($href);
	});
	function layer_popup(el) {

		var $el = $(el); //레이어의 id를 $el 변수에 저장
		var isDim = $el.prev().hasClass('dimBg'); //dimmed 레이어를 감지하기 위한 boolean 변수

		isDim ? $('.dim-layer').fadeIn() : $el.fadeIn();

		var $elWidth = ~~($el.outerWidth()), $elHeight = ~~($el.outerHeight()), docWidth = $(
				document).width(), docHeight = $(document).height();

		// 화면의 중앙에 레이어를 띄운다.
		if ($elHeight < docHeight || $elWidth < docWidth) {
			$el.css({
				marginTop : -$elHeight / 2,
				marginLeft : -$elWidth / 2
			})
		} else {
			$el.css({
				top : 0,
				left : 0
			});
		}

		$el.find('a.btn-layerClose').click(function() {
			isDim ? $('.dim-layer').fadeOut() : $el.fadeOut(); // 닫기 버튼을 클릭하면 레이어가 닫힌다.
			return false;
		});

		$('.layer .dimBg').click(function() {
			$('.dim-layer').fadeOut();
			return false;
		});

	}
</script>
<style>
.nav-counter {
	position: absolute;
	top: -1px;
	right: 1px;
	min-width: 8px;
	height: 20px;
	line-height: 20px;
	margin-top: -11px;
	padding: 0 6px;
	font-weight: normal;
	font-size: small;
	color: white;
	text-align: center;
	text-shadow: 0 1px rgba(0, 0, 0, 0.2);
	background: #e23442;
	border: 1px solid #911f28;
	border-radius: 11px;
	background-image: -webkit-linear-gradient(top, #e8616c, #dd202f);
	background-image: -moz-linear-gradient(top, #e8616c, #dd202f);
	background-image: -o-linear-gradient(top, #e8616c, #dd202f);
	background-image: linear-gradient(to bottom, #e8616c, #dd202f);
	-webkit-box-shadow: inset 0 0 1px 1px rgba(255, 255, 255, 0.1), 0 1px
		rgba(0, 0, 0, 0.12);
	box-shadow: inset 0 0 1px 1px rgba(255, 255, 255, 0.1), 0 1px
		rgba(0, 0, 0, 0.12);
}

* {
	margin: 0;
	padding: 0;
}

.pop-layer .pop-container {
	padding: 20px 25px;
}

.pop-layer p.ctxt {
	color: #666;
	line-height: 25px;
}

.pop-layer .btn-r {
	width: 100%;
	margin: 10px 0 20px;
	padding-top: 10px;
	border-top: 1px solid #DDD;
	text-align: right;
}

.pop-layer {
	display: none;
	position: absolute;
	top: 50%;
	left: 50%;
	width: 600px;
	height: auto;
	background-color: #fff;
	border: 5px solid #3571B5;
	z-index: 10;
}

.dim-layer {
	display: none;
	position: fixed;
	_position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	z-index: 100;
}

.dim-layer .dimBg {
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background: #000;
	opacity: .5;
	filter: alpha(opacity = 50);
}

.dim-layer .pop-layer {
	display: block;
}

a.btn-layerClose {
	display: inline-block;
	height: 25px;
	padding: 0 14px 0;
	border: 1px solid #304a8a;
	background-color: #3f5a9d;
	font-size: 13px;
	color: #fff;
	line-height: 25px;
}

a.btn-layerClose:hover {
	border: 1px solid #091940;
	background-color: #1f326a;
	color: #fff;
}
</style>

<body class="homepage">
	<%@ include file="sidebar.jsp"%>
	<!-- Header -->
	<div id="header">
		<div id="nav-wrapper">
			<!-- Nav -->
			<nav id="nav">
				<ul>
					<li class="active">
					<li><a href="calendarLogin.BT">Planner</a></li>
					<li><a href="webFolder.BT">Team Folder</a></li>
					<li><a href="textList.BT">BBS</a></li>
					<li><a href="imageBbsList.BT">Album</a></li>
					<li><a href="#layer2" class="btn-example">Notice</a></li>
					<c:choose>
						<c:when test="${empty manager }">
							<c:choose>
								<c:when test="${empty sid}">
									<li><a href="userJoin.BT">Sign Up</a></li>
									<li><a href="#" onclick="window.open('login.BT','popup','scrollbars=yes,width=500,height=450,top=200,left=500');">Login</a></li>
								</c:when>
								<c:otherwise>
									<li><input type="button" style="opacity: 0.8" class="btn btn-primary btn-xs" value="로그아웃" onclick="location.href='logout.BT'"> <input
										type="button" style="opacity: 0.8" class="btn btn-primary btn-xs" value="내 정보" onclick="location.href='myPage.BT'"> <input
										type="button" class="btn btn-primary btn-xs" style="opacity: 0.8" value="1:1 문의" onclick="location.href='UserQAMail.BT'"></li>&nbsp;
									<a href="message.BT" class="btn btn-link btn-xs" style="position: relative;">
									<input type="button" class="btn btn-primary btn-xs" style="opacity: 0.8" value="쪽지함">
									<span class="nav-counter">${notice }</span></a>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:otherwise>
							<li><a style="color: peachpuff;"> 관리자 로그인 중... </a>
							<input type="button" style="opacity: 0.8" class="btn btn-primary btn-xs" value="로그아웃" onclick="location.href='logout.BT'">
							<input type="button" style="opacity: 0.8" class="btn btn-primary btn-xs" value="사이트 정보" onclick="location.href='manager.BT'"></li>
						</c:otherwise>
					</c:choose>
					<div class="dim-layer">
						<div class="dimBg"></div>
						<div id="layer2" class="pop-layer">
							<div class="pop-container">
								<div class="pop-conts">
									<!--content //-->
									<p class="ctxt mb20">
									<h4>
									<b>BT-Plan</b>이 새로 오픈하였습니다!
									</h4>
									아직은 초창기라 부족한 것이 많지만 점차 더 나은 모습으로 찾아뵙겠습니다.<br> 여러분의 많은 성원 부탁드립니다.
									<br><br>후원문의: fiving@naver.com
									</p>

									<div class="btn-r">
									<a href="#" class="btn-layerClose">Close</a>
									</div>
									<!--// content-->
								</div>
							</div>
						</div>
					</div>
				</ul>
			</nav>
		</div>
		<div class="container">
			<!-- Logo -->
			<div id="logo">
				<h1>
					<a href="index.BT" style="opacity: 0.7; font-size: 100px;">BT - Plan</a>
				</h1>
				<span class="tag" style="opacity: 0.7">By CodeMonkeys</span>
			</div>
		</div>
	</div>
</body>
</html>