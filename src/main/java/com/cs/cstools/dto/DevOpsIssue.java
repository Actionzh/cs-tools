package com.cs.cstools.dto;

import lombok.Data;

@Data
public class DevOpsIssue {

    /**
     * 工单id
     */
    String ticketId;
    /**
     * 标题
     */
    String title;

    /**
     * 描述
     */
    String description;

    /**
     * 类型
     */
    String type;

    /**
     * 环境
     */
    String customer;

    /**
     * 优先级
     */
    String priority;

}
