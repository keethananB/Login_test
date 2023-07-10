package com.example.RegistationLogin.ServicesImp;

import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.RegistationLogin.Services.EmployeeServices;
import com.example.RegistationLogin.entity.Employee;
import com.example.RegistationLogin.repo.EmployeeRepository;
import com.example.RegistationLogin.request.EmployeeRequest;
import com.example.RegistationLogin.request.LoginRequest;
import com.example.RegistationLogin.response.LoginResponse;

@Service
public class EmployeeServiceImplement implements EmployeeServices {
  @Autowired
  private EmployeeRepository employeeRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public void addEmployee(EmployeeRequest employeeRequest) {
    Employee employee = new Employee();
    BeanUtils.copyProperties(employeeRequest, employee);
    BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
    String encryPassword = bcrypt.encode(employee.getPassword());
    employee.setPassword(encryPassword);

    // Check if employee already exists



      employeeRepository.save(employee);

  }

  @Override
  public LoginResponse loginEmployee(LoginRequest loginRequest) {

    Employee employee = employeeRepository.findByEmail(loginRequest.getEmail());
    if (employee != null) {
      String passwd = loginRequest.getPassword();
      String encodedPassword = employee.getPassword();
      Boolean isPwdRight = passwordEncoder.matches(passwd, encodedPassword);



      if (isPwdRight) {
        Optional<Employee> employee1 =
            employeeRepository.findOneByEmailAndPassword(loginRequest.getEmail(), encodedPassword);
        System.out.println(employee1);
        if (employee1.isPresent()) {
          return new LoginResponse("Login Success", true);
        } else {
          return new LoginResponse("Login Failed", false);
        }
      }

      else {
        return new LoginResponse("password Not Match", false);
      }
    }

    else {
      return new LoginResponse("Email not exits", false);
    }


  }

  @Override
  public boolean existsByEmailIgnoreCase(String email) {
    return employeeRepository.existsByEmailIgnoreCase(email);
  }
}



