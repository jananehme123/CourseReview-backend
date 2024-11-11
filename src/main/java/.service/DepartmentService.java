package com.example.coursereview.service;

import com.example.coursereview.model.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {
    List<Department> getAllDepartments();
    Optional<Department> getDepartmentById(int id);  // Changed Long to int
    Department saveDepartment(Department department);
    void deleteDepartment(int id);  // Changed Long to int
    List<Department> searchDepartments(String keyword);
}
