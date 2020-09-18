package com.ymmihw.hibernate.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.ymmihw.hibernate.entity.JoinChild;

public interface JoinChildRepo
    extends JpaRepository<JoinChild, Long>, JpaSpecificationExecutor<JoinChild> {
}
