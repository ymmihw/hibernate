package com.ymmihw.hibernate.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.ymmihw.hibernate.entity.SelectBatchParent;

public interface SelectBatchParentRepo
    extends JpaRepository<SelectBatchParent, Long>, JpaSpecificationExecutor<SelectBatchParent> {
}
