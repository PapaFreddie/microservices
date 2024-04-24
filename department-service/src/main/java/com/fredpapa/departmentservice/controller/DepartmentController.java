package com.fredpapa.departmentservice.controller;

import com.fredpapa.departmentservice.client.EmployeeClient;
import com.fredpapa.departmentservice.model.Department;
import com.fredpapa.departmentservice.repository.DepartmentRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
//@RequestMapping("/departments")
public class DepartmentController {

    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(DepartmentController.class);
    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeClient employeeClient;



    @PostMapping("/departments")
    public Department addDepartment(@RequestBody Department department){
        LOGGER.info("Department add: {}", department);
        return departmentRepository.addDepartment(department);
    }

    @GetMapping("/departments")
    public List<Department> findAll(){
        LOGGER.info("Department find");
        return departmentRepository.findAll();
    }

    @GetMapping("/departments/{id}")
    public Department findById(@PathVariable("id") Long id){
        LOGGER.info("Department find: id={}", id);
        return departmentRepository.findById(id);

    }
    @GetMapping("/departments/with-employees")
    public List<Department> findAllWithEmployees(){
        LOGGER.info("Department find");
        List<Department> departments = departmentRepository.findAll();

        departments.forEach(department ->
                department
                        .setEmployees(employeeClient
                                .findByDepartment(department
                                        .getId())));

        return departments;
    }






}
