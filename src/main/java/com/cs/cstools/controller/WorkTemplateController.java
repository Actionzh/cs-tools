package com.cs.cstools.controller;

import com.cs.cstools.entity.Template;
import com.cs.cstools.entity.WorkFields;
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

    @GetMapping(value = "/listAllTemp")
    @ResponseBody
    public List<Template> listAllTemp(@RequestParam(value = "size", defaultValue = "10") int size) {
        LOGGER.info("access ToolsController list");
        return templateService.listAllTemp(size);
    }

    @PostMapping(value = "/update")
    @ResponseBody
    public long update(@RequestBody Template template) {
        LOGGER.info("access ToolsController update");
        return templateService.update(template);
    }

    @PostMapping(value = "/updatefield")
    @ResponseBody
    public String updatefield(@RequestBody WorkFields workFields) {
        LOGGER.info("access ToolsController update");
        templateService.updatefield(workFields);
        return "success";
    }

    @PostMapping(value = "/updatetemp")
    @ResponseBody
    public long updatetemp(@RequestBody Template template) {
        LOGGER.info("access ToolsController update");
        return templateService.updatetemp(template);
    }

    @PostMapping(value = "/inserttemp")
    @ResponseBody
    public long inserttemp(@RequestBody Template template) {
        LOGGER.info("access ToolsController update");
        return templateService.inserttemp(template);
    }

    @PostMapping(value = "/delete")
    @ResponseBody
    public String delete(@RequestParam String templateId, @RequestBody List<WorkFields> workFields) {
        LOGGER.info("access ToolsController update");
        templateService.deleteField(templateId, workFields);
        return "success";
    }

}
