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
        .Newbgcancel{
            position: absolute;
            width: 16px;
            right: -8px;
            top: -8px;
        }
        .tupian{
            margin-right: 30px;
            display: flex;
        }
        .bannerUrl{
            /*margin-top:130px;*/
            position: absolute;
            width:110px;
            bottom:-30px
        }
        .wangEditor-container{
            z-index: 0 !important;
        }
        .bannerHref1,.house1{
            position: absolute;
            width:110px;
            bottom:-60px
        }
        .bannerHref2,.house2{
            position: absolute;
            width:110px;
            bottom:-60px
        }
        .bannerHref3,.house3{
            position: absolute;
            width:110px;
            bottom:-60px
        }
        .bannerHref4,.house4{
            position: absolute;
            width:110px;
            bottom:-60px
        }
        .bannerHref5,.house5{
            position: absolute;
            width:110px;
            bottom:-60px
        }

    </style>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 基本设置 <span class="c-gray en">&gt;</span> 广告位 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	<form id="form" method="post" class="form form-horizontal" enctype="multipart/form-data">
		<input type="hidden" id="uploadFile" name="uploadFile" />
		<input type="hidden" id="filePath" name="filePath" value="configBasic" />
        <input type="hidden" id="advertisingImg" name="advertisingImg" value='${configBasic.advertisingImg}' />
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">banner图：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<div class="inputfile">
					<div class="cnt_box pictureset" style="height:200px;">
                        <div class="tupian pr">
                            <img src="img/up-pic-bg.jpg" alt="" class="uploadimg loadimg0" />
                            <input type="file" id="upload1" name="upload1" class="file file0"/>
                            <img src="img/cancel.png" class="Newbgcancel">
                            <input type="hidden" name="bannerImg" />
                            <select class="layui-input-block bannerUrl selectFilter1">
                                <option value="0">无</option>
                                <option value="1">跳转链接</option>
                                <option value="2">选择酒店</option>
                            </select>
                            <input type="text" hidden name="bannerHref" placeholder="输入链接地址" class="href bannerHref1" />
                            <select class="layui-input-block house1 hou" hidden>
                                <option value="1">酒店1</option>
                                <option value="2">酒店2</option>
                                <option value="3">酒店3</option>
                            </select>

                        </div>
                        <div class="tupian pr">
                            <img src="img/up-pic-bg.jpg" alt="" class="uploadimg loadimg0" />
                            <input type="file" id="upload2" name="upload2" class="file file0"/>
                            <img src="img/cancel.png" class="Newbgcancel">
                            <input type="hidden" name="bannerImg" />
                            <select class="layui-input-block bannerUrl selectFilter2">
                                <option value="0">无</option>
                                <option value="1">跳转链接</option>
                                <option value="2">选择酒店</option>
                            </select>
                            <input type="text" hidden name="bannerHref" placeholder="输入链接地址" class="href bannerHref2" />
                            <select class="layui-input-block  house2 hou" hidden>
                                <option value="1">酒店1</option>
                                <option value="2">酒店2</option>
                                <option value="3">酒店3</option>
                            </select>
                        </div>
                        <div class="tupian pr">
                            <img src="img/up-pic-bg.jpg" alt="" class="uploadimg loadimg0" />
                            <input type="file" id="upload3" name="upload3" class="file file0"/>
                            <img src="img/cancel.png" class="Newbgcancel">
                            <input type="hidden" name="bannerImg" />
                            <select class="layui-input-block bannerUrl selectFilter3">
                                <option value="0">无</option>
                                <option value="1">跳转链接</option>
                                <option value="2">选择酒店</option>
                            </select>

                            <input type="text" hidden name="bannerHref" placeholder="输入链接地址" class="href bannerHref3" />

                            <select class="layui-input-block  house3 hou" hidden>
                                <option value="1">酒店1</option>
                                <option value="2">酒店2</option>
                                <option value="3">酒店3</option>
                            </select>

                        </div>
                        <div class="tupian pr">
                            <img src="img/up-pic-bg.jpg" alt="" class="uploadimg loadimg0" />
                            <input type="file" id="upload1" name="upload4" class="file file0"/>
                            <img src="img/cancel.png" class="Newbgcancel">
                            <input type="hidden" name="bannerImg" />
                            <%--<input type="text" name="bannerUrl" class="bannerUrl" />--%>
                            <select class="layui-input-block bannerUrl selectFilter4">
                                <option value="0">无</option>
                                <option value="1">跳转链接</option>
                                <option value="2">选择酒店</option>
                            </select>

                            <input type="text" hidden name="bannerHref" placeholder="输入链接地址" class="href bannerHref4" />

                            <select class="layui-input-block  house4 hou" hidden>
                                <option value="1">酒店1</option>
                                <option value="2">酒店2</option>
                                <option value="3">酒店3</option>
                            </select>

                        </div>
                        <div class="tupian pr">
                            <img src="img/up-pic-bg.jpg" alt="" class="uploadimg loadimg0" />
                            <input type="file" id="upload1" name="upload5" class="file file0"/>
                            <img src="img/cancel.png" class="Newbgcancel">
                            <input type="hidden" name="bannerImg" />
                            <%--<input type="text" name="bannerUrl" class="bannerUrl" />--%>
                            <select class="layui-input-block bannerUrl selectFilter5">
                                <option value="0">无</option>
                                <option value="1">跳转链接</option>
                                <option value="2">选择酒店</option>
                            </select>

                            <input type="text" hidden name="bannerHref" placeholder="输入链接地址" class="href  bannerHref5" />

                            <select class="layui-input-block  house5 hou" hidden>
                                <option value="1">酒店1</option>
                                <option value="2">酒店2</option>
                                <option value="3">酒店3</option>
                            </select>

                        </div>
					</div>
				</div>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">会员协议：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<%--<input type="text" id="featureDetails" name="featureDetails" value="${configBasic.featureDetails}" class="input-text">--%>
				<textarea id="editor-trigger" class="editor" name="memberAgreement">${configBasic.memberAgreement}</textarea>
			</div>
		</div>
		<div class="row cl detailsDemo">
			<label class="form-label col-xs-4 col-sm-2">用户协议：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<%--<input type="text" id="featureDetails" name="featureDetails" value="${configBasic.featureDetails}" class="input-text">--%>
				<textarea id="editor-trigger2" class="editor" name="customerAgreement">${configBasic.customerAgreement}</textarea>
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
<%--<script type="text/javascript" src="js/banner.js"></script>--%>
<script type="text/javascript" src="wangEditor/dist/js/wangEditor.js"></script>

<script type="text/javascript">
    //上传文件
    $(".pictureset").on('change', "input[type=file]", function(){
        $that = $(this);
        var name = $(this).attr("name");

        $("#uploadFile").val(name);
        var ff = $(this).val();
        if(ff == null || ff == ""){
            layer.msg("请选择文件");
            return;
        }else if(!/.(gif|jpg|jpeg|png|gif|jpg|png)$/.test(ff)){
            //layer.msg("图片类型必须是.gif,jpeg,jpg,png中的一种");
            layer.msg("图片格式不正确");
            return;
        }
        var formData = new FormData($("#form")[0]);
        $.ajax({
            type: "POST",
            url: "upload/uploadRealTimeWithCompress",
            data: formData,
            async: true,
            cache: false,
            contentType: false,
            processData: false,
            success: function(modelMap) {
                if (modelMap.state == "success") {
                    var uploadFile = modelMap.uploadFile;
                    $that.parents(".tupian").find(".uploadimg").attr("src", uploadFile);
                    $that.parents(".tupian").find("input[type=hidden]").val(uploadFile);
                }else{
                    layer.msg("上传失败");
                }
            }
        });
    })

    //切换图片跳转类型
    $(".pictureset").on('change', ".bannerUrl", function(){
        if($(this).val() == '0'){//无
            $(this).parents('.tupian').find('.href').hide();
            $(this).parents('.tupian').find('.hou').hide();
        } else if($(this).val() == '1'){
            $(this).parents('.tupian').find('.href').show();
            $(this).parents('.tupian').find('.hou').hide();
        } else {
            $(this).parents('.tupian').find('.href').hide();
            $(this).parents('.tupian').find('.hou').show();
        }
    })

    //删除轮播图
    $(".pictureset").on("click",".Newbgcancel",function(){
        $(this).parents(".tupian").find(".bannerUrl").val('0');
        $(this).parents(".tupian").find(".href").hide()
        $(this).parents(".tupian").find(".href").val('');
        $(this).parents(".tupian").find(".hou").hide();
        $(this).parents(".tupian").find(".hou").val('');
        $(this).parents(".tupian").find(".uploadimg").attr('src','img/up-pic-bg.jpg');
    })
</script>

<script type="text/javascript">
$(function(){
    //富文本编辑器基本信息
    var editor = new wangEditor('editor-trigger');
    // 隐藏网络图片
    editor.config.uploadImgHeaders = {
        'Accept' : 'text/x-json'
    };
    editor.config.uploadImgUrl = '/jinle/common/uploadWangeditor.action';
    editor.config.hideLinkImg = true;
    editor.config.menuFixed = false;
    editor.create();

	var editor2 = new wangEditor('editor-trigger2');
	// 隐藏网络图片
	editor2.config.uploadImgHeaders = {
		'Accept' : 'text/x-json'
	};
	editor2.config.uploadImgUrl = '/jinle/common/uploadWangeditor.action';
	editor2.config.hideLinkImg = true;
	editor2.config.menuFixed = false;
	editor2.create();

	init();

	function init(){
	    var advertisingImg = $("#advertisingImg").val();
	    if(advertisingImg!=""){
	       var jsonArr = $.parseJSON(advertisingImg);
	       for(var i=0;i<jsonArr.length;i++){
	           jsonObj = jsonArr[i];
	           if(jsonObj.img!=''){
                   $('.pictureset').find('.uploadimg').eq(i).attr('src',jsonObj.img);
                   $('.pictureset').find('input[name=bannerImg]').eq(i).val(jsonObj.img);
                   if(jsonObj.type==1){//跳转链接
                       $('.pictureset').find('.bannerUrl').eq(i).val(1);
                       $('.pictureset').find('.href').eq(i).show();
                       $('.pictureset').find('.href').eq(i).val(jsonObj.url);
                   }else if(jsonObj.type==2){//酒店
                       $('.pictureset').find('.bannerUrl').eq(i).val(2);
                       $('.pictureset').find('.hou').eq(i).show();
                       $('.pictureset').find('.hou').eq(i).val(jsonObj.url);
                   }

               }
           }
        }
    }

	//保存设置
	$(".save").on("click", function(){
	    var advertisingImg = "";
	    var advArr = [];
	    $(".tupian").each(function(){
	        img = $(this).find('input[name=bannerImg]').val();
	        type = $(this).find(".bannerUrl").val();
	        url = "";
	        if(type==1){//跳转链接
                url = $(this).find(".href").val();
            }else if(type==2){//选择酒店
                url = $(this).find(".hou").val();
            }
	        advObj = {
	            'img':img,
                'type':type,
                "url":url
            }
	        advArr.push(advObj);
        });
	    console.log('advArr', advArr);
        advertisingImg = JSON.stringify(advArr);
	    $("#advertisingImg").val(advertisingImg);

		var formData = new FormData($("#form")[0]);
		$.ajax({
			type: "POST",
			url: "configBasicManage/saveConfigBasic",
			data: formData,
	        async: false,  
	        cache: false,  
	        contentType: false,  
	        processData: false,  		
			success: function(modelMap) {
				if (modelMap.state == "success") {
					layer.msg("保存成功");
					setTimeout(function(){
						location.reload();
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