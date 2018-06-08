<#-- 评论区 -->
<div class="col-md-8 offset-md-2" id="comment-place">
   <p id="warning"></p>
   <div id="comment-post">
  	 <div class="cancel-reply" id="cancel-reply" style="display: none;">
		<a href="javascript:void(0);" onclick="cancelReply(this)"><i class="fa fa-share"></i>取消回复</a>
	</div>
	   <textarea id="texthelpblock" class="form-control" rows="3">
	   </textarea>
	   <p class="text-right"><button id="comment_sub" type="button" class="btn btn-primary btn-xs" onclick="addComment(this)">提交评论</button></p>
	</div>
</div>
<!-- 评论 -->
<div class="col-md-8 offset-md-2">
	<#if comments??>
	    <#if (comments?size>0)>
			<h4><i class="fa fa-comments-o fa-fw"></i><em>${count!""}</em>条评论~~~</h4>
			<ul class="comment" id="commentList">
                <#list comments as comment>
                    <li>
						<div class="comment-body" id="${comment.id!""}">
							<div class="cheader">
								<strong>${comment.userName!""}</strong>
								<div class="timer">
									<i class="fa fa-clock-o fa-fw"></i>${comment.createTime!""}
								</div>
							</div>
							<div class="content">
								${comment.comment!""}
							</div>
							<div class="reply">
								<#if replyList??>
	  							  <#if (replyList?size>0)>
								<#list replyList as reply>
								<#if (comment.id==reply.commentId)>
									<div class="cheader">
										<strong>${reply.userName!""}</strong>
										<div class="timer">
											<i class="fa fa-clock-o fa-fw"></i>${reply.createTime!""}
										</div>
									</div>
									<div class="content">
										${reply.comment!""}
									</div>
								 </#if>
								</#list>
								   </#if>
      							  </#if>
							</div>
							<div class="sign">
								<a href="#${comment.id!""}" class="comment-reply" onclick="reply(1, this)"><i class="fa fa-reply fa-fw"></i>回复</a>
							</div>
						</div>
					</li>
                </#list>
                  <#else>  
   			 		<div class='alert alert-warning' role='alert'>暂无评论，赶紧登陆评论吧</div>
            </#if>
        </#if>
	</ul>
</div>
