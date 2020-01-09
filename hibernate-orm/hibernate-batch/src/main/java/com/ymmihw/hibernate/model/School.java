package com.ymmihw.hibernate.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class School {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private long id;

  private String name;

  @OneToMany(mappedBy = "school")
  private List<Student> students;

}
