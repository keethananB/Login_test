package com.example.RegistationLogin.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeRequest {
  private int id;
  private String name;
  private String email;
  private String password;
  private Long mobile;
  private String address;



}
