package com.qrs.utils;

import com.qrs.pojo.User;
import org.apache.poi.ss.formula.functions.T;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zlx
 * @description
 * @Version 1.0
 * @Date 2021/6/21 19:33
 */
public class PageUtils {
    public static List<Object>page(List<Object> list , int pageCurrent, int pageSize){
        list= (List<Object>) list.get(0);
        List<Object>list1=new ArrayList<>();
        if(pageCurrent*pageSize>list.size()){
            for(int i=(pageCurrent-1)*pageSize;i<list.size();i++){
                list1.add(list.get(i));
            }
        }else {
            for(int i=(pageCurrent-1)*pageSize;i<pageCurrent*pageSize;i++){
                list1.add(list.get(i));
            }
        }
        return list1;
    }
}
