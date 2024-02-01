package com.example.apiclientsdk;


import com.example.apiclientsdk.client.ApiClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//标记一个类为配置类，通过该类可以定义和组装Spring Bean
@Configuration
// @ConfigurationProperties("api.client") 是 Spring Boot 中的一个注解，
// 用于将外部配置（比如 application.yml 或 application.yml 文件中的属性）
// 绑定到一个 Java 对象上。该注解可以简化配置属性的管理，并提供类型安全的方式来访问这些属性
@ConfigurationProperties("api.client")
@Data
// @ComponentScan是Spring框架中的一个注解，用于批量注册bean。
// 它会让Spring去扫描某些包及其子包中的所有类，然后将满足一定条件的类作为bean注册到Spring容器中。
@ComponentScan
public class ApiClientConfig {
    private String accessKey;
    private String secretKey;

    @Bean
    public ApiClient apiClient(){
        return new ApiClient(accessKey,secretKey);
    }
}
