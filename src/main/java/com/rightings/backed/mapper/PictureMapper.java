package com.rightings.backed.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rightings.backed.model.dto.picture.PictureQueryRequest;
import com.rightings.backed.model.entity.Picture;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rightings.backed.model.vo.PictureVO;
import org.apache.ibatis.annotations.Param;
/**
* @author 李鱼皮
* @description 针对表【picture(图片)】的数据库操作Mapper
* @createDate 2024-12-11 20:45:51
* @Entity com.rightings.backed.model.entity.Picture
*/
public interface PictureMapper extends BaseMapper<Picture> {

    // 根据条件进行分页查询
    Page<PictureVO> selectPageByCondition(Page<PictureVO> page, @Param("query") PictureQueryRequest query);

}




