package com.qrs.service;

import com.qrs.pojo.Warning;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zlx
 * @since 2021-06-17
 */
public interface IWarningService extends IService<Warning> {

    List<Warning> getByWhy(String why);

}
