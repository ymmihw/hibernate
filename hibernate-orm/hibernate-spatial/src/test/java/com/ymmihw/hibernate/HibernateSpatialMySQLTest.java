package com.ymmihw.hibernate;

import java.io.IOException;
import java.util.Properties;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.testcontainers.containers.MySQLContainer;

public class HibernateSpatialMySQLTest extends AbstractHibernateSpatialTest {
  public static MySQLContainer<?> container =
      new MySQLContainer<>("mysql:5.7.28").withPassword("123456");

  @Before
  public void setUp() throws IOException {
    container.start();
    Properties properties =
        SessionFactoryCreator.getProperties("hibernate-spatial-mysql.properties");
    properties.setProperty("hibernate.connection.url",
        "jdbc:mysql://" + container.getContainerIpAddress() + ":" + container.getFirstMappedPort()
            + "/test?createDatabaseIfNotExist=true");
    SessionFactory sessionFactory = SessionFactoryCreator.getSessionFactoryByProperties(properties);
    session = sessionFactory.openSession();
    transaction = session.beginTransaction();
  }

  @After
  public void tearDown() {
    transaction.rollback();
    session.close();
  }
}
