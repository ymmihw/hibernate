package com.ymmihw.hibernate;

import java.io.IOException;
import org.junit.After;
import org.junit.Before;

public class HibernateSpatialMySQLTest extends AbstractHibernateSpatialTest {

  @Before
  public void setUp() throws IOException {
    session =
        SessionFactoryCreator.getSessionFactory("hibernate-spatial-mysql.properties").openSession();
    transaction = session.beginTransaction();
  }

  @After
  public void tearDown() {
    transaction.rollback();
    session.close();
  }
}
