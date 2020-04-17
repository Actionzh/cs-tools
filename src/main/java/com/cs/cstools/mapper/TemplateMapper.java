package com.cs.cstools.mapper;


import com.cs.cstools.entity.Template;
import com.cs.cstools.entity.WorkFields;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TemplateMapper {
    List<Template> list(@Param("id") Long id);

    List<Template> listAllTemp(@Param("size") int size);


    int updateTemplate(Template template);

    int insertTemplate(Template template);


    void deleteField(@Param("templateId") String templateId, @Param("ids") List<Long> ids);

    void updateField(WorkFields w);
}
