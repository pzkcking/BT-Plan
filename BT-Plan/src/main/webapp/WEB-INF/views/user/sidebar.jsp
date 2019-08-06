<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<title>http://www.blueb.co.kr</title>
<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="js/jquery-contained-sticky-scroll.js"></script>

<script type="text/javascript">
jQuery(document).ready(function(){
	jQuery('#sidebar').containedStickyScroll();
});

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
<script>
!function(d,s,id){
	var js,fjs=d.getElementsByTagName(s)[0];
	if(!d.getElementById(id)){
		js=d.createElement(s);
		js.id=id;
		js.src='https://weatherwidget.io/js/widget.min.js';
		fjs.parentNode.insertBefore(js,fjs);
		}
	}
	(document,'script','weatherwidget-io-js');
</script>
<style type="text/css">
.grid_4{width:300px;display:inline;float:left;margin-left:10px;margin-right:10px;padding:10px;color:#fff;opacity: 0.85}

/*옵션 테이블 스타일*/

table.sidebart {font-size:11px;font-family:tahoma}
table.sidebart td {border-bottom:1px #eee solid;color:#555;padding:4px;}
table.sidebart th {border:2px #eee solid;color:#fff;background-color:#000;padding:4px;}
</style>
<script>
!function(d,s,id){
	var js,fjs=d.getElementsByTagName(s)[0];
	
		if(!d.getElementById(id)){
			
			js=d.createElement(s);js.id=id;js.src='https://weatherwidget.io/js/widget.min.js';
			
			fjs.parentNode.insertBefore(js,fjs);
			}
		}
		
		(document,'script','weatherwidget-io-js');
</script>
</head>
<body>

	<div id="sidebar" class="grid_4" style="position: relative; z-index: 1000">
	<a href="javascript:doDisplay();"><input type="button" style="opacity: 0.8;" class="btn btn-primary btn-xs" value="실시간 환율"></a>
	<div id="myDIV" style="display: none; position: relative; z-index: 100">
	
	<iframe src="http://fx.kebhana.com/fxportal/jsp/RS/DEPLOY_EXRATE/fxrate_all.html" width="180" height="183" frameborder="1"></iframe>
	</div><br>
	
	<input type="button" value="세계 날씨" class="btn btn-primary btn-xs" onclick="window.open('worldweather.BT','popup','scrollbars=yes,width=600,height=300,top=200,left=500');">
	<br>
<input type="button" value="세계 시간" class="btn btn-primary btn-xs" onclick="window.open('worldtime.BT','popup','scrollbars=yes,width=430,height=500,top=200,left=500');">
	
	</div>
	
</body>
</html>