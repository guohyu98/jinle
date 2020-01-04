<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="Bookmark" href="/favicon.ico" >
<link rel="Shortcut Icon" href="/favicon.ico" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5shiv.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>

<![endif]-->
<link rel="stylesheet" type="text/css" href="static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="lib/Hui-iconfont/1.0.8/iconfont.css" />

<link rel="stylesheet" type="text/css" href="static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/style.css" />
<link rel="stylesheet" type="text/css" href="css/banner.css" />
<link rel="stylesheet" type="text/css" href="wangEditor/dist/css/wangEditor.css" />
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<!--/meta 作为公共模版分离出去-->
    <style>

    </style>
</head>
<body>
<div class="page-container">
	<form id="form" method="post" class="form form-horizontal" enctype="multipart/form-data">
		<input type="hidden" id="id" name="id" value="${configFacility.id}" />
		<input type="hidden" id="uploadFile" name="uploadFile" />
		<input type="hidden" id="filePath" name="filePath" value="common" />
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>名称：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" id="name" name="name" value="${configFacility.name}" class="input-text">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>分类：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<span class="select-box">
				<select name="parentId" id="parentId" class="select">
					<c:forEach items="${parentList}" var="item">
						<option value="${item.id}">${item.name}</option>
					</c:forEach>
				</select>
				</span>
			</div>
		</div>

		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
				<button class="btn btn-primary radius save" type="button"><i class="Hui-iconfont">&#xe632;</i> 保存</button>
				<button onClick="layer_close();" class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
			</div>
		</div>
	</form>
</div>

<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="lib/jquery.validation/1.14.0/jquery.validate.js"></script> 
<script type="text/javascript" src="lib/jquery.validation/1.14.0/validate-methods.js"></script> 
<script type="text/javascript" src="lib/jquery.validation/1.14.0/messages_zh.js"></script>
<script type="text/javascript" src="lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="js/banner.js"></script>
<script type="text/javascript" src="wangEditor/dist/js/wangEditor.js"></script>
<script type="text/javascript">
$(function(){
	$("#parentId option[value='${configFacility.parentId}']").prop("selected","selected");


	//保存设置
	$(".save").on("click", function(){
		var name = $("#name").val();
		var parentId = $("#parentId").val();
		if(name==""){
			name.msg('名称不能为空');
			return;
		}else if(parentId==""){
			name.msg('分类不能为空');
			return;
		}
		var formData = new FormData($("#form")[0]);
		$.ajax({
			type: "POST",
			url: "configFacilityManage/saveConfigFacility",
			data: formData,
	        async: false,  
	        cache: false,  
	        contentType: false,  
	        processData: false,  		
			success: function(modelMap) {
				if (modelMap.state == "success") {
					layer.msg("保存成功");
					setTimeout(function(){
						parent.location.reload();
					},"1000");
				}else{
					layer.msg("保存失败");
				}
			}
		});
	})
});
</script>
</body>
</html>