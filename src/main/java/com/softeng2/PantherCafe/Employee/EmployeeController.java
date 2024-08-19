package com.softeng2.PantherCafe.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService  employeeService) {
        this.employeeService = employeeService;
    }
    @GetMapping
    public List<Employee> getEmployees() {
        return employeeService.getEmployees();
    }

    @PostMapping()
    public void addEmployee(@RequestBody Employee employee) {
        employeeService.addNewEmployee(employee);
    }
    @DeleteMapping(path = "{empID}")
    public void deleteEmployee(@PathVariable("empID") Long id) {
        employeeService.deleteEmployee(id);
    }

    @PutMapping(path = "{empId}")
    public void updateEmployee(@PathVariable ("empId") long empId,
                               @RequestParam (required = false) String fname,
                               @RequestParam (required = false) String lname,
                               @RequestParam (required = false) String email) {
        employeeService.updateEmployee(empId, fname, lname, email);


    }

}
