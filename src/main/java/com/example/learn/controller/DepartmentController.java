package com.example.learn.controller;

import com.example.learn.entity.Department;
import com.example.learn.service.DepartmentService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class DepartmentController {

    private final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    private DepartmentService service;

    @PostMapping("/departments")
    public Department saveDept(@Valid @RequestBody Department department){
        LOGGER.info("saveDept() of Department Controller Triggered!");
        return service.saveDepartment(department);
    }

    @GetMapping("/departments")
    public List<Department> fetchDepartments(){
        LOGGER.info("fetchDepartments() of Department Controller Triggered!");
        return service.fetchDepartments();
    }

    @GetMapping("/departments/{id}")
    public Optional<Department> fetchDepartmentById(@PathVariable("id") Long departmentId){
        return service.fetchDepartmentsById(departmentId);
    }

    @DeleteMapping("/departments/{id}")
    public String deleteDepartmentById(@PathVariable("id") Long departmentId){
        service.deleteDepartmentById(departmentId);
        return "Department deleted successfully!";
    }

    @PutMapping("/departments/{id}")
    public Department updateDepartment(@PathVariable("id") Long departmentId, @RequestBody Department department){
        return service.updateDepartment(departmentId, department);
    }

    @GetMapping("/departments/name/{name}")
    public Department fetchDepartmentByName(@PathVariable("name") String departmentName){
        return service.fetchDepartmentByName(departmentName);
    }
}
