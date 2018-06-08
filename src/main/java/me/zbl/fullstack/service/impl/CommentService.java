package me.zbl.fullstack.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
public void addComment(User user,Integer article_id, String content ,Integer commentId) {
    Comments comments = new Comments();
    comments.setArticleId(article_id);
    comments.setUserId(user.getId());
    comments.setUserName(user.getNickname());
    comments.setComment(content);
    comments.setCommentId(commentId);
    SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" ); 
    String date = sdf.format(new Date()); 
    comments.setCreateTime(date);
    mCommentsMapper.insertSelective(comments);
  }

@Override
protected List<PostView> transEntityToView(List<Article> entityList) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public List<Comments> getCommentList(Integer article_id,Integer pageNum) {
	List<Comments> commentList = mCommentsMapper.getCommentList(article_id,(pageNum-1)*5,5);
	return commentList;
}

@Override
public int getCount(Integer article_id) {
	int count = mCommentsMapper.getCount(article_id);
	return count;
}

@Override
public List<Comments> getReplyList(Integer article_id, List<Comments> commentList) {
	int ids[] = new  int[5];
	for(int i=0;i<commentList.size();i++){
		ids[i]=commentList.get(i).getId();
	}
	List<Comments> replyList = mCommentsMapper.getReplyList(article_id, ids);
	return replyList;
}
}

