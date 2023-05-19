package com.criown.controller;

import com.criown.service.ClientLogService;
import com.criown.utils.MD5;
import com.criown.utils.MapControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

//客户
@Controller
@RequestMapping("/client")
public class ClientLogController {
    @Autowired
    @Qualifier("ClientLogServiceImpl")
    private ClientLogService clientLogService;

    //接收表单登录信息并返回验证
    @RequestMapping("/logDeal")
    @ResponseBody
    public Map loginDeal(@RequestBody Map<String,Object> map, HttpSession session)
    {
        System.out.println("loginDeal::");
        System.out.println("获得Map::"+map);
        String pwd =String.valueOf(map.get("password"));
        pwd= MD5.getMD5(pwd);
        System.out.println("password::"+pwd);
        String username=String.valueOf(map.get("username"));
        System.out.println("username::"+username);
        try {
            int id = clientLogService.selectUseridByUsername(username);
            System.out.println("userid::"+id);
            String str= clientLogService.selectUserpwdByUserid(id);
            System.out.println("对应密码为::"+str);
            if(pwd.equals(str))//
            {
                System.out.println("验证成功");
                session.setAttribute("clientLogInfo",id);
                return MapControl.getInstance().success("成功登陆").getMap();
            }
            else
            {
                System.out.println("验证失败");
                return MapControl.getInstance().error("密码失败,请重试").getMap();
            }

        } catch (NullPointerException e)
        {
            System.out.println("NullPointerException");
            return MapControl.getInstance().error("用户名错误,请重试").getMap();
        }

    }

    //跳转主页
    @RequestMapping("/index")
    public String GotoIndex()
    {
        return "/client/index";
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
        pwd= MD5.getMD5(pwd);
        oldPwd=MD5.getMD5(oldPwd);
        HttpSession session=request.getSession();
        Integer id= (Integer) session.getAttribute("adminLogInfo");
        String temp= clientLogService.selectUserpwdByUserid(id);
        System.out.println(id+"::True::"+temp+"::"+oldPwd);
        if(temp.equals(oldPwd))
        {
            System.out.println("验证成功");
            clientLogService.updateUserpwdByUserid(pwd,id);
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

    //gotoreg
    @RequestMapping("/gotologreg")
    public String gotoreg(Model model){
        return "/login/register";
    }




}
