package model;
//empid, fname, lname, email, phone
//hdate, jobid, sal, comm, mgrid, deptid

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name= "employees")
@Data
public class Employees {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_ID")
    private Long empid;
    @Column(name = "FIRST_NAME")
    private String fname;
    @Column(name = "LAST_NAME")
    private String lname;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "PHONE_NUMBER")
    private String phone;
    @Column(name = "HIRE_DATE")
    private Date hdate;
    @Column(name = "JOB_ID")
    private String jobid;
    @Column(name = "SALARY")
    private Integer sal;
    @Column(name = "COMMISSION_PCT", precision = 5,scale = 1)
    private BigDecimal comm;
    @Column(name = "MANAGER_ID")
    private Integer mgrid;
    @Column(name = "DEPARTMENT_ID")
    @JoinColumn(name="departments_id")
    private long detpid;



}