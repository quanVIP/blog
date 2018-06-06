package me.zbl.fullstack.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import me.zbl.fullstack.entity.Comments;
import me.zbl.fullstack.framework.mapper.IMyMapper;
import tk.mybatis.mapper.common.Mapper;

public interface CommentsMapper extends IMyMapper<Comments> {
	
	@Select("select * from comments where article_id = #{article_id} order by create_time desc limit #{pageStart},#{pageEnd}")
	List<Comments> getCommentList(@Param("article_id")Integer article_id,@Param("pageStart")Integer pageStart,@Param("pageEnd")Integer pageEnd);
}