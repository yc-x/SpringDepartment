package com.example.learn.service;

import com.example.learn.entity.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {

    public Department saveDepartment(Department department);

    public List<Department> fetchDepartments();

    public Optional<Department> fetchDepartmentsById(Long departmentId);

    public void deleteDepartmentById(Long departmentId);

    public Department updateDepartment(Long departmentId, Department department);

    public Department fetchDepartmentByName(String departmentName);
}
