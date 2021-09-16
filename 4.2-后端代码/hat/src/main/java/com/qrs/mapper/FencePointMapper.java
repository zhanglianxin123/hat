package com.qrs.mapper;

import com.qrs.pojo.DTO.PointDTO;
import com.qrs.pojo.FencePoint;
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
public interface FencePointMapper extends BaseMapper<FencePoint> {

    /**
     * 通过围栏id获得点列表
     * @param fence_id
     * @return
     */
    @Select("SELECT h_fencePoint.`lng`,h_fencePoint.`lat` FROM h_fencePoint WHERE fence_id=#{fence_id}")
    List<PointDTO> getListByID(Integer fence_id);
}
