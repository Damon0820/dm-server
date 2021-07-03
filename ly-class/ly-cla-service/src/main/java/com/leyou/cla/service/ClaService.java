package com.leyou.cla.service;

import com.leyou.auth.api.AuthApi;
import com.leyou.cla.mapper.ClaMapper;
import com.leyou.cla.pojo.Cla;
import com.leyou.cla.query.ClaQuery;
import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.common.utils.UUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.rmi.CORBA.Util;
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
        if (StringUtils.isBlank(claQuery.getUserId())) {
            throw new LyException(ExceptionEnum.CLA_USER_ID_NOT_NULL);
        } else if (StringUtils.isBlank(claQuery.getClassName())) {
            throw new LyException(ExceptionEnum.CLA_CLA_NAME_NOT_NULL);
        }
        return true;
    }

    /**
     * 更新
     * @param claQuery
     */
    public int updateCla(ClaQuery claQuery) {
        if (StringUtils.isBlank(claQuery.getClassId())) {
            throw new LyException(ExceptionEnum.CLA_CLA_ID_NOT_NULL);
        }
        return claMapper.updateCla(claQuery);
    }

    /**
     * 删除
     */
    public int deleteCla(String id) {
        if (StringUtils.isBlank(id)) {
            throw new LyException(ExceptionEnum.CLA_CLA_ID_NOT_NULL);
        }
        return claMapper.deleteCla(id);
    }

    public void addClassPerson(ClaQuery claQuery) {
        if (StringUtils.isBlank(claQuery.getClassId())) {
            throw new LyException(ExceptionEnum.CLA_CLA_ID_NOT_NULL);
        }
        for (String pId : claQuery.getPersonIdList()) {
            claMapper.addClassPerson(claQuery.getClassId(), pId, new Date());
        }
    }
}
