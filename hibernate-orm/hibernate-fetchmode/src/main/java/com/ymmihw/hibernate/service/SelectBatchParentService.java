package com.ymmihw.hibernate.service;

import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.ymmihw.hibernate.entity.SelectBatchParent;
import com.ymmihw.hibernate.repos.SelectBatchParentRepo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class SelectBatchParentService {

  private final SelectBatchParentRepo selectBatchParentRepo;

  public void save(SelectBatchParent entity) {
    selectBatchParentRepo.save(entity);
  }

  public void findById(Long id) {
    selectBatchParentRepo.findById(id);
  }

  public void findAll() {
    selectBatchParentRepo.findAll();
  }

}
