package com.criown.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ArrayList;
import java.util.List;

public class Redisutils {

     private Jedis jedis =new Jedis("localhost");

     public void setPath(List<Integer> path)
     {
         jedis.del("pathlist");
         for(Integer i :path) jedis.rpush("pathlist",i.toString());
     }

     public List getPath()
     {
         List<String> stringList= jedis.lrange("pathlist",0,-1);
         List<Integer> list = new ArrayList<>();
         for(String s: stringList)
             list.add(Integer.parseInt(s));
         System.out.println(list);
         return list;
     }

     //1== 存在 直接运行
     //0== 不存在 要算
     public int judge()
     {
         try {
             String i= jedis.get("redisFunction");
             System.out.println(i);
             if(i.equals("null"))
                 return 0;
             return  Integer.parseInt(i);
         }catch (NullPointerException e)
         {
             System.out.println("redis空值");
             choose(false);
             return 0;
         }

     }

     public void choose(boolean b)
     {
         if(b)
         jedis.set("redisFunction","1");
         else  jedis.set("redisFunction","0");
     }

     public int clientNum(int Client){
          if (jedis.exists("clientNum"))
          {
              int now = Integer.parseInt(jedis.get("clientNum"));
              return now-Client;
          }
          else {
              jedis.set("clientNum", String.valueOf(Client));
              return 0;
          }
     }
}
