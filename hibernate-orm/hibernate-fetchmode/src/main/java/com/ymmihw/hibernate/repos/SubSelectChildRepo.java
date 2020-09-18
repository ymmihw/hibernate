package com.ymmihw.hibernate.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.ymmihw.hibernate.entity.SubSelectChild;

public interface SubSelectChildRepo
    extends JpaRepository<SubSelectChild, Long>, JpaSpecificationExecutor<SubSelectChild> {
}
