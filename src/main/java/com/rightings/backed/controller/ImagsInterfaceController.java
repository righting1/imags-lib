package com.rightings.backed.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rightings.backed.annotation.AuthCheck;
import com.rightings.backed.api.aliyunai.AliYunAiApi;
import com.rightings.backed.common.BaseResponse;
import com.rightings.backed.common.ResultUtils;
import com.rightings.backed.constant.UserConstant;
import com.rightings.backed.exception.ErrorCode;
import com.rightings.backed.exception.ThrowUtils;
import com.rightings.backed.manager.auth.SpaceUserAuthManager;
import com.rightings.backed.manager.auth.StpKit;
import com.rightings.backed.manager.auth.model.SpaceUserPermissionConstant;
import com.rightings.backed.model.dto.picture.PictureQueryRequest;
import com.rightings.backed.model.dto.space.analyze.SpaceRankAnalyzeRequest;
import com.rightings.backed.model.dto.space.analyze.SpaceTagAnalyzeRequest;
import com.rightings.backed.model.entity.Picture;
import com.rightings.backed.model.entity.Space;
import com.rightings.backed.model.entity.User;
import com.rightings.backed.model.enums.PictureReviewStatusEnum;
import com.rightings.backed.model.vo.PictureVO;
import com.rightings.backed.model.vo.space.analyze.SpaceTagAnalyzeResponse;
import com.rightings.backed.service.*;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/interface")
public class ImagsInterfaceController {

    @Resource
    private UserService userService;


    @Resource
    private PictureService pictureService;

    @Resource
    private SpaceService spaceService;
    @Resource
    private SpaceUserAuthManager spaceUserAuthManager;

    @Resource
    private SpaceAnalyzeService spaceAnalyzeService;

    @Resource
    private ImagsInterfaceService imagsInterfaceService;




    /**
     * 根据 id 获取图片（封装类）
     */
    @GetMapping("/getPicturevo")
    public BaseResponse<PictureVO> getPictureVOById(long id, HttpServletRequest request) {
        ThrowUtils.throwIf(id <= 0, ErrorCode.PARAMS_ERROR);
        // 查询数据库
        Picture picture = pictureService.getById(id);
        ThrowUtils.throwIf(picture == null, ErrorCode.NOT_FOUND_ERROR);
        // 空间权限校验
        Long spaceId = picture.getSpaceId();
        Space space = null;
        if (spaceId != null) {
            boolean hasPermission = StpKit.SPACE.hasPermission(SpaceUserPermissionConstant.PICTURE_VIEW);
            ThrowUtils.throwIf(!hasPermission, ErrorCode.NO_AUTH_ERROR);
            // 已经改为使用注解鉴权
            // User loginUser = userService.getLoginUser(request);
            // pictureService.checkPictureAuth(loginUser, picture);
            space = spaceService.getById(spaceId);
            ThrowUtils.throwIf(space == null, ErrorCode.NOT_FOUND_ERROR, "空间不存在");
        }

        PictureVO pictureVO = pictureService.getPictureVO(picture, request);
        // 获取封装类
        return ResultUtils.success(pictureVO);
    }

    @PostMapping("/space/analyze/rank")
    public BaseResponse<List<Space>> getSpaceRankAnalyze(int topx,
                                                         HttpServletRequest request) {
        return ResultUtils.success(imagsInterfaceService.getSpaceRankAnalyze(topx,request));
    }


    @PostMapping("/space/analyze/tag")
    public BaseResponse<List<SpaceTagAnalyzeResponse>> getSpaceTagAnalyze(
            @RequestBody SpaceTagAnalyzeRequest spaceTagAnalyzeRequest,
            HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        ThrowUtils.throwIf(loginUser == null, ErrorCode.PARAMS_ERROR, "参数错误");

        return ResultUtils.success(spaceAnalyzeService.getSpaceTagAnalyze(spaceTagAnalyzeRequest,loginUser));
    }




}
