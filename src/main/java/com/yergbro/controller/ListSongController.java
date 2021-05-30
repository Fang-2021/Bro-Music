package com.yergbro.controller;

import com.alibaba.fastjson.JSONObject;
import com.yergbro.domain.ListSong;
import com.yergbro.service.imp.ListSongServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Controller
@RequestMapping("/client")
public class ListSongController {
    @Autowired
    private ListSongServiceImpl listSongService;
    //给歌单添加歌曲
    @ResponseBody
    @RequestMapping(value = "/listSong/add",method = RequestMethod.POST)
    public Object addListSong(HttpServletRequest req){
        JSONObject jsonObject = new JSONObject();
        String songId = req.getParameter("songId");
        String songListId = req.getParameter("songListId");

        ListSong listSong = new ListSong();
        listSong.setSongListId(Integer.parseInt(songListId));
        listSong.setSongId(Integer.parseInt(songId));
        boolean res = listSongService.addListSong(listSong);
        if(res){
            jsonObject.put("code",1);
            jsonObject.put("msg","添加成功");
            return jsonObject;
        }else{
            jsonObject.put("code",0);
            jsonObject.put("msg","添加失败");
            return jsonObject;
        }
    }

    //返回歌单中所有歌曲
    @RequestMapping(value = "/listSong",method = RequestMethod.GET)
    public Object allListSong(HttpServletRequest req){
        return  listSongService.allListSong();
    }

    //返回指定歌单ID的歌曲
    @RequestMapping(value = "/listSong/detail",method = RequestMethod.GET)
    public Object listSongOfSongListId(HttpServletRequest req){
        String songListId = req.getParameter("songListId");
        return listSongService.listSongOfSongListId(Integer.parseInt(songListId));
    }

    //删除歌单里的歌曲
    @RequestMapping(value = "/listSong/delete",method = RequestMethod.GET)
    public Object deleteListSong(HttpServletRequest req){
        String songId = req.getParameter("songId");
        return listSongService.deleteListSongBySongID(Integer.parseInt(songId));
    }

    //更新歌单里的歌曲
    @ResponseBody
    @RequestMapping(value = "/listSong/update",method = RequestMethod.POST)
    public Object updateListSongMsg(HttpServletRequest req){
        JSONObject jsonObject = new JSONObject();
        String id = req.getParameter("id").trim();
        String songId = req.getParameter("songId").trim();
        String songListId = req.getParameter("songListId").trim();

        ListSong listSong = new ListSong();
        listSong.setId(Integer.parseInt(id));
        listSong.setSongId(Integer.parseInt(songId));
        listSong.setSongListId(Integer.parseInt(songListId));

        boolean res = listSongService.updateListSongMsg(listSong);
        if(res){
            jsonObject.put("code",1);
            jsonObject.put("msg","更新成功");
            return jsonObject;
        }else{
            jsonObject.put("code",0);
            jsonObject.put("msg","更新失败");
            return jsonObject;
        }
    }
}
