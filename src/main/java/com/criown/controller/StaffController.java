package com.criown.controller;

import com.criown.entity.Client;
import com.criown.entity.Good;
import com.criown.entity.Goodshow;
import com.criown.service.AdminService;
import com.criown.service.ClientService;
import com.criown.service.GoodService;
import com.criown.service.StaffService;
import com.criown.utils.DateUtil;
import com.criown.utils.MapControl;
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

//员工
@Controller
@RequestMapping("/staff")
public class StaffController {
    @Autowired
    @Qualifier("StaffServiceImpl")
    private StaffService staffService;

    @Autowired
    @Qualifier("GoodServiceImpl")
    private GoodService goodService;

    @Autowired
    @Qualifier("ClientServiceImpl")
    private ClientService clientService;

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
        staffService.updateNameAndSexAndLocalAndNumberAndDetailById(name,sex,local,phone,detail,id);
        return  MapControl.getInstance().error("修改成功").getMap();
    }


    //跳转功能
    @RequestMapping("/gotoFunction")
    public String gotoFunction(){
        return "TM/function";
    }
//==================main==============================
//主页数据图-人员占比
@PostMapping("/Main1Data1")
@ResponseBody
public Map<String,Object> Main1Data1(){
    System.out.println("Main1Data1::订单");
    List<Good> list[]=new ArrayList[13];
    List<Integer> integerList=new ArrayList<>();
    for(int i=0;i<13;i++){
        list[i]=goodService.searchAllByEnd(i);
        integerList.add(list[i].size());
    }
    return MapControl.getInstance().jsonSuccess(integerList,1).getMap();
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


//============================client==================

    //跳转功能
    @RequestMapping("/gotoListForClient")
    public String gotoListForClient(){
        return "staff/listForClient";
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

    //跳转客户编辑
    @RequestMapping("/gotoGoodClient")
    public String gotoEditClient(Integer id, Model model){
        System.out.println("gotoGoodClient::"+id);
        model.addAttribute("clientid",id);
        return "staff/listForGood";
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

//=========================client==================

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
        return "staff/main-1";
    }


    //========================good=======================
    //Good.json
    @PostMapping("/listForGood")
    @ResponseBody
    public Map<String,Object> GoodJsonData(@RequestBody(required = false)  Map<String,Object> map,Integer clientid){
        System.out.println("GoodJsonData::"+map+"clientid::"+clientid+"END");
        Integer id=null;

        //Integer clientid=Integer.valueOf(String.valueOf(map.get("clientid")));
        if(map.get("id")==null){
            //初始渲染
             System.out.println("初始渲染");
            List<Good> list= goodService.selectAllByIdAndClientid(id,clientid);
            Integer count=list.size();
            List<Goodshow> goodshowList=Goodshow.convertGoods(list);

            return MapControl.getInstance().jsonSuccess(goodshowList,count).getMap();
        }
        else{
            //搜索
            System.out.println("进入搜索");
            id=Integer.valueOf(String.valueOf(map.get("id")));
            System.out.println("id:"+id);
            List<Good> list=goodService.selectAllByIdAndClientid(id,clientid);
            System.out.println(list);
            Integer count=list.size();
            List<Goodshow> goodshowList=Goodshow.convertGoods(list);
            return MapControl.getInstance().jsonSuccess(goodshowList,count).getMap();
        }
    }
    //==================TSMg======
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
//        List<Integer> list=new ArrayList<>();
//        for(String s:ids){
//            list.add(Integer.parseInt(s));
//        }
//        System.out.println("list::"+list);
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
}
