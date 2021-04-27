package com.ymmihw.hibernate.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.ymmihw.hibernate.Application;
import com.ymmihw.hibernate.entity.SelectBatchChild;
import com.ymmihw.hibernate.entity.SelectBatchParent;
import com.ymmihw.hibernate.repos.SelectBatchChildRepo;

@SpringBootTest(classes = Application.class)
public class SelectBatchParentServiceTest {
  @Autowired
  private SelectBatchChildRepo childRepo;
  @Autowired
  private SelectBatchParentService parentService;


  @Test
  public void testFindById() {
    for (int j = 0; j < 2; j++) {
      SelectBatchParent parent = SelectBatchParent.builder().build();
      parentService.save(parent);
      for (int i = 0; i < 11; i++) {
        childRepo.save(SelectBatchChild.builder().parent(parent).build());
      }
    }

    parentService.findById(1L);
  }

  @Test
  public void testFindAll() {
    for (int j = 0; j < 2; j++) {
      SelectBatchParent parent = SelectBatchParent.builder().build();
      parentService.save(parent);
      for (int i = 0; i < 11; i++) {
        childRepo.save(SelectBatchChild.builder().parent(parent).build());
      }
    }

    parentService.findAll();
  }
}
