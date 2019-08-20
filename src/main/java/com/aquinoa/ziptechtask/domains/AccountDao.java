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
@Table(name = "accounts")
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@Accessors(chain = true)
public class AccountDao {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;
  
  @OneToOne
  @JoinColumn(name = "user_id", nullable = false)
  UserDao user;
  
  @Column(name = "credit_limit", nullable = false)
  double creditLimit;
  
  @Column(name = "debit_record", nullable = false)
  double debitRecord;

  @OneToOne
  @JoinColumn(name = "created_by", nullable = false)
  UserDao createdBy;
  
  @Column(name = "created_on", nullable = false)
  ZonedDateTime createdOn;
  
  @OneToOne(optional = true)
  @JoinColumn(name = "modified_by")
  UserDao modifiedBy;
  
  @Column(name = "modified_on")
  ZonedDateTime modifiedOn;
}
