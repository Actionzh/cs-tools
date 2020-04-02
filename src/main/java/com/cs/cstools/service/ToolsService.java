package com.cs.cstools.service;

import com.cs.cstools.dto.ToolsDTO;
import com.cs.cstools.entity.Tools;
import com.cs.cstools.mapper.ToolsMapper;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ToolsService {

    @Autowired
    private ToolsMapper toolsMapper;

    public List<ToolsDTO> list(Integer pageSize, Integer size) {
        List<Tools> list = toolsMapper.list(pageSize, size);
        return list.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public void add(ToolsDTO toolsDTO) {
        toolsDTO.setDateCreated(DateTime.now());
        Tools convert = convert(toolsDTO);
        toolsMapper.add(convert);
    }

    public void update(ToolsDTO toolsDTO) {
        toolsMapper.update(convert(toolsDTO));
    }

    public void delete(List<Long> ids) {
        toolsMapper.delete(ids);
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
