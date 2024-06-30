package com.brainstorm.employmentrecord.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/employee/friends")
public class employeeController {

    private final employeeService employeeService;

    @Autowired
    public employeeController(employeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<Employee>> getEmployees() {
        List<Employee> list = employeeService.getAllEmployee();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping(path = "/find/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable ("id") long id) {
        Employee emp = employeeService.findEmployee(id);
        return new ResponseEntity<>(emp, HttpStatus.OK);
    }


    @PutMapping(path = "/add")
    public ResponseEntity<Employee> addNewEmployee(@RequestBody Employee employee){
        Employee emp = employeeService.addEmployee(employee);
        return new ResponseEntity<>(emp, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable ("id") long id)
    {
        employeeService.deleteEmployee(id);
       return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(path = {"/update/{id}"})
    public ResponseEntity<Employee> updateEmployee(@PathVariable ("id") long id, @RequestParam(required = false) String email, @RequestParam(required = false) String companyName, @RequestParam(required = false) String previousCompany, @RequestParam(required = false) String phone)
    {
        Employee emp = employeeService.updateEmployeeById(id, email, companyName, previousCompany, phone);
        return new ResponseEntity<>(emp, HttpStatus.OK);
    }
}
