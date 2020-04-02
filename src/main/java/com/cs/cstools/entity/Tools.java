package com.cs.cstools.entity;

import lombok.*;
import org.joda.time.DateTime;

@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
@ToString(callSuper = true)
public class Tools {
    private Long id;
    private DateTime dateCreated;
    private DateTime lastUpdated;
    private String name;
    private Boolean isSend;
    private String path;
    private String description;
}
