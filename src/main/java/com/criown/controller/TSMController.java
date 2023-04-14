package com.criown.controller;


import com.criown.service.AdminLogService;
import com.criown.service.GoodService;
import com.criown.utils.MapControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

//物流
@Controller
@RequestMapping("/TSM")
public class TSMController {
    @Autowired
    @Qualifier("GoodServiceImpl")
    private GoodService goodService;

    //跳转 订单Add
    @RequestMapping("/gotoAddGood")
    public String gotoGoodAdd(){
        return "utils/addGood";
    }


}
