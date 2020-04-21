package com.cs.cstools.service;

import java.util.Map;

public interface IssueService {

    void handleWebhook(Map<String, Object> params);

    void handlecsWebhook(Map<String, Object> params);
}
