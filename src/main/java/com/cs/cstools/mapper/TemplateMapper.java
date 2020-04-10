package com.cs.cstools.mapper;


import com.cs.cstools.entity.Template;
import com.cs.cstools.entity.Tools;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TemplateMapper {
    List<Template> list(@Param("id") Long id);

    void add(Tools tools);

    void update(Tools toolsDTO);

    void delete(@Param("ids") List<Long> ids);
}
