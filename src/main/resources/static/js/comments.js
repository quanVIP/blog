$("#comment_sub").click(function(){
    if(!$('#texthelpblock').val())
      $('#warning').html("<div class='alert alert-warning' role='alert'>评论内容不能为空。</div>");
    else
    {
        //将警告区域的信息清除
        $('#warning').html("");
        var content = $('#texthelpblock').val();
        var mydate = new Date();

        //alert(content);
        $('#content').append(mydate.toLocaleDateString() + "：<br>" + content + "<hr>");

        //清空文本域
        $('#texthelpblock').val("");

        var article_id = $('#article_id').val();

        //使用ajax来向后台传递数据
        $.ajax({
            type: "POST",
            dataType: "json",
            url: "/comment/add",
            data: {article_id: article_id, content: content},
            success: function (result) {
                if (!(result.hasError)) {
                    // 返回博客批量管理页
//                            c_location("/admin/blogmanage");
                } else {
                	 $('#warning').html("<div class='alert alert-warning' role='alert'>请登录后评论！</div>");
                }
            },
            error: function () {
                msg("失败");
            }
        });
    } 
});
