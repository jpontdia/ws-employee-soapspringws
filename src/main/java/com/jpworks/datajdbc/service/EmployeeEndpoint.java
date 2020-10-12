package com.jpworks.datajdbc.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.jpworks.employee.*;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Slf4j
@Endpoint
@RequiredArgsConstructor
public class EmployeeEndpoint{

    private final BackendService backendService;

    //private static final String NAMESPACE_URI = "http://www.jpworks.com/employee";

    @PayloadRoot(namespace = "http://www.jpworks.com/employee", localPart = "EmployeeByNameRequest")
    @ResponsePayload
    public EmployeesResponse getEmployeesByName(@RequestPayload EmployeeByNameRequest parameters) {
        EmployeesResponse employeesResponse = new EmployeesResponse();
        try{
            employeesResponse.getEmployee().addAll(backendService.getEmployeesByName(parameters.getFirstname(), parameters.getLastname()));
        }
        catch (Exception e){
            log.error("Error while setting values for employee object", e);
        }
        return employeesResponse;
    }

    @PayloadRoot(namespace = "http://www.jpworks.com/employee", localPart = "EmployeeByIdRequest")
    @ResponsePayload
    public EmployeeResponse getEmployeeById(@RequestPayload EmployeeByIdRequest parameters) {
        EmployeeResponse employeeResponse = new EmployeeResponse();
        try{
            employeeResponse.setEmployee(backendService.getEmployeeById(parameters.getId()));
        }
        catch (Exception e){
            log.error("Error while setting values for employee object", e);
        }
        return employeeResponse;
    }
}
