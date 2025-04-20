package gl2.example.personnel.repository;

import gl2.example.personnel.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByName(String name);
    List<Employee> findByPosition(String position);
    @Query(value = "SELECT * FROM employees WHERE salary >= :minSalary", nativeQuery = true)
    List<Employee> findBySalaryGreaterThan(@Param("minSalary") Double minSalary);

    @Query(value = "SELECT * FROM employees WHERE salary <= :maxSalary", nativeQuery = true)
    List<Employee> findBySalaryLessThan(@Param("maxSalary") Double maxSalary);

    @Query(value = "SELECT * FROM employees WHERE salary >= :minSalary AND salary <= :maxSalary", nativeQuery = true)
    List<Employee> findBySalaryLessThanAndGreaterThan(@Param("minSalary") Double minSalary,@Param("maxSalary") Double maxSalary);

}
