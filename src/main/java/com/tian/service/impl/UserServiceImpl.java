package com.tian.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tian.dao.UserDao;
import com.tian.dto.UserLoginRes;
import com.tian.dto.Userlogin;
import com.tian.entity.User;
import com.tian.service.UserService;
import com.tian.utils.result.DefinitionException;
import com.tian.utils.token.JWTUtils;
import com.tian.utils.tools.ObjectAndMap;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @ projectName:    trip
 * @ package:        com.tian.service.impl
 * @ className:      UserServiceImpl
 * @ author:     tian
 * @ description:  TODO
 * @ date:    2021/12/24 14:34
 * @ version:    1.0
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {
    @Autowired
    private UserDao userDao;


    @Override
    public UserLoginRes userLoginOrRegister(Userlogin userlogin) throws IllegalAccessException {
        User user = new User();
        user.setTelephone(userlogin.getPhone());
        QueryWrapper<User> wrapper = new QueryWrapper<>(user);
        User one = this.getOne(wrapper);
        if(one!=null){
            //有人
            if(userlogin.getPassword()==null&&userlogin.getSmsCode()==null){
                throw new DefinitionException("缺少参数");
            }else if(userlogin.getPassword()==null){
                //密码登录
                if(one.getPassword()==userlogin.getPassword()){
                    //密码登陆成功
                    UserLoginRes loginRes = new UserLoginRes();
                    BeanUtils.copyProperties(one,loginRes);
                    loginRes.setToken(JWTUtils.createToken(one.getId().toString()));
                    return loginRes;
                }else if(userlogin.getPassword()!=null){
                    throw new DefinitionException("密码错误");
                }
            }else if(userlogin.getSmsCode()!=null){
                //验证码登录 TODO 判断验证码是否正确
                UserLoginRes loginRes = new UserLoginRes();
                BeanUtils.copyProperties(one,loginRes);
                loginRes.setToken(JWTUtils.createToken(one.getId().toString()));
                return loginRes;
            }
        }else{
            //注册
            User userNew = new User();
            userNew.setTelephone(userlogin.getPhone());
            if(userlogin.getSmsCode()!=null){
                //验证码登录 TODO 判断验证码是否正确
                boolean save = this.save(userNew);
                if(!save){
                    throw new DefinitionException("注册失败");
                }else {
                    UserLoginRes loginRes = new UserLoginRes();
                    BeanUtils.copyProperties(userNew,loginRes);
                    loginRes.setToken(JWTUtils.createToken(one.getId().toString()));
                    return loginRes;
                }
            }else {
                throw new DefinitionException("用户未注册，请使用验证码登录");
            }
        }
        return null;
    }
}
