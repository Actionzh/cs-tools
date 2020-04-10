package com.cs.cstools.controller;

import com.cs.cstools.entity.Template;
import com.cs.cstools.service.TemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/Template")
public class WorkTemplateController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WorkTemplateController.class);

    @Autowired
    private TemplateService templateService;

    @GetMapping(value = "/list")
    @ResponseBody
    public List<Template> list(@RequestParam(value = "id", defaultValue = "1") Long id) {
        LOGGER.info("access ToolsController list");
        return templateService.list(id);
    }


}
