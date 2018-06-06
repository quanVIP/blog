package me.zbl.fullstack.controller;


import static me.zbl.fullstack.consts.StatusCode.NO_USER;
import static me.zbl.fullstack.consts.StatusCode.WITH_ERROR;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import me.zbl.fullstack.consts.SessionConstants;
import me.zbl.fullstack.controller.base.BaseController;
import me.zbl.fullstack.entity.Comments;
import me.zbl.fullstack.entity.User;
import me.zbl.fullstack.entity.dto.response.SimpleResponse;
import me.zbl.fullstack.service.api.ICommentService;

@Controller
@RequestMapping("/comment")
public class CommentsController extends BaseController {
	
	  @Autowired
	  private ICommentService mComment;

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
	  public Object addComment(HttpServletRequest request, Model model,Integer article_id, String content) throws Exception {
		User user = (User)getSessionAttr(request, SessionConstants.SESSION_CURRENT_USER);
		if(user==null){
			return new SimpleResponse(NO_USER, WITH_ERROR);
		}
	    mComment.addComment(user.getId(),article_id, content);
	    return responseSimpleOK();
	  }
	  
	  @RequestMapping("/list")
	  public Object commentList(HttpServletRequest request, Model model,Integer article_id,Integer pageNum) throws Exception {
	    List<Comments> commentList = mComment.getCommentList(article_id,pageNum);
	    return responseSimpleOK();
	  }

}
