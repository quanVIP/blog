queryUser();
    function queryUser() {
     $.ajax({
        async: true,
        type: "post",
        url: "UI_selectUser.action",//向后台发送请求，后台为stuts2框架
        dataType: "json",
        data: {page:'1'},
        cache: false,
        success: function(data) {
            var result = JSON.parse(data.json_data);   //data.json_data为后台返回的JSON字符串，这里需要将其转换为JSON对象
             
                
                tbody="<tr style='background:#fff;'><th >用户名</th><th>姓名</th>" +
                        "<th >角色</th><th>职务</th><th>联系方式</th></tr>";
                for (var i = 0; i <result.list.length; i++) {//拼接对应<th>需要的值
                     var trs = "";
                     trs+='<tr ><td >' + result.list[i].USERCODE   
                                        + '</td><td >' + result.list[i].REALNAME
                                    + '</td><td >' + result.list[i].ROLEID
                                    + '</td><td>' + result.list[i].ROLENAME
                                    + '</td><td>' + ""
                                    +'</td></tr>';
                     tbody+=trs; 
               };
               $("#userTable").html(tbody);  
               
             var currentPage = result.CurrentPage; //当前页数
             var pageCount = result.pageCount; //总页数
             var options = {
            bootstrapMajorVersion: 3, //版本

            currentPage: currentPage, //当前页数

            totalPages: pageCount, //总页数

            numberOfPages: 5,
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
                    url: "UI_selectUser.action",
                    type: "post",
                    dataType : "json",
                    data: {page:page},
                    cache: false,
                    success: function (data) {
                        var result = JSON.parse(data.msg);
                       
                        tbody="<tr style='background:#fff;'><th >用户名</th><th>姓名</th>" +
                                "<th >角色</th><th>职务</th><th>联系方式</th></tr>";
                        for (var i = 0; i <result.list.length; i++) {
                        
                                 var trs = "";
                                 trs+='<tr ><td >'+ result.list[i].USERCODE
                                                    + '</td><td >' + result.list[i].REALNAME
                                                + '</td><td >' + result.list[i].ROLEID
                                                + '</td><td>' + result.list[i].ROLENAME
                                                + '</td><td>' + ""
                                                +'</td></tr>';
                                 tbody+=trs; 
                           
                       };
                       $("#userTable").html(tbody);  

                    }/*success*/
                });

            }

        };
        $('#useroption').bootstrapPaginator(options);     
            }/*success*/
        
    });
    }