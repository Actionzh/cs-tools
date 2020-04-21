package com.cs.cstools.controller;

import com.cs.cstools.dto.InternalIssue;

public class Split {
    private final static com.demo.kfjira.utils.JsonMapper jsonMapper = com.demo.kfjira.utils.JsonMapper.INSTANCE;

    public static void main(String[] args) {
        String[] split = "\"[\"LFCS\",\"任务\"]\"".split("\",\"");
        String substring = split[0].substring(split[0].lastIndexOf("\"") + 1);
        System.out.println(substring);
        String substring1 = split[1].substring(0, split[1].indexOf("\""));
        System.out.println(substring);

        InternalIssue internalIssue = jsonMapper.fromJson("{\"title\":\"jiratest\",\"description\":\"<p>jira测试描述</p>\",\"assignee\":\"vicki.zhang@linkflowtech.com\",\"project\":\"[\"NAZAIO\",\"任务\"]\",\"env\":\" - \",\"branch\":\" - \",\"priority\":\"Highest\",\"operator\":\"李逍遥（Kyle Lee）kyle.li@linkflowtech.com\"}", InternalIssue.class);

        System.out.println(internalIssue.toString());
       /* String[] s = "李逍遥（Kyle Lee）kyle.li@linkflowtech.com".split(" ");
        int i = "李逍遥（Kyle Lee）kyle.li@linkflowtech.com".lastIndexOf("）");
        String text = "李逍遥（Kyle Lee）kyle.li@linkflowtech.com".substring("李逍遥（Kyle Lee）kyle.li@linkflowtech.com".lastIndexOf("）") + 1);

        System.out.println(text);*/
    }
}
