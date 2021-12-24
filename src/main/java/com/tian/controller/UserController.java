package com.tian.controller;

import com.alibaba.fastjson.JSONObject;
import com.tian.dto.UserLoginRes;
import com.tian.dto.Userlogin;
import com.tian.service.UserService;
import com.tian.utils.result.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @ projectName:    trip
 * @ package:        com.tian.controller
 * @ className:      UserController
 * @ author:     tian
 * @ description:  TODO
 * @ date:    2021/12/24 14:30
 * @ version:    1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResultResponse<JSONObject> login(@RequestBody @Valid Userlogin userlogin) throws IllegalAccessException {
        UserLoginRes userLoginRes = userService.userLoginOrRegister(userlogin);
        return ResultResponse.success(userLoginRes);
    }
}
