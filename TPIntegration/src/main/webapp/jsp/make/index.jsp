<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
<head>
  <meta charset="utf-8">
<link href="/TPIntegration/resources/css/bootstrap.min.css" rel="stylesheet" media="screen">
<link rel="stylesheet" type="text/css" href="/TPIntegration/resources/css/style.css"/>
</head>
<body>
 <div id="wrap">
	 	<!-- 左边拖动工具栏组件-->
	 		<div id="leftTB" class="">
				<div id="com1"class="div_drag" draggable="true" ondragstart="drag(event)">
					<p>componet1</p>
				</div>
				<div id="com2"class="div_drag" draggable="true" ondragstart="drag(event)">
					<p>componet2</p>
				</div>
				<div id="com3"class="div_drag" draggable="true" ondragstart="drag(event)">
					<p>componet3</p>
				</div>
			</div>
	 		<div id="mainContent"class="" >
	 				<div id="editArea" class="" ondrop="drop(event)" ondragover="allowDrop(event)">
		 			<p>编辑部分</p>
		 			</div>	
	 		</div>

	 		<!-- 右边拖动工具栏-->
	 		<div id="rightTB" class="">
				<div id="com11"class="div_drag" draggable="true" ondragstart="drag(event)">
					<p>componet11</p>
				</div>
				<div id="com12"class="div_drag" draggable="true" ondragstart="drag(event)">
					<p>componet12</p>
				</div>
				<div id="com13"class="div_drag" draggable="true" ondragstart="drag(event)">
					<p>componet13</p>
				</div>
			</div>
</div>

<!--组件部分（隐藏）-->
<div id="com1_sub" class="com_sub">
	start native app1: <input id="bt_com1" type="button"  value="Hello world!" onclick="bt_com1_click()" />
</div>
<div id="com2_sub" class="com_sub">
	start native app2: <input id="bt_com2" type="button"  value="Hello world!" onclick="bt_com2_click()" />
</div>
<div id="com3_sub" class="com_sub">
	start native app3: <input id="bt_com3" type="button"  value="Hello world!" onclick="bt_com3_click()" />
</div>

<!--底部固定-->
<nav class="navbar navbar-default navbar-fixed-bottom footer" role="navigation">
<p>底部预览窗口</p>
	<div class="container-fluid">
	 	<div  class="row-fluid">
	 		<div class="span2 preview">
	 		预览1
	 		</div>
	 		<div class="span2 preview">
	 		预览2
	 		</div>
	 		<div  class="span2 preview">
	 		预览3
	 		</div>
	 		<div  class="span2 preview">
	 		预览4
	 		</div>
	 	</div>
	 </div>
</nav>
<script type="text/javascript" src="/TPIntegration/resources/js/jquery-1.11.2.min.js"></script>
<script src="/TPIntegration/resources/js/bootstrap.min.js"></script>
<script src="/TPIntegration/resources/js/drag.js"></script>
</body>
</html>