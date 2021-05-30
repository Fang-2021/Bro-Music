package com.yergbro.controller;

import com.alibaba.fastjson.JSONObject;
import com.yergbro.domain.Collect;
import com.yergbro.service.CollectService;
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
public class CollectController {
    @Autowired
    private CollectService collectService;
    //添加收藏的歌曲
    @ResponseBody
    @RequestMapping(value = "/collection/add",method = RequestMethod.POST)
    public Object addCollection(HttpServletRequest req){
        JSONObject jsonObject = new JSONObject();
        String user_id = req.getParameter("user_id");
        String type = req.getParameter("type");
        String song_id = req.getParameter("song_id");
        String song_list_id = req.getParameter("song_list_id");
        if(song_id.equals("")){
            jsonObject.put("code",0);
            jsonObject.put("msg","收藏歌曲为空");
            return jsonObject;
        }
        if(collectService.existSongId(Integer.parseInt(user_id) ,Integer.parseInt(song_id))){
            boolean res = collectService.deleteCollect(Integer.parseInt(user_id), Integer.parseInt(song_id));
            if(res){
                jsonObject.put("code",2);
                jsonObject.put("msg","已取消收藏！");
                return jsonObject;
            }else{
                jsonObject.put("code",0);
                jsonObject.put("msg","取消收藏失败！");
                return jsonObject;
            }

        }else{
            Collect collect = new Collect();
            collect.setUserId(Integer.parseInt(user_id));
            collect.setType(new Byte(type));
            if(new Byte(type)==0){
                collect.setSongId(Integer.parseInt(song_id));
            }
            else if(new Byte(type)==1){
                collect.setSongListId(Integer.parseInt(song_list_id));
            }
            collect.setCreateTime(new Date());
            boolean res = collectService.addCollection(collect);
            if(res){
                jsonObject.put("code",1);
                jsonObject.put("msg","收藏成功");
                return jsonObject;
            }else{
                jsonObject.put("code",0);
                jsonObject.put("msg","收藏失败");
                return  jsonObject;
            }
        }




    }
    //返回所有用户收藏列表
    @RequestMapping(value = "/collection",method =  RequestMethod.GET)
    public Object allCollection(){ return collectService.allCollect();}
    //返回指定用户ID收藏列表
    @RequestMapping(value = "/collection/detail",method =RequestMethod.GET)
    public Object collectionOfUser(HttpServletRequest req) {
        String userId = req.getParameter("userId");
        return collectService.collectionOfUser(Integer.parseInt(userId));
    }

    //删除收藏的歌曲
    @RequestMapping(value = "/collection/delete",method = RequestMethod.GET)
    public Object deleteCollection(HttpServletRequest req){
        String userId = req.getParameter("userId").trim();
        String songId = req.getParameter("songId").trim();
        return collectService.deleteCollect(Integer.parseInt(userId) , Integer.parseInt(songId));
    }

    //更新收藏
    @ResponseBody
    @RequestMapping(value = "/collection/update",method = RequestMethod.POST)
    public Object updateCollection(HttpServletRequest req){
        JSONObject jsonObject = new JSONObject();
        String userId = req.getParameter("userId").trim();
        String songId = req.getParameter("songId").trim();
        String type = req.getParameter("type").trim();
        String id = req.getParameter("id").trim();

        Collect collect = new Collect();
        collect.setId(Integer.parseInt(id));
        collect.setUserId(Integer.parseInt(userId));
        collect.setSongId(Integer.parseInt(songId));
        collect.setType(new Byte(type));

        boolean res = collectService.updateCollectMsg(collect);
        if(res){
            jsonObject.put("code",1);
            jsonObject.put("msg","更新成功");
            return  jsonObject;
        }else{
            jsonObject.put("code",0);
            jsonObject.put("msg","更新失败");
            return  jsonObject;
        }
    }

    //判断是否收藏
    @ResponseBody
    @RequestMapping(value = "/collection/isCollected",method = RequestMethod.POST)
    public Object isCollected(HttpServletRequest req){
        JSONObject jsonObject = new JSONObject();
        String songId = req.getParameter("song_id").trim();
        String userId = req.getParameter("user_id").trim();
        boolean res = collectService.existSongId(Integer.parseInt(userId), Integer.parseInt(songId));
        if(res){
            jsonObject.put("code",1);
            jsonObject.put("msg","已收藏");
            return  jsonObject;
        }else{
            jsonObject.put("code",0);
            jsonObject.put("msg","未收藏");
            return  jsonObject;
        }
    }
}
