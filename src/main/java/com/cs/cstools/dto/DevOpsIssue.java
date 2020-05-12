package com.cs.cstools.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
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

    /**
     * 受理客服邮箱
     */
    String assignee;

}
