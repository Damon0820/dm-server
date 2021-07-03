package com.leyou.cla.mapper;

import com.leyou.cla.pojo.Person;
import com.leyou.cla.query.PersonQuery;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface PersonMapper extends Mapper<Person> {
    /**
     * 查询班级列表
     * @param classId
     * @return
     */
    public List<Person> queryList(
            @Param("classId") String classId,
            @Param("page") int page,
            @Param("pageSize") int pageSize
    );

    /**
     * 新增班级
     * @param personQuery
     * @return
     */
    public void addBean(PersonQuery personQuery);

    /**
     * 更新班级
     */
    public int updateBean(PersonQuery personQuery);

    /**
     * 删除班级
     */
    public int deleteBean(
            @Param("id") String id
    );

}
