package com.example.coursereview.repository;

import com.example.coursereview.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {  // Changed Long to Integer
    @Query("SELECT d FROM Department d WHERE d.name LIKE %:keyword%")
    List<Department> searchDepartments(@Param("keyword") String keyword);
}
