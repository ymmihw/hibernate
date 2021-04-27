package com.ymmihw.hibernate;

import static com.ymmihw.hibernate.TestObjectHelper.createSchool;
import static com.ymmihw.hibernate.TestObjectHelper.createStudent;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import com.ymmihw.hibernate.model.School;
import com.ymmihw.hibernate.model.Student;

@SpringBootTest(classes = Application.class)
@Transactional
@ActiveProfiles("batchinserts")
public class JpaBatchInsertsIntegrationTest {

  @PersistenceContext
  private EntityManager entityManager;

  private static final int BATCH_SIZE = 5;

  @Transactional
  @Test
  public void whenInsertingSingleTypeOfEntity_thenCreatesSingleBatch() {
    for (int i = 0; i < 10; i++) {
      School school = createSchool(i);
      entityManager.persist(school);
    }
  }

  @Transactional
  @Test
  public void whenFlushingAfterBatch_ThenClearsMemory() {
    for (int i = 0; i < 10; i++) {
      if (i > 0 && i % BATCH_SIZE == 0) {
        entityManager.flush();
        entityManager.clear();
      }

      School school = createSchool(i);
      entityManager.persist(school);
    }
  }

  @Transactional
  @Test
  public void whenThereAreMultipleEntities_ThenCreatesNewBatch() {
    for (int i = 0; i < 10; i++) {
      if (i > 0 && i % BATCH_SIZE == 0) {
        entityManager.flush();
        entityManager.clear();
      }

      School school = createSchool(i);
      entityManager.persist(school);
      Student firstStudent = createStudent(school);
      Student secondStudent = createStudent(school);
      entityManager.persist(firstStudent);
      entityManager.persist(secondStudent);
    }
  }

  @Transactional
  @Test
  public void whenUpdatingEntities_thenCreatesBatch() {
    for (int i = 0; i < 10; i++) {
      School school = createSchool(i);
      entityManager.persist(school);
    }

    entityManager.flush();

    TypedQuery<School> schoolQuery =
        entityManager.createQuery("SELECT s from School s", School.class);
    List<School> allSchools = schoolQuery.getResultList();

    for (School school : allSchools) {
      school.setName("Updated_" + school.getName());
    }
  }

  @AfterEach
  public void tearDown() {
    entityManager.flush();
  }
}
