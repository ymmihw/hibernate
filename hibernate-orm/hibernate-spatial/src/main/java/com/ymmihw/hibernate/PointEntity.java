package com.ymmihw.hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import com.vividsolutions.jts.geom.Point;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PointEntity {

  @Id
  @GeneratedValue
  private Long id;

  private Point point;

  @Override
  public String toString() {
    return "PointEntity{" + "id=" + id + ", point=" + point + '}';
  }
}
