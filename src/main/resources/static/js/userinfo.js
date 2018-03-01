//<![CDATA[ js代码
$(document).ready(function(){
    $('#uploadimg').hide();
    //跳转到角色管理
    $("#role").click(function () {
//        widows.location.href="";
        window.location.href = "/role";
    } )
    //修改头像
    $("#reimg").click(function (){
        var html = "";
        $('#usercontent').html(html);
        $('#uploadimg').show();
    })
    $('#file-zh').fileinput({
        language : 'zh',
        uploadUrl : "/reimg",
        allowedFileExtensions: ['jpg', 'png', 'gif'],
         uploadAsync: true, //默认异步上传
                        showUpload: false, //是否显示上传按钮
                        showRemove : false, //显示移除按钮
                        showPreview : true, //是否显示预览
                        showCaption: false,//是否显示标题
                        dropZoneEnabled: false,//是否显示拖拽区域
                        //minImageWidth: 50, //图片的最小宽度
                        //minImageHeight: 50,//图片的最小高度
                        maxImageWidth: 600,//图片的最大宽度
                        maxImageHeight: 600,//图片的最大高度
                        maxFileSize: 1024,//单位为kb，如果为0表示不限制文件大小
                        //minFileCount: 0,
//                        maxFileCount: 10, //表示允许同时上传的最大文件个数
//                        enctype: 'multipart/form-data',
                        validateInitialCount:true,
//                        previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
//                        msgFilesTooMany: "选择上传的文件数量({1}) 超过允许的最大数值{m}！",
    });
    //修改密码
    $("#repass").click(function () {
        $('#uploadimg').hide();
        var html = "<form id='formrepass' class=\"form-horizontal\" onsubmit=\"return false\" role=\"form\" action=\"##\" method=\"post\">\n" +

            "                    <div class=\"form-group\">\n" +
            "                        <label for=\"inputPassword3\" class=\"col-sm-2 control-label\">原密码</label>\n" +
            "                        <div class=\"col-sm-10\">\n" +
            "                            <input type=\"password\" class=\"form-control\" id=\"inputPasswordold\" name=\"passold\"/>\n" +
            "                        </div>\n" +
            "                    </div>\n" +
            "                   <div class=\"form-group\">\n" +
                         "                        <label for=\"inputPassword3\" class=\"col-sm-2 control-label\">新密码</label>\n" +
                         "                        <div class=\"col-sm-10\">\n" +
                         "                            <input type=\"password\" class=\"form-control\" id=\"inputPasswordnew\" name=\"passnew\"/>\n" +
                         "                        </div>\n" +
                         "                    </div>\n" +
            "                    <div class=\"form-group\">\n" +
            "                        <div class=\"col-sm-offset-2 col-sm-10\">\n" +
            "                            <button type=\"button\" class=\"btn btn-default\"  onclick=\"repass()\" >修改密码</button>\n" +
            "                        </div>\n" +
            "                    </div>\n" +
            "                </form>";
        $('#usercontent').html(html);
    });
})

function repass(){
     $.ajax({
            //几个参数需要注意一下
            type: "POST",//方法类型
            dataType: "json",//预期服务器返回的数据类型
            url: "/repass" ,//url
            data: $('#formrepass').serialize(),
            success: function (result) {
                //console.log(result);//打印服务端返回的数据(调试用)
                if (result==1){
                    var html = "<h1> 修改成功 </h1>";
                    $("#usercontent").html(html);
                    // alert("SUCCESS");
                };
            },
            error : function() {
                alert("修改失败！");
            }
        });
}
//]]>