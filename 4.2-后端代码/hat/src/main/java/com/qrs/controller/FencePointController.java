package com.qrs.controller;


import com.qrs.pojo.DTO.PointDTO;
import com.qrs.pojo.RespBean;
import com.qrs.service.IFencePointService;
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
@RequestMapping("//fence-point")
public class FencePointController {

    @Autowired
    private IFencePointService fencePointService;
    @PostMapping("/getList")
    @ApiOperation("通过围栏id获得点列表")
    public RespBean getListByID(Integer fence_id){
        List<PointDTO> list = fencePointService.getListByID(fence_id);
        return RespBean.success("成功",list);
    }

}
