<!DOCTYPE HTML>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/TPIntegration/resources/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="/TPIntegration/resources/css/style.css"/>
<script type="text/javascript" src="/TPIntegration/resources/js/jquery-1.11.2.min.js"></script>
<style type="text/css">
#div_main{
	width:488px;
	height:300px;
	padding:10px;
	margin: 10px;
	float:left;
	border:1px solid #aaaaaa;
}

#div_sub{
	width:488px;
	height:300px;
	padding:10px;
	margin: 10px;
	float: right;
	border:1px solid #aaaaaa;
}

.div_drag{
	width:80px;
	height:80px;
	margin: 10px;
	border:1px solid #000000;
	float: left;
}

.div_content{
	
	margin: 10px;
	background: red;
	border:1px solid #000000;
	display: none;
}
</style>
<script type="text/javascript">

/*
	drag the component
*/
function allowDrop(ev)
{
ev.preventDefault();
}

function drag(ev)
{
ev.dataTransfer.setData("Text",ev.target.id+"_sub");
//ev.dataTransfer.setData("Text","div_content");
}

function drop(ev)
{
ev.preventDefault();
var data=ev.dataTransfer.getData("Text");
ev.target.appendChild(document.getElementById(data));
document.getElementById(data).style.display="block";
}

/*
	the function for buttons 
*/

//get the data from server
function bt_com1_click(){
	//alert("you click the button com1 !!");
	$.ajax({
        type: "post",
        url: "/TPIntegration/bt_com1",
        dataType:"text",
        success:function(data){     
           //$("#com1_sub").html(data);   
           alert(data);
         } 
    });
}

//open the native app
function bt_com2_click(){
	$.ajax({
        type: "post",
        url: "/TPIntegration/bt_com2",
        success:function(data){     
           //$("#com1_sub").html(data);   
          // alert(data);
         } 
    });
}

//get the content from DIV
function bt_com3_click(){
	alert($("#div_main").html());
}

</script>
</head>
<body>

<p>please drag:</p>

<div id="div_main"  class="container"ondrop="drop(event)" ondragover="allowDrop(event)"></div>
<div id="div_sub" >
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

<div id="com1_sub" class="div_content col-xs-3 ">
	start native app: <input id="bt_com1" type="button"  value="get temperature" onclick="bt_com1_click()" />
</div>
<div id="com2_sub" class="div_content col-xs-3">
	start native app: <input id="bt_com2" type="button"  value="open notepad" onclick="bt_com2_click()" />
</div>
<div id="com3_sub" class="div_content col-xs-3">
	start native app: <input id="bt_com3" type="button"  value="get the content" onclick="bt_com3_click()" />
</div>
</body>
</html>