package com.ymmihw.hibernate.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.ymmihw.hibernate.entity.SubSelectParent;

public interface SubSelectParentRepo
    extends JpaRepository<SubSelectParent, Long>, JpaSpecificationExecutor<SubSelectParent> {
}
