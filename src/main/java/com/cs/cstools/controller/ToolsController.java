package com.cs.cstools.controller;

import com.cs.cstools.dto.ToolsDTO;
import com.cs.cstools.service.ToolsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/tools")
public class ToolsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ToolsController.class);

    @Autowired
    private ToolsService toolsService;

    @GetMapping(value = "/list")
    @ResponseBody
    public List<ToolsDTO> list(@RequestParam(value = "page", defaultValue = "0") Integer page,
                               @RequestParam(value = "size", defaultValue = "20") Integer size) {
        LOGGER.info("access ToolsController list");
        Integer pageSize = page * size;
        return toolsService.list(pageSize, size);
    }

    @PostMapping(value = "/insert")
    @ResponseBody
    public String add(@RequestBody ToolsDTO toolsDTO) {
        LOGGER.info("access ToolsController insert");
        toolsService.add(toolsDTO);
        return "success";
    }

    @PutMapping(value = "/update")
    @ResponseBody
    public String update(@RequestBody ToolsDTO toolsDTO) {
        LOGGER.info("access ToolsController update");
        toolsService.update(toolsDTO);
        return "success";
    }

    @DeleteMapping(value = "/delete")
    @ResponseBody
    public String delete(@RequestBody List<Long> ids) {
        LOGGER.info("access ToolsController delete");
        toolsService.delete(ids);
        return "success";
    }
}
