package com.criown.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapControl {
    private static final int SUCCESS=200;
    private static final int ERROR=404;

    private static final int JSONSUCCESS=0;
    private static final int JSONERROR=404;


    private Map<String ,Object> paramMap =new HashMap<>();

    private MapControl(){}

    public static  MapControl getInstance(){return new MapControl();}

    public Map<String, Object> getMap() {return paramMap;}

    public MapControl put(Map<String,Object> map){
        for(Map.Entry<String,Object> entry:map.entrySet()){
            paramMap.put(entry.getKey(),entry.getValue());
        }
        return this;
    }

    public  MapControl put(String key,Object val){
        paramMap.put(key,val);
        return this;
    }

    public MapControl success(){
        return this.put("msg","success").put("code",SUCCESS);
    }

    public MapControl success(String  str){
        return this.put("msg",str).put("code",SUCCESS);
    }

    public MapControl error(){
        return this.put("msg","error").put("code",ERROR);
    }
    public MapControl error(String  str){
        return this.put("msg",str).put("code",ERROR);
    }

    public MapControl jsonSuccess(){
        return this.put("msg","success").put("code",JSONSUCCESS);
    }
    public MapControl jsonSuccess(List<?> data,Integer count){
        return this.put("msg","success").put("code",JSONSUCCESS).put("count",count).put("data",data);
    }
    public MapControl jsonSuccess(Map data){
        return this.put("msg","success").put("code",JSONSUCCESS).put("data",data);
    }

    public MapControl jsonError(){
        return this.put("msg","error").put("code",JSONERROR);
    }

    public MapControl jsonError(List<?> data,Integer count){
        return this.put("msg","error").put("code",JSONERROR).put("count",count).put("data",data);
    }
    public MapControl jsonError(Map data){
        return this.put("msg","error").put("code",JSONERROR).put("data",data);
    }

}
