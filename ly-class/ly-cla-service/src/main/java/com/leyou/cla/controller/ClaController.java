package com.leyou.cla.controller;

import com.leyou.auth.api.AuthApi;
import com.leyou.auth.pojo.UserInfo;
import com.leyou.cla.pojo.Cla;
import com.leyou.cla.query.ClaQuery;
import com.leyou.cla.service.ClaService;
import com.leyou.common.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClaController {

    private static int PAGE_SIZE_DEF = 10;
    private static int PAGE_DEF = 1;

    @Autowired
    private AuthApi authApi;


    @Autowired
    ClaService claService;

    /**
     * 列表
     * @param userId
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("queryClaList")
    public ResponseEntity<Result<List<Cla>>> queryClaList(
            @RequestParam(value = "userId") String userId,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "pageSize", required = false) Integer pageSize
    ) {
        if (page == null) page = PAGE_DEF;
        if (pageSize == null) pageSize = PAGE_SIZE_DEF;
        List<Cla> claList = claService.queryClaList(userId, page -1, pageSize);
        return ResponseEntity.ok(Result.success(claList));
    }

    /**
     * 新增
     * @param token
     * @param claQuery
     * @return
     */
    @PostMapping("addCla")
    public ResponseEntity<Result> addCla(
            @CookieValue("LY_TOKEN") String token,
            @RequestBody ClaQuery claQuery
    ) {
        // 通过token，设置userId
        UserInfo userInfo = authApi.verifyUser(token);
        claQuery.setUserId(userInfo.getUserId());
        claService.addCla(claQuery);
        return  ResponseEntity.ok(Result.success(true));
    }

    /**
     * 更新
     * @param claQuery
     * @return
     */
    @PostMapping("updateCla")
    public ResponseEntity<Result> updateCla(
            @RequestBody ClaQuery claQuery
    ) {
        int res = claService.updateCla(claQuery);
        return ResponseEntity.ok(Result.success(true));
    }

    /**
     * 删除
     */
    @PostMapping("deleteCla")
    public ResponseEntity<Result> deleteCla(
            @RequestBody ClaQuery claQuery
    ) {
        int res = claService.deleteCla(claQuery.getClassId());
        return ResponseEntity.ok(Result.success(true));
    }
}
