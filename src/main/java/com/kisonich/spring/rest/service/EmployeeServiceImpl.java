package com.kisonich.spring.rest.service;

import com.kisonich.spring.rest.dao.EmployeeDAO;
import com.kisonich.spring.rest.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
    public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
     private EmployeeDAO employeeDAO;

    @Override
    @Transactional// В applicationContext прописали bean ***/// который открывает и закрывает транзакции сам. в классе прописывать не надо теперь
    public List<Employee> getAllEmployees() {
        return employeeDAO.getAllEmployees();
    }

    @Override
    @Transactional
    public void saveEmployee(Employee employee) {
        employeeDAO.saveEmployee(employee);
    }

    @Override
    @Transactional
    public Employee getEmployee(int id) {
        return employeeDAO.getEmployee(id);
    }

    @Override
    @Transactional
    public void deleteEmployee(int id) {
        employeeDAO.deleteEmployee(id);
    }
}
// Бизнес логига
// Controller работаем с бд через Service
// Методы сервиса имплеиентируются EmployeeServiceImpl, методы перенаправляют всю работу на репозиторий EmployeeDAOImpl, а репозиторий делает всю работу с бд

