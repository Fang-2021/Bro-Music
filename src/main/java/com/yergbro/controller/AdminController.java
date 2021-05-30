package com.yergbro.controller;

import com.alibaba.fastjson.JSONObject;
import com.yergbro.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@Controller
@RequestMapping("/client")
public class AdminController {
    @Autowired
    private AdminService adminService;

    //判断是否登陆成功
    @ResponseBody
    @RequestMapping(value = "/admin/login/status",method = RequestMethod.POST)
    public Object loginStatus(HttpServletRequest req, HttpSession session ){
        JSONObject jsonObject = new JSONObject();
        String name=req.getParameter("name");
        String password = req.getParameter("password");

        boolean res = adminService.veritypasswd(name, password);
        if(res){
            jsonObject.put("code",1);
            jsonObject.put("msg","登陆成功");
            session.setAttribute("name",name);
            return jsonObject;
        }else{
            jsonObject.put("code",0);
            jsonObject.put("msg","登陆失败");
            return jsonObject;

        }

    }
}
