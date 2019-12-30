/**
 * 初始化内容
 */
$(function(){
	setTree();
	initTag();
});

function setTree(){
	$("#dirTreeId").tree({
		onClick:function(node){
			if(node.state == "closed"){
				//展开
				$(this).tree("expand",node.target);
				//打开选项卡
				openTag(node);
			} else {
				$(this).tree("collapse",node.target);
			}
		}
	});
}

function openTag(node){
	//addTag(node.id,$(this).tree("options").url + "?id=" + node.id);
	var uri = node.target.baseURI;
	
	if(! uri){
		uri = "/TheTaskOfMonitor/";
	}
	addTag(node.id, uri + "ShowAllFile.action?id=" + encodeURI(encodeURI(node.id)));
}


/*
 * 添加选择卡
 */
function addTag(obj,url){
	//处理，支持字符串
	var title;
	if(typeof(obj) == "string"){
		title = obj;
	} else{
		title = $(obj).text();
	}
	
	if($("#centerTabId").tabs("exists",title)){
		$("#centerTabId").tabs("select",title);
	} else {
		$("#centerTabId").tabs("add",{
			title:title,
			content:createFrame(url),
			closable:true
		});
	}
}

function createFrame(url){
	return '<iframe src="'+url+'" style="width: 100%;height:100%" frameborder="0" scrolling="auto"></iframe>';
}


function initTag(){
	$("#centerTabId").tabs({
		onClose:function(title){
			var node = $("#dirTreeId").tree("find",title);
			if(node.state == "open"){
				$("#dirTreeId").tree("collapse",node.target);
			}
		}
	});
}


