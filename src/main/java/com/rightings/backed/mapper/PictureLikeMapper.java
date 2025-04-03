package com.rightings.backed.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rightings.backed.model.entity.PictureLike;
import com.rightings.backed.model.vo.UserLikeCategoryVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author lenovo
* @description 针对表【picture_like(用户-图片点赞关系表)】的数据库操作Mapper
* @createDate 2025-04-02 19:13:42
* @Entity generator.domain.PictureLike
*/
public interface PictureLikeMapper extends BaseMapper<PictureLike> {



    int checkLikeRecord(@Param("userId") Long userId, @Param("pictureId") Long pictureId);

    void insertLikeRecord(@Param("userId") Long userId, @Param("pictureId") Long pictureId);

    void updateLikeStatus(@Param("userId") Long userId, @Param("pictureId") Long pictureId);

    List<UserLikeCategoryVo> getUserLikeList(@Param("userId") Long userId);
}




