package com.cs.cstools.controller;

import com.cs.cstools.service.IssueService;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("issue")
public class IssueController {
    private static final Logger LOGGER = LoggerFactory.getLogger(IssueController.class);

    @Autowired
    private IssueService issueService;

    @PostMapping("create")
    public void create(@RequestParam Map<String, Object> params) throws UnirestException {
        LOGGER.info("access create issue with params:" + params.toString());
        issueService.handleWebhook(params);
    }

    @PostMapping("createcs")
    public void createcs(@RequestParam Map<String, Object> params) throws UnirestException {
        LOGGER.info("access create issue with params:" + params.toString());
        issueService.handlecsWebhook(params);
    }


    @PostMapping(value = "/test")
    @ResponseBody
    public void test(@RequestParam Map<String, Object> params) {
        LOGGER.info("access my controller with ticketDTO:" + params.toString());
//        webhookService.handleWebhook(params);
    }

}
