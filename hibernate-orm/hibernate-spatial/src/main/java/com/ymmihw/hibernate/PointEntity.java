package com.ymmihw.hibernate;

import com.vividsolutions.jts.geom.Point;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class PointEntity {

  @Id
  @GeneratedValue
  private Long id;

  @Column(columnDefinition = "BLOB")
  private Point point;

  @Override
  public String toString() {
    return "PointEntity{" + "id=" + id + ", point=" + point + '}';
  }
}
