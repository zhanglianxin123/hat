package com.qrs.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qrs.annotation.PassToken;
import com.qrs.annotation.UserLoginToken;
import com.qrs.pojo.DTO.UserDTO;
import com.qrs.pojo.RespBean;
import com.qrs.pojo.User;
import com.qrs.service.impl.UserServiceImpl;
import com.qrs.utils.TokenUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author zlx
 * @description
 * @Version 1.0
 * @Date 2021/6/17 8:26
 */
@RestController
@RequestMapping("/Login")
@Api(value = "登录接口", tags = "hat登录接口")
public class LogoController {
    @Autowired
    private UserServiceImpl userService;



    @PassToken
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public RespBean userLogin(HttpServletRequest request, @RequestBody UserDTO userDTO){
        System.out.println(userDTO);
        QueryWrapper<User> wrapper = new QueryWrapper();
        wrapper.eq("user_id",userDTO.getId());
        List<User> list=userService.list(wrapper);
        User user=list.get(0);
        HttpSession session = request.getSession();
        if (user==null) {
            session.setAttribute("isError", true);
            return RespBean.error("isError");
        }
        else{
            Map<String,Object> tokenMap = new HashMap<>();
            tokenMap.put("token", TokenUtils.getUserToken(user));
            System.out.println(TokenUtils.getUserToken(user));
            tokenMap.put("user",user);
            return RespBean.success("登录成功",tokenMap);
        }

    }



    @UserLoginToken
    @PostMapping("/getMessage")
    public RespBean getMessage(){
        String aaa="hello word";
        return RespBean.success("你已经通过验证",aaa);
    }
}
