// ! Database model !!
package com.kanesa.restapi.product.repository.model;

// import java.sql.Date;
// import java.time.Instant;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import com.kanesa.restapi.category.repository.model.category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
@Builder
@EnableJpaAuditing
@EntityListeners(AuditingEntityListener.class)
@Table(name = "products")
public class products {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @NotEmpty(message = "Product 'name' is required!")
  @Column(name = "name", updatable = true)
  private String name;

  // * [Relation Database with Category table]
  // * create foreign key = @joinColumn(name = "category_id")
  @ManyToOne
  @JoinColumn(name = "category_id", referencedColumnName = "id")
  private category Category;

  @Column(name = "desc", updatable = true)
  private String desc;

  @NotEmpty(message = "Product 'name' is required!")
  @Column(name = "price", nullable = false, updatable = true)
  private Double price;

  @CreationTimestamp
  @Column(name = "created_at", updatable = false)
  private Date created_at;

  @UpdateTimestamp
  @Column(name = "updated_at", updatable = true)
  private Date updated_at;


  // * constructor as a overloading
  // * this as a @AllArgsConstructor
  // public product(long id, String name, String desc, int price) {
  // this.id = id;
  // this.name = name;
  // this.desc = desc;
  // this.price = price;
  // }

  // * this as a @NoArgsConstructor
  // public product() {}
}