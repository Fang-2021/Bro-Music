package com.yergbro.controller;

import com.alibaba.fastjson.JSONObject;
import com.yergbro.domain.Comment;
import com.yergbro.service.imp.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@Controller
@RequestMapping("/client")
public class CommentController {
    @Autowired
    private CommentServiceImpl commentService;
    //添加评论
    @ResponseBody
    @RequestMapping(value = "/comment/add",method = RequestMethod.POST)
    public Object addComment(HttpServletRequest req){
        JSONObject jsonObject = new JSONObject();
        String userId = req.getParameter("userId");
        String songId = req.getParameter("songId");
        String songListId = req.getParameter("songListId");
        String type = req.getParameter("type");
        String content = req.getParameter("content").trim();
        Comment comment = new Comment();
        comment.setUserId(Integer.parseInt(userId));
        comment.setType(new Byte(type));

        if(new Byte(type) ==0){
            comment.setSongId(Integer.parseInt(songId));
        }else if (new Byte(type)==1){
            comment.setSongListId(Integer.parseInt(songListId));
        }
        comment.setContent(content);
        comment.setCreateTime(new Date());
        boolean res = commentService.addComment(comment);
        if(res){
            jsonObject.put("code",1);
            jsonObject.put("msg","评论成功");
            return jsonObject;
        }else{
            jsonObject.put("code",0);
            jsonObject.put("msg","评论失败");
            return jsonObject;
        }
    }

    //获取所有评论列表
    @RequestMapping(value = "/comment",method = RequestMethod.GET)
    public Object allComment(){
        return commentService.allComment();
    }
    //返回指定歌曲Id的评论列表
    @RequestMapping(value = "/comment/song/detail",method = RequestMethod.GET)
    public Object commentOfSongId(HttpServletRequest req){
        String songId = req.getParameter("songId");
        return commentService.commentOfSongId(Integer.parseInt(songId));
    }

    //返回指定歌单Id的评论列表
    @RequestMapping(value = "/comment/songList/detail",method = RequestMethod.GET)
    public Object commentOfSongListId(HttpServletRequest req){
        String songListId = req.getParameter("songListId");
        return commentService.commentOfSongListId(Integer.parseInt(songListId));
    }

    //点赞
    @ResponseBody
    @RequestMapping(value = "/comment/like",method = RequestMethod.POST)
    public Object commentOfLike(HttpServletRequest req){
        JSONObject jsonObject = new JSONObject();
        String up = req.getParameter("up").trim();
        String id = req.getParameter("id").trim();

        Comment comment = new Comment();
        comment.setUp(Integer.parseInt(up));
        comment.setId(Integer.parseInt(id));

        boolean res = commentService.updateCommentMsg(comment);
        if(res){
            jsonObject.put("code",1);
            jsonObject.put("msg","点赞成功");
            return jsonObject;
        }else{
            jsonObject.put("code",0);
            jsonObject.put("msg","点赞失败");
            return jsonObject;
        }
    }

    //删除评论
    @RequestMapping(value = "/comment/delete",method = RequestMethod.GET)
    public Object deleteComment(HttpServletRequest req){
        String id = req.getParameter("id");
        return commentService.deleteComment(Integer.parseInt(id));
    }

    @ResponseBody
    @RequestMapping(value = "/comment/update",method = RequestMethod.POST)
    public  Object updateCommentMsg(HttpServletRequest req){
        JSONObject jsonObject = new JSONObject();
        String id = req.getParameter("id").trim();
        String userId = req.getParameter("userId").trim();
        String songId = req.getParameter("songId").trim();
        String songListId = req.getParameter("songListId").trim();
        String content = req.getParameter("content").trim();
        String type = req.getParameter("type").trim();
        String up = req.getParameter("up").trim();
        Comment comment = new Comment();
        comment.setId(Integer.parseInt(id));
        comment.setUserId(Integer.parseInt(userId));

        if(songId==""){
            comment.setSongId(null);
        }else{
            comment.setSongId(Integer.parseInt(songId));
        }

        if(songListId==""){
            comment.setSongListId(null);
        }else{
            comment.setSongListId(Integer.parseInt(songListId));
        }
        comment.setType(new Byte(type));
        comment.setContent(content);
        comment.setUp(Integer.parseInt(up));
        boolean res = commentService.updateCommentMsg(comment);
        if(res){
            jsonObject.put("code",1);
            jsonObject.put("msg","更新成功");
            return jsonObject;
        }else {
            jsonObject.put("code",0);
            jsonObject.put("msg","更新失败");
            return jsonObject;
        }
    }
}
