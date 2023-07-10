package com.example.RegistationLogin.controller;

import com.example.RegistationLogin.repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.RegistationLogin.Services.EmployeeServices;
import com.example.RegistationLogin.request.EmployeeRequest;
import com.example.RegistationLogin.request.LoginRequest;
import com.example.RegistationLogin.response.LoginResponse;

@RestController
@CrossOrigin
public class EmployeeController {

  @Autowired
    private EmployeeRepository employeeRepositiory;
  @Autowired
  private EmployeeServices employeeServices;

  @PostMapping(value = "/signIn")
  public String saveEmployee(@RequestBody EmployeeRequest employeeRequest) {
    if(employeeServices.existsByEmailIgnoreCase(employeeRequest.getEmail())){
      return "Email already exists";
    }
    employeeServices.addEmployee(employeeRequest);
    return "successfully add Details";

  }


  @PostMapping(path = "/login")
  public ResponseEntity<?> loginEmployee(@RequestBody LoginRequest loginRequest) {
    LoginResponse loginResponse = employeeServices.loginEmployee(loginRequest);
    return ResponseEntity.ok(loginResponse);
  }

}
