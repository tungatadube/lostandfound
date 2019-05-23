package com.demo.lostandfound.service.config;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ApiWebConfiguration {


    @Bean
    public DozerBeanMapper mapper() {
        List<String> mappingFiles = new ArrayList<>();
        mappingFiles.add("type-mapping/global-config.xml");
        return new DozerBeanMapper(mappingFiles);
    }
}
