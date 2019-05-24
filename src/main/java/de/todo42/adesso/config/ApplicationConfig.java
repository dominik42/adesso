package de.todo42.adesso.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@ConfigurationProperties(prefix = "adesso")
@Component
@Data
public class ApplicationConfig {
    
    private String singleProp;
    
    private Integer port;
    
    private Workshop workshop;
    
    @Data
    static class Workshop {
        private String label;
        private String prop2;
    }

}
