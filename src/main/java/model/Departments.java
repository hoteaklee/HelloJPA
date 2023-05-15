package model;

import lombok.Data;

import javax.persistence.*;

// deptid, dname, mgrid, locid
@Entity
@Table(name="departments")
@Data
public class Departments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="DEPARTMENT_ID" )
    private  long deptid;
    @Column(name="DEPARTMENT_NAME")
    private String dname;
    @Column(name="MANAGER_ID")
    private Integer mgrid;
    @Column(name="LOCATION_ID")
    private Integer locid;
}
