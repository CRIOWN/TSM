package com.criown.entity;

import com.criown.utils.CityEnum;
import com.criown.utils.DateUtil;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class Goodshow extends Good{

    //private String clientname;
    private String startS;

    private String endS;

    private String sendtimeS;

    private String recetimeS;

    private String detail;

    public static List<Goodshow> convertGoods(List<Good> goods) {
        List<Goodshow> goodshows = new ArrayList<>();
        for (Good good : goods) {
            Goodshow goodshow = new Goodshow();
            goodshow.setId(good.getId());
            goodshow.setClientid(good.getClientid());

            goodshow.setDetail(good.getDetail());
            //地名
            goodshow.setStartS(CityEnum.getNameByValue(good.getStart()));
            goodshow.setEndS(CityEnum.getNameByValue(good.getEnd()));

            //System.out.println("forgoodsendtime::"+good.getSendtime());
            goodshow.setSendtimeS(DateUtil.formatDate2(good.getSendtime()));
            //System.out.println("forgoodrecetime::"+good.getRecetime());
            goodshow.setRecetimeS(DateUtil.formatDate2(good.getRecetime()));
            goodshows.add(goodshow);
        }
        return goodshows;
    }

}
