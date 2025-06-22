package com.example.CRUD_API.service;

import com.example.CRUD_API.dao.EmployeeRepository;
import com.example.CRUD_API.entity.Employee;
import com.example.CRUD_API.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository)
    {
        this.employeeRepository = employeeRepository;
    }


    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(int empId) {

        Optional<Employee> result = employeeRepository.findById(empId);
        Employee employee = null;
        if(result.isPresent())
            employee = result.get();
        else
            throw new CustomException("Employee ID not Found: " + empId);
        return employee;
    }

    @Override
    public Employee save(Employee employee)
    {
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteById(int empId)
    {
        Optional<Employee> employee = employeeRepository.findById(empId);
        if (employee.isEmpty()) {
            throw new CustomException("Employee ID not Found: " + empId);
        }
        else
        {
            employeeRepository.deleteById(empId);
        }
    }
}
