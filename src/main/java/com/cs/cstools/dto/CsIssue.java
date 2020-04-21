package com.cs.cstools.dto;

import lombok.Data;

@Data
public class CsIssue {
    /**
     * 标题
     */
    String title;

    /**
     * 描述
     */
    String description;

    /**
     * 受理客服邮箱
     */
    String assignee;

    /**
     * jira_项目
     */
    String project;

    /**
     * jira_类型
     */
    String issueType;

    /**
     * 优先级
     */
    String priority;

    /**
     * taskType
     */
    String taskType;
}
