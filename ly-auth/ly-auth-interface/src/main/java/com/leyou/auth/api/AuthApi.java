package com.leyou.auth.api;

import com.leyou.auth.pojo.UserInfo;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "auth-service", path = "api/auth/inner")
public interface AuthApi {

    @GetMapping("verify")
    public UserInfo verifyUser(
            @RequestParam("token") String token
    );
}
