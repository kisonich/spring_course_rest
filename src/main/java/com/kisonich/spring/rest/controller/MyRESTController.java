package com.kisonich.spring.rest.controller;

import com.kisonich.spring.rest.entity.Employee;
import com.kisonich.spring.rest.exception_handlig.NoSuchEmployeeException;
import com.kisonich.spring.rest.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MyRESTController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees") // Используем метод GET при обращении клиента к серверу
    public List<Employee> showAllEmployees() { // Получение всех Employees
        List<Employee> allEmployees = employeeService.getAllEmployees();
        return allEmployees;
    }

    /// =/===/=/=/=/==/=/=/ Ищем работника по id. Обрабатываем exception если нет работника с таким id
    @GetMapping("/employees/{id}")
    public Employee getEmployee(@PathVariable int id) { // Получение значения переменной из адреса запрома. в адресе пищем сыллку и вставляем цыфру id
        Employee employee = employeeService.getEmployee(id);
//
        if (employee == null) {
            throw new NoSuchEmployeeException("There is  no employee with ID = " + id + " in database");
        }
//
        return employee;
    }
//  Добавление работника через Postman там прописываем JSON формат поля
    @PostMapping("/employees")
    public Employee addNewEmployee (@RequestBody Employee employee){
       employeeService.saveEmployee(employee);
       return employee;
    }

// обновление через postman . не прописываем id
    @PutMapping("/employees")
    public Employee updateEmployee (@RequestBody Employee employee){
        employeeService.saveEmployee(employee);
        return employee;
    }
    @DeleteMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable int id){
    Employee employee = employeeService.getEmployee(id);
    if (employee==null){
        throw new NoSuchEmployeeException("There is no  employee with ID " + id + " in database");
    }

        employeeService.deleteEmployee(id);
        return "Employee with ID = " + id + "was deleted";
    }
}
// С сервисом работает контроллер