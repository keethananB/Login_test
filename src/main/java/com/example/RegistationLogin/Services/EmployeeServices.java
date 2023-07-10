package com.example.RegistationLogin.Services;

import com.example.RegistationLogin.request.EmployeeRequest;
import com.example.RegistationLogin.request.LoginRequest;
import com.example.RegistationLogin.response.LoginResponse;

public interface EmployeeServices {

  public void addEmployee(EmployeeRequest employeeRequest);

  public LoginResponse loginEmployee(LoginRequest loginRequest);

  public boolean existsByEmailIgnoreCase(String email);


}
