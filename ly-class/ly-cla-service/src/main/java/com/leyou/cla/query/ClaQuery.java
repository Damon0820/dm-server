package com.leyou.cla.query;

import com.leyou.cla.pojo.Cla;
import lombok.Data;

import java.util.List;

@Data
public class ClaQuery extends Cla {
    private List<String> personIdList;
}
