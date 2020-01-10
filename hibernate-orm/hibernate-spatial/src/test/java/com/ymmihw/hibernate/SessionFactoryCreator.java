package com.ymmihw.hibernate;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

public class SessionFactoryCreator {

  public static SessionFactory getSessionFactory(String propertes)
      throws FileNotFoundException, IOException {
    Properties properties = getProperties(propertes);
    ServiceRegistry serviceRegistry =
        new StandardServiceRegistryBuilder().applySettings(properties).build();;
    MetadataSources metadataSources = new MetadataSources(serviceRegistry);

    metadataSources.addAnnotatedClass(PointEntity.class);
    metadataSources.addAnnotatedClass(PolygonEntity.class);

    Metadata metadata = metadataSources.getMetadataBuilder().build();

    return metadata.getSessionFactoryBuilder().build();
  }

  private static Properties getProperties(String propertes)
      throws IOException, FileNotFoundException {
    Properties properties = new Properties();
    URL propertiesURL = Thread.currentThread().getContextClassLoader().getResource(propertes);
    try (FileInputStream inputStream = new FileInputStream(propertiesURL.getFile())) {
      properties.load(inputStream);
    }
    return properties;
  }

}
