package com.rightings.backed.service;

import com.rightings.backed.common.BaseResponse;
import com.rightings.backed.model.dto.space.analyze.SpaceTagAnalyzeRequest;
import com.rightings.backed.model.entity.AccessKeys;
import com.rightings.backed.model.entity.InterfaceInfo;
import com.rightings.backed.model.entity.Space;
import com.rightings.backed.model.vo.space.analyze.SpaceTagAnalyzeResponse;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ImagsInterfaceService {

    AccessKeys getAccessKey(String accessKey);

    InterfaceInfo getInterFaceInfo(String url, String method);

    List<Space> getSpaceRankAnalyze(int topx, HttpServletRequest request);

    public List<SpaceTagAnalyzeResponse> getSpaceTagAnalyze(SpaceTagAnalyzeRequest spaceTagAnalyzeRequest,
            HttpServletRequest request);
}
