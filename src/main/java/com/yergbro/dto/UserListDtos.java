package com.yergbro.dto;

import com.yergbro.domain.Comment;
import com.yergbro.domain.Consumer;
import lombok.Data;

import java.util.List;
@Data
public class UserListDtos {
    public List<Comment> commentList;
}
