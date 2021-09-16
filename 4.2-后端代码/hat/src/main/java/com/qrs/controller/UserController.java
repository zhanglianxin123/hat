package com.qrs.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.qrs.annotation.PassToken;
import com.qrs.pojo.DTO.PageDTO;
import com.qrs.pojo.DTO.PageDTO3;
import com.qrs.pojo.DTO.UserDTO2;
import com.qrs.pojo.RespBean;
import com.qrs.pojo.User;
import com.qrs.service.IUserService;
import com.qrs.utils.PageUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zlx
 * @since 2021-06-15
 */
@RestController
@Api(value = "安全帽用户模块" ,tags = "hat用户接口")
@RequestMapping("//user")
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping("/getSize")
    @ApiOperation(value = "获得用户人数")
    public RespBean getSize(){
        List<User> list = userService.list();
        return  RespBean.success("成功",list.size());
    }

    @PostMapping("/getListById")
    @ApiOperation(value = "通过用户id或者名字查询用户信息")
    public RespBean getListById(@RequestBody PageDTO3 pageDTO3){
        int current=pageDTO3.getCurrent();
        int size=pageDTO3.getSize();

        String ch=pageDTO3.getCh();
        QueryWrapper<User> wrapper = new QueryWrapper();
        wrapper.eq("user_id",ch);
        List<User> list = userService.list(wrapper);
        if(list.size()>0){
            List<UserDTO2> list1=userService.getListById(ch);
            Map<String,Object>ob=new HashMap<>();
            ob.put("list",list1);
            return RespBean.success("成功",ob);
        }
        ch="%"+ch+"%";
        List<UserDTO2> listByName = userService.getListByName(ch);
        List<Object> list2=Collections.singletonList(listByName);
        List<Object> page = PageUtils.page(list2,current, size);
        Map<String,Object>ob=new HashMap<>();
        ob.put("size",listByName.size());
        ob.put("list",page);
        return RespBean.success("成功",ob);
    }
    @PostMapping("/getUser2")
    @ApiOperation(value = "获得用户列表")
    public RespBean getUser2(@RequestBody PageDTO pageDTO){


        int current=pageDTO.getCurrent();
        int size=pageDTO.getSize();
        int fromIndex = (current-1)*size;

        List<User> list = userService.getList1(fromIndex,size);
        return RespBean.success("获取成功",list);
    }

    @PostMapping("/getUser")
    @ApiOperation(value = "获得用户列表,分页")
    public RespBean getUser(@RequestBody PageDTO pageDTO){
        

        int current=pageDTO.getCurrent();
        int size=pageDTO.getSize();

        List<Object> list =  Collections.singletonList(userService.getList());
        List<Object> page = PageUtils.page(list, current, size);
        return RespBean.success("获取成功",page);
    }

    @PostMapping("/delete")
    @ApiOperation(value = "用过id删除用户")
    public RespBean deleteUser(@RequestBody Integer user_id) {
        System.out.println(user_id);
        QueryWrapper<User> wrapper = new QueryWrapper();
        wrapper.eq("user_id",user_id);
        if(userService.remove(wrapper)){
            return RespBean.success("用户删除成功");
        }
        return RespBean.success("用户删除失败");
    }

    @PostMapping("/addUser")
    @ApiOperation(value = "添加用户")
    public RespBean addUser(@RequestBody User user){
        QueryWrapper<User> wrapper = new QueryWrapper();
        wrapper.eq("user_id",user.getUser_id());
        List<User> list = userService.list(wrapper);
        if(list.size()>0){
            return RespBean.error("添加失败，用户id已经存在");
        }
        user.setUser_password(DigestUtils.md5DigestAsHex("123456".getBytes()));
        if (userService.save(user)){
            return RespBean.success("添加成功");
        }
        return RespBean.success("添加失败");
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改某个用户")
    public RespBean update(@RequestBody User user){
        QueryWrapper<User> wrapper = new QueryWrapper();
        wrapper.eq("user_id",user.getUser_id());
        List<User> list = userService.list(wrapper);
        if(!list.get(0).getUser_password().equals(user.getUser_password())){
            user.setUser_password(DigestUtils.md5DigestAsHex(user.getUser_password().getBytes()));
        }
        userService.remove(wrapper);
        userService.save(user);
        return RespBean.success("修改成功成功");
    }

}
