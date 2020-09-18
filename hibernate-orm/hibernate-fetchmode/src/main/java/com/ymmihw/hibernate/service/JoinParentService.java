package com.ymmihw.hibernate.service;

import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.ymmihw.hibernate.entity.JoinParent;
import com.ymmihw.hibernate.repos.JoinParentRepo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class JoinParentService {

  private final JoinParentRepo joinParentRepo;

  public void save(JoinParent entity) {
    joinParentRepo.save(entity);
  }

  public void findById(Long id) {
    joinParentRepo.findById(id);
  }

  public void findAll() {
    joinParentRepo.findAll();
  }

}
