package com.rest.springboot.controller;

import com.rest.springboot.exception.ResourceNotFOundException;
import com.rest.springboot.model.Employee;
import com.rest.springboot.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {
	@Autowired
    private EmployeeRepository employeeRepository;
    //get employees data
    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){

        return employeeRepository.findAll();
    }

    @PostMapping("/employees")
    // crete employee data
    public Employee creteEmployee(@RequestBody Employee employee){

        return employeeRepository.save(employee);
    }

    @GetMapping("/employees/{id}")
    //get employee by id
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id){
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFOundException("Employee not exists with id:"+ id));
        return ResponseEntity.ok(employee);
    }

    @PutMapping("/employees/{id}")
    // update employee
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails){

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFOundException("Employee not exists with id:"+ id));
        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());
        employee.setEmailId(employeeDetails.getEmailId());

        Employee updatedEmployee = employeeRepository.save(employee);

        return ResponseEntity.ok(updatedEmployee);

    }

    //delete employee

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFOundException("Employee not exists with id:"+ id));

        employeeRepository.delete(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
