// ! Database model !!
package com.kanesa.restapi.product_impl.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "products")
public class products {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "name")
  private String name;
  private String desc;
  private int price;

  @CreatedDate()
  @Column(name = "created_at")
  private Date created_at;

  @LastModifiedDate()
  @Column(name = "updated_at")
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
