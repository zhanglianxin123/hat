package com.qrs.mapper;

import com.qrs.pojo.Warning;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zlx
 * @since 2021-06-17
 */
public interface WarningMapper extends BaseMapper<Warning> {
    /**
     *
     * @param why
     * @return
     */
    @Select("SELECT h_warning.*\n" +
            "FROM h_warning\n" +
            "WHERE warning_why LIKE concat(#{why})")
    List<Warning> getByWhy(String why);
}
