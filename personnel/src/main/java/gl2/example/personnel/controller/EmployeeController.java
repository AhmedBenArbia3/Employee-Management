package gl2.example.personnel.controller;



import gl2.example.personnel.model.Employee;
import gl2.example.personnel.response.ResponseHandler;
import gl2.example.personnel.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/welcome")
    public ResponseEntity<String> welcomePage(){
        return new ResponseEntity<String>("Welcome to Employee management system.", HttpStatus.OK);
    }

    @GetMapping
    @Operation(description = "Voir les détails de tous les employés.")
    @PreAuthorize("hasRole('USER') || hasRole('ADMIN')")
    public ResponseEntity<Object> getAllEmployees(@RequestParam(value = "minSalary", required = false) Double minSalary, @RequestParam(value = "maxSalary", required = false) Double maxSalary) {
        if(minSalary != null && maxSalary!= null){
            return  ResponseHandler.responseBuilder("Détails des employés", HttpStatus.FOUND, employeeService.findBySalaryLessThanAndGreaterThan(minSalary, maxSalary));
        }
        else if(minSalary != null){
            return  ResponseHandler.responseBuilder("Détails des employés", HttpStatus.FOUND, employeeService.findBySalaryGreaterThan(minSalary));
        }
        else if(maxSalary != null){
            return  ResponseHandler.responseBuilder("Détails des employés", HttpStatus.FOUND, employeeService.findBySalaryLessThan(maxSalary));
        }
        else{
            return  ResponseHandler.responseBuilder("Détails des employés", HttpStatus.FOUND, employeeService.getAllEmployees());
        }

    }

    @GetMapping("/{id}")
    @Operation(summary = "Donner l'id de l'employé.", description = "Voir les détails de l'employé.")
    @PreAuthorize("hasRole('USER') || hasRole('ADMIN')")
    public ResponseEntity<Object> getEmployeeById(@PathVariable Long id) {
        return  ResponseHandler.responseBuilder("Détails de l'employé", HttpStatus.FOUND, employeeService.getEmployeeById(id));
    }

    @PostMapping
    @Operation(summary = "Donner les info de l'employé.", description = "Ajouter l'employé.")
    @PreAuthorize("hasRole('ADMIN')")
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }

    @Operation(summary = "Donner l'id de l'employé.", description = "Supprimer l'employé.")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Donner l'id de l'employé.", description = "Mettre a jour l'employé.")
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable Long id,
            @RequestBody Employee employee)
    {
        employee.setId(id);
        Employee updatedEmployee = employeeService.addEmployee(employee);

        return ResponseEntity.ok(updatedEmployee);
    }
    @GetMapping("/name/{name}")
    @Operation(summary = "Donner le nom de l'employé.", description = "Voir les détails de l'employé.")
    @PreAuthorize("hasRole('USER') || hasRole('ADMIN')")
    public ResponseEntity<Object> getEmployeeById(@PathVariable String name) {
        return  ResponseHandler.responseBuilder("Détails de l'employé", HttpStatus.FOUND, employeeService.getEmployeeByName(name));
    }
    @GetMapping("/position/{position}")
    @Operation(summary = "Donner la position de l'employé.", description = "Voir les détails de l'employé.")
    @PreAuthorize("hasRole('USER') || hasRole('ADMIN')")

    public ResponseEntity<Object> getEmployeeByPosition(@PathVariable String position) {
        return  ResponseHandler.responseBuilder("Détails de l'employé", HttpStatus.FOUND, employeeService.getEmployeeByPosition(position));
    }
}


