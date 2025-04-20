package gl2.example.personnel.service;


import gl2.example.personnel.model.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAllEmployees();

    Employee getEmployeeById(Long id);

    Employee addEmployee(Employee employee);

    void deleteEmployee(Long id);

    List<Employee> getEmployeeByName(String name);

    List<Employee> getEmployeeByPosition(String position);

    List <Employee> findBySalaryGreaterThan(Double minSalary);

    List<Employee> findBySalaryLessThanAndGreaterThan(Double minSalary, Double maxSalary);

    List<Employee> findBySalaryLessThan(Double maxSalary);
}
