package com.rightings.sdk.client;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;
import com.rightings.sdk.model.User;
import org.checkerframework.checker.units.qual.C;

public class ImagsClient extends CommonApiClient{

    public ImagsClient(String accessKey, String secretKey) {
        super(accessKey,secretKey);
    }

    public String getPictureVOById(String id){
        return HttpRequest.get(GATEWAY_HOST+"/api/interface/getPicturevo")
                .form("id",id)
                .addHeaders(getHeadMap("",accessKey,secretKey))
                .execute().body();
    }

    public static void main(String[] args) {
        ImagsClient imagsClient = new ImagsClient("a", "b");
        System.out.println(imagsClient.getPictureVOById("1906600435528622082"));
    }
}
