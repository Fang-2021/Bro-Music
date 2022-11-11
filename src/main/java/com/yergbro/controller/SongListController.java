package com.yergbro.controller;

import com.alibaba.fastjson.JSONObject;
import com.yergbro.domain.SongList;
import com.yergbro.service.imp.SongListServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@RestController
@Controller
@RequestMapping("/client")
public class SongListController {
    @Autowired
    private SongListServiceImpl songListService;

    @Configuration
    public class MyPicConfig implements WebMvcConfigurer{
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/img/songListPic/**").addResourceLocations("file:"+"./img/songListPic/");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/songList/add",method = RequestMethod.POST)
    public Object addSongList(HttpServletRequest req){
        JSONObject jsonObject = new JSONObject();
        String title = req.getParameter("title").trim();
        String pic = req.getParameter("pic").trim();
        String introduction = req.getParameter("introduction").trim();
        String style = req.getParameter("style").trim();
        if(title.equals("")||title==null){
            jsonObject.put("code",0);
            jsonObject.put("msg","标题错误");
            return jsonObject;
        }
        SongList songList = new SongList();
        songList.setTitle(title);
        songList.setPic(pic);
        songList.setIntroduction(introduction);
        songList.setStyle(style);

        boolean res = songListService.addSongList(songList);
        if(res){
            jsonObject.put("code", 1);
            jsonObject.put("msg","添加成功");
            return  jsonObject;
        }else {
            jsonObject.put("code", 0);
            jsonObject.put("msg","添加失败");
            return  jsonObject;
        }
    }

    //返回所有歌单
    @RequestMapping(value = "/songList",method = RequestMethod.GET)
    public Object allSongList(){
        return songListService.allSongList();
    }

    //删除歌单
    @RequestMapping(value = "/songList/delete",method = RequestMethod.GET)
    public Object deleteSongList(HttpServletRequest req){
        String id = req.getParameter("id").trim();
        return songListService.deleteSongList(Integer.parseInt(id));
    }

    //根据Title返回歌单
    @RequestMapping(value = "/songList/title/detail",method = RequestMethod.GET)
    public  Object songListOfTitle(HttpServletRequest req){
        String title = req.getParameter("title").trim();
        return songListService.songListOfTitle(title);
    }

    //返回Title包含文字的歌单
    @RequestMapping(value = "/songList/likeTitle/detail",method = RequestMethod.GET)
    public Object songListOfLikeTitle(HttpServletRequest req){
        String title = req.getParameter("title").trim();
        return songListService.likeTitle('%'+title+'%');
    }

    //返回Style包含文字的歌单
    @RequestMapping(value = "/songList/likeStyle/detail",method = RequestMethod.GET)
    public  Object songListOfLikeStyle(HttpServletRequest req){
        String style = req.getParameter("style").trim();
        return songListService.likeStyle('%'+style+'%');
    }

    //更新歌单信息
    @ResponseBody
    @RequestMapping(value = "/songList/update",method = RequestMethod.POST)
    public Object updateSongListMsg(HttpServletRequest req){
        JSONObject jsonObject = new JSONObject();
        String id = req.getParameter("id").trim();
        String title = req.getParameter("title").trim();
        String pic = req.getParameter("pic").trim();
        String introduction = req.getParameter("introduction").trim();
        String style = req.getParameter("style").trim();

        SongList songList = new SongList();
        songList.setId(Integer.parseInt(id));
        songList.setTitle(title);
        songList.setPic(pic);
        songList.setIntroduction(introduction);
        songList.setStyle(style);

        boolean res = songListService.updateSongListMsg(songList);
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
    //修改歌单封面
    @ResponseBody
    @RequestMapping(value = "/songList/updatePic",method = RequestMethod.POST)
    public Object updateSongListPic(@RequestParam("file")MultipartFile file,@RequestParam("id")int id){
        JSONObject jsonObject = new JSONObject();
        if(file.isEmpty()){
            jsonObject.put("code",0);
            jsonObject.put("msg","文件为空");
            return  jsonObject;
        }
        String fileName = System.currentTimeMillis() + file.getOriginalFilename();
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "img" + System.getProperty("file.separator") + "songListPic";
        File file1 = new File(filePath);
        if(!file1.exists()){
            file1.mkdir();
        }
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        String storeAvatarPath= "/img/songListPic/"+fileName;

        try {
            file.transferTo(dest);
            SongList songList = new SongList();
            songList.setId(id);
            songList.setPic(storeAvatarPath);
            boolean res = songListService.updateSongListImg(songList);
            if(res){
                jsonObject.put("code",1);
                jsonObject.put("msg","上传成功");
                jsonObject.put("avatar",storeAvatarPath);
                return jsonObject;
            }else {
                jsonObject.put("code",0);
                jsonObject.put("msg","上传失败");
                return jsonObject;
            }
        } catch (IOException e) {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "上传失败" + e.getMessage());
            return jsonObject;
        }finally {
            return jsonObject;
        }
    }
}
