package com.aquinoa.ziptechtask.domains;

import java.time.ZonedDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@Accessors(chain = true)
public class UserDao {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;
  
  @Column(name = "name", nullable = false)
  String name;
  
  @Column(name = "email_address", nullable = false)
  String emailAddress;

  @Column(name = "monthly_salary", nullable = false)
  double monthlySalary;
  
  @Column(name = "monthly_expenses", nullable = false)
  double monthlyExpenses;
  
  @Column(name = "created_on", nullable = false)
  ZonedDateTime createdOn;
  
  @OneToOne(optional = true)
  @JoinColumn(name = "modified_by")
  UserDao modifiedBy;
  
  @Column(name = "modified_on")
  ZonedDateTime modifiedOn;
}
