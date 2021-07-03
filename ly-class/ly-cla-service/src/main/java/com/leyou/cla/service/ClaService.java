package com.leyou.cla.service;

import com.leyou.auth.api.AuthApi;
import com.leyou.cla.mapper.ClaMapper;
import com.leyou.cla.pojo.Cla;
import com.leyou.cla.query.ClaQuery;
import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.common.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ClaService {
    
    @Autowired
    private ClaMapper claMapper;

    public List<Cla> queryClaList(String userId, int page, int pageSize) {
        return claMapper.queryClaList(userId, page, pageSize);
    }

    public void addCla(ClaQuery claQuery) {
        claQuery.setClassId(UUIDUtil.getUUID());
        claQuery.setCreateDate(new Date());
        claQuery.setActive("Y");
        this.checkAdd(claQuery);
        claMapper.addCla(claQuery);
    }

    /**
     * 校验参数是否可以新增
     * @param claQuery
     * @return
     */
    public Boolean checkAdd(ClaQuery claQuery) {
        if (claQuery.getUserId() == null) {
            throw new LyException(ExceptionEnum.CLA_USER_ID_NOT_NULL);
        }
        return true;
    }
}
