package com.qrs.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qrs.pojo.*;
import com.qrs.pojo.DTO.PointDTO;
import com.qrs.pojo.DTO.SignDTO;
import com.qrs.pojo.DTO.UserDTO3;
import com.qrs.service.ISignPointService;
import com.qrs.service.ISignService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zlx
 * @since 2021-06-17
 */
@RestController
@RequestMapping("//sign")
@Api(value = "签到接口", tags = "hat签到接口")
public class SignController {
    @Autowired
    private ISignService signService;
    @Autowired
    private ISignPointService signPointService;

    @GetMapping("/list")
    @ApiOperation("获得所有签到区域")
    public RespBean list(){
        List<Sign> list = signService.list();
        List<SignDTO>list1=new ArrayList<>();
        for(Sign sign:list){
            List<PointDTO> listByID =signPointService.getListById(sign.getSign_id());
            PointDTO pointDTO2=gravityPoint(listByID);
            SignDTO signDTO=new SignDTO(sign.getSign_id(),sign.getSign_number(),sign.getSign_name(),sign.getSign_node(),signPointService.getListById(sign.getSign_id()),pointDTO2);
            list1.add(signDTO);
        }
        return RespBean.success("成功",list1);

    }

    @PostMapping("/add")
    @ApiOperation("添加签到区域")
    public RespBean add(@RequestBody SignDTO signDTO){
        Sign sign=Sign.builder()
                .sign_id(signDTO.getSign_id())
                .sign_number(signDTO.getSign_number())
                .sign_name(signDTO.getSign_name())
                .sign_node(signDTO.getSign_node())
                .build();
        List<PointDTO> signDTOS=signDTO.getPoint();
        for(PointDTO pointDTO:signDTOS){
            SignPoint signPoint=SignPoint.builder()
                    .sign_id(signDTO.getSign_id())
                    .lng(pointDTO.getLng())
                    .lat(pointDTO.getLat())
                    .build();
            signPointService.save(signPoint);
        }
        if(signService.save(sign)){
            return RespBean.success("成功");
        }
        return RespBean.error("失败");
    }
    @GetMapping("/getUserList")
    @ApiOperation("获得签到人员列表")
    public RespBean getUserList(){
        List<UserDTO3> list = signService.getUserList();
        return RespBean.success("成功",list);
    }

    @PostMapping("/getListById")
    @ApiOperation("通过设备id获得签到记录")
    public RespBean getListById(@RequestBody Integer equipment_id ){
        List<SignRecord> list = signService.getListById(equipment_id);
        return RespBean.success("成功",list);
    }
    @PostMapping("/delete")
    @ApiOperation("删除")
    public RespBean delete(@RequestBody Integer sign_id ){
        QueryWrapper<SignPoint> wrapper1=new QueryWrapper<>();
        wrapper1.eq("sign_id",sign_id);
        signPointService.remove(wrapper1);

        QueryWrapper<Sign> wrapper=new QueryWrapper<>();
        wrapper.eq("sign_id",sign_id);
        if(signService.remove(wrapper)){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }


    public PointDTO gravityPoint(List<PointDTO> mPoints){
        double area = 0.0;//多边形面积
        double Gx = 0.0, Gy = 0.0;// 重心的x、y
        for (int i = 1; i <= mPoints.size(); i++) {
            double iLat = mPoints.get(i % mPoints.size()).getLat();
            double iLng = mPoints.get(i % mPoints.size()).getLng();
            double nextLat = mPoints.get(i - 1).getLat();
            double nextLng = mPoints.get(i - 1).getLng();
            double temp = (iLat * nextLng - iLng * nextLat) / 2.0;
            area += temp;
            Gx += temp * (iLat + nextLat) / 3.0;
            Gy += temp * (iLng + nextLng) / 3.0;
        }
        Gx = Gx / area;
        Gy = Gy / area;
        PointDTO pointDTO=new PointDTO(Double.valueOf(String.valueOf(Gy).substring(0, String.valueOf(Gy).indexOf(".")+7)),Double.valueOf(String.valueOf(Gx).substring(0, String.valueOf(Gx).indexOf(".")+7)));
        return pointDTO;
    }



}
