package com.leyou.cla.service;

import com.leyou.cla.mapper.ClaMapper;
import com.leyou.cla.mapper.PersonMapper;
import com.leyou.cla.pojo.Person;
import com.leyou.cla.query.PersonQuery;
import com.leyou.cla.query.PersonQuery;
import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.common.utils.UUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PersonService {
    
    @Autowired
    private PersonMapper personMapper;

    public List<Person> queryList(String classId, int page, int pageSize) {
        return personMapper.queryList(classId, page, pageSize);
    }

    public void addBean(PersonQuery personQuery) {
        personQuery.setPersonId(UUIDUtil.getUUID());
        personQuery.setCreateDate(new Date());
        personQuery.setActive("Y");
        this.checkAdd(personQuery);
        personMapper.addBean(personQuery);
    }

    /**
     * 校验参数是否可以新增
     * @param personQuery
     * @return
     */
    public Boolean checkAdd(PersonQuery personQuery) {
        if (StringUtils.isBlank(personQuery.getPersonName())) {
            throw new LyException(ExceptionEnum.PERSON_NAME_NOT_NULL);
        }
        return true;
    }

    /**
     * 更新
     * @param personQuery
     */
    public int updateBean(PersonQuery personQuery) {
        if (StringUtils.isBlank(personQuery.getPersonId())) {
            throw new LyException(ExceptionEnum.PERSON_ID_NOT_NULL);
        }
        return personMapper.updateBean(personQuery);
    }

    /**
     * 删除
     */
    public int deleteBean(String id) {
        if (StringUtils.isBlank(id)) {
            throw new LyException(ExceptionEnum.PERSON_ID_NOT_NULL);
        }
        return personMapper.deleteBean(id);
    }
}
