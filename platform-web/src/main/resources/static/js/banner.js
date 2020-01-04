//----------------轮播图----------------
var n=$(".pictureset").find(".tupian").length-2;//用于计算添加(删除)了多少个轮播图
//添加轮播图设置
$(".addpicture").on("click",function(){
    $that = $(this);
    if($that.parents(".pictureset").find(".tupian").length>=6){
        layer.msg("最多仅支持添加5张轮播图");
        return;
    }
    n++;
    var name = $that.data('name');
    var htm="<div class=\"tupian pr\">"+
        "<img src=\"img/up-pic-bg.jpg\"  class=\"uploadimg loadimg"+n+"\" />"+
        "<input type=\"file\" id=\"upload"+(n+1)+"\" name=\"upload"+(n+1)+"\" class=\"file file"+n+"\"/>"+
        "<img src=\"img/cancel.png\" class=\"bgcancel\">"+
        '<input type="hidden" name="'+name+'" />'+
        "</div>";
    $that.before(htm);
})
//删除轮播图
$(".pictureset").on("click",".bgcancel",function(){
    if($(this).parents(".pictureset").find(".tupian").length-2>0){
        $(this).parent().remove();
    }else{
        layui.layer.msg("至少添加一张轮播图");
    }
})
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