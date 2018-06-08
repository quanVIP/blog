package me.zbl.fullstack.controller;

import static me.zbl.fullstack.consts.ViewConsts.VIEW_ARTICLE;
import static me.zbl.fullstack.consts.ViewConsts.VIEW_POSTLIST;
import static me.zbl.fullstack.consts.ViewConsts.VIEW_TAGLIST;
import static me.zbl.fullstack.consts.ViewConsts.VIEW_TITLE;
import static me.zbl.fullstack.consts.ViewConsts.VIEW_COMMENTS;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import me.zbl.fullstack.controller.base.BaseController;
import me.zbl.fullstack.entity.Article;
import me.zbl.fullstack.entity.Comments;
import me.zbl.fullstack.entity.dto.form.ArticleSearchForm;
import me.zbl.fullstack.entity.vo.PostView;
import me.zbl.fullstack.entity.vo.TagView;
import me.zbl.fullstack.service.api.IAdminBlogService;
import me.zbl.fullstack.service.api.ICommentService;
import me.zbl.fullstack.service.api.IPostsService;
import me.zbl.fullstack.service.api.ITagService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 博客控制器
 *
 * @author James
 */
@Controller
public class PostContoller extends BaseController {

  @Autowired
  private IPostsService mPostService;

  @Autowired
  private ITagService mTagService;

  @Autowired
  private IAdminBlogService mBlogService;
  
  @Autowired
  private ICommentService mComment;

  /**
   * 博客列表页
   * 视图模型：
   * 1.博客列表（文章标题/内容简介/发布时间）
   * 2.标签（tagId/文章总数）
   */
  @GetMapping("/post")
  public String pPostList(HttpServletRequest request, Model model, Integer tagId) throws Exception {
    List<PostView> postViewList = null;
    if (null != tagId) {
      postViewList = mPostService.getPostListByTagId(tagId);
    } else {
      postViewList = mPostService.getPostList();
    }
    List<TagView> tagViewList = mTagService.getAllTagView();
    addModelAtt(model, VIEW_TAGLIST, tagViewList);
    addModelAtt(model, VIEW_POSTLIST, postViewList);
    return "posts";
  }

  /**
   * 查看文章内容页
   *
   * @param id 文章 id
   */
  @GetMapping("/blog/{id}")
  public String pFrontBlogView(HttpServletRequest request, Model model, @PathVariable Integer id) throws Exception {
    Article article = mBlogService.blogSelectByPrimaryKey(id);
    PostView postView = new PostView(article);
    addModelAtt(model, VIEW_ARTICLE, postView);
    addModelAtt(model, VIEW_TITLE, article.getTitle());
//    List<Comments> commentList = mComment.getCommentList(article.getId(),1);
//    addModelAtt(model, VIEW_COMMENTS, commentList);
    return "article";
  }

  /**
   * 文章搜索结果页
   * <p>
   * 根据指定条件查找文章
   * <p>
   * 视图模型：
   * 博客列表（文章标题/内容简介/发布时间）
   */
  @GetMapping("/postsearch")
  public String pPostSearch(HttpServletRequest request, Model model, ArticleSearchForm form) throws Exception {
    List<PostView> postViewList = mPostService.getPostListByArticleCondition(form);
    addModelAtt(model, VIEW_POSTLIST, postViewList);
    List<TagView> tagViewList = mTagService.getAllTagView();
    addModelAtt(model, VIEW_TAGLIST, tagViewList);
    return "posts";
  }
}
