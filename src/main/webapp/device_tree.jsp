<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.net.InetAddress" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>树状图</title>
<style>

.node circle {
  fill: #fff;
  stroke: steelblue;
  stroke-width: 1.5px;
}

.node {
  font: 14px sans-serif;
}

.node .rect,.rect{
	fill: #fff;
	stroke: steelblue;
	stroke-width: 1.5px;
}
.link {
  fill: none;
  stroke: #ccc;
  stroke-width: 1.5px;
}
h2,p{
	padding-left: 20px;
	margin-bottom: -20px;
	text-align: left;
}
.legend{
	position: relative;
	top: 5pt;
	left: 4%;
}
.legend .text{
	font-size: 12pt;
}

#group_statu{
	margin: 0pt 20pt;
}
	
</style>
</head>
<body>
<script src="../js/d3.v3.min.js"></script>
<h2 id="title"></h2>

<script>
var width = 500,index,height = 500,count=0,baseurl="";

function getCookie(c_name) {
	if (document.cookie.length>0) {c_start=document.cookie.indexOf(c_name + "=")
		if (c_start!=-1) {
			c_start=c_start + c_name.length+1
			c_end=document.cookie.indexOf(";",c_start)
			if (c_end==-1) c_end=document.cookie.length
			return unescape(document.cookie.substring(c_start,c_end))
		}
	}
	return ""
}

function setCookie(c_name,value,expiredays) {
	var exdate=new Date()
	exdate.setDate(exdate.getDate()+expiredays)
	document.cookie=c_name+ "=" +escape(value)+
	((expiredays==null) ? "" : "; expires="+exdate.toGMTString())
}

var tree = d3.layout.tree()
	.size([width, height-200])
	.separation(function(a, b) { return (a.parent == b.parent ? 1 : 2); });

var diagonal = d3.svg.diagonal()
	.projection(function(d) { return [d.y, d.x]; });

var svg = d3.select("body").append("svg")
	.attr("width", width)
	.attr("height", height)
	.append("g")
	.attr("transform", "translate(80,0)");
var localhost ='<%=InetAddress.getLocalHost().getHostAddress().toString() %>';

d3.json("http://"+localhost+":8888/device/get_device_relationship_list", function(error, root) {
	console.log(root);
	if(getCookie("index")==""){
		index=0;
		setCookie("index",index);
	}else{
		index = getCookie("index");
	}

	d3.select("#previous").on("click",function() {
		if (index>0) {index--;}else{index=0; alert("已经是第一组");return}
		group_current.text(" "+(index+1)+" ");
		setCookie("index",index);
		location.reload(); 
	});
	d3.select("#next").on("click",function() {
		if (count>index+1) {index++;}else{alert("已经是最后一组");return}
		group_current.text(" "+(index+1)+" ");
		setCookie("index",index);
		location.reload();
	});
	var strjson = JSON.stringify(root).replace(/childrens/g, "children");
	var obj = eval('(' + strjson + ')');
	count = obj.data.device_list.length;
	//console.log(count);
	root = obj.data.device_list[index];

	var nodes = tree.nodes(root);
	var links = tree.links(nodes);

	console.log(nodes);
	console.log(links);

	var	legendnode = d3.select("h2#title").text(function(){
		if(root.group_name!=undefined){
			return root.group_name+"结构拓扑图";
		}
		return "结构拓扑图";
	}).append("svg").attr("class", "legend").attr("width", "130").attr("height", "30");

	legendnode.append("rect").attr('width', 12).attr('height', 12).attr("x", 2).attr("y", 10).attr("class", "rect");
	legendnode.append("text").text("空开").attr("class", "text").attr("x", 20).attr("y", 21);

	legendnode.append("circle").attr('r', 6.5).attr("cx", 80).attr("cy", 16).attr("class", "rect");
	legendnode.append("text").text("插座").attr("class", "text").attr("x", 93).attr("y", 21);

	var link = svg.selectAll(".link")
			.data(links)
			.enter()
			.append("path")
			.attr("class", "link")
			.attr("d", diagonal);

	link.each(function (d) {
		if (d.target.device_name == undefined) {
			this.remove();
		}
	});

	var node = svg.selectAll(".node")
			.data(nodes)
			.enter()
			.append("g")
			.attr("class", "node")
			.attr("transform", function (d) {
				return "translate(" + d.y + "," + d.x + ")";
			})

	node.each(function (d) {
		if (d.pdutype == 1) {
			d3.select(this).append("rect").attr('width', 12)
					.attr('height', 12).attr("x", 0).attr("y", -5).attr("class", "rect");
			//console.log("true");
		} else {
			//console.log("false");
			d3.select(this).append("circle").attr("r", 6);
		}
	});

	node.append("text")
			.attr("dy", -11)
			.style("text-anchor", function (d) {
				return d.children ? "end" : "start";
			})
			.text(function (d) {
				return d.device_name;
			});

	node.each(function (d) {
		if (d.device_name == undefined) {
			this.remove();
		}
	});

	node.select("text").attr("dx", function (d) {
		if (d.children != undefined) {
			return d.children[0].device_name != undefined ? 34 : 60;
		}
		return -20;
	});
	var group_count = d3.select("#group_count").text(" "+count+" ");
	var group_current = d3.select("#group_current").text(" "+(parseInt(index)+1)+" ");
});

</script>

<p><button id="previous">上一组</button>
	<cl id="group_statu">共<span id="group_count"></span>组，第<span id="group_current"></span>组</cl>
	<button id="next">下一组</button></p>
</body>
</html>