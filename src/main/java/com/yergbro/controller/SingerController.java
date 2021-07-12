package com.yergbro.controller;

import com.alibaba.fastjson.JSONObject;
import com.yergbro.domain.Singer;
import com.yergbro.service.imp.SingerServiceImpl;
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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@Controller
@RequestMapping("/client")
public class SingerController {
@Autowired
    private SingerServiceImpl singerService;

@Configuration
    public class  MyPicConfig implements WebMvcConfigurer{
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/img/singerPic/**").addResourceLocations("file:"+"./img/singerPic/");

        }
    }

    @ResponseBody
    @RequestMapping(value = "/singer/add",method = RequestMethod.POST)
    public Object addSinger(HttpServletRequest req){
        JSONObject jsonObject = new JSONObject();
        String name = req.getParameter("name");
        String sex = req.getParameter("sex");
        String pic = req.getParameter("pic");
        String location = req.getParameter("location");
        String introduction = req.getParameter("introduction");
        String birth = req.getParameter("birth");

        Singer singer = new Singer();
        singer.setName(name);
        singer.setSex(new Byte(sex));
        singer.setPic(pic);
        singer.setLocation(location);
        singer.setIntroduction(introduction);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date myBirth = new Date();
        try {
            myBirth = dateFormat.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        singer.setBirth(myBirth);
        boolean res = singerService.addSinger(singer);
        if(res){
            jsonObject.put("code",1);
            jsonObject.put("msg","添加成功");
            return jsonObject;
        }else {
            jsonObject.put("code",0);
            jsonObject.put("msg","添加失败");
            return jsonObject;
        }
    }
    //返回所有歌手
    @RequestMapping(value = "/singer",method = RequestMethod.GET)
    public Object allSinger(){
        return singerService.allSinger();
    }

    //根据歌手名查找歌手
    @RequestMapping(value = "/singer/name/detail",method = RequestMethod.GET)
    public Object singerOfName(HttpServletRequest req){
        String name = req.getParameter("name").trim();
        return singerService.singerOfName(name);
    }
    //根据歌手性别查找歌手
    @RequestMapping(value = "/singer/sex/detail",method = RequestMethod.GET)
    public Object singerOfSex(HttpServletRequest req){
        String sex = req.getParameter("sex").trim();
        return singerService.singerOfSex(Integer.parseInt(sex));
    }

    //删除歌手
    @RequestMapping(value = "/singer/delete",method = RequestMethod.GET)
    public Object deleteSinger(HttpServletRequest req){

        String id = req.getParameter("id").trim();
        return singerService.deleteSingerByID(Integer.parseInt(id));
    }

    //更新歌手
    @ResponseBody
    @RequestMapping(value = "/singer/update",method = RequestMethod.POST)
    public Object updateSinger(HttpServletRequest req){
        JSONObject jsonObject = new JSONObject();

        String id = req.getParameter("id").trim();
        String name = req.getParameter("name").trim();
        String sex = req.getParameter("sex").trim();
        String pic = req.getParameter("pic").trim();
        String birth = req.getParameter("birth").trim();
        String location = req.getParameter("location").trim();
        String introduction = req.getParameter("introduction").trim();

        Singer singer = new Singer();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date mybirth = new Date();
        try {
            mybirth=dateFormat.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        singer.setId(Integer.parseInt(id));
        singer.setName(name);
        singer.setSex(new Byte(sex));
        singer.setPic(pic);
        singer.setBirth(mybirth);
        singer.setLocation(location);
        singer.setIntroduction(introduction);
        boolean res = singerService.updateSingerMsg(singer);
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


    @ResponseBody
    @RequestMapping(value = "/singer/avatar/update",method = RequestMethod.POST)
    public Object updateSingerPic(@RequestParam("file")MultipartFile avatarFile,@RequestParam("id") int id){
        JSONObject jsonObject = new JSONObject();
        if(avatarFile.isEmpty()){
            jsonObject.put("code",0);
            jsonObject.put("msg","文件上传失败");
            return jsonObject;
        }
        String fileName = System.currentTimeMillis() + avatarFile.getOriginalFilename();
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "img" + System.getProperty("file.separator") + "singerPic";
        File file1 =new File(filePath);
        if(file1.exists()){
            file1.mkdir();
        }

        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        String storeAvatarPath="/img/singerPic/"+fileName;
        try {
            avatarFile.transferTo(dest);
            Singer singer = new Singer();
            singer.setId(id);
            singer.setPic(storeAvatarPath);
            boolean res = singerService.updateSingerPic(singer);
            if(res){
                jsonObject.put("code", 1);
                jsonObject.put("pic", storeAvatarPath);
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

    //根据歌手ID查找歌手
    @RequestMapping(value = "/singer/id/detail",method = RequestMethod.GET)
    public Object singerOfId(HttpServletRequest req){
        String id = req.getParameter("id").trim();
        return singerService.singerOfId(Integer.parseInt(id));
    }
}
