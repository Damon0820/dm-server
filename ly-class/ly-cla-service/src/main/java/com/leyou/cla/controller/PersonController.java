package com.leyou.cla.controller;

import com.leyou.auth.api.AuthApi;
import com.leyou.auth.pojo.UserInfo;
import com.leyou.cla.pojo.Person;
import com.leyou.cla.query.PersonQuery;
import com.leyou.cla.service.PersonService;
import com.leyou.common.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("person")
public class PersonController {

    private static int PAGE_SIZE_DEF = 10;
    private static int PAGE_DEF = 1;

    @Autowired
    private AuthApi authApi;


    @Autowired
    PersonService personService;

    /**
     * 列表，获取当前登录人的人员列表
     * @param userId
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("queryList")
    public ResponseEntity<Result<List<Person>>> queryList(
            @CookieValue("LY_TOKEN") String token,
            @RequestParam(value = "classId") String classId,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "pageSize", required = false) Integer pageSize
    ) {
        if (page == null) page = PAGE_DEF;
        if (pageSize == null) pageSize = PAGE_SIZE_DEF;
        // 通过token，设置userId
        UserInfo userInfo = authApi.verifyUser(token);
        List<Person> list = personService.queryList( classId, page -1, pageSize);
        return ResponseEntity.ok(Result.success(list));
    }

    /**
     * 新增
     * @param token
     * @param personQuery
     * @return
     */
    @PostMapping("addBean")
    public ResponseEntity<Result> addBean(
            @CookieValue("LY_TOKEN") String token,
            @RequestBody PersonQuery personQuery
    ) {
        // 通过token，设置userId
        UserInfo userInfo = authApi.verifyUser(token);
        personQuery.setCreateBy(userInfo.getUserId());
        personService.addBean(personQuery);
        return  ResponseEntity.ok(Result.success(true));
    }

    /**
     * 更新
     * @param personQuery
     * @return
     */
    @PostMapping("updateBean")
    public ResponseEntity<Result> updateBean(
            @RequestBody PersonQuery personQuery
    ) {
        int res = personService.updateBean(personQuery);
        return ResponseEntity.ok(Result.success(true));
    }

    /**
     * 删除
     */
    @PostMapping("deleteBean")
    public ResponseEntity<Result> deleteBean(
            @RequestBody PersonQuery personQuery
    ) {
        int res = personService.deleteBean(personQuery.getClassId());
        return ResponseEntity.ok(Result.success(true));
    }
}
