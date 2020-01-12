package com.ymmihw.hibernate.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.TermVector;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Indexed
@Table(name = "product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

  @Id
  private int id;

  @Field(termVector = TermVector.YES)
  private String productName;

  @Field
  private int memory;

  @Field(termVector = TermVector.YES)
  private String description;

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof Product))
      return false;

    Product product = (Product) o;

    if (id != product.id)
      return false;
    if (memory != product.memory)
      return false;
    if (!productName.equals(product.productName))
      return false;
    return description.equals(product.description);
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + productName.hashCode();
    result = 31 * result + memory;
    result = 31 * result + description.hashCode();
    return result;
  }
}
