package com.cs.cstools.dto;

import lombok.Data;

import java.util.List;

@Data
public class InternalIssue {
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
     * jira_环境
     */
    String env;

    /**
     * jira_分支
     */
    List<String> branch;
    /**
     * 标题
     */
    String priority;

    /**
     * jira_经办人
     */
    String operator;


}
