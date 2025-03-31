package com.rightings.backed.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rightings.backed.exception.BusinessException;
import com.rightings.backed.exception.ErrorCode;
import com.rightings.backed.mapper.AccessKeysMapper;
import com.rightings.backed.mapper.InterfaceInfoMapper;
import com.rightings.backed.model.entity.AccessKeys;
import com.rightings.backed.model.entity.InterfaceInfo;
import com.rightings.backed.model.entity.User;
import com.rightings.backed.service.ImagsInterfaceService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class ImagsInterfaceServiceImpl implements ImagsInterfaceService {


    @Resource
    private InterfaceInfoMapper interfaceInfoMapper;

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
}
