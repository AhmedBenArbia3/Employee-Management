package gl2.example.personnel.service.impl;

import gl2.example.personnel.exception.EmployeeNotFoundException;
import gl2.example.personnel.model.Employee;
import gl2.example.personnel.repository.EmployeeRepository;
import gl2.example.personnel.response.ResponseHandler;
import gl2.example.personnel.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public List<Employee> getEmployeeByName(String name) {
        List<Employee> employees = employeeRepository.findByName(name);
        if (employees.isEmpty()) {
            throw new EmployeeNotFoundException("L'employé n'existe pas.");
        }
        return employees;
    }

    public List<Employee> getEmployeeByPosition(String position){
        List<Employee> employees = employeeRepository.findByPosition(position);
        if (employees.isEmpty()) {
            throw new EmployeeNotFoundException("L'employé n'existe pas.");
        }
        return employees;
    }

    public Employee getEmployeeById(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isEmpty()) {
            throw new EmployeeNotFoundException("L'employé n'existe pas.");
        }
        return employee.get();
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Long id){
        Optional<Employee> employee = employeeRepository.findById(id);
        if(employee.isEmpty()){
            throw new EmployeeNotFoundException("L'employé n'existe pas déja.");
        }
        else{
            return employeeRepository.save(employee.get());
        }

    }

    public void deleteEmployee(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if(employee.isEmpty()){
            throw new EmployeeNotFoundException("L'employé n'existe pas déja.");
        }
        else{
            employeeRepository.deleteById(id);
        }
    }
    public List <Employee> findBySalaryGreaterThan(Double minSalary){
        return employeeRepository.findBySalaryGreaterThan(minSalary);
    }

    public List<Employee> findBySalaryLessThanAndGreaterThan(Double minSalary, Double maxSalary) {
        return employeeRepository.findBySalaryLessThanAndGreaterThan(minSalary, maxSalary);
    }

    public List<Employee> findBySalaryLessThan(Double maxSalary) {
        return employeeRepository.findBySalaryLessThan(maxSalary);
    }
}
