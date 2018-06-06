package me.zbl.fullstack.service.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import me.zbl.fullstack.entity.Comments;


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
  void addComment(Integer user_id,Integer article_id, String content);
  
  List<Comments> getCommentList(Integer article_id,Integer pageNum);

}
