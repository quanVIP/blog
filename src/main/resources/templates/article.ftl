<html lang="zh">

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>${article.title!'全栈博客'}</title>

<#-- EditorMD -->
    <link href="/vendor/editor/css/editormd.css" rel="stylesheet">

<#-- 自定义 样式 -->
<#include "public/front_custom_css.ftl">

<#-- CSS -->
<link href="/css/comment/comment.css" rel="stylesheet">
<#include "public/front_css.ftl">
</head>

<body>

<#-- s-nav.ftl -->
<#include "public/nav.ftl">
<#-- e-nav.ftl -->

<div class="container container-fluid cus_content">
<#-- 博客标题 -->
    <div class="row mt-md-5"></div>
    <div class="row mt-md-5">
    <#-- 博客内容 -->
        <div class="col-md-8 offset-md-2">
            <h3 class="p-3 font-weight-bold">${article.title!'标题'}</h3>
            <div class="row pl-3 pr-3">
                <div class="col-md-6"></div>
                <div class="col-md-6 text-md-right">
                    <p class="small text-secondary">${article.dateTime!""}</p>
                </div>
            </div>
        ${article.htmlMaterial!'文章内容'}
        </div>
    </div>
    <div class="row mb-md-5"></div>
</div>

<input type="hidden" id="article_id" value="${article.id!''}">

<div class="col-md-8 offset-md-2">
   <h4>评论区</h4>
   <p id="warning"></p>
   <textarea id="texthelpblock" class="form-control" rows="3">
   </textarea>
   <p class="text-right"><button id="comment_sub" type="button" class="btn btn-primary btn-xs">提交评论</button></p>
</div>

<!-- 评论 -->
<div class="col-md-8 offset-md-2">
	<h4><i class="fa fa-comments-o fa-fw"></i><em>20</em>条评论~~~</h4>
	<ul class="comment">
		<li>
			<div class="comment-body" id="comment-1">
				<div class="cheader">
					<strong>张三</strong>
					<div class="timer">
						<i class="fa fa-clock-o fa-fw"></i>2018-01-01 14:14:14
					</div>
				</div>
				<div class="content">
					有人在车上分娩，有人在地铁怀孕，北京真是个充满生机的城市…<img src="http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/3c/pcmoren_wu_org.png" alt="[污]" data-w-e="1">
				</div>
				<div class="sign">
					<a href="#comment-1" class="comment-reply" onclick="$.comment.reply(1, this)"><i class="fa fa-reply fa-fw"></i>回复</a>
				</div>
			</div>
		</li>
	</ul>
	<ul class="pagination" >
	<li><a href="#">&laquo;</a></li>
	<li><a href="#">1</a></li>
	<li><a href="#">2</a></li>
	<li><a href="#">3</a></li>
	<li><a href="#">4</a></li>
	<li><a href="#">5</a></li>
	<li><a href="#">&raquo;</a></li>
</ul>
</div>


<#-- s-footer -->
<#include "public/footer.ftl">
<#-- e-footer -->

<#-- JS -->
<#include "public/front_js.ftl">
<script src="/js/comments.js"></script>
</body>
</html>
