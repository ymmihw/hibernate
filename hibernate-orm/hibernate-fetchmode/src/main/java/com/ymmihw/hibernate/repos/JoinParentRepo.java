package com.ymmihw.hibernate.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.ymmihw.hibernate.entity.JoinParent;

public interface JoinParentRepo
    extends JpaRepository<JoinParent, Long>, JpaSpecificationExecutor<JoinParent> {
}
