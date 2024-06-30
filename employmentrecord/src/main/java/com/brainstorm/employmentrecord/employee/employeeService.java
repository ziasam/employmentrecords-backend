package com.brainstorm.employmentrecord.employee;

import com.brainstorm.employmentrecord.employee.exception.UserNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class employeeService {

    private final employeeRepo employeeRepo;

    @Autowired
    public employeeService(employeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public List<Employee> getAllEmployee(){
        return employeeRepo.findAll();
    }

    public Employee updateEmployeeById(long id, String email, String companyName, String previousCompany, String phone){
        Employee emp = employeeRepo.findEmployeeById(id).orElseThrow(() -> new UserNotFound("No employee by this ID"));

        if(email == null) throw  new IllegalStateException("No email selected");
        if(companyName == null) throw  new IllegalStateException("No companyName selected");
        emp.setEmail(email);
        emp.setCompanyName(companyName);
        emp.setPreviousCompany(previousCompany);
        emp.setPhone(phone);

        return employeeRepo.save(emp);
    }

    public Employee addEmployee(Employee employee)
    {
        Optional<Employee> Emp = employeeRepo.findEmployeeById(employee.getId());
        if(Emp.isEmpty())
        {
            return employeeRepo.save(employee);
        }
        else
        {
            throw new IllegalStateException("Wrong ID");
        }
    }

    public void deleteEmployee(long id)
    {
        boolean exist = employeeRepo.existsById(id);

        if(!exist)
        {
            throw new IllegalStateException(" No employee by this id Exist ");
        }
        else
        {
            employeeRepo.deleteById(id);
        }
    }

    public Employee findEmployee(long id)
    {
        Employee emp = employeeRepo.findEmployeeById(id).orElseThrow(() -> new UserNotFound("No user by this id"));
        return emp;
    }
}
