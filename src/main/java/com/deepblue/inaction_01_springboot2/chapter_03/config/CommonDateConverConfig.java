package com.deepblue.inaction_01_springboot2.chapter_03.config;

import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 */
@Component
public class CommonDateConverConfig implements Converter<String, Date> {

    @SneakyThrows
    @Override
    public Date convert(String source) {
        if(StringUtils.isEmpty(source)) {
            return new Date();
        }

        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(source);
    }
}
