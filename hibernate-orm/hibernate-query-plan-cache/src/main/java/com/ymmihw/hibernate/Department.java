package com.ymmihw.hibernate;

import java.util.List;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Department {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private long id;

  private String name;

  @OneToMany(mappedBy = "department")
  private List<DeptEmployee> employees;

}
