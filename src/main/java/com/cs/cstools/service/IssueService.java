package com.cs.cstools.service;

import java.util.Map;

public interface IssueService {

    void handleWebhook(Map<String, Object> params);

    void handlecsWebhook(Map<String, Object> params);

    void createBreakdown(Map<String, Object> params);

    void createNeed(Map<String, Object> params);

    void createDevOps(Map<String, Object> params);

}
