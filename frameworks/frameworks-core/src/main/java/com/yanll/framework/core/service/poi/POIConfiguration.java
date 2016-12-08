package com.yanll.framework.core.service.poi;

import com.yanll.framework.core.service.poi.excel.ExcelImportPreHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class POIConfiguration {
    @Bean
    public ExcelImportPreHandler excelImportPreHandler() {
        return new ExcelImportPreHandler();
    }
}
