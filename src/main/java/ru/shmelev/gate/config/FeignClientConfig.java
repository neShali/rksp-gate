package ru.shmelev.gate.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.shmelev.gate.client.ApiClient;
import ru.shmelev.gate.client.api.StudentDataApi;

@Configuration
public class FeignClientConfig {

    @Bean
    public StudentDataApi someStudentApi() {
        ApiClient apiClient = new ApiClient();
        apiClient.setBasePath("http://localhost:8083");
        return apiClient.buildClient(StudentDataApi.class);
    }
}
