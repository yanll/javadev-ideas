package com.yanll.framework.core.service.poi;

import com.yanll.framework.core.service.poi.excel.ExcelImportHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class POIConfiguration {
    @Bean
    public ExcelImportHandler excelImportHandler() {
        return new ExcelImportHandler();
    }
}
