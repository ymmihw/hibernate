package com.ymmihw.hibernate.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.ymmihw.hibernate.entity.SelectParent;

public interface SelectParentRepo
    extends JpaRepository<SelectParent, Long>, JpaSpecificationExecutor<SelectParent> {
}
