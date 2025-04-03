package com.rightings.backed.service.impl;

import cn.hutool.core.util.ObjUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rightings.backed.exception.BusinessException;
import com.rightings.backed.exception.ErrorCode;
import com.rightings.backed.exception.ThrowUtils;
import com.rightings.backed.mapper.AccessKeysMapper;
import com.rightings.backed.mapper.InterfaceInfoMapper;
import com.rightings.backed.model.dto.space.analyze.SpaceTagAnalyzeRequest;
import com.rightings.backed.model.entity.*;
import com.rightings.backed.model.vo.space.analyze.SpaceTagAnalyzeResponse;
import com.rightings.backed.service.ImagsInterfaceService;
import com.rightings.backed.service.SpaceAnalyzeService;
import com.rightings.backed.service.SpaceService;
import com.rightings.backed.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ImagsInterfaceServiceImpl implements ImagsInterfaceService {


    @Resource
    private InterfaceInfoMapper interfaceInfoMapper;

    @Resource
    private SpaceService spaceService;

    @Resource
    private UserService userService;

    @Resource
    private SpaceAnalyzeService spaceAnalyzeService;

    @Resource
    private AccessKeysMapper accessKeysMapper;
    @Override
    public AccessKeys getAccessKey(String accessKey) {
        //根据ak找到sk

        QueryWrapper<AccessKeys> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("accessKey",accessKey);
        return accessKeysMapper.selectOne(queryWrapper);
        //return accessKeys.getSecretKey();
        //return null;
    }

    @Override
    public InterfaceInfo getInterFaceInfo(String url, String method) {
        if (StringUtils.isBlank(url) || StringUtils.isBlank(method)){
            //throw new BusinessException(ErrorCode.PARAMS_ERROR);
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "不存在这该接口");
        }

        QueryWrapper<InterfaceInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("url",url);
        queryWrapper.eq("method",method);
        return interfaceInfoMapper.selectOne(queryWrapper);
    }

    @Override
    public List<Space> getSpaceRankAnalyze(int topx, HttpServletRequest request) {
        // 构造查询条件
        QueryWrapper<Space> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "spaceName", "userId", "totalSize")
                .orderByDesc("totalSize")
                .last("limit " + topx); // 取前 N 名

        // 查询并封装结果
        return spaceService.list(queryWrapper);
    }

    @Override
    public List<SpaceTagAnalyzeResponse> getSpaceTagAnalyze(SpaceTagAnalyzeRequest spaceTagAnalyzeRequest, HttpServletRequest request) {
        ThrowUtils.throwIf(spaceTagAnalyzeRequest == null, ErrorCode.PARAMS_ERROR);
        User loginUser = userService.getLoginUser(request);
        List<SpaceTagAnalyzeResponse> spaceTagAnalyze = spaceAnalyzeService.getSpaceTagAnalyze(spaceTagAnalyzeRequest, loginUser);
        return spaceTagAnalyze;
    }
}
