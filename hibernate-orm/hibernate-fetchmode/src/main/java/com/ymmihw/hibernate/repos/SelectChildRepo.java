package com.ymmihw.hibernate.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.ymmihw.hibernate.entity.SelectChild;

public interface SelectChildRepo
    extends JpaRepository<SelectChild, Long>, JpaSpecificationExecutor<SelectChild> {
}
