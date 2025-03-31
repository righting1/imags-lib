package com.rightings.backed.controller;

import com.rightings.backed.common.BaseResponse;
import com.rightings.backed.common.ResultUtils;
import com.rightings.backed.model.entity.User;
import com.rightings.backed.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
public class UserProfileController {


    @Resource
    private UserService userService;


    @GetMapping("/user_profile")
    public BaseResponse<User> getUserProfile(HttpServletRequest request) {
        //Long userId = (Long) request.getSession().getAttribute("userId");
        //User user = userService.getUserById(userId);
        User user = userService.getLoginUser(request);
        return ResultUtils.success(user);
        //return user != null ? Response.success(user) : Response.error("用户不存在");
    }
}
