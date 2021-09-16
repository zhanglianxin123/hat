package com.qrs.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qrs.pojo.DTO.FenceDTO;
import com.qrs.pojo.DTO.PointDTO;
import com.qrs.pojo.Fence;
import com.qrs.pojo.FencePoint;
import com.qrs.pojo.RespBean;
import com.qrs.service.IFencePointService;
import com.qrs.service.IFenceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zlx
 * @since 2021-06-17
 */
@RestController
@RequestMapping("//fence")
@Api(value = "围栏接口", tags = "hat围栏接口")
public class FenceController {
    @Autowired
    private IFenceService fenceService;
    @Autowired
    private IFencePointService fencePointService;

    @GetMapping("/get")
    @ApiOperation("获得围栏列表")
    public RespBean get(){
        List<Fence> list = fenceService.list();
        List<FenceDTO> list1=new ArrayList<>();

        for(Fence fence:list){
            List<PointDTO> listByID = fencePointService.getListByID(fence.getFence_id());
            PointDTO stringObjectMap = gravityPoint(listByID);
            FenceDTO fenceDTO=new FenceDTO(fence.getFence_id(),fence.getFence_number(),fence.getFence_name(),fence.getFence_node(),stringObjectMap,fencePointService.getListByID(fence.getFence_id()));
            list1.add(fenceDTO);
        }
        return RespBean.success("成功",list1);

    }

    @PostMapping("/add")
    @ApiOperation("添加围栏")
    public RespBean add(@RequestBody FenceDTO fenceDTO){
        System.out.println(fenceDTO);
        Fence fence=Fence.builder()
                .fence_id(fenceDTO.getFence_id())
                .fence_name(fenceDTO.getFence_name())
                .fence_node(fenceDTO.getFence_node())
                .fence_number(fenceDTO.getFence_number())
                .build();
        List<PointDTO> list=fenceDTO.getPoint();

        for(PointDTO fencePointDTO:list){
            FencePoint fencePoint=FencePoint.builder()
                    .fence_id(fence.getFence_id())
                    .lng(fencePointDTO.getLng())
                    .lat(fencePointDTO.getLat())
                    .build();
            fencePointService.save(fencePoint);
        }
        if(fenceService.save(fence)){
            return RespBean.success("添加成功");
        }
        return RespBean.success("添加失败");

    }

    @PostMapping("/delete")
    @ApiOperation("删除围栏")
    public RespBean delete(@RequestBody Integer fence_id){
        QueryWrapper<FencePoint> wrapper1=new QueryWrapper<>();
        wrapper1.eq("fence_id",fence_id);
        fencePointService.remove(wrapper1);

        QueryWrapper<Fence> wrapper=new QueryWrapper<>();
        wrapper.eq("fence_id",fence_id);
        if(fenceService.remove(wrapper)){
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
