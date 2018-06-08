var article_id = $("#article_id").val();
$(function(){  
	//评论列表
	$('#comments').html("");
	$.ajax({
        url: '/comment/list',//url以具体为实现
        type: 'POST',
        dataType: 'json',
        data: {article_id: article_id,pageNum:1},
        cache: false,
        error: erryFunction, //错误执行方法
        success: succFunction //成功执行方法
    })

    function erryFunction() {
		alert("获取数据错误，请重试！");
    }
    function succFunction(result) {
    	if(result.comments!=null && result.comments!=""){
    		$('#comments').html(result.comments);
    		
    		 var currentPage = result.CurrentPage; //当前页数
             var pageCount = result.pageCount; //总页数
             var options = {
            		 bootstrapMajorVersion: 3, //版本
            		 currentPage: currentPage, //当前页数
            		 totalPages: pageCount, //总页数
            		 shouldShowPage:true,//是否显示该按钮
            		 itemTexts: function (type, page, current) {
            		 switch (type) {
            			 case "first":
                        return "首页";
                    case "prev":
                        return "上一页";
                    case "next":
                        return "下一页";
                    case "last":
                        return "末页";
                    case "page":
                        return page;
                }
            },//点击事件，用于通过Ajax来刷新整个list列表
            onPageClicked: function (event, originalEvent, type, page) {
                $.ajax({
                    async: true,
                    url: "/comment/list",
                    type: "post",
                    dataType : "json",
                    data: {article_id: article_id,pageNum:page},
                    cache: false,
                    success: function (result) {
                    	if(result.comments!=null && result.comments!=""){
                    		$('#comments').html(result.comments);
                    	}
                    }
                });
            }
        };
             if(result.count!=0){
            	 $('#useroption').bootstrapPaginator(options);     
             }
        }
     }
}); 

function addComment(c){
	var commentId=$(c).parents(".comment-body").attr("id");
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
            data: {article_id: article_id, content: content,commentId:commentId},
            success: function (result) {
                if (!(result.hasError)) {
                    // 返回博客批量管理页
                	window.location.href="/blog/"+article_id;
                } else {
                	 $('#warning').html("<div class='alert alert-warning' role='alert'>请登录后评论！</div>");
                }
            },
            error: function () {
                msg("失败");
            }
        });
    } 
}

function reply(pid, c){
	$('#cancel-reply').show();
	$('.comment-reply').show();
	$(c).hide();
	$(c).parents('.comment-body').append($('#comment-post'));
}

function cancelReply(c) {
	$('#cancel-reply').hide();
	$(c).parents(".comment-body").find('.comment-reply').show();
	$("#comment-place").append($('#comment-post'));
}
	  
