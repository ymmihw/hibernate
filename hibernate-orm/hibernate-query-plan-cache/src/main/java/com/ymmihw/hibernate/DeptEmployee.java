package com.ymmihw.hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class DeptEmployee {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private long id;

  private String employeeNumber;

  private String title;

  private String name;

  @ManyToOne
  private Department department;

  public DeptEmployee(String name, String employeeNumber, Department department) {
    this.name = name;
    this.employeeNumber = employeeNumber;
    this.department = department;
  }

  public DeptEmployee(String name, String employeeNumber, String title, Department department) {
    super();
    this.name = name;
    this.employeeNumber = employeeNumber;
    this.title = title;
    this.department = department;
  }

}
