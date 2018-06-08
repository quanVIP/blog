package me.zbl.fullstack.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import me.zbl.fullstack.entity.Comments;
import tk.mybatis.mapper.common.Mapper;

public interface CommentsMapper extends Mapper<Comments> {
	
	@Select("select id,comment_id as commentId, user_name as userName,comment,create_time as createTime from comments where article_id=#{article_id} and comment_id is null order by createTime desc limit #{pageStart},#{pageEnd}")
	List<Comments> getCommentList(@Param("article_id")Integer article_id,@Param("pageStart")Integer pageStart,@Param("pageEnd")Integer pageEnd);
	
	@Select("select id,comment_id as commentId, user_name as userName,comment,create_time as createTime from comments where comment_id in (#{ids[0]},#{ids[1]},#{ids[2]},#{ids[3]},#{ids[4]}) and article_id=#{article_id}  ")
	List<Comments> getReplyList(@Param("article_id")Integer article_id,@Param("ids")int[] ids);
	
	@Select("select count(*) from comments where article_id=#{article_id} ")
	int getCount(@Param("article_id")Integer article_id);

}