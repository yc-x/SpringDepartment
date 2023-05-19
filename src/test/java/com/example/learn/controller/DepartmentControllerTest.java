package com.example.learn.controller;

import com.example.learn.entity.Department;
import com.example.learn.error.DepartmentNotFoundException;
import com.example.learn.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DepartmentService departmentService;
    @MockBean
    private Department department;

    @BeforeEach
    void setUp() {
        department = Department.builder()
                .departmentName("Test")
                .departmentCode("TE-00")
                .departmentAddr("Home")
                .departmentId(1L)
                .build();
    }

    @Test
    void saveDept() throws Exception {
        Department inputDept = Department.builder()
                .departmentName("Test")
                .departmentCode("TE-00")
                .departmentAddr("Home")
                .build();
        Mockito.when(departmentService.saveDepartment(inputDept))
                .thenReturn(department);    // mock the sample department.
        mockMvc.perform(post("/departments")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("    {\n" +
                            "        \"departmentName\": \"Test Update\",\n" +
                            "        \"departmentAddr\": \"Home\",\n" +
                            "        \"departmentCode\": \"TE-00\"\n" +
                            "    }")
                )
                .andExpect(status().isOk());
    }

    @Test
    void fetchDepartmentById() throws Exception {
        Mockito.when(departmentService.fetchDepartmentsById(1L))
                .thenReturn(department);
        mockMvc.perform(MockMvcRequestBuilders.get("/departments/1")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.departmentName")
                        .value(department.getDepartmentName())
                ).andExpect(jsonPath("$.departmentAddr")
                        .value(department.getDepartmentAddr())
                ).andExpect(jsonPath("$.departmentCode")
                .value(department.getDepartmentCode())
                );
    }
}