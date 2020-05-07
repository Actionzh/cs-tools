package com.cs.cstools.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

@RestController
@RequestMapping("workwechat")
public class TestWorkWechat {


    @RequestMapping("get")
    public void create(@RequestParam(value = "code") String code,
                       @RequestParam(value = "state") String state) {
        LOGGER.info("access workwechat param===========");
        LOGGER.info("access workwechat params code:" + code);
        LOGGER.info("access workwechat params state:" + state);

    }

}
