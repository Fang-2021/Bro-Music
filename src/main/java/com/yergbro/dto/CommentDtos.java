package com.yergbro.dto;

import com.yergbro.domain.Comment;
import lombok.Data;

import java.util.List;

@Data
public class CommentDtos {
    public List<String> userName;
    public List<String> userPic;
}
