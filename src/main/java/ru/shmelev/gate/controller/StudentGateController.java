package ru.shmelev.gate.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import ru.shmelev.gate.api.StudentGateApi;
import ru.shmelev.gate.model.StudentGateCreateRequest;
import ru.shmelev.gate.model.StudentGateResponse;

import ru.shmelev.gate.client.api.StudentDataApi;
import ru.shmelev.gate.client.model.StudentDataCreateRequest;
import ru.shmelev.gate.client.model.StudentDataResponse;

@RestController
@RequiredArgsConstructor
public class StudentGateController implements StudentGateApi {

    private final StudentDataApi studentsFeignClient;

    /**
     * Проксирует создание студента во внутренний DATA-SERVICE.
     */
    @Override
    public ResponseEntity<StudentGateResponse> createStudent(StudentGateCreateRequest request) {
        // запрос во внутренний сервис
        StudentDataCreateRequest dataRequest = new StudentDataCreateRequest();
        dataRequest.setFullName(request.getFullName());
        dataRequest.setPassport(request.getPassport());

        StudentDataResponse dataResponse = studentsFeignClient.createStudentDataInData(dataRequest);

        StudentGateResponse gateResponse = new StudentGateResponse();
        gateResponse.setId(dataResponse.getId());
        gateResponse.setFullName(dataResponse.getFullName());
        gateResponse.setPassport(dataResponse.getPassport());

        return ResponseEntity.status(201).body(gateResponse);
    }
}