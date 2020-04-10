package com.cs.cstools.service;

import com.cs.cstools.dto.ToolsDTO;
import com.cs.cstools.entity.Template;
import com.cs.cstools.entity.Tools;
import com.cs.cstools.mapper.TemplateMapper;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TemplateService {

    @Autowired
    private TemplateMapper templateMapper;

    public List<Template> list(Long id) {
        List<Template> list = templateMapper.list(id);
        return list;
    }


    private Tools convert(ToolsDTO toolsDTO) {
        return Tools.builder().id(toolsDTO.getId())
                .name(toolsDTO.getName())
                .isSend(toolsDTO.getIsSend())
                .path(toolsDTO.getPath())
                .description(toolsDTO.getDescription())
                .dateCreated(toolsDTO.getDateCreated())
                .lastUpdated(DateTime.now()).build();
    }

    private ToolsDTO convertToDto(Tools tools) {
        return ToolsDTO.builder().id(tools.getId())
                .name(tools.getName())
                .isSend(tools.getIsSend())
                .path(tools.getPath())
                .description(tools.getDescription())
                .dateCreated(tools.getDateCreated())
                .lastUpdated(tools.getLastUpdated()).build();
    }
}
