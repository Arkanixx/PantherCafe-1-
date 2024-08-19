package com.softeng2.PantherCafe.Employee;

import jakarta.persistence.*;
import java.util.Collections;

@Entity
@Table(
        name = "employees",
        uniqueConstraints = {
                @UniqueConstraint(name = "employee_unique_email", columnNames = "email")
        }
)
public class Employee {
    @Id
    @SequenceGenerator(
        name ="emp_sequence",
        sequenceName = "emp_sequence",
        allocationSize = 1
        )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "emp_sequence"
    )
    @Column(
            name = "employee_id",
            updatable = false

    )
    private Long empID;

    @Column(
            name= "f_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String empFName;


    @Column(
            name= "l_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String empLName;

    @Column(
            name= "email",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String empEmail;

    @Column(
            name= "pswrd",
            columnDefinition = "TEXT",
            nullable = false
    )
    private String passwrd;

    @Enumerated(EnumType.STRING)
    private EmployeeRole empRole;

    public Employee() {
    }


    public Employee(String empFName,
                    String empLName,
                    String empEmail,
                    String passwrd,
                    EmployeeRole employeeRole) {

        this.empFName = empFName;
        this.empLName = empLName;
        this.empEmail = empEmail;
        this.passwrd = passwrd;
        this.empRole = employeeRole;
    }

    public Long getEmpID() {
        return empID;
    }

    public void setEmpID(Long empID) {
        this.empID = empID;
    }

    public String getEmpFName() {
        return empFName;
    }

    public void setEmpFName(String empFName) {
        this.empFName = empFName;
    }

    public String getEmpLName() {
        return empLName;
    }

    public void setEmpLName(String empLName) {
        this.empLName = empLName;
    }

    public String getEmpEmail() {
        return empEmail;
    }

    public void setEmpEmail(String empEmail) {
        this.empEmail = empEmail;
    }


    public void setPasswrd(String passwrd) {
        this.passwrd = passwrd;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empID=" + empID +
                ", empFName='" + empFName + '\'' +
                ", empLName='" + empLName + '\'' +
                ", empEmail='" + empEmail + '\'' +
                ", passwrd='" + passwrd + '\'' +
                ", empRole=" + empRole + '\'' +
                '}';
    }
}
