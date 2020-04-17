package com.cs.cstools.entity;

import lombok.*;
import org.joda.time.DateTime;

@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
@ToString(callSuper = true)
public class WorkFields {
    private Long id;
    private String titleName;
    private String fieldName;
    private String fieldId;
    private String fieldType;
    private Boolean fieldRequired;
    private Long templateId;
    private DateTime dateCreated;
    private DateTime lastUpdated;
}
