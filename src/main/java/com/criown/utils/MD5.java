package com.criown.utils;

import org.springframework.util.DigestUtils;

public class MD5 {
    private static  final String salt="criown";

    public static String getMD5(String string){
        String val=string+salt;
        return DigestUtils.md5DigestAsHex(val.getBytes());
    }

    public static void main(String[] args){

    }
}
