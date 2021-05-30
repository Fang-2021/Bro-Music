package com.yergbro.controller;

import com.alibaba.fastjson.JSONObject;
import com.yergbro.domain.Consumer;
import com.yergbro.service.imp.ConsumerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
public class ConsumerController {
    @Autowired
    private ConsumerServiceImpl consumerService;

    //图片输入流,加载图片资源
    @Configuration
    public class MyPicConfig implements WebMvcConfigurer{
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry){
            registry.addResourceHandler("/avatarImages/**").addResourceLocations("file:D:/MyIDEAFreeFile/bro-music/avatarImages/");
        }
    }
    //添加用户
    @ResponseBody
    @RequestMapping(value = "/user/add",method = RequestMethod.POST)
    public Object addUser(HttpServletRequest req){
        JSONObject jsonObject = new JSONObject();
        String username = req.getParameter("username").trim();
        String password = req.getParameter("password").trim();
        String sex = req.getParameter("sex").trim();
        String phone_num = req.getParameter("phone_num").trim();
        String email = req.getParameter("email").trim();
        String birth = req.getParameter("birth").trim();
        String introduction = req.getParameter("introduction").trim();
        String location = req.getParameter("location").trim();
        String avatar = req.getParameter("avatar").trim();
        List<Consumer> userByUsername = consumerService.getUserByUsername(username);
        if(userByUsername.size()>0){
            jsonObject.put("code",2);
            jsonObject.put("msg","用户名已存在");
            return jsonObject;
        }
        if(username.equals("")||username==null){
            jsonObject.put("code",0);
            jsonObject.put("msg","用户名或密码错误");
            return jsonObject;
        }
        Consumer consumer = new Consumer();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date myBirth = new Date();

        try {
            myBirth=dateFormat.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        consumer.setUsername(username);
        consumer.setPassword(password);
        consumer.setSex(new Byte(sex));

        if(phone_num.equals("")){
            consumer.setPhoneNum(null);
        }else{
            consumer.setPhoneNum(phone_num);
        }
        if(email.equals("")){
            consumer.setEmail(null);
        }else{
            consumer.setEmail(email);
        }

        consumer.setBirth(myBirth);
        consumer.setIntroduction(introduction);
        consumer.setLocation(location);
        consumer.setAvatar(avatar);
        consumer.setCreateTime(new Date());
        consumer.setUpdateTime(new Date());

        boolean res = consumerService.addUser(consumer);

        if(res){
            jsonObject.put("code",1);
            jsonObject.put("msg","注册成功");
            return jsonObject;
        }else {
            jsonObject.put("code",0);
            jsonObject.put("msg","注册失败");
            return jsonObject;
        }
    }


    //判断是否登陆成功
    @ResponseBody
    @RequestMapping(value="/user/login/status",method = RequestMethod.POST)
    public Object loginStatus(HttpServletRequest req , HttpSession session){
        JSONObject jsonObject = new JSONObject();
        String username=req.getParameter("username");
        String password=req.getParameter("password");
        boolean res = consumerService.veritypasswd(username, password);
        //登陆成功
        if(res){
            jsonObject.put("code",1);
            jsonObject.put("msg","登陆成功");
            jsonObject.put("userMsg",consumerService.getUserByUsername(username));
            session.setAttribute("username",username);
            return jsonObject;
        }else{//登录失败
            jsonObject.put("code",0);
            jsonObject.put("msg","登陆失败");
            return jsonObject;
        }
    }
//    返回所有用户
    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public Object allUser(){return consumerService.allUser();}

    //返回指定ID的用户
   @RequestMapping(value = "/user/detail",method = RequestMethod.GET)
    public Object userOdId(HttpServletRequest req){
       String id = req.getParameter("id");
       return consumerService.getUserByID(Integer.parseInt(id));
    }

    //删除用户
    @RequestMapping(value = "/user/delete",method = RequestMethod.GET)
    public Object deleteUser(HttpServletRequest req){
        String id = req.getParameter("id");
        return consumerService.deleteUserByID(Integer.parseInt(id));
    }

    //更新用户
    @ResponseBody
    @RequestMapping(value = "/user/update",method = RequestMethod.POST)
    public Object updateUser(HttpServletRequest req){
        JSONObject jsonObject = new JSONObject();

        String id = req.getParameter("id").trim();
        String username = req.getParameter("username").trim();
        String password = req.getParameter("password").trim();
        String sex = req.getParameter("sex").trim();
        String phone_num = req.getParameter("phone_num").trim();
        String email = req.getParameter("email").trim();
        String birth = req.getParameter("birth").trim();
        String introduction = req.getParameter("introduction").trim();
        String location = req.getParameter("location").trim();
//        String avatar = req.getParameter("avatar").trim();

        if(username.equals("")||username==null){
            jsonObject.put("code",0);
            jsonObject.put("msg","用户名或密码错误");
            return jsonObject;
        }
        Consumer consumer = new Consumer();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date myBirth = new Date();

        try {
            myBirth=dateFormat.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        consumer.setId(Integer.parseInt(id));
        consumer.setUsername(username);
        consumer.setPassword(password);
        consumer.setSex(new Byte(sex));
        consumer.setPhoneNum(phone_num);
        consumer.setBirth(myBirth);
        consumer.setEmail(email);
        consumer.setIntroduction(introduction);
        consumer.setLocation(location);
        consumer.setUpdateTime(new Date());

        boolean res = consumerService.updateUserMsg(consumer);
        if(res){
            jsonObject.put("code",1);
            jsonObject.put("msg","删除成功");
            return jsonObject;
        }else{
            jsonObject.put("code",0);
            jsonObject.put("msg","删除失败");
            return jsonObject;
        }
    }


    @ResponseBody
    @RequestMapping(value = "/user/avatar/update",method = RequestMethod.POST)
    public Object updateUserPic(@RequestParam("file")MultipartFile avatarFile, @RequestParam("id")int id){

        JSONObject jsonObject = new JSONObject();
        if(avatarFile.isEmpty()){
            jsonObject.put("code",0);
            jsonObject.put("msg","文件上传失败");
            return jsonObject;
        }

        String fileName= System.currentTimeMillis()+avatarFile.getOriginalFilename();
        String filePath=System.getProperty("user.dir")+System.getProperty("file.separator") +"avatarImages";
        File file1 =new File(filePath);
      if(!file1.exists()){
          file1.mkdir();
      }

        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        String storeAvatarPath="/avatarImages/"+fileName;

        try {
            avatarFile.transferTo(dest);
            Consumer consumer = new Consumer();
            consumer.setId(id);
            consumer.setAvatar(storeAvatarPath);
            boolean res = consumerService.updateUserAvatar(consumer);

            if (res){
                jsonObject.put("code", 1);
                jsonObject.put("avatar", storeAvatarPath);
                jsonObject.put("msg", "上传成功");
                return jsonObject;
            }else {
                jsonObject.put("code", 0);
                jsonObject.put("msg", "上传失败");
                return jsonObject;
            }
        } catch (IOException e) {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "上传失败");
            return jsonObject;
        }finally {
            return jsonObject;
        }
    }

}
