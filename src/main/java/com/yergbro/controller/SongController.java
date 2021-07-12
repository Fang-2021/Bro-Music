package com.yergbro.controller;

import com.alibaba.fastjson.JSONObject;
import com.yergbro.domain.Singer;
import com.yergbro.domain.Song;
import com.yergbro.service.imp.SongServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@Controller
@RequestMapping("/client")
public class SongController {
@Autowired
    private SongServiceImpl songService;
//用于控制最大请求大小
@Bean
    public MultipartConfigElement multipartConfigElement(){
    MultipartConfigFactory factory= new MultipartConfigFactory();
    //最大文件10M
    factory.setMaxFileSize(DataSize.of(50, DataUnit.MEGABYTES));
    //上传数据最大10M
    factory.setMaxRequestSize(DataSize.of(50,DataUnit.MEGABYTES));
    return factory.createMultipartConfig();
    }

    @Configuration
    public class  MyPicConfig implements WebMvcConfigurer{
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/img/songPic/**").addResourceLocations("file:"+"./img/songPic/");
            registry.addResourceHandler("/song/**").addResourceLocations("file:"+"./song/");
        }
    }
    //添加歌曲
    @ResponseBody
    @RequestMapping(value = "/song/add",method = RequestMethod.POST)
    public Object addSong(HttpServletRequest req){
        JSONObject jsonObject = new JSONObject();
        String singerId = req.getParameter("singerId").trim();
        String name = req.getParameter("name").trim();
        String introduction = req.getParameter("introduction").trim();
        String pic = "/img/songPic/default.jpg";
        String lyric = req.getParameter("lyric").trim();



        Song song = new Song();
        song.setSingerId(Integer.parseInt(singerId));
        song.setName(name);
        song.setIntroduction(introduction);
        song.setCreateTime(new Date());
        song.setUpdateTime(new Date());
        song.setPic(pic);
        song.setLyric(lyric);
        song.setLyric(lyric);
        boolean res = songService.addSong(song);
        if(res){
            jsonObject.put("code", 1);
            jsonObject.put("msg", "添加成功");
            return jsonObject;
        } else {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "添加失败");
            return jsonObject;
        }
    }
    //返回所有歌曲
    @RequestMapping(value = "/song",method = RequestMethod.GET)
    public Object allSong(){
        return songService.allSong();
    }

    //返回指定歌单ID的歌曲
    @RequestMapping(value = "/song/detail",method = RequestMethod.GET)
    public Object songOfId(HttpServletRequest req){
        String id = req.getParameter("id");
        return  songService.songOfId(Integer.parseInt(id));
    }

    //返回指定歌名的歌曲
    @RequestMapping(value = "/song/name/detail",method = RequestMethod.GET)
    public  Object songOfName(HttpServletRequest req){
        String name = req.getParameter("name").trim();
        return songService.songOfName(name);
    }

    //返回指定歌手姓名的歌曲
    @RequestMapping(value = "song/singerName/detail",method = RequestMethod.GET)
    public JSONObject songOfSinger(HttpServletRequest req){
        JSONObject jsonObject = new JSONObject();
        String name = req.getParameter("name");
        List<Song> songs = songService.songOfSingerName(name);
        if(songs.size()>0){
            jsonObject.put("code", 1);
            jsonObject.put("msg", "成功");
            jsonObject.put("song",songs);
            return jsonObject;
        } else {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "失败");
            return jsonObject;
        }
    }
    //返回指定歌手ID的歌曲
    @RequestMapping(value = "/song/singerId/detail",method = RequestMethod.GET)
    public Object songOfSingerId(HttpServletRequest req){
        String singerId = req.getParameter("singerId");
        return songService.songOfSingerId(Integer.parseInt(singerId));
    }

    //删除指定ID歌曲
    @RequestMapping(value = "/song/delete",method = RequestMethod.GET)
    public Object deleteSong(HttpServletRequest req){
        String id = req.getParameter("id");
        return songService.deleteSong(Integer.parseInt(id));
    }

    @ResponseBody
    @RequestMapping(value = "/song/update",method = RequestMethod.POST)
    public Object updateSongMsg(HttpServletRequest req){
        JSONObject jsonObject = new JSONObject();
        String id = req.getParameter("id").trim();
        String name = req.getParameter("name").trim();
        String introduction = req.getParameter("introduction").trim();
        String lyric = req.getParameter("lyric").trim();

        Song song = new Song();
        song.setId(Integer.parseInt(id));
        song.setName(name);
        song.setIntroduction(introduction);
        song.setLyric(lyric);
        song.setUpdateTime(new Date());
        boolean res = songService.updateSongMsg(song);
        if(res){
            jsonObject.put("code", 1);
            jsonObject.put("msg", "修改成功");
            return jsonObject;
        }else {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "修改失败");
            return jsonObject;
        }
    }

    //更新歌曲图片
    @ResponseBody
    @RequestMapping(value = "/song/updatePic",method = RequestMethod.POST)
    public Object updateSongPic(@RequestParam("file")MultipartFile urlFile,@RequestParam("id") int id){
        JSONObject jsonObject = new JSONObject();
        if(urlFile.isEmpty()){
            jsonObject.put("code",0);
            jsonObject.put("msg","文件不存在");
            return jsonObject;
        }
        String fileName = System.currentTimeMillis() + urlFile.getOriginalFilename();
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "img" + System.getProperty("file.separator")+"songPic";
        File file1 = new File(filePath);
        if(!file1.exists()){
            file1.mkdir();
        }

        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        String storeUrlPath="/img/songPic/"+fileName;
        try {
            urlFile.transferTo(dest);
            Song song = new Song();
            song.setId(id);
            song.setPic(storeUrlPath);
            boolean res = songService.updateSongPic(song);
            if(res){
                jsonObject.put("code", 1);
                jsonObject.put("avatar", storeUrlPath);
                jsonObject.put("msg", "上传成功");
                return jsonObject;
            }else {
                jsonObject.put("code", 0);
                jsonObject.put("msg", "上传失败");
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

    //更新歌曲URL
    @ResponseBody
    @RequestMapping(value = "/song/updateUrl",method = RequestMethod.POST)
    public Object updateSongUrl(@RequestParam("file")MultipartFile urlFile ,@RequestParam("id")int id){
        JSONObject jsonObject = new JSONObject();
        if(urlFile.isEmpty()){
            jsonObject.put("code",0);
            jsonObject.put("msg","文件为空");
            return  jsonObject;
        }
        String fileName = System.currentTimeMillis() + urlFile.getOriginalFilename();
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "song";
        File file1 = new File(filePath);
        if(!file1.exists()){
            file1.mkdir();
        }
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        String storeUrlPath="/song/"+fileName;
        try {
            urlFile.transferTo(dest);
            Song song = new Song();
            song.setId(id);
            song.setUrl(storeUrlPath);
            boolean res = songService.updateSongUrl(song);
            if(res){
                jsonObject.put("code",1);
                jsonObject.put("msg","上传成功");
                return jsonObject;
            }else{
                jsonObject.put("code",1);
                jsonObject.put("msg","上传失败");
                return jsonObject;
            }
        } catch (IOException e) {
            jsonObject.put("code",0);
            jsonObject.put("msg","上传失败"+e.getMessage());
            return jsonObject;
        }finally {
            return jsonObject;
        }
    }
}

