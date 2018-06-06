package me.zbl.fullstack.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import me.zbl.fullstack.consts.SessionConstants;
import me.zbl.fullstack.entity.Article;
import me.zbl.fullstack.entity.Comments;
import me.zbl.fullstack.entity.User;
import me.zbl.fullstack.entity.vo.PostView;
import me.zbl.fullstack.mapper.CommentsMapper;
import me.zbl.fullstack.service.api.ICommentService;
import me.zbl.fullstack.service.base.BaseViewTransableService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 评论业务实现类
 *
 * @author James
 */
@Service
public class CommentService extends BaseViewTransableService<Article, PostView> implements ICommentService {

  @Autowired
  CommentsMapper mCommentsMapper;

@Override
public void addComment(Integer user_id,Integer article_id, String content) {
    Comments comments = new Comments();
    comments.setArticleId(article_id);
    comments.setUserId(user_id);
    comments.setComment(content);
    comments.setCreateTime(new Date());
    mCommentsMapper.insertSelective(comments);
  }

@Override
protected List<PostView> transEntityToView(List<Article> entityList) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public List<Comments> getCommentList(Integer article_id,Integer pageNum) {
	List<Comments> commentList = mCommentsMapper.getCommentList(article_id,(pageNum-1)*10,pageNum*10);
	return commentList;
}
}

