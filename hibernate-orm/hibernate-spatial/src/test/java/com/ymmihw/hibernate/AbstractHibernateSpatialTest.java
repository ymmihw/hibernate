package com.ymmihw.hibernate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;
import org.locationtech.jts.util.GeometricShapeFactory;

public abstract class AbstractHibernateSpatialTest {

  protected Session session;
  protected Transaction transaction;

  @Test
  public void shouldConvertWktToGeometry() throws ParseException {
    Geometry geometry = wktToGeometry("POINT (2 5)");
    assertEquals("Point", geometry.getGeometryType());
    assertTrue(geometry instanceof Point);
  }

  @Test
  public void shouldInsertAndSelectPoints() throws ParseException {
    PointEntity entity = new PointEntity();
    entity.setPoint((Point) wktToGeometry("POINT (1 1)"));

    session.persist(entity);
    PointEntity fromDb = session.find(PointEntity.class, entity.getId());
    assertEquals("POINT (1 1)", fromDb.getPoint().toString());
  }

  @Test
  public void shouldSelectDisjointPoints() throws ParseException {
    insertPoint("POINT (1 2)");
    insertPoint("POINT (3 4)");
    insertPoint("POINT (5 6)");

    Point point = (Point) wktToGeometry("POINT (3 4)");
    Query query = session.createQuery(
        "select p from PointEntity p " + "where disjoint(p.point, :point) = true",
        PointEntity.class);
    query.setParameter("point", point);
    assertEquals("POINT (1 2)", ((PointEntity) query.getResultList().get(0)).getPoint().toString());
    assertEquals("POINT (5 6)", ((PointEntity) query.getResultList().get(1)).getPoint().toString());
  }

  @Test
  public void shouldSelectAllPointsWithinPolygon() throws ParseException {
    insertPoint("POINT (1 1)");
    insertPoint("POINT (1 2)");
    insertPoint("POINT (3 4)");
    insertPoint("POINT (5 6)");

    Query query = session.createQuery(
        "select p from PointEntity p where within(p.point, :area) = true", PointEntity.class);
    query.setParameter("area", wktToGeometry("POLYGON((0 0, 0 5, 5 5, 5 0, 0 0))"));
    List<?> resultList = query.getResultList();
    assertThat(resultList.stream().map(p -> ((PointEntity) p).getPoint().toString()))
        .containsOnly("POINT (1 1)", "POINT (1 2)", "POINT (3 4)");
  }

  @Test
  public void shouldSelectAllPointsWithinRadius() throws ParseException {
    insertPoint("POINT (1 1)");
    insertPoint("POINT (1 2)");
    insertPoint("POINT (3 4)");
    insertPoint("POINT (5 6)");

    Query query = session.createQuery(
        "select p from PointEntity p where within(p.point, :circle) = true", PointEntity.class);
    query.setParameter("circle", createCircle(0.0, 0.0, 5));

    List<?> resultList = query.getResultList();
    assertThat(resultList.stream().map(p -> ((PointEntity) p).getPoint().toString()))
        .containsOnly("POINT (1 1)", "POINT (1 2)");
  }

  @Test
  public void shouldSelectAdjacentPolygons() throws ParseException {
    insertPolygon("POLYGON ((0 0, 0 5, 5 5, 5 0, 0 0))");
    insertPolygon("POLYGON ((3 0, 3 5, 8 5, 8 0, 3 0))");
    insertPolygon("POLYGON ((2 2, 3 1, 2 5, 4 3, 3 3, 2 2))");

    Query query = session.createQuery(
        "select p from PolygonEntity p where touches(p.polygon, :polygon) = true",
        PolygonEntity.class);
    query.setParameter("polygon", wktToGeometry("POLYGON ((5 5, 5 10, 10 10, 10 5, 5 5))"));
    List<?> resultList = query.getResultList();
    assertThat(resultList.stream().map(p -> ((PolygonEntity) p).getPolygon().toString()))
        .containsOnly("POLYGON ((0 0, 0 5, 5 5, 5 0, 0 0))", "POLYGON ((3 0, 3 5, 8 5, 8 0, 3 0))");
  }

  private void insertPoint(String point) throws ParseException {
    PointEntity entity = new PointEntity();
    entity.setPoint((Point) wktToGeometry(point));
    session.persist(entity);
  }

  private void insertPolygon(String polygon) throws ParseException {
    PolygonEntity entity = new PolygonEntity();
    entity.setPolygon((Polygon) wktToGeometry(polygon));
    session.persist(entity);
  }

  private Geometry wktToGeometry(String wellKnownText) throws ParseException {
    WKTReader fromText = new WKTReader();
    Geometry geom = null;
    geom = fromText.read(wellKnownText);
    return geom;
  }

  private static Geometry createCircle(double x, double y, double radius) {
    GeometricShapeFactory shapeFactory = new GeometricShapeFactory();
    shapeFactory.setNumPoints(32);
    shapeFactory.setCentre(new Coordinate(x, y));
    shapeFactory.setSize(radius * 2);
    return shapeFactory.createCircle();
  }

}
