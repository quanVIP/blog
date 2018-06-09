package me.zbl.fullstack.controller;


import static me.zbl.fullstack.consts.StatusCode.NO_USER;
import static me.zbl.fullstack.consts.ViewConsts.VIEW_COMMENTS;
import static me.zbl.fullstack.consts.StatusCode.WITH_ERROR;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import freemarker.template.Configuration;
import me.zbl.fullstack.consts.SessionConstants;
import me.zbl.fullstack.controller.base.BaseController;
import me.zbl.fullstack.entity.Comments;
import me.zbl.fullstack.entity.User;
import me.zbl.fullstack.entity.dto.response.SimpleResponse;
import me.zbl.fullstack.service.api.ICommentService;
import me.zbl.fullstack.utils.FreemarkerUtils;

@Controller
@RequestMapping("/comment")
public class CommentsController extends BaseController {
	
	  @Autowired
	  private ICommentService mComment;
	  
	  @Autowired
	  private Configuration configuration;

	  /**
	   * 增加评论
	   * @param request
	   * @param model
	   * @param article_id
	   * @param content
	   * @return
	   * @throws Exception
	   */
	  @RequestMapping("/add")
	  @ResponseBody
	  public Object addComment(HttpServletRequest request, Model model,Integer article_id, String content,Integer commentId) throws Exception {
		User user = (User)getSessionAttr(request, SessionConstants.SESSION_CURRENT_USER);
		if(user==null){
			return new SimpleResponse(NO_USER, WITH_ERROR);
		}
	    mComment.addComment(user,article_id, content,commentId);
	    return responseSimpleOK();
	  }
	  
	  @RequestMapping("/list")
	  @ResponseBody
	  public Object commentList(HttpServletRequest request, Model model,Integer article_id,Integer pageNum) throws Exception {
		   Map<String,Object> result = new HashMap<String,Object>();
		   List<Comments> commentList = mComment.getCommentList(article_id,pageNum);
		   result.put("comments", commentList);
		   int count = mComment.getCount(article_id);
		   result.put("count", count);
		   List<Comments> replyList = mComment.getReplyList(article_id,commentList);
		   result.put("replyList", replyList);
		   String comments = FreemarkerUtils.analysisTemplate(configuration,"comments.ftl", result);
		   int pageCount = (count/5)+1;
		   result.put("CurrentPage", pageNum);
		   result.put("pageCount", pageCount);
		   result.put("comments", comments);
		   return result;
	  }

}
