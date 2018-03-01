//<![CDATA[ js代码
$(document).ready(function(){
    //所有用户
    $("#getAll").click(function(){
        $('#uploadimg').hide();
        htmlobj=$.ajax({url:"/alluser",async:false});
        /*[{"name":"1321","id":"123","password":"214214124","status":13},{"name":"1213","id":"124","password":"2435325","status":11}]*/
        var jsonData = htmlobj.responseText;
        jsonData = JSON.parse(jsonData); //将json字符串转换成json对象
        // alert(jsonData)
        var html = '<table class="table table-striped"><thead>'+'<tr>'+'<th>'+'姓名'+'</th>'+'<th>'+
            ' I D '+'</th>'+'<th>'+' 密码 '+'</th>'+'<th>'+' 状态 '+'</th>'+'</tr>'+'</thead>';
        for (var j in jsonData) {
            var obj = jsonData[j];
            // alert(obj)
            html += '<tr>'
            html += '<td>' + obj.name + '</td>';
            html += '<td>' + obj.id + '</td>';
            html += '<td>' + obj.password + '</td>';
            html += '<td>' + obj.status + '</td>';
            html += '</tr>';
        }
        html += '</table>';
        $('#usercontent').html(html);
    });
    //添加用户
    $("#addUser").click(function () {
        $('#uploadimg').hide();
        var html = "<form id='formadduser' class=\"form-horizontal\" onsubmit=\"return false\" role=\"form\" action=\"##\" method=\"post\">\n" +
            "                    <div class=\"form-group\">\n" +
            "                        <label for=\"inputEmail\" class=\"col-sm-2 control-label\">账号</label>\n" +
            "                        <div class=\"col-sm-10\">\n" +
            "                            <input type=\"text\" class=\"form-control\" id=\"inputEmail\" name=\"id\"/>\n" +
            "                        </div>\n" +
            "                    </div>\n" +
            "                    <div class=\"form-group\">\n" +
            "                        <label for=\"inputName\" class=\"col-sm-2 control-label\">姓名</label>\n" +
            "                        <div class=\"col-sm-10\">\n" +
            "                            <input type=\"text\" class=\"form-control\" id=\"inputName\" name=\"name\"/>\n" +
            "                        </div>\n" +
            "                    </div>\n" +
            "                    <div class=\"form-group\">\n" +
            "                        <label for=\"inputPassword3\" class=\"col-sm-2 control-label\">密码</label>\n" +
            "                        <div class=\"col-sm-10\">\n" +
            "                            <input type=\"password\" class=\"form-control\" id=\"inputPassword3\" name=\"pass\"/>\n" +
            "                        </div>\n" +
            "                    </div>\n" +
            "                    <div class=\"form-group\">\n" +
            "                        <div class=\"col-sm-offset-2 col-sm-10\">\n" +
            "                            <button type=\"button\" class=\"btn btn-default\"  onclick=\"addAUser()\" >添加</button>\n" +
            "                        </div>\n" +
            "                    </div>\n" +
            "                </form>";
        $('#usercontent').html(html);
    });
    //删除用户
    $("#delUser").click(function () {
        $('#uploadimg').hide();
        var html = "<form id='formdeluser' class=\"form-horizontal\" onsubmit=\"return false\" role=\"form\" action=\"##\" method=\"post\">\n" +
            "                    <div class=\"form-group\">\n" +
            "                        <label for=\"inputEmail\" class=\"col-sm-2 control-label\">账号</label>\n" +
            "                        <div class=\"col-sm-10\">\n" +
            "                            <input type=\"text\" class=\"form-control\" id=\"inputEmail\" name=\"id\"/>\n" +
            "                        </div>\n" +
            "                    </div>\n" +
            "                    <div class=\"form-group\">\n" +
            "                        <div class=\"col-sm-offset-2 col-sm-10\">\n" +
            "                            <button type=\"button\" class=\"btn btn-default\"  onclick=\"delUser()\" >删除</button>\n" +
            "                        </div>\n" +
            "                    </div>\n" +
            "                </form>";
        $('#usercontent').html(html);
    });
    //修改用户
    $("#updateUser").click(function () {
        $('#uploadimg').hide();
        var html = "<form id='formupuser' class=\"form-horizontal\" onsubmit=\"return false\" role=\"form\" action=\"##\" method=\"post\">\n" +
            "                    <div class=\"form-group\">\n" +
            "                        <label for=\"inputEmail\" class=\"col-sm-2 control-label\">账号</label>\n" +
            "                        <div class=\"col-sm-10\">\n" +
            "                            <input type=\"text\" class=\"form-control\" id=\"inputEmail\" name=\"id\"/>\n" +
            "                        </div>\n" +
            "                    </div>\n" +
            "                    <div class=\"form-group\">\n" +
            "                        <label for=\"inputName\" class=\"col-sm-2 control-label\">姓名</label>\n" +
            "                        <div class=\"col-sm-10\">\n" +
            "                            <input type=\"text\" class=\"form-control\" id=\"inputName\" name=\"name\"/>\n" +
            "                        </div>\n" +
            "                    </div>\n" +
            "                    <div class=\"form-group\">\n" +
            "                        <label for=\"inputPassword3\" class=\"col-sm-2 control-label\">密码</label>\n" +
            "                        <div class=\"col-sm-10\">\n" +
            "                            <input type=\"password\" class=\"form-control\" id=\"inputPassword3\" name=\"pass\"/>\n" +
            "                        </div>\n" +
            "                    </div>\n" +
            "                    <div class=\"form-group\">\n" +
            "                        <div class=\"col-sm-offset-2 col-sm-10\">\n" +
            "                            <button type=\"button\" class=\"btn btn-default\"  onclick=\"upAUser()\" >修改</button>\n" +
            "                        </div>\n" +
            "                    </div>\n" +
            "                </form>";
        $('#usercontent').html(html);
    })
});

//添加用户
function addAUser() {
    $.ajax({
        //几个参数需要注意一下
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: "/addUser" ,//url
        data: $('#formadduser').serialize(),
        success: function (result) {
            //console.log(result);//打印服务端返回的数据(调试用)
            if (result==1){
                var html = "<h1> 添加成功 </h1>";
                $("#usercontent").html(html);
                // alert("SUCCESS");
            };
        },
        error : function() {
            alert("添加失败！");
        }
    });
}
//删除用户
function delUser() {
    $.ajax({
        //几个参数需要注意一下
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: "/delUser" ,//url
        data: $('#formdeluser').serialize(),
        success: function (result) {
            //console.log(result);//打印服务端返回的数据(调试用)
            if (result==1){
                var html = "<h1> 删除成功 </h1>";
                $("#usercontent").html(html);
                // alert("SUCCESS");
            };
        },
        error : function() {
            alert("删除失败！");
        }
    });
}

//更新用户信息
function upAUser() {
    $.ajax({
        //几个参数需要注意一下
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: "/updateUser" ,//url
        data: $('#formupuser').serialize(),
        success: function (result) {
            //console.log(result);//打印服务端返回的数据(调试用)
            if (result==1){
                var html = "<h1> 更新成功 </h1>";
                $("#usercontent").html(html);
                // alert("SUCCESS");
            };
        },
        error : function() {
            alert("更新失败！");
        }
    })
}
//]]>