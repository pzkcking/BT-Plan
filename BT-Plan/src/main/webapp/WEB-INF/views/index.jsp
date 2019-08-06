<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
	<title>BT-Plan</title>
</head>
<%@ include file="header.jsp" %>
	<body class="homepage">
		<!-- Featured -->
		<div id="featured">
			<div class="container">
				<header>
					<h2>Welcome to BT - Plan</h2>
				</header>
				<hr />
				<div class="row">
					<section class="4u">
						<span class="pennant"><span class="fa fa-briefcase"></span></span>
						<h3>Team Project Plan</h3>
						<p>팀 프로젝트 계획을 만드십시오. BT-Plan이 제공해 주는 멋진 기능을 이용해보세요.</p>
						<a href="calendarLogin.BT" class="button button-style1">Go to Planer</a>
					</section>
					<section class="4u">
						<span class="pennant"><span class="fa fa-lock"></span></span>
						<h3>Team WebFolder</h3>
						<p>팀의 공유공간을 제공하고 있습니다. 개인 또는 팀원들과 공유하세요. </p>
						<a href="webFolder.BT" class="button button-style1">Go to WebFolder</a>
					</section>
					<section class="4u">
						<span class="pennant"><span class="fa fa-globe"></span></span>
						<h3>User BBS</h3>
						<p>다른나라로 출장을 가 이용자간 정보를 공유하거나 멋진 사진을 올릴 수 있습니다. </p>
						<a href="textList.BT" class="button button-style1">Go to BBS</a>
					</section>
				</div>
			</div>
		</div>
		
		<!-- Main -->
		<div id="main">
			<div id="content" class="container">

				<div class="row">
					<section class="6u">
						<a href="https://www.naver.com" class="image full"><img src="images/pic01.jpg" alt=""></a>
						<header>
							<h2>해외 출장 관련 정보</h2>
						</header>
						<p>비자 발급 방법, 주요 국가 정보, 대사관 및 영사관의 정보 등<br> 해외출장시 숙지해야할 정보를 제공해드립니다</p>
					</section>				
					<section class="6u">
						<a href="https://www.google.com" class="image full"><img src="images/pic02.jpg" height="270" alt=""></a>
						<header>
							<h2>가볼만한 곳</h2>
						</header>
						<p>출장지에서 가볼한 명소, 맛집 등 일을 끝내고 시간이 남는다면 <br>갈 수 있는 곳을 추천해 드립니다</p>
					</section>				
				</div>

				<div class="row">
				<section class="6u">
						<a href="webFolder.BT" class="image full"><img src="images/pic03.jpg" alt=""></a>
						<header>
							<h2>자료실</h2>
						</header>
						<p>회원 간 자료 공유 또는 BT-Plan에서 제공해드리는 <br>각종 자료들을 받아볼 수 있습니다. .</p>
					</section>				
					<section class="6u">
						<a href="imageBbsList.BT" class="image full"><img src="images/pic04.jpg" height="320" alt=""></a>
						<header>
							<h2>갤러리</h2>
						</header>
						<p>앨범 게시판에 올린 사진들을 모아 봤습니다. <br>멋진 사진들을 한눈에 감상할 수 있습니다.</p>
					</section>				
				</div>
			</div>
		</div> 
	<%@ include file="footer.jsp" %>
	</body>
</html>