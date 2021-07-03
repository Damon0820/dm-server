package com.leyou.user.controller.app;

import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.common.vo.Result;
import com.leyou.user.pojo.User;
import com.leyou.user.query.UserQuery;
import com.leyou.user.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("check")
    public ResponseEntity<Result<Boolean>> checkRegData(
            @RequestParam(value = "username", required = true) String username,
            @RequestParam(value = "phone", required = true) String phone,
            @RequestParam(value = "type", required = true) String type
    ) {
        Boolean checkEnable = userService.checkRegData(type, username, phone);
        return ResponseEntity.ok(Result.success(checkEnable));
    }

    @PostMapping("register")
    public ResponseEntity<Result<Boolean>> register(
            @RequestBody UserQuery userQuery
            ) {
        User user = new User();
        user.setPhone(userQuery.getPhone());
        user.setUsername(userQuery.getUsername());
        user.setPassword(userQuery.getPassword());
        user.setActive("Y");
        // 校验是否用户名和手机号可用
        userService.checkRegData("1", userQuery.getUsername(), userQuery.getPhone());
        userService.checkRegData("2", userQuery.getUsername(), userQuery.getPhone());
        Boolean isSuccess = userService.register( user, userQuery.getCode());
        return ResponseEntity.ok(Result.success(isSuccess));
    }

    @GetMapping("queryUserByUsernameAndPassword")
    public ResponseEntity<User> queryUserByUsernameAndPassword(
            @RequestParam(value = "username", required = true) String username,
            @RequestParam(value = "password", required = true) String password
    ) {
        User user = userService.queryUserByUsernameAndPassword(username, password);
        if (user == null) {
            throw new LyException(ExceptionEnum.USER_LOGIN_ERROR);
//            return new ResponseEntity<>(, HttpStatus.OK);
        }
        return ResponseEntity.ok(user);
    }
}
