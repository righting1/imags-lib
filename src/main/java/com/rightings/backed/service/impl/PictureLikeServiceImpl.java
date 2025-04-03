package com.rightings.backed.service.impl;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rightings.backed.exception.BusinessException;
import com.rightings.backed.exception.ErrorCode;
import com.rightings.backed.exception.ThrowUtils;
import com.rightings.backed.mapper.PictureLikeMapper;
import com.rightings.backed.mapper.SpaceMapper;
import com.rightings.backed.model.dto.space.analyze.*;
import com.rightings.backed.model.entity.Picture;
import com.rightings.backed.model.entity.PictureLike;
import com.rightings.backed.model.entity.Space;
import com.rightings.backed.model.entity.User;
import com.rightings.backed.model.vo.space.analyze.*;
import com.rightings.backed.service.PictureService;
import com.rightings.backed.service.SpaceAnalyzeService;
import com.rightings.backed.service.SpaceService;
import com.rightings.backed.service.UserService;
import org.springframework.stereotype.Service;
import com.rightings.backed.service.PictureLikeService;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
* @author lenovo
* @description 针对表【picture_like(用户-图片点赞关系表)】的数据库操作Service实现
* @createDate 2025-04-02 19:13:42
*/
@Service
public class PictureLikeServiceImpl extends ServiceImpl<PictureLikeMapper, PictureLike>
        implements PictureLikeService {


    @Resource
    private  PictureLikeMapper pictureLikeMapper;



    @Override
    public PictureLike getByUserPicture(Long userId, Long pictureId) {
        PictureLike pictureLike = pictureLikeMapper.selectOne(
                new LambdaQueryWrapper<PictureLike>().
                        eq(PictureLike::getUserId, userId).
                        eq(PictureLike::getPictureId, pictureId));

        return pictureLike;
    }
}




