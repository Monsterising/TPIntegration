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
alert($("#editArea").html());
}


//获得位置
function CPos(x,y,z){
	this.x=x;
	this.y=y;
	this.z=z;
}
function getPosition(target){
	var _target = target;
	var pos = new CPos(_target.offsetLeft,_target.offsetTop);
	var parent = _target.offsetParent;
	while(parent){
		pos.x += target.offsetLeft;
		pos.y += target.offsetTop

		parent = parent.offsetParent;
	}
	return pos;
}