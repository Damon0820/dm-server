package com.leyou.cla.mapper;

import com.leyou.cla.pojo.Cla;
import com.leyou.cla.query.ClaQuery;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ClaMapper extends Mapper<Cla> {
    /**
     * 查询班级列表
     * @param userId
     * @return
     */
    public List<Cla> queryClaList(
            @Param("userId") String userId,
            @Param("page") int page,
            @Param("pageSize") int pageSize
    );

    /**
     * 新增班级
     * @param claQuery
     * @return
     */
    public void addCla(ClaQuery claQuery);

    /**
     * 更新班级
     */
    public int updateCla(ClaQuery claQuery);

    /**
     * 删除班级
     */
    public int deleteCla(
            @Param("id") String id
    );

}
