package com.ymmihw.hibernate;

import com.ymmihw.hibernate.model.School;
import com.ymmihw.hibernate.model.Student;

public class TestObjectHelper {

  public static School createSchool(int nameIdentifier) {
    School school = new School();
    school.setName("School" + (nameIdentifier + 1));
    return school;
  }

  public static Student createStudent(School school) {
    Student student = new Student();
    student.setName("Student-" + school.getName());
    student.setSchool(school);
    return student;
  }
}
