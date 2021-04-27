package com.ymmihw.hibernate;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.hibernate.HibernateException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import ch.vorburger.exec.ManagedProcessException;
import ch.vorburger.mariadb4j.DB;
import ch.vorburger.mariadb4j.DBConfigurationBuilder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HibernateSpatialMariaDBTest extends AbstractHibernateSpatialTest {
  private static DB db;

  @BeforeAll
  public static void beforeClass() {
    try {
      DBConfigurationBuilder configBuilder = DBConfigurationBuilder.newBuilder();
      configBuilder.setPort(13306);
      configBuilder.setDataDir("./mariadb");
      db = DB.newEmbeddedDB(configBuilder.build());
      db.start();
    } catch (ManagedProcessException e) {
      log.error("", e);
    }
  }

  @BeforeEach
  public void setUp() throws HibernateException, FileNotFoundException, IOException {
    session = SessionFactoryCreator.getSessionFactory("hibernate-spatial-mariadb.properties")
        .openSession();
    transaction = session.beginTransaction();
  }

  @AfterEach
  public void tearDown() throws ManagedProcessException {
    transaction.rollback();
    session.close();
  }

  @AfterAll
  public static void afterClass() throws ManagedProcessException {
    db.stop();
  }
}
