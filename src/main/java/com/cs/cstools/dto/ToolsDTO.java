package com.cs.cstools.dto;

import lombok.*;
import org.joda.time.DateTime;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
@ToString(callSuper = true)
public class ToolsDTO implements Serializable {
    private static final long serialVersionUID = 4711538607866890978L;
    private Long id;
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private DateTime dateCreated;
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private DateTime lastUpdated;
    private String name;
    private Boolean isSend;
    private String path;
    private String description;
}
