package com.ymmihw.hibernate;

import static com.ymmihw.hibernate.TestObjectHelper.createSchool;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import com.ymmihw.hibernate.model.School;

@SpringBootTest(classes = Application.class)
@Transactional
@ActiveProfiles("batchinserts")
@TestPropertySource(properties = "spring.jpa.properties.hibernate.jdbc.batch_size=-1")
public class JpaNoBatchInsertsIntegrationTest {

  @PersistenceContext
  private EntityManager entityManager;

  @Test
  public void whenNotConfigured_ThenSendsInsertsSeparately() {
    for (int i = 0; i < 10; i++) {
      School school = createSchool(i);
      entityManager.persist(school);
    }
  }

  @AfterEach
  public void tearDown() {
    entityManager.flush();
  }
}
