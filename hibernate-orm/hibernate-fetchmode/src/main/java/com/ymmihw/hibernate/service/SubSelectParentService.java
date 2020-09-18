package com.ymmihw.hibernate.service;

import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.ymmihw.hibernate.entity.SubSelectParent;
import com.ymmihw.hibernate.repos.SubSelectParentRepo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class SubSelectParentService {

  private final SubSelectParentRepo subSelectParentRepo;

  public void save(SubSelectParent entity) {
    subSelectParentRepo.save(entity);
  }

  public void findById(Long id) {
    subSelectParentRepo.findById(id);
  }

  public void findAll() {
    subSelectParentRepo.findAll();
  }

}
