package com.leyou.auth.service;


import com.leyou.auth.properties.JwtProperties;
import com.leyou.auth.utils.JwtUtils;
import com.leyou.user.api.UserApi;
import com.leyou.user.pojo.User;
import com.leyou.auth.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserApi userApi;

    @Autowired
    JwtProperties jwtProperties;

    /**
     * 授权，生成token
     * @param username
     * @param password
     * @return
     */
    public String authentication(String username, String password){
        // 调用微服务，查询用户信息
        try{
            User user = userApi.queryUserByUsernameAndPassword(username, password);
            if (user == null) return null;
            // 生成token
            String token = JwtUtils.generateToken(new UserInfo(user.getUserId(), user.getUsername()), jwtProperties.getPrivateKey(), jwtProperties.getExpire());
            return token;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取用户信息
     * @param username
     * @return
     */
    public User queryUserInfoByUsername(String username){
        // 调用微服务，查询用户信息
        try{
            User user = userApi.queryUserByUsername(username);
            if (user == null) return null;
            return user;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
