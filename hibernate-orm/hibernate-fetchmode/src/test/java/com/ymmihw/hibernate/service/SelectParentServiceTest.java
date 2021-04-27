package com.ymmihw.hibernate.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.ymmihw.hibernate.Application;
import com.ymmihw.hibernate.entity.SelectChild;
import com.ymmihw.hibernate.entity.SelectParent;
import com.ymmihw.hibernate.repos.SelectChildRepo;

@SpringBootTest(classes = Application.class)
public class SelectParentServiceTest {
  @Autowired
  private SelectChildRepo selectChildRepo;
  @Autowired
  private SelectParentService selectParentService;


  @Test
  public void testFindById() {
    for (int j = 0; j < 2; j++) {
      SelectParent parent = SelectParent.builder().build();
      selectParentService.save(parent);
      for (int i = 0; i < 11; i++) {
        selectChildRepo.save(SelectChild.builder().parent(parent).build());
      }
    }

    selectParentService.findById(1L);
  }
  
  @Test
  public void testFindAll() {
    for (int j = 0; j < 2; j++) {
      SelectParent parent = SelectParent.builder().build();
      selectParentService.save(parent);
      for (int i = 0; i < 11; i++) {
        selectChildRepo.save(SelectChild.builder().parent(parent).build());
      }
    }

    selectParentService.findAll();
  }
}
