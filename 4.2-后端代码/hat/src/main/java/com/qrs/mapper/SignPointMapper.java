package com.qrs.mapper;

import com.qrs.pojo.DTO.PointDTO;
import com.qrs.pojo.SignPoint;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zlx
 * @since 2021-06-23
 */
public interface SignPointMapper extends BaseMapper<SignPoint> {
    /**
     * 通过签到区域id获得点列表
     * @param sign_id
     * @return
     */
    @Select("SELECT h_signPoint.`lng`,h_signPoint.`lat` FROM h_signPoint WHERE sign_id=#{sign_id}")
    List<PointDTO> getListById(Integer sign_id);
}
