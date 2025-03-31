package com.rightings.backed.config;

import cn.hutool.core.text.AntPathMatcher;
import com.rightings.backed.exception.BusinessException;
import com.rightings.backed.exception.ErrorCode;
import com.rightings.backed.model.entity.AccessKeys;
import com.rightings.backed.model.entity.InterfaceInfo;
import com.rightings.backed.service.ImagsInterfaceService;
import com.rightings.sdk.utils.SignUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
@Slf4j
@Order(1)
@Component
public class SdkRequestWrapperFilter implements Filter {

    @Resource
    private ImagsInterfaceService imagsInterfaceService;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public static final List<String> NOT_LOGIN_PATH = Arrays.asList("/api/interface/**");
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest var = (HttpServletRequest) request;
        String path = var.getRequestURI();

        // 记录请求详细信息
        log.info("===== 请求信息 =====");
        log.info("请求路径 (URI): {}", path);
        log.info("请求方法 (Method): {}", var.getMethod());
        log.info("请求参数 (Query String): {}", var.getQueryString());
        log.info("客户端 IP: {}", var.getRemoteAddr());

        // 记录请求头
        Enumeration<String> headerNames = var.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            log.info("请求头 [{}]: {}", headerName, var.getHeader(headerName));
        }
        //String path = request.getPath().toString();
        //判断请求路径是否需要登录
        List<Boolean> collect = NOT_LOGIN_PATH.stream().map(notLoginPath -> {
            AntPathMatcher antPathMatcher = new AntPathMatcher();
            return antPathMatcher.match(notLoginPath, path);
        }).collect(Collectors.toList());

        if (!collect.contains(true)){
             log.info("通过");
             filterChain.doFilter(request, response);
             return ;
        }

        log.info("未通过");

        //HttpHeaders headers = request.getHeaders();

        //String accessKey = headers.getFirst("accessKey");
        String accessKey = var.getHeader("accessKey");
        String body = var.getHeader("body");
        String sign = var.getHeader("sign");
        String nonce = var.getHeader("nonce");
        String timestamp = var.getHeader("timestamp");



        //根据accessKey获取secretKey，判断accessKey是否合法

        AccessKeys accessKeys = imagsInterfaceService.getAccessKey(accessKey);
        String secretKey = accessKeys.getSecretKey();
        String serverSign = SignUtils.generateSign(body, secretKey);

        if (sign == null || !sign.equals(serverSign)){
            log.error("签名校验失败!!!!");
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "签名校验失败");
        }

        //3.1防重放，使用redis存储请求的唯一标识，随机时间，并定时淘汰，那使用什么redis结构来实现嗯？
        //既然是单个数据，这样用string结构实现即可

        Boolean success = stringRedisTemplate.opsForValue().setIfAbsent(nonce, "1", 5, TimeUnit.MINUTES);
        if (success ==null){
            log.error("随机数存储失败!!!!");
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "随机数存储失败");
        }

        //4.远程调用判断接口是否存在以及获取调用接口信息
        InterfaceInfo interFaceInfo = null;
        try {
            interFaceInfo = imagsInterfaceService.getInterFaceInfo(path, var.getMethod());
        } catch (Exception e) {
            log.info("远程调用获取被调用接口信息失败");
            e.printStackTrace();
        }


        if (interFaceInfo == null){
            log.error("接口不存在！！！！");
            throw  new BusinessException(ErrorCode.PARAMS_ERROR, "接口不存在");
        }

        filterChain.doFilter(request, response);
    }
}
