package com.rightings.backed.service;

import com.rightings.backed.model.entity.AccessKeys;
import com.rightings.backed.model.entity.InterfaceInfo;

public interface ImagsInterfaceService {

    AccessKeys getAccessKey(String accessKey);

    InterfaceInfo getInterFaceInfo(String url, String method);
}
