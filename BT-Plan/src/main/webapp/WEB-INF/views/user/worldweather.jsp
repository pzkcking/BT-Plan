<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<!-- 애니매이션 담당 JQUERY -->
 <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script> 
<script type="text/javascript" src="js/httpRequest.js"></script> 

 <!-- 부트스트랩 JS  -->
 <link rel='stylesheet' href="css/bootstrap.css">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>BT-Plan</title>

</head>
<script type="text/javascript">

$(function(){
	  $(".zeta-menu li").hover(function(){
	    $('ul:first',this).show();
	  }, function(){
	    $('ul:first',this).hide();
	  });
	  $(".zeta-menu>li:has(ul)>a").each( function() {
	    $(this).html( $(this).html()+' &or;' );
	  });
	  $(".zeta-menu ul li:has(ul)")
	    .find("a:first")
	    .append("<p style='float:right;margin:-3px'>&#9656;</p>");
	});
</script>
<script type="text/javascript">
var bDisplay = true;
function doDisplay(){
    var con = document.getElementById("myDIV");
    if(con.style.display=='none'){
        con.style.display = 'block';
    }else{
        con.style.display = 'none';
    }
}
</script>
<script type="text/javascript">
var bDisplay = true;
function doDisplay1(){
    var con = document.getElementById("myDIV1");
    if(con.style.display=='none'){
        con.style.display = 'block';
    }else{
        con.style.display = 'none';
    }
}
</script>
<script type="text/javascript">
var bDisplay = true;
function doDisplay2(){
    var con = document.getElementById("myDIV2");
    if(con.style.display=='none'){
        con.style.display = 'block';
    }else{
        con.style.display = 'none';
    }
}
</script>
<script type="text/javascript">
var bDisplay = true;
function doDisplay3(){
    var con = document.getElementById("myDIV3");
    if(con.style.display=='none'){
        con.style.display = 'block';
    }else{
        con.style.display = 'none';
    }
}
</script>
<script>
!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0];if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src='https://weatherwidget.io/js/widget.min.js';fjs.parentNode.insertBefore(js,fjs);}}(document,'script','weatherwidget-io-js');
</script>
<style>
body { margin: 0; }
.zeta-menu-bar {
  background: grey;
  display: inline-block;
  width: 100%;
}
.zeta-menu { margin: 0; padding: 0; }
.zeta-menu li {
  float: left;
  list-style:none;
  position: relative;
}
.zeta-menu li:hover { background: white; }
.zeta-menu li:hover>a { color: lightgrey; }
.zeta-menu a {
  color: white;
  display: block;
  padding: 10px 20px;
  text-decoration: none;
}
.zeta-menu ul {
  background: #eee;
  border: 1px solid lightgrey;
  display: none;
  padding: 0;
  position: absolute;
  left: 0;
  top: 100%;
  width: 180px;
}
.zeta-menu ul li { float: none; }
.zeta-menu ul li:hover { background: #ddd; }
.zeta-menu ul li:hover a { color: grey; }
.zeta-menu ul a { color: black; }
.zeta-menu ul ul { left: 100%; top: 0; }
.zeta-menu ul ul li {float:left; margin-right:10px;}
</style>
</head>
<body>
<div class='zeta-menu-bar'>
  <ul class="zeta-menu" style="position: relative; z-index: 2;">
    <li><a href="#">BT-Plan</a></li>
   
    <li><a href="https://www.accuweather.com/ko/world-weather" target="_blank">Weather</a></li>
  </ul>
</div>
 <!-- 로긴폼 -->

 <div class="container" style="position: relative; z-index: 1;">

  <div class="col-lg-4"></div>

  <div class="col-lg-4">

	<h3 style="text-align: center;"> 세계 날씨 </h3>
	<br>
	
</div>
	<a href="javascript:doDisplay();">·Taipei</a>
	<a href="javascript:doDisplay1();">·Tokyo</a>
	<a href="javascript:doDisplay2();">·London</a>
	<a href="javascript:doDisplay3();">·Newyork</a>
	

<div id="myDIV" style="display: none;">
    <a class="weatherwidget-io" href="https://forecast7.com/en/25d03121d57/taipei/" data-label_1="TAIPEI" data-label_2="WEATHER" data-icons="Climacons Animated" data-days="3" data-theme="original" >TAIPEI WEATHER</a><br>
</div>
<div id="myDIV1" style="display: none;">
    <a class="weatherwidget-io" href="https://forecast7.com/en/35d69139d69/tokyo/" data-label_1="TOKYO" data-label_2="WEATHER" data-icons="Climacons Animated" data-days="3" data-theme="original" >TOKYO WEATHER</a><br>
</div>
<div id="myDIV2" style="display: none;">
   <a class="weatherwidget-io" href="https://forecast7.com/en/51d51n0d13/london/" data-label_1="LONDON" data-label_2="WEATHER" data-icons="Climacons Animated" data-days="3" data-theme="original" >LONDON WEATHER</a><br>
</div>
<div id="myDIV3" style="display: none;">
    <a class="weatherwidget-io" href="https://forecast7.com/en/40d71n74d01/new-york/" data-label_1="NEW YORK" data-label_2="WEATHER" data-icons="Climacons Animated" data-days="3" data-theme="original" >NEW YORK WEATHER</a><br>
</div>

	
	
</div>
</body>