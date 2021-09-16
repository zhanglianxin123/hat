package com.qrs.controller;

import com.qrs.annotation.PassToken;
import com.qrs.utils.OkHttpUtil2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zlx
 * @description
 * @Version 1.0
 * @Date 2021/7/2 15:44
 */
@RestController
@RequestMapping("/cmd")
public class testController {

    @Autowired
    private OkHttpUtil2 okHttpUtil2;

    @PassToken
    @GetMapping("/cmd")
    public void cmd(){
        okHttpUtil2.issue("ol");
    }
}