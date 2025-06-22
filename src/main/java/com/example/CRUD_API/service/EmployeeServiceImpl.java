package com.example.CRUD_API.service;

import com.example.CRUD_API.dao.EmployeeDAO;
import com.example.CRUD_API.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeDAO employeeDAO;

    @Autowired
    public EmployeeServiceImpl(EmployeeDAO employeeDAO)
    {
        this.employeeDAO = employeeDAO;
    }


    @Override
    public List<Employee> findAll() {
        return employeeDAO.findAll();
    }

    @Override
    public Employee findById(int empId) {
        return employeeDAO.findById(empId);
    }

    @Override
    @Transactional
    public Employee save(Employee employee)
    {
        return employeeDAO.save(employee);
    }

    @Override
    @Transactional
    public Employee deleteById(int empId)
    {
        return employeeDAO.deleteById(empId);
    }
}
