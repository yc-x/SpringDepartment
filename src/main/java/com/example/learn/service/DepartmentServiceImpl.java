package com.example.learn.service;

import com.example.learn.entity.Department;
import com.example.learn.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService{
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> fetchDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Optional<Department> fetchDepartmentsById(Long departmentId) {
        return departmentRepository.findById(departmentId);
    }

    @Override
    public void deleteDepartmentById(Long departmentId) {
        departmentRepository.deleteById(departmentId);
    }

    @Override
    public Department updateDepartment(Long departmentId, Department department) {
        Department fetchedDept = departmentRepository.findById(departmentId).get();

        if(Objects.nonNull(department.getDepartmentName())
                && !department.getDepartmentName().isEmpty()
        ){
            fetchedDept.setDepartmentName(department.getDepartmentName());
        }
        if(Objects.nonNull(department.getDepartmentCode())
                && !department.getDepartmentCode().isEmpty()
        ){
            fetchedDept.setDepartmentCode(department.getDepartmentCode());
        }
        if(Objects.nonNull(department.getDepartmentAddr())
                && !department.getDepartmentAddr().isEmpty()
        ){
            fetchedDept.setDepartmentAddr(department.getDepartmentAddr());
        }
        return departmentRepository.save(fetchedDept);
    }

    @Override
    public Department fetchDepartmentByName(String departmentName) {
        //return departmentRepository.findByDepartmentName(departmentName);
        return departmentRepository.findByDepartmentNameIgnoreCase(departmentName);
    }


}
