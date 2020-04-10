package com.cs.cstools.entity;

import lombok.*;
import org.joda.time.DateTime;

import java.util.List;

@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
@ToString(callSuper = true)
public class Template {
    private Long id;
    private String templateId;
    private String templateName;
    private String templateDescription;
    private DateTime dateCreated;
    private DateTime lastUpdated;
    private List<WorkFields> workFields;
}
