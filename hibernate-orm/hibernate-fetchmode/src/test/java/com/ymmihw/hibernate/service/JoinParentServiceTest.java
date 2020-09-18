package com.ymmihw.hibernate.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.ymmihw.hibernate.Application;
import com.ymmihw.hibernate.entity.JoinChild;
import com.ymmihw.hibernate.entity.JoinParent;
import com.ymmihw.hibernate.repos.JoinChildRepo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class JoinParentServiceTest {
  @Autowired
  private JoinChildRepo childRepo;
  @Autowired
  private JoinParentService parentService;


  @Test
  public void testFindById() {
    for (int j = 0; j < 2; j++) {
      JoinParent parent = JoinParent.builder().build();
      parentService.save(parent);
      for (int i = 0; i < 11; i++) {
        childRepo.save(JoinChild.builder().parent(parent).build());
      }
    }

    parentService.findById(1L);
  }

  @Test
  public void testFindAll() {
    for (int j = 0; j < 2; j++) {
      JoinParent parent = JoinParent.builder().build();
      parentService.save(parent);
      for (int i = 0; i < 11; i++) {
        childRepo.save(JoinChild.builder().parent(parent).build());
      }
    }

    parentService.findAll();
  }
}
