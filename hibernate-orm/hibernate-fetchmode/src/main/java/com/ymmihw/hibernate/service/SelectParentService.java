package com.ymmihw.hibernate.service;

import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.ymmihw.hibernate.entity.SelectParent;
import com.ymmihw.hibernate.repos.SelectParentRepo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class SelectParentService {

  private final SelectParentRepo selectParentRepo;

  public void save(SelectParent entity) {
    selectParentRepo.save(entity);
  }

  public void findById(Long id) {
    selectParentRepo.findById(id);
  }

  public void findAll() {
    selectParentRepo.findAll();
  }

}
