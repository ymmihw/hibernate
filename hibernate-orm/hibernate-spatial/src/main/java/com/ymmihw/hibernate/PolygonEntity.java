package com.ymmihw.hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.locationtech.jts.geom.Polygon;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PolygonEntity {

  @Id
  @GeneratedValue
  private Long id;

  private Polygon polygon;

  @Override
  public String toString() {
    return "PolygonEntity{" + "id=" + id + ", polygon=" + polygon + '}';
  }
}
