package com.brainstorm.employmentrecord.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface employeeRepo extends JpaRepository<Employee, Long> {

    Optional<Employee> findEmployeeById(long id);
}
