package com.example.coursereview.controller;

import com.example.coursereview.model.Department;
import com.example.coursereview.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/departments")  // Base URL for all department-related API calls
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    // GET /departments: Get all departments
    @GetMapping
    public List<Department> getAllDepartments() {
        return DepartmentService.getAllDepartments();
    }

    // GET /departments/{id}: Get a department by ID
    @GetMapping("/{id}")
    public Optional<Department> getDepartmentById(@PathVariable Long id) {
        return DepartmentService.getDepartmentById(id);
    }

    // POST /departments: Create a new department
    @PostMapping
    public Department addDepartment(@RequestBody Department department) {
        return DepartmentService.saveDepartment(department);
    }

    // PUT /departments/{id}: Update an existing department
    @PutMapping("/{id}")
    public Department updateDepartment(@PathVariable Long id, @RequestBody Department department) {
        department.setId(id);  // Make sure we update the correct department
        return DepartmentService.saveDepartment(department);
    }

    // DELETE /departments/{id}: Delete a department by ID
    @DeleteMapping("/{id}")
    public void deleteDepartment(@PathVariable Long id) {
        DepartmentService.deleteDepartment(id);
    }
}
