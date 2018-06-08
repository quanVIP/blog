package me.zbl.fullstack.service.api;

import java.util.List;

import me.zbl.fullstack.entity.Comments;
import me.zbl.fullstack.entity.User;


/**
 * 评论服务接口
 *
 * @author James
 */
public interface ICommentService {

  /**
   * 增加评论
   *
   */
  void addComment(User user,Integer article_id, String content,Integer commentId);
  
  List<Comments> getCommentList(Integer article_id,Integer pageNum);
  
  List<Comments> getReplyList(Integer article_id,List<Comments> commentList);
  
  int getCount(Integer article_id);

}
