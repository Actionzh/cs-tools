package com.cs.cstools.service;

import com.cs.cstools.entity.Template;
import com.cs.cstools.entity.WorkFields;
import com.cs.cstools.mapper.TemplateMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TemplateService {

    @Autowired
    private TemplateMapper templateMapper;

    public List<Template> list(Long id) {
        List<Template> list = templateMapper.list(id);
        mapTemplateList(list);
        return list;
    }

    public List<Template> listAllTemp(int size) {
        List<Template> list = templateMapper.listAllTemp(size);
        mapTemplateList(list);
        return list;
    }

    private void mapTemplateList(List<Template> list) {
        list.stream().map(t -> {
            List<WorkFields> workFields = t.getWorkFields();
            workFields.removeIf(w -> w.getId() == null);
            return t;
        }).collect(Collectors.toList());
    }


    public long update(Template template) {
        templateMapper.updateTemplate(template);
        List<WorkFields> workFields = template.getWorkFields();
        List<Long> ids = workFields.stream().map(WorkFields::getId).collect(Collectors.toList());
        if (!ids.isEmpty()) {
            templateMapper.deleteField(template.getTemplateId(), ids);
        }
        for (WorkFields w : workFields) {
            templateMapper.updateField(w);
        }
        return template.getId();
    }

    public void updatefield(WorkFields w) {
        templateMapper.updateField(w);
    }

    public long updatetemp(Template template) {
        templateMapper.updateTemplate(template);
        return template.getId();
    }

    public long inserttemp(Template template) {
        templateMapper.insertTemplate(template);
        return template.getId();
    }

    public void deleteField(String templateId, List<WorkFields> workFields) {
        List<Long> ids = workFields.stream().map(WorkFields::getId).collect(Collectors.toList());
        templateMapper.deleteField(templateId, ids);
        for (WorkFields w : workFields) {
            templateMapper.updateField(w);
        }
    }

}
