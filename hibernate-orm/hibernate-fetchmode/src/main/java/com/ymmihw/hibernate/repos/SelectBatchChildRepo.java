package com.ymmihw.hibernate.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.ymmihw.hibernate.entity.SelectBatchChild;

public interface SelectBatchChildRepo
    extends JpaRepository<SelectBatchChild, Long>, JpaSpecificationExecutor<SelectBatchChild> {
}
