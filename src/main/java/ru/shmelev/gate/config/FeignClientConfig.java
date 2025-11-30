package ru.shmelev.gate.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.shmelev.gate.client.ApiClient;
import ru.shmelev.gate.client.api.StudentDataApi;

@Configuration
public class FeignClientConfig {

    @Value("${data.service.url}")
    private String dataServiceUrl;

    @Bean
    public StudentDataApi studentDataApi() {
        ApiClient apiClient = new ApiClient();
        apiClient.setBasePath(dataServiceUrl);
        return apiClient.buildClient(StudentDataApi.class);
    }
}