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

<link rel="stylesheet" type="text/css" href="css/banner.css" />
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
		<input type="hidden" id="id" name="id" value="${hotel.id}" />
		<input type="hidden" id="uploadFile" name="uploadFile" />
		<input type="hidden" id="filePath" name="filePath" value="hotel" />
		<div id="tab-system" class="HuiTab">
			<div class="tabBar cl">
				<span>基本信息</span>
				<span>入住须知</span>
				<span>详情</span>
				<span>设施</span>
				<span>房型</span>
				<span>餐厅</span>
				<span>套餐</span>
			</div>
			<div class="tabCon">
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>酒店名称：</label>
					<div class="formControls col-xs-8 col-sm-9">
						<input type="text" id="name" name="name" value="${hotel.name}" class="input-text" placeholder="请输入酒店名称">
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>酒店级别：</label>
					<div class="formControls col-xs-8 col-sm-9">
						<span class="select-box">
						<select name="hotelStar" id="hotelStar" class="select">
							<option value="1">一星</option>
							<option value="2">二星</option>
							<option value="3">三星</option>
							<option value="4">四星</option>
							<option value="5">五星</option>
						</select>
						</span>
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>酒店封面图：</label>
					<div class="formControls col-xs-8 col-sm-9">
						<div class="inputfile">
							<div class="cnt_box pictureset">
								<div class="tupian pr">
									<c:if test="${empty hotel.cover}">
										<img src="img/up-pic-bg.jpg" alt="" class="uploadimg loadimg0" />
									</c:if>
									<c:if test="${!empty hotel.cover}">
										<img src="${hotel.cover}" alt="" class="uploadimg loadimg0" />
									</c:if>
									<input type="file" id="upload0" name="upload0" class="file file0"/>
									<%--<img src="img/cancel.png" class="bgcancel">--%>
									<input type="hidden" id="coverPicture" name="cover" value="${hotel.cover}" />
								</div>
							</div>
						</div>
						<div class="cl"><span class="f-l">*建议上传尺寸720*1080</span></div>
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>酒店地址：</label>
					<div class="formControls col-xs-8 col-sm-9">
						<input type="text" id="address" name="address" value="${hotel.address}" class="input-text" placeholder="请输入酒店地址">
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-2">酒店联系人：</label>
					<div class="formControls col-xs-8 col-sm-9">
						<input type="text" id="contact" name="contact" value="${hotel.contact}" class="input-text" placeholder="请输入酒店联系人">
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-2">酒店电话：</label>
					<div class="formControls col-xs-8 col-sm-9">
						<input type="text" id="phoneNumber" name="phoneNumber" value="${hotel.phoneNumber}" class="input-text" placeholder="请输入酒店电话">
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-2">酒店banner：</label>
					<div class="formControls">
						<input type="hidden" id="banner" name="banner" />
						<div class="inputfile">
							<div class="cnt_box pictureset">
								<c:if test="${empty bannerList}" >
									<div class="tupian pr">
										<img src="img/up-pic-bg.jpg" alt="" class="uploadimg loadimg0" />
										<input type="file" id="upload1" name="upload1" class="file file0"/>
										<img src="img/cancel.png" class="bgcancel">
										<input type="hidden" name="mallPicture" />
									</div>
								</c:if>
								<c:if test="${!empty bannerList}" >
									<c:forEach items="${bannerList}" var="item" varStatus="status">
										<c:set var="number" value="${status.index + 1}" />
										<div class="tupian pr">
											<img src="${item}" alt="" class="uploadimg loadimg0" />
											<input type="file" id="upload${number}" name="upload${number}" class="file file0"/>
											<img src="img/cancel.png" class="bgcancel">
											<input type="hidden" name="bannerPicture" value="${item}" />
										</div>
									</c:forEach>
								</c:if>
								<div class="tupian addpicture pr" data-name="bannerPicture">
									<img src="img/uploadimg.png" style="align-self:center;width:76%;height:100%;" alt="" class="add" />
								</div>
							</div>
						</div>
					</div>
					<div class="cl"><span class="f-r">*建议大小900*540，最大不超过150kb,最多不超过7张&nbsp;&nbsp;</span></div>
				</div>

				<div class="row cl">
					<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
						<button class="btn btn-primary radius next" type="button"><i class="Hui-iconfont">&#xe632;</i> 下一步</button>
					</div>
				</div>
			</div>
			<div class="tabCon">

				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>早餐价格：</label>
					<div class="formControls col-xs-8 col-sm-9">
						<input type="number" id="breakfastPrice" name="breakfastPrice" value="${hotel.breakfastPrice}" class="input-text" placeholder="请输入早餐价格">
					</div>
				</div>
			</div>
			<div class="tabCon">

				<div class="row cl">
					<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
						<button class="btn btn-primary radius save" type="button"><i class="Hui-iconfont">&#xe632;</i> 保存</button>
						<button onClick="layer_close();" class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
					</div>
				</div>
			</div>
			<div class="tabCon">
			</div>
			<div class="tabCon">
			</div>
			<div class="tabCon">
			</div>
			<div class="tabCon">
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

<script type="text/javascript" src="js/banner.js"></script>
<script type="text/javascript">
$(function(){
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	$("#tab-system").Huitab({
		index:0
	});


	<%--$("#parentId option[value='${hotel.parentId}']").prop("selected","selected");--%>


	//保存设置
	$(".save").on("click", function(){
		var name = $("#name").val();
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
			url: "hotelManage/saveHotel",
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