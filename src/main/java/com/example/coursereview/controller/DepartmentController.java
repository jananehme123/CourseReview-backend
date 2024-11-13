package com.example.coursereview.controller;

import com.example.coursereview.model.Course;
import com.example.coursereview.model.Department;
import com.example.coursereview.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
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
        return departmentService.getAllDepartments();
    }

    // GET /departments/{id}: Get a department by ID
    @GetMapping("/{id}")
    public Optional<Department> getDepartmentById(@PathVariable int id) {  // Changed Long to int
        return departmentService.getDepartmentById(id);
    }

    // POST /departments: Create a new department
    @PostMapping
    public Department addDepartment(@RequestBody Department department) {
        return departmentService.saveDepartment(department);
    }

    // PUT /departments/{id}: Update an existing department
    @PutMapping("/{id}")
    public Department updateDepartment(@PathVariable int id, @RequestBody Department department) {  // Changed Long to int
        department.setName(department.getName());
        department.setFaculty(department.getFaculty());
        return departmentService.saveDepartment(department);
    }

    // DELETE /departments/{id}: Delete a department by ID
    @DeleteMapping("/{id}")
    public void deleteDepartment(@PathVariable int id) {  // Changed Long to int
        departmentService.deleteDepartment(id);
    }

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/search")
    public List<Department> searchDepartments(@RequestParam String keyword) {
        return departmentService.searchDepartments(keyword);
    }

    @GetMapping("/{id}/courses")
    public List<Course> getCoursesByDepartmentId(@PathVariable int id) {
        Optional<Department> department = departmentService.getDepartmentById(id);
        return department.map(Department::getCourses).orElse(Collections.emptyList());
    }
}

