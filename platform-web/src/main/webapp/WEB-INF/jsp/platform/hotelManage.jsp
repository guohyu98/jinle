<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5shiv.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/style.css" />
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>酒店管理</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 酒店管理 <span class="c-gray en">&gt;</span> 酒店列表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	<div class="text-c">
	</div>
	<div class="cl pd-5 bg-1 bk-gray mt-20">
		<span class="l">
			<a class="btn btn-primary radius" data-title="添加酒店" onclick="add('添加酒店', 'hotelManage/queryHotelDetails')" href="javascript:;"><i class="Hui-iconfont">&#xe600;</i> 添加酒店</a></span>
		</span> 
	</div>
	<div class="mt-20">
		<table class="table table-border table-bordered table-bg table-hover table-sort table-responsive">
			<thead>
				<tr class="text-c">
					<th>热门排序</th>
					<th>酒店名称</th>
					<th>总机电话</th>
					<th>热门</th>
					<th>房型数量</th>
					<th>餐厅预定</th>
					<th>状态</th>
					<th>添加时间</th>
					<th width="120">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${!empty hotelList}">
				<c:forEach items="${hotelList}" var="item">
				<tr class="text-c">
					<td>${item.hotSort}</td>
					<td>${item.name}</td>
					<td>${item.phoneNumber}</td>
					<td>${item.isHot}</td>
					<td>11</td>
					<td>${item.isDiningReservation}</td>
					<td>${item.status}</td>
					<td class="td-status">
						<fmt:formatDate value="${item.addTime}" pattern="yyyy-MM-dd HH:mm" />
					</td>
					<td class="f-14 td-manage">
						<a style="text-decoration:none" class="ml-5" onClick="edit('详情','hotelManage/queryHotelDetails?id=${item.id}')" href="javascript:;" title="详情"><i class="Hui-iconfont">&#xe6df;</i></a>
					</td>
				</tr>
				</c:forEach>
				</c:if>
			</tbody>
		</table>
	</div>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="lib/My97DatePicker/4.8/WdatePicker.js"></script> 
<script type="text/javascript" src="lib/datatables/1.10.0/jquery.dataTables.min.js"></script> 
<script type="text/javascript" src="lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript">
$('.table-sort').dataTable({
	//"aaSorting": [[ 1, "desc" ]],//默认第几个排序
	"bStateSave": true,//状态保存
	"pading":false,
	"aoColumnDefs": [
	  //{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
	  {"orderable":false}// 不参与排序的列
	]
});

$("#isChecked option[value='${isChecked}']").prop("selected","selected");

/*酒店-新增*/
function add(title,url,id,w,h){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
/*酒店-编辑*/
function edit(title,url,id,w,h){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
/*酒店-删除*/
function del(obj,id){
	layer.confirm('确认要删除吗？',function(index){
		$.ajax({
			type: 'POST',
			url: 'hotelManage/deleteHotel?id='+id,
			dataType: 'json',
			success: function(data){
				if(data.state=="success"){
					$(obj).parents("tr").remove();
					layer.msg('已删除!',{icon:1,time:1000});
				}else{
					layer.msg(data.msg);
				}
			},
			error:function(data) {
				console.log(data.msg);
			},
		});		
	});
}
$(".search").on("click", function(){
	form.action="hotelManage/queryHotel";
	form.submit();
})


</script> 
</body>
</html>