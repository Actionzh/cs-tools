package com.cs.cstools.mapper;


import com.cs.cstools.entity.Tools;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ToolsMapper {
    List<Tools> list(@Param("pageSize") Integer pageSize, @Param("size") Integer size);

    void add(Tools tools);

    void update(Tools toolsDTO);

    void delete(@Param("ids") List<Long> ids);
}
