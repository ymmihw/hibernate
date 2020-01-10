package com.ymmihw.hibernate;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.jpa.QueryHints;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.RunnerException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class QueryPlanCacheBenchmark {

  private static SessionFactory getSessionFactoryByProperties(Properties properties)
      throws FileNotFoundException, IOException {
    ServiceRegistry serviceRegistry =
        new StandardServiceRegistryBuilder().applySettings(properties).build();;
    MetadataSources metadataSources = new MetadataSources(serviceRegistry);

    metadataSources.addAnnotatedClass(Department.class);
    metadataSources.addAnnotatedClass(DeptEmployee.class);

    Metadata metadata = metadataSources.getMetadataBuilder().build();

    return metadata.getSessionFactoryBuilder().build();
  }

  private static Properties getProperties() throws IOException, FileNotFoundException {
    Properties properties = new Properties();
    URL propertiesURL =
        Thread.currentThread().getContextClassLoader().getResource("hibernate.properties");
    try (FileInputStream inputStream = new FileInputStream(propertiesURL.getFile())) {
      properties.load(inputStream);
    }
    return properties;
  }

  @State(Scope.Thread)
  public static class QueryPlanCacheBenchMarkState {
    @Param({"1", "2", "3"})
    public int planCacheSize;

    public Session session;

    @Setup
    public void stateSetup() throws IOException {
      log.info("State - Setup");
      session = initSession(planCacheSize);
      log.info("State - Setup Complete");
    }

    private Session initSession(int planCacheSize) throws IOException {
      Properties properties = getProperties();
      properties.put("hibernate.query.plan_cache_max_size", planCacheSize);
      properties.put("hibernate.query.plan_parameter_metadata_max_size", planCacheSize);
      SessionFactory sessionFactory = getSessionFactoryByProperties(properties);
      return sessionFactory.openSession();
    }

    @TearDown
    public void tearDownState() {
      log.info("State - Teardown");
      SessionFactory sessionFactory = session.getSessionFactory();
      log.info("QueryPlanCacheHitCount is {}",
          sessionFactory.getStatistics().getQueryPlanCacheHitCount());
      log.info("QueryPlanCacheMissCount is {}",
          sessionFactory.getStatistics().getQueryPlanCacheMissCount());
      session.close();
      sessionFactory.close();
      log.info("State - Teardown complete");
    }
  }

  @Benchmark
  @BenchmarkMode(Mode.AverageTime)
  @OutputTimeUnit(TimeUnit.MICROSECONDS)
  @Fork(1)
  @Warmup(iterations = 2)
  @Measurement(iterations = 5)
  public void givenQueryPlanCacheSize_thenCompileQueries(QueryPlanCacheBenchMarkState state,
      Blackhole blackhole) {

    Query<?> query1 = findEmployeesByDepartmentNameQuery(state.session);
    Query<?> query2 = findEmployeesByDesignationQuery(state.session);
    Query<?> query3 = findDepartmentOfAnEmployeeQuery(state.session);

    blackhole.consume(query1);
    blackhole.consume(query2);
    blackhole.consume(query3);

  }

  private Query<?> findEmployeesByDepartmentNameQuery(Session session) {
    return session
        .createQuery("SELECT e FROM DeptEmployee e "
            + "JOIN e.department WHERE e.department.name = :deptName")
        .setMaxResults(30).setHint(QueryHints.HINT_FETCH_SIZE, 30);
  }

  private Query<?> findEmployeesByDesignationQuery(Session session) {
    return session.createQuery("SELECT e FROM DeptEmployee e " + "WHERE e.title = :designation")
        .setHint(QueryHints.SPEC_HINT_TIMEOUT, 1000);
  }

  private Query<?> findDepartmentOfAnEmployeeQuery(Session session) {
    return session.createQuery("SELECT e.department FROM DeptEmployee e "
        + "JOIN e.department WHERE e.employeeNumber = :empId");

  }

  public static void main(String... args) throws IOException, RunnerException {
    // main-class to run the benchmark
    org.openjdk.jmh.Main.main(args);
  }
}
