package com.ymmihw.hibernate.model;

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
public class Student {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private long id;

  private String name;

  @ManyToOne
  private School school;

}
