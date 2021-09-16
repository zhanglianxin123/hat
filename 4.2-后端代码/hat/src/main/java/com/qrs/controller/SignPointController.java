package com.qrs.controller;


import com.qrs.pojo.DTO.PointDTO;
import com.qrs.pojo.RespBean;
import com.qrs.pojo.SignPoint;
import com.qrs.service.ISignPointService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zlx
 * @since 2021-06-23
 */
@RestController
@RequestMapping("//sign-point")
public class SignPointController {
    @Autowired
    private ISignPointService signPointService;

    @PostMapping("/get")
    @ApiOperation("通过签到区域id获得点列表")
    public RespBean get(Integer sign_id){
        List<PointDTO> list = signPointService.getListById(sign_id);
        return RespBean.success("成功",list);
    }

}
