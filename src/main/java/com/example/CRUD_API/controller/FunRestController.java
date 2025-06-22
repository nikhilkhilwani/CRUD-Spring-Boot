package com.example.CRUD_API.controller;

import com.example.CRUD_API.entity.Employee;
import com.example.CRUD_API.exception.CustomException;
import com.example.CRUD_API.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class FunRestController {

    private EmployeeService employeeService;
    private ObjectMapper objectMapper;

    @Autowired
    public FunRestController(EmployeeService employeeService, ObjectMapper objectMapper)
    {
        this.employeeService = employeeService;
        this.objectMapper = objectMapper;
    }


    @GetMapping("/employees/")
    public List<Employee> getEmployees()
    {
       return employeeService.findAll();
    }

    @GetMapping("/employees/{empId}")
    public Employee getEmployeeBuId(@PathVariable int empId)
    {
        Employee employee = employeeService.findById(empId);
        if(employee == null)
        {
            throw new CustomException("Employee ID not Found: " + empId);
        }
        else
        {
            return employee;
        }
    }

    @PostMapping("/employees/")
    public Employee addEmployee(@RequestBody Employee theEmployee)
    {
        theEmployee.setId(0);
        Employee newEmployee = employeeService.save(theEmployee);
        return newEmployee;
    }

    @PutMapping("/employees/")
    public Employee updateEmployee(@RequestBody Employee theEmployee)
    {
        Employee employee = employeeService.save(theEmployee);
        return employee;
    }

    @PatchMapping("/employees/{empId}")
    public Employee patchEmployee(@PathVariable int empId, @RequestBody Map<String, Object> patchPayLoad)
    {
        Employee tempEmployee = employeeService.findById(empId);

        if(tempEmployee == null)
        {
            throw new CustomException("Employee ID not Found: " + empId);
        }

        if(patchPayLoad.containsKey("id"))
        {
            throw new RuntimeException("Employee ID not allowed to Modify");
        }

        Employee patchEmployee = apply(patchPayLoad, tempEmployee);
        Employee employee = employeeService.save(patchEmployee);
        return employee;
    }

    private Employee apply(Map<String, Object> patchPayLoad, Employee tempEmployee) {

        ObjectNode employeeNode = objectMapper.convertValue(tempEmployee, ObjectNode.class);

        ObjectNode patchNode = objectMapper.convertValue(patchPayLoad, ObjectNode.class);

        employeeNode.setAll(patchNode);

        return objectMapper.convertValue(employeeNode, Employee.class);
    }

    @DeleteMapping("/employees/{empId}")
    public String deleteById(@PathVariable int empId)
    {
        employeeService.deleteById(empId);
        return "Employee Deleted. Employee ID: " + empId;
    }

}
