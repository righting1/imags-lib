package com.rightings.backed.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rightings.backed.model.entity.PictureLike;

/**
* @author lenovo
* @description 针对表【picture_like(用户-图片点赞关系表)】的数据库操作Service
* @createDate 2025-04-02 19:13:42
*/
public interface PictureLikeService extends IService<PictureLike> {

    public PictureLike getByUserPicture(Long userId, Long pictureId);

}
