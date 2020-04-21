package com.cs.cstools.service.impl;


import com.cs.cstools.dto.CsIssue;
import com.cs.cstools.dto.InternalIssue;
import com.cs.cstools.dto.ProjectAndType;
import com.cs.cstools.service.IssueService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author zh
 */
@Service
@Slf4j
public class IssueServiceImpl implements IssueService {

    private final static com.demo.kfjira.utils.JsonMapper JSON_MAPPER = com.demo.kfjira.utils.JsonMapper.INSTANCE;

    @Override
    public void handleWebhook(Map<String, Object> params) {
        String message = params.get("message").toString();
        String[] split = message.split("--");

        InternalIssue internalIssue = JSON_MAPPER.fromJson(split[0], InternalIssue.class);
        try {
            String[] convert = convertProjectAndType(split[1]);
            if (convert != null) {
                internalIssue.setProject(convert[0]);
                internalIssue.setIssueType(convert[1]);
            }
            this.createIssue(internalIssue);
        } catch (UnirestException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void handlecsWebhook(Map<String, Object> params) {
        String message = params.get("message").toString();
        String[] split = message.split("--");

        CsIssue csIssue = JSON_MAPPER.fromJson(split[0], CsIssue.class);
        try {
            String[] split1 = split[1].split("=");
            if (split1[1] != null && !split1[1].isEmpty()) {
                String[] fin = split1[1].split("\",\"");
                String pro = fin[0].substring(fin[0].lastIndexOf("\"") + 1);
                String type = fin[1].substring(0, fin[1].indexOf("\""));
                csIssue.setProject(pro);
                csIssue.setIssueType(type);
            }
            this.createCsIssue(csIssue);
        } catch (UnirestException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    private String[] convertProjectAndType(String pt) {
        String[] split1 = pt.split("=");
        if (split1[1] != null && !split1[1].isEmpty()) {
            String[] fin = split1[1].split("\",\"");
            String pro = fin[0].substring(fin[0].lastIndexOf("\"") + 1);
            String type = fin[1].substring(0, fin[1].indexOf("\""));
            return new String[]{pro, type};
        }
        return null;
    }

    private void createCsIssue(CsIssue issue) throws UnirestException {
        ObjectNode payload = createCsIssueBody(issue);
        postJira(payload);
    }


    private void createIssue(InternalIssue issue) throws UnirestException {
        ObjectNode payload = createIssueBody(issue);
        postJira(payload);
    }

    private void postJira(ObjectNode payload) throws UnirestException {
        // Connect Jackson ObjectMapper to Unirest
        Unirest.setObjectMapper(new ObjectMapper() {
            private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper
                    = new com.fasterxml.jackson.databind.ObjectMapper();

            @Override
            public <T> T readValue(String value, Class<T> valueType) {
                try {
                    return jacksonObjectMapper.readValue(value, valueType);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public String writeValue(Object value) {
                try {
                    return jacksonObjectMapper.writeValueAsString(value);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        HttpResponse<JsonNode> response = Unirest.post("https://jira.leadswarp.com/rest/api/2/issue")
                //直接用户名密码
                .basicAuth("vicki.zhang", "Zhang123")
                //header带Authorization方式，两种方式2选1
                //.header("Authorization", "Basic aHVhbWluZy5mYW5nOlF3ZXJhc2RmMTAyMQ==")
                //.header("Authorization", "Basic c3VjY2VzczpJbml0aWFsMA==")
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .body(payload)
                .asJson();

        System.out.println(response.getBody());
        ObjectNode payload2 = createIssueBody2();
        JsonNode body = response.getBody();
        String key = body.getObject().getString("key");
        HttpResponse<JsonNode> response2 = Unirest.post("https://jira.leadswarp.com/rest/api/2/issue/" + key + "/comment")
                .basicAuth("vicki.zhang", "Zhang123")
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .body(payload2)
                .asJson();
        System.out.println(response2.getBody());
    }

    private ObjectNode createCsIssueBody(CsIssue issue) {
        // The payload definition using the Jackson library
        String summary = issue.getTitle();
        String description = issue.getDescription();
        JsonNodeFactory jnf = JsonNodeFactory.instance;
        ObjectNode payload = jnf.objectNode();
        {
            ObjectNode fields = payload.putObject("fields");
            {
                //标题
                fields.put("summary", summary);
                log.info("csissue summary:" + summary);

                fields.put("description", description);
                log.info("csissue description:" + description);
                ObjectNode issuetype = fields.putObject("issuetype");
                {
                    log.info("csissue issuetype:" + issuetype);
                    issuetype.put("id", "10602");
                }

                ObjectNode project = fields.putObject("project");
                {
                    project.put("key", issue.getProject());
                }


                //优先级
                ObjectNode priority = fields.putObject("priority");
                {
                    priority.put("name", issue.getPriority());
                }

                //TaskType
                if (!" - ".equals(issue.getTaskType())) {

                    ObjectNode customfield_10701 = fields.putObject("customfield_10701");
                    {
                        customfield_10701.put("value", issue.getTaskType());
                    }
                }

                //上报人
                ObjectNode reporter = fields.putObject("reporter");
                {
                    reporter.put("name", issue.getAssignee().split("@")[0]);
                }
            }
        }
        return payload;
    }

    private ObjectNode createIssueBody(InternalIssue issue) {
        // The payload definition using the Jackson library
        String summary = issue.getTitle();
        String description = issue.getDescription();
        JsonNodeFactory jnf = JsonNodeFactory.instance;
        ObjectNode payload = jnf.objectNode();
        {
            ObjectNode fields = payload.putObject("fields");
            {
                //标题
                fields.put("summary", summary);
                //描述
                fields.put("description", description);

                ObjectNode issuetype = fields.putObject("issuetype");
                {
                    issuetype.put("name", issue.getIssueType());
                }

                ObjectNode project = fields.putObject("project");
                {
                    project.put("key", issue.getProject());
                }


                //分支
                if (!issue.getBranch().isEmpty()) {
                    List<String> branchs = issue.getBranch();
                    String branch = "";
                    Iterator<String> iterator = branchs.iterator();
                    while (iterator.hasNext()) {
                        String next = iterator.next();
                        if (!" - ".equals(next)) {
                            branch = next;
                        }
                    }

                    if (!branch.isEmpty()) {
                        ObjectNode customfield_10601 = fields.putObject("customfield_10601");
                        {
                            customfield_10601.put("value", branch);
                        }
                    }
                }

               /* ObjectNode customfield_10601 = fields.putObject("customfield_10601");
                {
                    customfield_10601.put("value", "Master");
                }*/

                //环境
                if (!" - ".equals(issue.getEnv())) {

                    ObjectNode customfield_10202 = fields.putObject("customfield_10202");
                    {
                        customfield_10202.put("value", issue.getEnv());
                    }
                }

                //优先级
                ObjectNode priority = fields.putObject("priority");
                {
                    priority.put("name", issue.getPriority());
                }

                //上报人
                // fields.put("customfield_10701", "10500");
               /* ObjectNode reporter = fields.putObject("reporter");
                {
                    //  reporter.put("id", "5b10a2844c20165700ede21g");
                    //  reporter.put("key", "huaming.fang");
                    reporter.put("name", issue.getAssignee().split("@")[0]);
                }*/

                //处理人
                ObjectNode assignee = fields.putObject("assignee");
                {
                    String substring = issue.getOperator().substring(issue.getOperator().lastIndexOf("）") + 1);
                    assignee.put("name", substring.split("@")[0]);
                }
            }
        }
        return payload;
    }

    private ProjectAndType getProjectAndType(String projectString) throws UnirestException {

        ProjectAndType pat = new ProjectAndType();
        if (projectString == null || projectString.isEmpty()) {
            return pat;
        }
        String[] split = projectString.substring(2, 14).split("\",\"");

        String project = split[0];
        String type = split[1];
        HttpResponse<JsonNode> response = Unirest.get("https://jira.leadswarp.com/rest/api/2/issue/createmeta")
                //直接用户名密码
                //.basicAuth("huaming.fang", "password")
                //header带Authorization方式，两种方式2选1
                //.header("Authorization", "Basic aHVhbWluZy5mYW5nOlF3ZXJhc2RmMTAyMQ==")
                .header("Authorization", "Basic c3VjY2VzczpJbml0aWFsMA==")
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .asJson();
        JsonNode body = response.getBody();
        JSONArray projects = body.getObject().getJSONArray("projects");
        Iterator<Object> iterator = projects.iterator();


        while (iterator.hasNext()) {
            JSONObject next = (JSONObject) iterator.next();
            if (!project.equals(next.getString("name"))) {
                continue;
            }
            pat.setProject(next.getString("id"));

            JSONArray issuetypes = next.getJSONArray("issuetypes");
            Iterator<Object> typesIterator = issuetypes.iterator();
            while (typesIterator.hasNext()) {
                JSONObject issueType = (JSONObject) typesIterator.next();
                if (type.equals(issueType.getString("name"))) {
                    pat.setType(issueType.getString("id"));
                    return pat;
                }
            }
        }
        return pat;
    }

    private ObjectNode createIssueBody2() {
        JsonNodeFactory jnf = JsonNodeFactory.instance;
        ObjectNode payload2 = jnf.objectNode();
        {
            payload2.put("body", "此jira由逸创云生成");
        }
        return payload2;
    }

}
