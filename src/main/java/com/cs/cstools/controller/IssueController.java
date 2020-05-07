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

    /**
     * 创建故障
     *
     * @param params
     */
    @PostMapping(value = "/breakdown")
    @ResponseBody
    public void createBreakdown(@RequestParam Map<String, Object> params) {
        LOGGER.info("access createBreakdown with ticketDTO:" + params.toString());

        issueService.createBreakdown(params);
    }

    /**
     * 创建linkflow需求
     *
     * @param params
     */
    @PostMapping(value = "/need")
    @ResponseBody
    public void createNeed(@RequestParam Map<String, Object> params) {
        LOGGER.info("access createNeed with ticketDTO:" + params.toString());
        issueService.createNeed(params);
    }

    /**
     * 创建devops jira
     *
     * @param params
     */
    @PostMapping(value = "/lfdo")
    @ResponseBody
    public void createDevOps(@RequestParam Map<String, Object> params) {
        LOGGER.info("access createDevOps with ticketDTO:" + params.toString());
        issueService.createDevOps(params);
    }
}
