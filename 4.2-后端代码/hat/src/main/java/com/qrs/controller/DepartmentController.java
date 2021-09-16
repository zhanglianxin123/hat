package com.qrs.controller;


import com.qrs.pojo.Department;
import com.qrs.pojo.RespBean;
import com.qrs.service.IDepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zlx
 * @since 2021-06-21
 */
@RestController
@RequestMapping("//department")
@Api(value = "安全帽部门模块" ,tags = "hat部门接口")
public class DepartmentController {
    @Autowired
    private IDepartmentService departmentService;

    @GetMapping("/list")
    @ApiOperation(value = "获得部门列表")
    public RespBean getList(){
        List<Department> list = departmentService.list();
        return RespBean.success("成功",list);
    }

}
