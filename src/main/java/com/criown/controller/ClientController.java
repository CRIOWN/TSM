package com.criown.controller;

import com.criown.entity.Good;
import com.criown.entity.Goodshow;
import com.criown.entity.Node;
import com.criown.service.ClientService;
import com.criown.service.GoodService;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

//客户
@Controller
@RequestMapping("/client")
public class ClientController {
    @Autowired
    @Qualifier("ClientServiceImpl")
    private ClientService clientService;

    @Autowired
    @Qualifier("GoodServiceImpl")
    private GoodService goodService;


    //跳转首页
    @RequestMapping("/gotoMain1")
    public String gotoMain1(){
        return "client/listForGood";
    }

    //跳转新增订单
    @RequestMapping("/gotoAddGood")
    public String gotoAddGoodC(){
        return "client/GoodAdd";
    }

    //listForGood
    //Good.json
    @PostMapping("/listForGood")
    @ResponseBody
    public Map<String,Object> GoodJsonData(@RequestBody(required = false)  Map<String,Object> map, HttpServletRequest request)
    {
        System.out.println("GoodJsonData::"+map+"END");
        //clientLogInfo
        HttpSession session=request.getSession();
        Integer clientid= (Integer) session.getAttribute("clientLogInfo");
        System.out.println("clientid="+clientid);
        if(map.get("id")==null){
            //初始渲染
            List<Good> list= goodService.searchAllByClientid(clientid);
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
            List<Good> list=goodService.selectAllByIdAndClientid(id,clientid);
            System.out.println(list);
            Integer count=list.size();
            List<Goodshow> goodshowList=Goodshow.convertGoods(list);
            return MapControl.getInstance().jsonSuccess(goodshowList,count).getMap();
        }
    }

    //Good增加
    @PostMapping("/AddGood")
    @ResponseBody
    public Map<String,Object> AddGood(@RequestBody(required = false)  Map<String,Object> map,HttpServletRequest request) throws IOException
    {
        System.out.println("ClientAddGood::"+map);
        HttpSession session= request.getSession();
        Integer clientid= (Integer) session.getAttribute("clientLogInfo");
        Integer start=Integer.valueOf(String.valueOf(map.get("start")));
        Integer end=Integer.valueOf(String.valueOf(map.get("end")));
        String detail=String.valueOf(map.get("detail"));
        Date sendtime=new Date();

        Date recetime=null;
        System.out.println("clientid="+clientid+"::start="+start+"::end="+end+"::detail="+detail+"::sendtime="+sendtime+"::recetime"+recetime);
        goodService.addAll(clientid,start,end,sendtime,recetime,detail);
        return MapControl.getInstance().jsonSuccess().getMap();
    }

    //客户删除
    @PostMapping("/DelGood")
    @ResponseBody
    public Map<String,Object> DelGood(@RequestBody List<String> ids)
    {
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
    public String gotoEditGood(Integer id,HttpServletRequest request, Model model)
    {
        System.out.println("gotoEditGood::"+id);
        Integer clientid = (Integer) request.getSession().getAttribute("clientLogInfo");
        model.addAttribute("clientid",clientid);
        model.addAttribute("id",id);
        return "utils/editGoodById";
    }

    //Good编辑
    @PostMapping("/EditGood")
    @ResponseBody
    public Map<String,Object> GoodEdit(@RequestBody Map<String,Object> map) throws IOException
    {
        System.out.println("GoodEdit::"+map);
        int id=Integer.parseInt(String.valueOf(map.get("id")));
        int clientid=Integer.parseInt(String.valueOf(map.get("clientid")));
        int start =Integer.parseInt(String.valueOf(map.get("start")));
        int end =Integer.parseInt(String.valueOf(map.get("end")));
        String detail=String.valueOf(map.get("detail"));
//        Date sendtime=new Date();
//        //Tsp 计算路径长
//        int n=0;
//        n=TxTsp.tsp(start,end);
//        //计算时长
//        int hour=n/(5000/24);
//        System.out.println(hour);
//        //计算
//        Date recetime= DateUtil.calculate(sendtime,hour);
        Date sendtime=new Date();
        Date recetime=null;
        goodService.updateById(id,clientid,start,end,sendtime,recetime,detail);
        System.out.println("修改完毕");
        return MapControl.getInstance().jsonSuccess().getMap();
    }

    //资料修改
    @RequestMapping("/changeUser")
    @ResponseBody
    public Map changeUser(@RequestBody Map<String,Object> map, HttpServletRequest request)
    {
        System.out.println("ChangePassword::" + map);
        HttpSession session = request.getSession();
        Integer id = (Integer) session.getAttribute("adminLogInfo");
        String name = (String) map.get("username");
        String sex = (String) map.get("sex");
        String local = (String) map.get("local");
        Integer phone = Integer.valueOf((String) map.get("phone"));
        String detail = (String) map.get("detail");
        clientService.updateAllById(name, sex, local, detail, phone, id);
        return MapControl.getInstance().error("修改成功").getMap();

    }

    //跳转QS
    @RequestMapping("/gotoQS")
    public String gotoQS(Integer id,HttpServletRequest request,Model model){
        Integer clientid = (Integer) request.getSession().getAttribute("clientLogInfo");
        System.out.println("QSDeal::id=" +id+"::clientid="+clientid );
        model.addAttribute("id",id);
        model.addAttribute("clientid",clientid);
        return "test/echarts";
    }

    //QS
    @RequestMapping("/QSDeal")
    @ResponseBody
    public Map QSDeal(@RequestBody Map<String,Object> map,Model model) throws IOException {
        System.out.println("QSdeal::"+map);
        Integer id = Integer.valueOf((String) map.get("id"));
        Integer clientid = Integer.valueOf((String) map.get("clientid"));
        System.out.println("QSDeal::id=" +id+"::clientid="+clientid );
        int start=goodService.selectStartById(id);
        int end=goodService.selectEndById(id);
        List<Node> path=TxTsp.tsp_path(start,end);
        int pathWeight= TxTsp.getValue(path);
        System.out.println("pathWeight::"+pathWeight);
        model.addAttribute("pathWeight",pathWeight);
        List<Integer> list=new ArrayList<>();
        for(int i=0;i<path.size();i++) list.add(path.get(i).id);
        System.out.println("list::"+list);
        return MapControl.getInstance().jsonSuccess(list,1).getMap();
    }

}


