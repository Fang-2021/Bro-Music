package com.yergbro.service;

import com.yergbro.domain.Comment;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CommentService {

    boolean addComment(Comment comment);

    boolean updateCommentMsg(Comment comment);

    boolean deleteComment(Integer id);

    List<Comment> allComment();

    List<Comment> commentOfSongId(Integer songId);

    List<Comment> commentOfSongListId(Integer songListId);
}
