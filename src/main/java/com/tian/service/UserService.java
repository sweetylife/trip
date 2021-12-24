package com.tian.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tian.dto.UserLoginRes;
import com.tian.dto.Userlogin;
import com.tian.entity.User;

public interface UserService extends IService<User> {
    UserLoginRes userLoginOrRegister(Userlogin userlogin) throws IllegalAccessException;
}
