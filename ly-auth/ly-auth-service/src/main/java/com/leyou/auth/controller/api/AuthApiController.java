package com.leyou.auth.controller.api;

import com.leyou.auth.api.AuthApi;
import com.leyou.auth.pojo.UserInfo;
import com.leyou.auth.properties.JwtProperties;
import com.leyou.auth.utils.JwtUtils;
import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import feign.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth/inner")
public class AuthApiController implements AuthApi {

    @Autowired
    JwtProperties jwtProperties;

    /**
     * 提供给其他服务调用。根据token，获取简单用户信息
     * TODO：是否可以优化为一个方法，直接引入调用，而不用通过服务间调用
     * @param token
     * @return
     */
    @Override
    public UserInfo verifyUser(
            String token
    ) {
        try {
            UserInfo userInfo = JwtUtils.getInfoFromToken(token, jwtProperties.getPublicKey());
            return userInfo;
        } catch (Exception e) {
            throw new LyException(ExceptionEnum.TOKEN_ERROR);
        }
    }
}
