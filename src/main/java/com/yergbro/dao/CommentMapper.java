package com.yergbro.dao;

import com.yergbro.domain.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CommentMapper {

    int insert(Comment record);

    Comment selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(Comment record);

    int addComment(Comment comment);

    int updateCommentMsg(Comment comment);

    int deleteComment(Integer id);

    List<Comment> allComment();

    List<Comment> commentOfSongId(Integer songId);

    List<Comment> commentOfSongListId(Integer songListId);
}