package com.ymmihw.hibernate.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.ymmihw.hibernate.Application;
import com.ymmihw.hibernate.entity.SubSelectChild;
import com.ymmihw.hibernate.entity.SubSelectParent;
import com.ymmihw.hibernate.repos.SubSelectChildRepo;

@SpringBootTest(classes = Application.class)
public class SubSelectParentServiceTest {
  @Autowired
  private SubSelectChildRepo childRepo;
  @Autowired
  private SubSelectParentService parentService;


  @Test
  public void testFindById() {
    for (int j = 0; j < 2; j++) {
      SubSelectParent parent = SubSelectParent.builder().build();
      parentService.save(parent);
      for (int i = 0; i < 11; i++) {
        childRepo.save(SubSelectChild.builder().parent(parent).build());
      }
    }

    parentService.findById(1L);
  }

  @Test
  public void testFindAll() {
    for (int j = 0; j < 2; j++) {
      SubSelectParent parent = SubSelectParent.builder().build();
      parentService.save(parent);
      for (int i = 0; i < 11; i++) {
        childRepo.save(SubSelectChild.builder().parent(parent).build());
      }
    }

    parentService.findAll();
  }
}
