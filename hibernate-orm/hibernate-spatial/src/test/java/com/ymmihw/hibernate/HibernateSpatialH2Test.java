package com.ymmihw.hibernate;

import java.io.IOException;
import org.junit.After;
import org.junit.Before;
import geodb.GeoDB;

public class HibernateSpatialH2Test extends AbstractHibernateSpatialTest {

  @Before
  public void setUp() throws IOException {
    session =
        SessionFactoryCreator.getSessionFactory("hibernate-spatial-h2.properties").openSession();
    transaction = session.beginTransaction();
    session.doWork(conn -> {
      GeoDB.InitGeoDB(conn);
    });
  }

  @After
  public void tearDown() {
    transaction.rollback();
    session.close();
  }

}
