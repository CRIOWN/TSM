package com.criown.controller;


import com.criown.entity.AdminLog;
import com.criown.service.AdminLogService;
import com.criown.utils.MD5;
import com.criown.utils.MapControl;
import com.mysql.cj.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.Objects;


//管理员
@Controller
@RequestMapping("/admin")
public class AdminLogController {
    @Autowired
    @Qualifier("AdminLogServiceImpl")
    private AdminLogService adminLogService;

//    @Value("classpath:init.json")
//    private Resource resource;

    //跳转到登录界面
    @RequestMapping("/log")
    public String login()
    {
        System.out.println("login::");
        return "/login/logPage";
        //login/logPage
    }

    //接收表单登录信息并返回验证
    @RequestMapping("/logDeal")
    @ResponseBody
    public Map loginDeal(@RequestBody Map<String,Object> map,HttpSession session)
    {
        System.out.println("loginDeal::");
        System.out.println("获得Map::"+map);
        String pwd =String.valueOf(map.get("password"));
        pwd= MD5.getMD5(pwd);
        String username=String.valueOf(map.get("username"));
        System.out.println("username::"+username);
        try{
            int id = adminLogService.selectUseridByUsername(username);
            System.out.println("userid::"+id);
            String str= adminLogService.selectUserpwdByUserid(id);
            System.out.println("password::"+pwd);
            System.out.println("对应密码为::"+str);
            if(pwd.equals(str))//
            {
                System.out.println("验证成功");
                session.setAttribute("adminLogInfo",id);
                return MapControl.getInstance().success("成功登陆").getMap();
            }
            else
            {
                System.out.println("验证失败");
                return MapControl.getInstance().error("密码错误,请重试").getMap();
            }
        }
        catch (NullPointerException e){
            System.out.println("NullPointerException");
            return MapControl.getInstance().error("用户名错误,请重试").getMap();
        }

    }

    //跳转管理员主页
    @RequestMapping("/index")
    public String GotoIndex()
    {
        return "/admin/index";
    }

    //跳转list页面--staff
    @RequestMapping("/gotoListForStaff")
    public String GotoStaffList(){
        return "/admin/listForStaff";
    }

    //跳转list页面--client
    @RequestMapping("/gotoListForClient")
    public String GotoClientList(){
        return "/admin/listForClient";
    }

    //跳转list页面--Car
    @RequestMapping("/gotoListForCar")
    public String GotoCarList(){
        return "/TM/listForCar";
    }

    //跳转list页面--Tsm
    @RequestMapping("/gotoListForGood")
    public String GotoGoodList(){
        return "/TM/listForGood";
    }

//===========================
    //goto密码修改
    @RequestMapping("/gotochangePassword")
    public String GotoChangePassword(){
        System.out.println("GotoChangePassword");
        return "/utils/user-pwd";
    }


    //密码修改
    @RequestMapping("/changePassword")
    @ResponseBody
    public Map ChangePassword(@RequestBody Map<String,Object> map, HttpServletRequest request){
        System.out.println("ChangePassword::"+map);
        String oldPwd = (String) map.get("oldPwd");
        String pwd = (String) map.get("pwd");
        HttpSession session=request.getSession();
        Integer id= (Integer) session.getAttribute("adminLogInfo");
        String temp= adminLogService.selectUserpwdByUserid(id);
        System.out.println(id+"::True::"+temp+"::"+oldPwd);
        if(temp.equals(oldPwd))
        {
            System.out.println("验证成功");
            adminLogService.updateUserpwdByUserid(pwd,id);
            return MapControl.getInstance().success("修改成功").getMap();
        }
        else
        return MapControl.getInstance().error("密码错误,请重试").getMap();
    }
//===============================
    //gotoUserChange
    @RequestMapping("/gotoUserChange")
    public String gotoUserChange(Model model){
        return "/utils/UserEdit";
    }


}
