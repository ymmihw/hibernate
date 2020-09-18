package com.ymmihw.hibernate.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SelectBatchParent {

  @Id
  @GeneratedValue
  private Long id;

  @OneToMany(mappedBy = "parent", fetch = FetchType.EAGER)
  @Fetch(value = FetchMode.SELECT)
  @BatchSize(size = 10)
  @Builder.Default
  private Set<SelectBatchChild> children = new HashSet<>();
}
