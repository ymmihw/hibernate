package com.ymmihw.hibernate;

import java.io.IOException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import geodb.GeoDB;

public class HibernateSpatialH2Test extends AbstractHibernateSpatialTest {

  @BeforeEach
  public void setUp() throws IOException {
    session =
        SessionFactoryCreator.getSessionFactory("hibernate-spatial-h2.properties").openSession();
    transaction = session.beginTransaction();
    session.doWork(conn -> {
      GeoDB.InitGeoDB(conn);
    });
  }

  @AfterEach
  public void tearDown() {
    transaction.rollback();
    session.close();
  }

}
