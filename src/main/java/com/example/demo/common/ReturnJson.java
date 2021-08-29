package com.example.demo.common;

import java.util.HashMap;
import java.util.Map;

public class ReturnJson {
    //返回列表
    public static Map returndata(int code,String msg,Map data){
        Map listdata = new HashMap();
        listdata.put("code",code);
        listdata.put("msg",msg);
        listdata.put("data",data);
        return listdata;
    }
}
