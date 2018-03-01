//<![CDATA[ js代码
$(document).ready(function(){
    //修改密码
    $("#repass").click(function () {
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
    }
)

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