package com.criown.controller;

import com.criown.entity.Client;
import com.criown.entity.Good;
import com.criown.entity.Goodshow;
import com.criown.entity.Staff;
import com.criown.service.*;
import com.criown.utils.DateUtil;
import com.criown.utils.MapControl;
import com.criown.utils.Redisutils;
import com.criown.utils.TxTsp;
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
import java.io.IOException;
import java.util.*;

//管理员
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    @Qualifier("AdminLogServiceImpl")
    private AdminLogService adminLogService;

    @Autowired
    @Qualifier("AdminServiceImpl")
    private AdminService adminService;

    @Autowired
    @Qualifier("ClientServiceImpl")
    private ClientService clientService;

    @Autowired
    @Qualifier("StaffServiceImpl")
    private StaffService staffService;

    @Autowired
    @Qualifier("GoodServiceImpl")
    private GoodService goodService;

    @Autowired
    @Qualifier("PostServiceImpl")
    private PostService postService;



    //员工staff.json name-sex-local-career
    @PostMapping("/listForStaff")
    @ResponseBody
    public Map<String,Object> staffJsonData(@RequestBody(required = false)  Map<String,Object> map){
        System.out.println("staffJsonData::"+map+"END");

        if(map.get("name")==null||map.get("sex")==null||map.get("local")==null||map.get("career")==null){
            //初始渲染
            List<Staff> list= staffService.selectAll();
            Integer count=list.size();
            System.out.println("初始渲染");
            return MapControl.getInstance().jsonSuccess(list,count).getMap();
        }
        else{
            //搜索
            System.out.println("进入搜索");
            String name=String.valueOf(map.get("name"));
            String sex=String.valueOf(map.get("sex"));
            String local=String.valueOf(map.get("local"));
            String career=String.valueOf(map.get("career"));
            System.out.println("name:"+name+"--sex:"+sex+"--local:"+local+"--career:"+career);
            List<Staff> list=staffService.getAllByQuery(name,sex,career,local);
            Integer count=list.size();
            return MapControl.getInstance().jsonSuccess(list,count).getMap();
        }
    }


    //客户client.json name-sex-local
    @PostMapping("/listForClient")
    @ResponseBody
    public Map<String,Object> clientJsonData(@RequestBody(required = false)  Map<String,Object> map){
        System.out.println("clientJsonData::"+map+"END");

        if(map.get("name")==null||map.get("sex")==null||map.get("local")==null){
           //初始渲染

            List<Client> list=clientService.selectAll();
            Integer count=list.size();
            System.out.println("初始渲染");
            return MapControl.getInstance().jsonSuccess(list,count).getMap();
        }
        else{
            //进入搜索
            System.out.println("进入搜索");
            String name=String.valueOf(map.get("name"));
            String sex=String.valueOf(map.get("sex"));
            String local=String.valueOf(map.get("local"));
            System.out.println("name:"+name+"--sex:"+sex+"--local:"+local);
            List<Client> list=clientService.getAllByQuery(name,sex,local);
            Integer count=list.size();
            return MapControl.getInstance().jsonSuccess(list,count).getMap();
        }
    }

    //跳转 客户Add
    @RequestMapping("/gotoAddClient")
    public String gotoClientAdd(){
        return "utils/addClient";
    }

    //跳转 员工Add
    @RequestMapping("/gotoAddStaff")
    public String gotoStaffAdd(){
        return "utils/addStaff";
    }

    //客户增加
    @PostMapping("/AddClient")
    @ResponseBody
    public Map<String,Object> ClientAdd(@RequestBody(required = false)  Map<String,Object> map){
        System.out.println("ClientAdd::"+map);
        String name=String.valueOf(map.get("name"));
        String sex=String.valueOf(map.get("sex"));
        String local=String.valueOf(map.get("local"));
        int number =Integer.parseInt(String.valueOf(map.get("number")));
        String detail=String.valueOf(map.get("detail"));
        clientService.addAll(name,sex,local,number,detail);
        return MapControl.getInstance().jsonSuccess().getMap();
    }

    //员工增加
    @PostMapping("/AddStaff")
    @ResponseBody
    public Map<String,Object> StaffAdd(@RequestBody Map<String,Object> map){
        System.out.println("StaffAdd::"+map);
        String name=String.valueOf(map.get("name"));
        String sex=String.valueOf(map.get("sex"));
        String local=String.valueOf(map.get("local"));
        int number =Integer.parseInt(String.valueOf(map.get("number")));
        String career=String.valueOf(map.get("career"));
        String detail=String.valueOf(map.get("detail"));

        staffService.addAll(name,sex,local,number,career,detail);
        return MapControl.getInstance().jsonSuccess().getMap();
    }

    //客户删除
    @PostMapping("/DelClient")
    @ResponseBody
    public Map<String,Object> ClientDel(@RequestBody List<String> ids){
        System.out.println("ClientDel::"+ids);
        List<Integer> list=new ArrayList<>();
        for(String s:ids){
            list.add(Integer.parseInt(s));
        }
        System.out.println("list::"+list);
        clientService.delById(list);
        System.out.println("删除完毕");
        return MapControl.getInstance().jsonSuccess().getMap();
    }

    //员工删除
    @PostMapping("/DelStaff")
    @ResponseBody
    public Map<String,Object> StaffDel(@RequestBody List<String> ids){
        System.out.println("StaffDel::"+ids);
        List<Integer> list=new ArrayList<>();
        for(String s:ids){
            list.add(Integer.parseInt(s));
        }
        System.out.println("list::"+list);
        staffService.delById(list);
        System.out.println("删除完毕");
        return MapControl.getInstance().jsonSuccess().getMap();
    }

    //跳转员工编辑
    @RequestMapping("/gotoEditStaff")
    public String gotoEditStaff(Integer id,Model model){
        System.out.println("gotoEditStaff::"+id);
        model.addAttribute("id",id);
        return "utils/editStaff";
    }

    //跳转客户编辑
    @RequestMapping("/gotoEditClient")
    public String gotoEditClient(Integer id,Model model){
        System.out.println("gotoEditClient::"+id);
        model.addAttribute("id",id);
    return "utils/editClient";
}

    //员工编辑
    @PostMapping("/EditStaff")
    @ResponseBody
    public Map<String,Object> StaffEdit(@RequestBody Map<String,Object> map){
        System.out.println("StaffEdit::"+map);
        int id=Integer.parseInt(String.valueOf(map.get("id")));
        String name=String.valueOf(map.get("name"));
        String sex=String.valueOf(map.get("sex"));
        String local=String.valueOf(map.get("local"));
        String career=String.valueOf(map.get("career"));
        int number =Integer.parseInt(String.valueOf(map.get("number")));
        String detail=String.valueOf(map.get("detail"));
        staffService.updateAddById(number,career,detail,local,name,sex,id);
        System.out.println("修改完毕");
        return MapControl.getInstance().jsonSuccess().getMap();
    }


    //客户编辑
    @PostMapping("/EditClient")
    @ResponseBody
    public Map<String,Object> ClientEdit(@RequestBody Map<String,Object> map){
        System.out.println("ClientEdit::"+map);
        int id=Integer.parseInt(String.valueOf(map.get("id")));
        String name=String.valueOf(map.get("name"));
        String sex=String.valueOf(map.get("sex"));
        String local=String.valueOf(map.get("local"));
        int number =Integer.parseInt(String.valueOf(map.get("number")));
        String detail=String.valueOf(map.get("detail"));
        clientService.updateAllById(name,sex,local,detail,number,id);
        System.out.println("修改完毕");
        return MapControl.getInstance().jsonSuccess().getMap();
    }


    //跳转首页
    @RequestMapping("/gotoMain1")
    public String gotoMain1(Model model){
        List list2=staffService.selectAll();
        List list3=clientService.selectAll();
        List list4=goodService.selectAll();
        Integer StaffNum =list2.size();
        Integer ClientNum=list3.size();
        Integer GoodNum = list4.size();
        model.addAttribute("StaffNum",StaffNum);
        model.addAttribute("ClientNum",ClientNum);
        model.addAttribute("GoodNum",GoodNum);
        return "admin/main-1";
    }


    //跳转首页
    @RequestMapping("/gotofirst")
    public String gotoFirst(Model model){
        List list2=staffService.selectAll();
        List list3=clientService.selectAll();
        List list4=goodService.selectAll();
        //List list5=postService.selectAll();
        Integer StaffNum =list2.size();
        Integer ClientNum=list3.size();
        Integer GoodNum = list4.size();
        model.addAttribute("StaffNum",StaffNum);
        model.addAttribute("ClientNum",ClientNum);
        model.addAttribute("GoodNum",GoodNum);

        return "admin/first";
    }

    //跳转首页
    @PostMapping("/first")
    @ResponseBody
    public Map<String,Object> first(){

        List list=postService.selectAll();
        return MapControl.getInstance().jsonSuccess(list,1).getMap();
    }



    //主页数据图-人员占比
    @PostMapping("/Main1Data1")
    @ResponseBody
    public Map<String,Object> Main1Data1(){
        List list1=adminService.selectAll();
        List list2=staffService.selectAll();
        List list3=clientService.selectAll();
        Map map= new HashMap<String, String>();
        map.put("admin", list1.size());
        map.put("staff", list2.size());
        map.put("client", list3.size());
        System.out.println("Main1Data1::人员"+map);
        return MapControl.getInstance().jsonSuccess(map).getMap();
    }

    //主页数据图-订单问题
    @PostMapping("/Main1Data2")
    @ResponseBody
    public Map<String,Object> Main1Data2(){
        System.out.println("Main1Data2::订单");
        List<Good> list[]=new ArrayList[13];
        List<Integer> integerList=new ArrayList<>();

        for(int i=0;i<13;i++){
            list[i]=goodService.searchAllByFrom(i);
            integerList.add(list[i].size());
        }
        return MapControl.getInstance().jsonSuccess(integerList,1).getMap();
    }

//==========Good================
    //Good.json
    @PostMapping("/listForGood")
    @ResponseBody
    public Map<String,Object> GoodJsonData(@RequestBody(required = false)  Map<String,Object> map){
        System.out.println("GoodJsonData::"+map+"END");

        if(map.get("id")==null){
            //初始渲染
            List<Good> list= goodService.selectAll();
            Integer count=list.size();
            List<Goodshow> goodshowList=Goodshow.convertGoods(list);
            System.out.println("初始渲染");
            return MapControl.getInstance().jsonSuccess(goodshowList,count).getMap();
        }
        else{
            //搜索
            System.out.println("进入搜索");
            Integer id=Integer.valueOf(String.valueOf(map.get("id")));
            System.out.println("id:"+id);
            List<Good> list=goodService.selectAllById(id);
            System.out.println(list);
            Integer count=list.size();
            List<Goodshow> goodshowList=Goodshow.convertGoods(list);
            return MapControl.getInstance().jsonSuccess(goodshowList,count).getMap();
        }
    }

    //跳转 GoodAdd
    @RequestMapping("/gotoAddGood")
    public String gotoAddGood(){
        return "utils/addGood";
    }

    //Good增加
    @PostMapping("/AddGood")
    @ResponseBody
    public Map<String,Object> AddGood(@RequestBody(required = false)  Map<String,Object> map) throws IOException {
        System.out.println("AddGood::"+map);
        //String id=String.valueOf(map.get("id"));
        Integer clientid=Integer.valueOf(String.valueOf(map.get("clientid")));
        Integer start=Integer.valueOf(String.valueOf(map.get("start")));
        Integer end=Integer.valueOf(String.valueOf(map.get("end")));

        String detail=String.valueOf(map.get("detail"));
        Date sendtime=new Date();
        //Tsp 计算路径长
        int n=0;
        n=TxTsp.tsp(start,end);
        //计算时长
        int hour=n/(5000/24);
        System.out.println(hour);
        //计算
        Date recetime= DateUtil.calculate(sendtime,hour);

        System.out.println("clientid="+clientid+"::start="+start+"::end="+end+"::detail="+detail+"::sendtime="+sendtime+"::recetime"+recetime);


        goodService.addAll(clientid,start,end,sendtime,recetime,detail);
        return MapControl.getInstance().jsonSuccess().getMap();
    }

    //客户删除
    @PostMapping("/DelGood")
    @ResponseBody
    public Map<String,Object> DelGood(@RequestBody List<String> ids){
        System.out.println("DelGood::"+ids);
        List<Integer> list=new ArrayList<>();
        for(String s:ids){
            list.add(Integer.parseInt(s));
        }
        System.out.println("list::"+list);
        goodService.delByList(list);
        System.out.println("删除完毕");
        return MapControl.getInstance().jsonSuccess().getMap();
    }

    //跳转Good编辑
    @RequestMapping("/gotoEditGood")
    public String gotoEditGood(Integer id,Model model){
        System.out.println("gotoEditGood::"+id);
        model.addAttribute("id",id);
        return "utils/editGood";
    }

    //Good编辑
    @PostMapping("/EditGood")
    @ResponseBody
    public Map<String,Object> GoodEdit(@RequestBody Map<String,Object> map) throws IOException {
        System.out.println("GoodEdit::"+map);
        int id=Integer.parseInt(String.valueOf(map.get("id")));
        int clientid=Integer.parseInt(String.valueOf(map.get("clientid")));
        int start =Integer.parseInt(String.valueOf(map.get("start")));
        int end =Integer.parseInt(String.valueOf(map.get("end")));
        String detail=String.valueOf(map.get("detail"));
        Date sendtime=new Date();
        //Tsp 计算路径长
        int n=0;
        n=TxTsp.tsp(start,end);
        //计算时长
        int hour=n/(5000/24);
        System.out.println(hour);
        //计算
        Date recetime= DateUtil.calculate(sendtime,hour);
        goodService.updateById(id,clientid,start,end,sendtime,recetime,detail);
        System.out.println("修改完毕");
        return MapControl.getInstance().jsonSuccess().getMap();
    }

    //=========good==================
    //资料修改
    @RequestMapping("/changeUser")
    @ResponseBody
    public Map changeUser(@RequestBody Map<String,Object> map, HttpServletRequest request){
        System.out.println("ChangePassword::"+map);
    HttpSession session=request.getSession();
    Integer id= (Integer) session.getAttribute("adminLogInfo");
    String name=(String) map.get("username");
    String sex=(String) map.get("sex");
    String local=(String) map.get("local");
    Integer phone=Integer.valueOf((String) map.get("phone"));
    String detail=(String) map.get("detail");
     adminService.updateNameAndSexAndLocalAndPhoneAndDetailByUserid(name,sex,local,phone,detail,id);
    return  MapControl.getInstance().error("修改成功").getMap();
    }


    //======用户信息=======

    //跳转功能
    @RequestMapping("/gotoFunction")
    public String gotoFunction(){
        return "TM/function";
    }


    //
    //保存TSP
    @PostMapping("/function1")
    @ResponseBody
    public Map<String,Object> function1() throws IOException {
        System.out.println("function:1:");
        int flag=0;
        Redisutils redisutils = new Redisutils();
        flag = redisutils.judge();
        if( flag==1 ) System.out.println("成功");
        else redisutils.choose(true);
        return MapControl.getInstance().jsonSuccess().getMap();
    }
    //删除Tsp
    @PostMapping("/function2")
    @ResponseBody
    public Map<String,Object> function2() throws IOException {
        System.out.println("function:2:");
        int flag=0;
        Redisutils redisutils = new Redisutils();
        flag = redisutils.judge();
        if( flag==0 ) System.out.println("成功");
        else redisutils.choose(false);

        return MapControl.getInstance().jsonSuccess().getMap();
    }
    //==========QS=====================
    //Good.json
    @PostMapping("/listForQS")
    @ResponseBody
    public Map<String,Object> GoodJsonDataQS(@RequestBody(required = false)  Map<String,Object> map,Integer clientid)
    {
        System.out.println("GoodJsonData::"+map+"END");
        if(map.get("id")==null){
            //初始渲染
            List<Good> list= goodService.selectAll();
            Integer count=list.size();
            List<Goodshow> goodshowList=Goodshow.convertGoods(list);
            List<Goodshow> list1=new ArrayList<>();
            for (Goodshow goodshow : goodshowList) {
                if ( goodshow.getRecetimeS().equals("null") ) {
                    list1.add(goodshow);
                }
            }
            System.out.println("初始渲染");
            return MapControl.getInstance().jsonSuccess(list1,count).getMap();
        }
        else{
            //搜索
            System.out.println("进入搜索");
            Integer id=Integer.valueOf(String.valueOf(map.get("id")));
            System.out.println("id:"+id);
            List<Good> list=goodService.selectAllById(id);
            System.out.println(list);
            Integer count=list.size();
            List<Goodshow> goodshowList=Goodshow.convertGoods(list);
            List<Goodshow> list1=new ArrayList<>();
            for (Goodshow goodshow : goodshowList) {
                if (goodshow.getRecetimeS().equals("null")) {
                    list1.add(goodshow);
                }
            }
            return MapControl.getInstance().jsonSuccess(list1,count).getMap();
        }
    }


    //跳转
    @RequestMapping("/gotoQSDeal")
    public String gotoQSDeal(){
        return "staff/QSDeal";
    }

    //确认
    @PostMapping("/QSDeal")
    @ResponseBody
    public Map<String,Object> QSDeal(@RequestBody Integer id) throws IOException {
        System.out.println("QSDeal::"+id);
        int start=goodService.selectStartById(id);
        int end =goodService.selectEndById(id);
        Date sendtime=new Date();
        //Tsp 计算路径长
        int n=0;
        n= TxTsp.tsp(start,end);
        //计算时长
        int hour=n/(5000/24);
        System.out.println("耗时：："+hour);
        //计算
        Date recetime= DateUtil.calculate(sendtime,hour);

        goodService.updateSendtimeAndRecetimeById(sendtime,recetime,id);
        System.out.println("确认");
        return MapControl.getInstance().jsonSuccess().getMap();
    }



}
