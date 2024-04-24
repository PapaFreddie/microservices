package com.fredpapa.employeeservice.controller;

import com.fredpapa.employeeservice.model.Employee;
import com.fredpapa.employeeservice.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping("/employees")

    public Employee addEmployee(@RequestBody Employee employee){
        LOGGER.info("Employee add: {}", employee);
        return employeeRepository.addEmployee(employee);

    }

    @GetMapping("/employees/{Id}")

    public Employee findById(@PathVariable("Id") Long Id){
        LOGGER.info("Employee find: id = {}", Id);
        return employeeRepository.findById(Id);
    }

    @GetMapping("/employees")
    public List<Employee> findAll(){
        LOGGER.info("Employee find");
        return employeeRepository.findAll();
    }

    @GetMapping("/employees/departments/{departmentId}")

    public List<Employee> findByDepartment(@PathVariable("departmentId") Long departmentId){
        LOGGER.info("Employee find: departmentId = {}", departmentId);
        return employeeRepository.findByDepartment(departmentId);
    }
}
