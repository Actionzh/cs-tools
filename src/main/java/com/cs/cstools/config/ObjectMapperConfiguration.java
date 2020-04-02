package com.cs.cstools.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import javax.annotation.PostConstruct;

@Configuration
public class ObjectMapperConfiguration {

    @Autowired
    private ObjectFactory<HttpMessageConverters> messageConverters;

    @PostConstruct
    public void registerJacksonModule() {
        //auto format DateTime to yyyy-MM-dd HH:mm:ss
        //auto parse yyyy-MM-dd HH:mm:ss to DateTime
        for (HttpMessageConverter<?> messageConverter : messageConverters.getObject().getConverters()) {
            if (messageConverter instanceof MappingJackson2HttpMessageConverter) {
                MappingJackson2HttpMessageConverter converter = (MappingJackson2HttpMessageConverter) messageConverter;
                ObjectMapper objectMapper = converter.getObjectMapper();
                objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
                objectMapper.registerModule(new DateTimeJacksonModule());
            }
        }
    }

}
