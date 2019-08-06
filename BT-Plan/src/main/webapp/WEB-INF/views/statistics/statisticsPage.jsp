<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://d3js.org/d3.v4.min.js"></script>
<Style>
.pie {
	margin: 20px;
}

.pie text {
	font-family: "Verdana";
	fill: #888;
}

.pie.name-text {
	font-size: 1em;
}

.pie.value-text {
	font-size: 3em;
}
</Style>
</head>
<body>
<%@ include file="../header.jsp" %>
	<h1>Statistics Page</h1>
	<%-- 게시판 별 통계 작동 안됨
	<fieldset>
		<legend>접속수</legend>

		<table>
			<c:forEach var="dto" items="${connectionCount }">
				<tr>
					<th>누적 총 접속 수:</th>
					<td>${dto.mtotalconnection }</td>
				</tr>
				<tr>
					<th>플래너 접속 수:</th>
					<td>${dto.mplannerconnection}</td>
				</tr>
				<tr>
					<th>웹 폴더 접속 수:</th>
					<td>${dto.mwebfolderconnection }</td>
				</tr>
				<tr>
					<th>텍스트 게시판 접속 수:</th>
					<td>${dto.mtextconnection }</td>
				</tr>
				<tr>
					<th>이미지 게시판 접속 수:</th>
					<td>${dto.mimageconnection}</td>
				</tr>
			</c:forEach>
		</table>
	</fieldset>
	<hr>
	 --%>
	<fieldset>
		<legend>사이트 통계</legend>
		<table>
			<tr>
				<th>총 회원수:</th>
				<td>${lists2[0].MEMBERCOUNT}</td>
			</tr>
			<tr>
				<th>밴처리된 총회원수:</th>
				<td>${lists3[0].BANNEDCOUNT}</td>
			</tr>
			<tr>
				<th>나이 및 성별:</th>
				<td>10대:${age_10} &nbsp&nbsp 20대:${age_20} &nbsp&nbsp
					30대:${age_30} &nbsp&nbsp 40대:${age_40} &nbsp&nbsp 50대:${age_50}
					&nbsp&nbsp 60대:${age_60} &nbsp&nbsp 기타:${age_etc}<br> 남자 :
					${male } &nbsp&nbsp 여자 : ${female }
				</td>

			</tr>
			<tr>
				<th>총 게시물 수:</th>
				<td>${textCount+imageCount}</td>
			</tr>

		</table>
	</fieldset>
	<span id="chart"></span>
	<script>
	var data = [
		  {name: "일반회원", value: ${lists2[0].MEMBERCOUNT-lists3[0].BANNEDCOUNT}},
		  {name: "밴처리된 회원", value: ${lists3[0].BANNEDCOUNT}},
		];
		var text = "총 회원수:${lists2[0].MEMBERCOUNT}";

		var width = 360;
		var height = 360;
		var thickness = 60;
		var duration = 750;

		var radius = Math.min(width, height) / 2;
		var color = d3.scaleOrdinal(d3.schemeCategory10);

		var svg = d3.select("#chart")
		.append('svg')
		.attr('class', 'pie')
		.attr('width', width)
		.attr('height', height);

		var g = svg.append('g')
		.attr('transform', 'translate(' + (width/2) + ',' + (height/2) + ')');

		var arc = d3.arc()
		.innerRadius(radius - thickness)
		.outerRadius(radius);

		var pie = d3.pie()
		.value(function(d) { return d.value; })
		.sort(null);

		var path = g.selectAll('path')
		.data(pie(data))
		.enter()
		.append("g")
		.on("mouseover", function(d) {
		      let g = d3.select(this)
		        .style("cursor", "pointer")
		        .style("fill", "black")
		        .append("g")
		        .attr("class", "text-group");
		 
		      g.append("text")
		        .attr("class", "name-text")
		        .text(d.data.name)
		        .attr('text-anchor', 'middle')
		        .attr('dy', '-1.2em');
		  
		      g.append("text")
		        .attr("class", "value-text")
		        .text(d.data.value)
		        .attr('text-anchor', 'middle')
		        .attr('dy', '.6em');
		    })
		  .on("mouseout", function(d) {
		      d3.select(this)
		        .style("cursor", "none")  
		        .style("fill", color(this._current))
		        .select(".text-group").remove();
		    })
		  .append('path')
		  .attr('d', arc)
		  .attr('fill', (d,i) => color(i))
		  .on("mouseover", function(d) {
		      d3.select(this)     
		        .style("cursor", "pointer")
		        .style("fill", "black");
		    })
		  .on("mouseout", function(d) {
		      d3.select(this)
		        .style("cursor", "none")  
		        .style("fill", color(this._current));
		    })
		  .each(function(d, i) { this._current = i; });


		g.append('text')
		  .attr('text-anchor', 'middle')
		  .attr('dy', '2em')
		  .text(text);
	</script>
	<span class="two-graph"></span>
	<script>
var w = 400, h = 400;
var dataName = ["텍스트", "이미지"];
var graphData = [${textCount}, ${imageCount}];
var colorData = ["#FFFF00", "#0000FF"];
var pie = d3.pie();
var arc = d3.arc().innerRadius(60).outerRadius(150);
 
var svg = d3.select(".two-graph")
    .append("svg")
    .attr("width", w)
    .attr("height", h)
    .attr("id", "graphWrap");
 
var g = svg.selectAll(".pie")
    .data(pie(graphData))
    .enter()
    .append("g")
    .attr("class", "pie")
    .attr("transform","translate("+w/2+","+h/2+")");
 
g.append("path")
    .style("fill", function(d, i) {
        return colorData[i];
    }) 
    .transition()
    .duration(400)
    .delay(function(d, i) { 
        return i * 400;
    })
    .attrTween("d", function(d, i) { 
        var interpolate = d3.interpolate(
            {startAngle : d.startAngle, endAngle : d.startAngle}, 
            {startAngle : d.startAngle, endAngle : d.endAngle} 
        );
        return function(t){
            return arc(interpolate(t)); 
        }
    });
 
g.append("text")
    .attr("transform", function(d) { return "translate(" + arc.centroid(d) + ")"; })
    .attr("dy", ".35em")
    .style("text-anchor", "middle")
    .text(function(d, i) {
        return  d.endAngle-d.startAngle > 0.2 ?
                dataName[i] + " (" + Math.round(1000*(d.endAngle-d.startAngle)/(Math.PI*2))/10 + "%)" : ""
    });
 
svg.append("text")
    .attr("class", "total")
    .attr("transform", "translate("+(w/2-35)+", "+(h/2+5)+")")
    .text(" Total:" + d3.sum(graphData));
 
 
</script>
	<span class="one-graph"></span>
	<script>
var w = 400, h = 400;
var dataName = ["남자", "여자"];
var graphData = [${male}, ${female}];
var colorData = ["#FD2F56", "#5ED1D4"];
var pie = d3.pie();
var arc = d3.arc().innerRadius(70).outerRadius(200);
 
var svg = d3.select(".one-graph")
    .append("svg")
    .attr("width", w)
    .attr("height", h)
    .attr("id", "graphWrap");
 
var g = svg.selectAll(".pie")
    .data(pie(graphData))
    .enter()
    .append("g")
    .attr("class", "pie")
    .attr("transform","translate("+w/2+","+h/2+")");
 
g.append("path")
    .style("fill", function(d, i) {
        return colorData[i];
    }) 
    .transition()
    .duration(400)
    .delay(function(d, i) { 
        return i * 400;
    })
    .attrTween("d", function(d, i) { 
        var interpolate = d3.interpolate(
            {startAngle : d.startAngle, endAngle : d.startAngle}, 
            {startAngle : d.startAngle, endAngle : d.endAngle} 
        );
        return function(t){
            return arc(interpolate(t)); 
        }
    });
 
g.append("text")
    .attr("transform", function(d) { return "translate(" + arc.centroid(d) + ")"; })
    .attr("dy", ".35em")
    .style("text-anchor", "middle")
    .text(function(d, i) {
        return  d.endAngle-d.startAngle > 0.2 ?
                dataName[i] + " (" + Math.round(1000*(d.endAngle-d.startAngle)/(Math.PI*2))/10 + "%)" : ""
    });
 
svg.append("text")
    .attr("class", "total")
    .attr("transform", "translate("+(w/2-35)+", "+(h/2+5)+")")
    .text(" Total:" + d3.sum(graphData));
 
 
</script>
	<span class="three-graph"></span>
	<script>
var w = 400, h = 400;
var dataName = ["10대", "20대", "30대", "40대", "50대", "60대","기타"];
var graphData = [${age_10}, ${age_20},${age_30}, ${age_40},${age_50}, ${age_60},${age_etc}];
var colorData = ["#FF0000","#FF8000","#FFFF00","#00FF00", "#0000FF","#FF00FF","#585858"];
var pie = d3.pie();
var arc = d3.arc().innerRadius(60).outerRadius(150);
 
var svg = d3.select(".three-graph")
    .append("svg")
    .attr("width", w)
    .attr("height", h)
    .attr("id", "graphWrap");
 
var g = svg.selectAll(".pie")
    .data(pie(graphData))
    .enter()
    .append("g")
    .attr("class", "pie")
    .attr("transform","translate("+w/2+","+h/2+")");
 
g.append("path")
    .style("fill", function(d, i) {
        return colorData[i];
    }) 
    .transition()
    .duration(400)
    .delay(function(d, i) { 
        return i * 400;
    })
    .attrTween("d", function(d, i) { 
        var interpolate = d3.interpolate(
            {startAngle : d.startAngle, endAngle : d.startAngle}, 
            {startAngle : d.startAngle, endAngle : d.endAngle} 
        );
        return function(t){
            return arc(interpolate(t)); 
        }
    });
 
g.append("text")
    .attr("transform", function(d) { return "translate(" + arc.centroid(d) + ")"; })
    .attr("dy", ".35em")
    .style("text-anchor", "middle")
    .text(function(d, i) {
        return  d.endAngle-d.startAngle > 0.2 ?
                dataName[i] + " (" + Math.round(1000*(d.endAngle-d.startAngle)/(Math.PI*2))/10 + "%)" : ""
    });
 
svg.append("text")
    .attr("class", "total")
    .attr("transform", "translate("+(w/2-35)+", "+(h/2+5)+")")
    .text(" Total:" + d3.sum(graphData));
 
 
</script>
	<hr>
	<%@ include file="../footer.jsp" %>
</body>
</html>