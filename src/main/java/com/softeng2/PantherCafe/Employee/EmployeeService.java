package com.softeng2.PantherCafe.Employee;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private final EmployeeRepository employeeRepository;



    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    public List<Employee> getEmployees(){
        return employeeRepository.findAll();

    }

    public void addNewEmployee(Employee employee) {
           Optional<Employee> employeeByEmail =
                   employeeRepository.findEmployeeByEmail(employee.getEmpEmail());

           if (employeeByEmail.isPresent()) {
               throw new RuntimeException("Employee already exists");
           }
        employeeRepository.save(employee);
    }

    public void deleteEmployee(Long empID) {
        boolean exists = employeeRepository.existsById(empID);
        if (!exists){
            throw new RuntimeException("Employee with id " + empID + " does not exist");
        }else{
            employeeRepository.deleteById(empID);
        }
    }
    @Transactional
    public void updateEmployee(long empId, String fname, String lname, String email) {
        Employee emp = employeeRepository.findById(empId).
                orElseThrow(()-> new IllegalStateException( "Employee with id " + empId + " does not exist"));

        if (fname != null && !fname.isEmpty() && !Objects.equals(emp.getEmpFName(), fname)) {
            emp.setEmpFName(fname);
        }
        if (lname != null && !lname.isEmpty() && !Objects.equals(emp.getEmpLName(), lname)) {
            emp.setEmpFName(lname);
        }
        if (email != null && !email.isEmpty() && !Objects.equals(emp.getEmpEmail(), email)) {
            Optional<Employee> employeeOptional = employeeRepository.findEmployeeByEmail(email);
            if (employeeOptional.isPresent()) {
                throw new RuntimeException("Employee with email " + email + " already exists");
            }
            emp.setEmpEmail(email);
        }
    }
}
